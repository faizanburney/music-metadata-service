
package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MusicMetadataService

object MusicMetadataServiceApp {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[MusicMetadataService], args: _*)
  }
}
