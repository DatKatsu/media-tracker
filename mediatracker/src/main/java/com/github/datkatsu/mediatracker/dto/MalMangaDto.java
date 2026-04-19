package com.github.datkatsu.mediatracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MalMangaDto(@JsonProperty("id") int malId, String title) {
}
