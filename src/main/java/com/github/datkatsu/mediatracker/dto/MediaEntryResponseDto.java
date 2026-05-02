package com.github.datkatsu.mediatracker.dto;

import com.github.datkatsu.mediatracker.model.MediaFormat;
import com.github.datkatsu.mediatracker.model.Status;

public record MediaEntryResponseDto(
        Long id,
        String title,
        MediaFormat format,
        Status status,
        String notes
        //String imageUrl,
        //String sourceUrl
) { }
