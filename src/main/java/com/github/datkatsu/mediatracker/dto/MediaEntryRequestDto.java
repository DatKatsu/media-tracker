package com.github.datkatsu.mediatracker.dto;


import com.github.datkatsu.mediatracker.model.MediaFormat;
import com.github.datkatsu.mediatracker.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MediaEntryRequestDto(
        @NotBlank(message = "Title required and not empty")
        @Size(max = 255) String title,
        @NotNull(message = "Format is required") MediaFormat format,
        @NotNull(message = "Status is required") Status status,
        @Size(max = 1000) String notes
        //String imageUrl,
        //String sourceUrl
) { }
