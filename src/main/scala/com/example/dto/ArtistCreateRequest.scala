package com.example.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.{NotBlank, Size}

@Schema(description = "Request object for creating an artist")
class ArtistCreateRequest {

  @Schema(description = "Name of the artist", example = "John Doe", required = true)
  @NotBlank(message = "Name is mandatory")
  @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
  private var name: String = _

  @Schema(description = "Genre of the artist", example = "Rock", required = true)
  @NotBlank(message = "Genre is mandatory")
  @Size(min = 2, max = 50, message = "Genre must be between 2 and 50 characters")
  private var genre: String = _

  def getName: String = name
  def setName(name: String): Unit = { this.name = name }

  def getGenre: String = genre
  def setGenre(genre: String): Unit = { this.genre = genre }
}