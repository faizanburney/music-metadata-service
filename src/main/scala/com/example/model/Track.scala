package com.example.model

import jakarta.persistence.{Entity, GeneratedValue, GenerationType, Id, ManyToOne}

@Entity
class Track {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private var id: Long = 0
  private var title: String = _
  private var genre: String = _
  private var length: Int = 0
  @ManyToOne
  private var artist: Artist = _

  def getId: Long = id
  def setId(id: Long): Unit = {
    this.id = id
  }

  def getTitle: String = title
  def setTitle(title: String): Unit = {
    this.title = title
  }

  def getGenre: String = genre
  def setGenre(genre: String): Unit = {
    this.genre = genre
  }

  def getLength: Int = length
  def setLength(length: Int): Unit = {
    this.length = length
  }

  def getArtist: Artist = artist
  def setArtist(artist: Artist): Unit = {
    this.artist = artist
  }
}