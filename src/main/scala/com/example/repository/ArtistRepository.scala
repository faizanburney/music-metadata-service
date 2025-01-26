package com.example.repository

import com.example.model.Artist
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.Optional

trait ArtistRepository extends CrudRepository[Artist, Long] {

  @Query(value = "SELECT * FROM Artist ORDER BY id LIMIT 1 OFFSET ?1", nativeQuery = true)
  def findByIndex(index: Int): Optional[Artist]
}