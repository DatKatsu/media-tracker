package com.github.datkatsu.mediatracker.dto;

import com.github.datkatsu.mediatracker.model.*;
import jakarta.validation.constraints.NotNull;

public record MediaEntryUpdateDto(
        Status status,
        String notes
) { }
