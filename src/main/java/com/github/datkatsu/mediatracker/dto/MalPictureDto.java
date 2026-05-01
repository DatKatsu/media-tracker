package com.github.datkatsu.mediatracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MalPictureDto(String medium, String large) {
}
