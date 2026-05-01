package com.github.datkatsu.mediatracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MalAnimeNodeWrapper(@JsonProperty("node") MalAnimeDto node) {
}
