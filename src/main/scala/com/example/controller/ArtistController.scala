package com.example.controller

import com.example.dto.{ArtistCreateRequest, TrackCreateRequest}
import com.example.model.{Artist, Track}
import com.example.service.ArtistService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._

import java.util.Optional

@Tag(name = "Artist Controller", description = "Endpoints for managing artists and their tracks")
@RestController
@RequestMapping(Array("/artists"))
class ArtistController @Autowired()(val artistService: ArtistService) {

  @Operation(summary = "Add a new artist")
  @PostMapping
  def createArtist(@Valid @RequestBody artistDTO: ArtistCreateRequest): ResponseEntity[Artist] = {
    val artist = artistService.addArtist(artistDTO)
    ResponseEntity.ok(artist)
  }

  @Operation(summary = "Edit an existing artist's name")
  @PutMapping(Array("/{id}"))
  def editArtist(@PathVariable id: Long, @RequestBody newName: String): ResponseEntity[Optional[Artist]] = {
    val updatedArtist = artistService.editArtist(id, newName)
    ResponseEntity.ok(updatedArtist)
  }

  @Operation(summary = "Get an artist by ID")
  @GetMapping(Array("/{id}"))
  def getArtistById(@PathVariable id: Long): ResponseEntity[Optional[Artist]] = {
    val artist = artistService.getArtistById(id)
    if (artist.isPresent) {
      ResponseEntity.ok(artist)
    } else {
      ResponseEntity.notFound().build()
    }
  }

  @Operation(summary = "Get the artist of the day")
  @GetMapping(Array("/artistOfTheDay"))
  def getArtistOfTheDay: ResponseEntity[Optional[Artist]] = {
    val artistOfTheDay = artistService.getArtistOfTheDay
    if (artistOfTheDay.isPresent) {
      ResponseEntity.ok(artistOfTheDay)
    } else {
      ResponseEntity.notFound().build()
    }
  }

  @Operation(summary = "Add a new track to an artist")
  @PostMapping(Array("/{artistId}/tracks"))
  def addTrackToArtist(@PathVariable artistId: Long, @RequestBody track: TrackCreateRequest): ResponseEntity[Track] = {
    val savedTrack = artistService.addTrackToArtist(artistId, track)
    if (savedTrack.isDefined) {
      ResponseEntity.ok(savedTrack.get)
    } else {
      ResponseEntity.notFound().build()
    }
  }
}