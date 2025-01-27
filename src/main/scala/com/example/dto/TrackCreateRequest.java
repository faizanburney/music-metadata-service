package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request object for creating a track")
public class TrackCreateRequest {

    @Schema(description = "Title of the track", example = "Test Track", required = true)
    @NotBlank(message = "Title is mandatory")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @Schema(description = "Genre of the track", example = "Rock", required = true)
    @NotBlank(message = "Genre is mandatory")
    @Size(min = 2, max = 50, message = "Genre must be between 2 and 50 characters")
    private String genre;

    @Schema(description = "Length of the track in seconds", example = "300", required = true)
    private int length;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}