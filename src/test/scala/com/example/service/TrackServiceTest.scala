package com.example.service

import com.example.model.Track
import com.example.repository.TrackRepository
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test
import org.mockito.Mockito._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TrackServiceTest {

  @Autowired
  private var trackService: TrackService = _

  @Autowired
  private var trackRepository: TrackRepository = _

  @Test
  def testAddTrack(): Unit = {
    val track = new Track()
    track.setTitle("Test Track")
    val savedTrack = trackService.addTrack(track)
    assertNotNull(savedTrack.getId)
  }
}