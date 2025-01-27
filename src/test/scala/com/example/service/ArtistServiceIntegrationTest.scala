package com.example.service

import com.example.model.{Artist, Track}
import com.example.repository.ArtistRepository
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ArtistServiceIntegrationTest {

  @Autowired
  private var artistService: ArtistService = _

  @Autowired
  private var artistRepository: ArtistRepository = _

  @Test
  def testAddArtist(): Unit = {
    val artist = new Artist()
    artist.setName("Test Artist")
    val savedArtist = artistService.addArtist(artist)
    assertNotNull(savedArtist.getId)
  }

  @Test
  def testEditArtist(): Unit = {
    val artist = new Artist()
    artist.setName("Original Name")
    val savedArtist = artistRepository.save(artist)
    val updatedArtist = artistService.editArtist(savedArtist.getId, "New Name")
    assertTrue(updatedArtist.isPresent)
    assertEquals("New Name", updatedArtist.get().getName)
  }

  @Test
  def testGetArtistById(): Unit = {
    val artist = new Artist()
    artist.setName("Test Artist")
    val savedArtist = artistRepository.save(artist)
    val foundArtist = artistService.getArtistById(savedArtist.getId)
    assertTrue(foundArtist.isPresent)
    assertEquals("Test Artist", foundArtist.get().getName)
  }

  @Test
  def testDeleteAllArtists(): Unit = {
    artistService.deleteAllArtists()
    assertEquals(0, artistRepository.count())
  }

  @Test
  def testGetArtistOfTheDay(): Unit = {
    // Add multiple artists to the repository
    val artist1 = new Artist()
    artist1.setName("Artist 1")
    artistRepository.save(artist1)

    val artist2 = new Artist()
    artist2.setName("Artist 2")
    artistRepository.save(artist2)

    val artist3 = new Artist()
    artist3.setName("Artist 3")
    artistRepository.save(artist3)

    // Get the artist of the day
    val artistOfTheDay = artistService.getArtistOfTheDay

    // Verify that the artist of the day is present
    assertTrue(artistOfTheDay.isPresent)

    // Print the artist of the day for manual verification
    println(s"Artist of the Day: ${artistOfTheDay.get().getName}")
  }

  @Test
  def testAddTrackToArtist(): Unit = {
    // Add an artist to the repository
    val artist = new Artist()
    artist.setName("Test Artist")
    val savedArtist = artistRepository.save(artist)

    // Create a new track
    val track = new Track()
    track.setTitle("Test Track")
    track.setGenre("Test Genre")
    track.setLength(300)

    // Add the track to the artist
    val savedTrack = artistService.addTrackToArtist(savedArtist.getId, track)

    // Verify that the track was added successfully
    assertTrue(savedTrack.isDefined)
    assertNotNull(savedTrack.get.getId)
    assertEquals("Test Track", savedTrack.get.getTitle)
    assertEquals("Test Genre", savedTrack.get.getGenre)
    assertEquals(300, savedTrack.get.getLength)
    assertEquals(savedArtist.getId, savedTrack.get.getArtist.getId)
  }




}