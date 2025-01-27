package com.example.service

import com.example.model.Artist
import com.example.repository.ArtistRepository
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test
import org.mockito.Mockito._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ArtistServiceTest {

  @Autowired
  private var artistService: ArtistService = _

  @Autowired
  private var trackService: TrackService = _

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
    trackService.deleteAllTracks();
    artistService.deleteAllArtists()
    assertEquals(0, artistRepository.count())
  }
}