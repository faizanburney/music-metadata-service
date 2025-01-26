package com.example.repository

import com.example.model.Track
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait TrackRepository extends JpaRepository[Track, Long] {
  def findByArtistId(artistId: Long): java.util.List[Track]
}