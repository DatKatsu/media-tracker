package com.github.datkatsu.mediatracker.dto;

import com.github.datkatsu.mediatracker.model.MediaType;
import com.github.datkatsu.mediatracker.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MediaEntryResponseDto(
        Long id,
        String title,
        MediaType type,
        Status status,
        String notes,
        String imageUrl,
        String sourceUrl
) { }
