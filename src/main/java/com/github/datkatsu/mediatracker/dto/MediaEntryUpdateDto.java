package com.github.datkatsu.mediatracker.dto;

import com.github.datkatsu.mediatracker.model.*;

public record MediaEntryUpdateDto(
        Status status,
        String notes
) { }
