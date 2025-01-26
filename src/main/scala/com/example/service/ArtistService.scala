package com.example.service

import com.example.model.Artist
import com.example.repository.ArtistRepository
import org.springframework.stereotype.Service

import java.util.Optional
import java.time.LocalDate
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ArtistService(private val artistRepository: ArtistRepository) {

  def addArtist(artist: Artist): Artist = {
    artistRepository.save(artist)
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
  }

  def getArtistOfTheDay: Optional[Artist] = {
    val artistCount = artistRepository.count()
    if (artistCount == 0) {
      Optional.empty()
    } else {
      val daysSinceEpoch = LocalDate.now().toEpochDay
      val artistIndex = (daysSinceEpoch % artistCount).toInt
      artistRepository.findByIndex(artistIndex)
    }
  }
}