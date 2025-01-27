package com.example.service

import com.example.dto.{ArtistCreateRequest, TrackCreateRequest}
import com.example.model.{Artist, Track}
import com.example.repository.{ArtistRepository, TrackRepository}
import org.springframework.stereotype.Service

import java.util.Optional
import java.time.LocalDate
import org.springframework.transaction.annotation.Transactional

import java.util.concurrent.atomic.AtomicLong

@Service
@Transactional
class ArtistService(private val artistRepository: ArtistRepository, val trackRepository: TrackRepository) {

  private val artistCountCache = new AtomicLong(artistRepository.count())

  def addArtist(artistDTO: ArtistCreateRequest): Artist = {
    val artist = new Artist()
    artist.setName(artistDTO.getName)
    artistRepository.save(artist)
    artistCountCache.incrementAndGet()
    artist
  }

  def editArtist(id: Long, newName: String): Optional[Artist] = {
    artistRepository.findById(id).map { artist =>
      artist.setName(newName)
      artistRepository.save(artist)
    }
  }

  def getArtistById(id: Long): Optional[Artist] = {
    artistRepository.findById(id)
  }

  def deleteAllArtists(): Unit = {
    artistRepository.deleteAll()
    artistCountCache.decrementAndGet()
  }

  def getArtistOfTheDay: Optional[Artist] = {
    val artistCount = artistCountCache.get()
    if (artistCount == 0) {
      Optional.empty()
    } else {
      val daysSinceEpoch = LocalDate.now().toEpochDay
      val artistIndex = (daysSinceEpoch % artistCount).toInt
      artistRepository.findByIndex(artistIndex)
    }
  }

  def addTrackToArtist(artistId: Long, trackDTO: TrackCreateRequest): Option[Track] = {
    val artistOpt = artistRepository.findById(artistId)
    if (artistOpt.isPresent) {
      val artist = artistOpt.get
      val track = new Track()
      track.setTitle(trackDTO.getTitle)
      track.setArtist(artist)
      track.setGenre(trackDTO.getGenre)
      track.setLength(trackDTO.getLength)
      Some(trackRepository.save(track))
    } else {
      None
    }
  }
}