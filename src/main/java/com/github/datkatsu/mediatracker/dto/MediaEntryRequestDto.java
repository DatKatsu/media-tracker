package com.github.datkatsu.mediatracker.dto;

import com.github.datkatsu.mediatracker.model.MediaType;
import com.github.datkatsu.mediatracker.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MediaEntryRequestDto(
        @NotBlank(message = "Title required and not empty") String title,
        @NotNull(message = "Type is required") MediaType type,
        @NotNull(message = "Status is required") Status status,
        String notes,
        String imageUrl,
        String sourceUrl
) { }
