package com.example.model

import jakarta.persistence.{Entity, GeneratedValue, GenerationType, Id}

@Entity
class Artist {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private var id: Long = 0
  private var name: String = _

  def getId: Long = id
  def setId(id: Long): Unit = {
    this.id = id
  }

  def getName: String = name
  def setName(name: String): Unit = {
    this.name = name
  }
}