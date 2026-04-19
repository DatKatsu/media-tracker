package com.github.datkatsu.mediatracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MalAnimeDto(@JsonProperty("id") int id, @JsonProperty("title") String title) {

}
