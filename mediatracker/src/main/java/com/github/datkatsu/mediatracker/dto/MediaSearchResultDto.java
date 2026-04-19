package com.github.datkatsu.mediatracker.dto;

import com.github.datkatsu.mediatracker.model.MediaType;

public record MediaSearchResultDto(String title, MediaType type, String imageUrl, String sourceUrl) {
}
