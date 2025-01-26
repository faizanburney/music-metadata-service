package com.example.service

import com.example.model.Track
import com.example.repository.TrackRepository
import org.springframework.stereotype.Service

import java.util.List
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TrackService(val trackRepository: TrackRepository) {

  def addTrack(track: Track): Track = {
    trackRepository.save(track)
  }

  def getTracksByArtist(artistId: Long): List[Track] = {
    trackRepository.findByArtistId(artistId)
  }
}