package com.example.controller

import com.example.model.Artist
import com.example.service.ArtistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._

import java.util.Optional

@RestController
@RequestMapping(Array("/artists"))
class ArtistController @Autowired()(val artistService: ArtistService) {

  @PostMapping
  def addArtist(@RequestBody artist: Artist): ResponseEntity[Artist] = {
    val savedArtist = artistService.addArtist(artist)
    ResponseEntity.ok(savedArtist)
  }

  @PutMapping(Array("/{id}"))
  def editArtist(@PathVariable id: Long, @RequestBody newName: String): ResponseEntity[Optional[Artist]] = {
    val updatedArtist = artistService.editArtist(id, newName)
    ResponseEntity.ok(updatedArtist)
  }

  @GetMapping(Array("/{id}"))
  def getArtistById(@PathVariable id: Long): ResponseEntity[Optional[Artist]] = {
    val artist = artistService.getArtistById(id)
    ResponseEntity.ok(artist)
  }

  @DeleteMapping
  def deleteAllArtists(): ResponseEntity[Void] = {
    artistService.deleteAllArtists()
    ResponseEntity.noContent().build()
  }
}