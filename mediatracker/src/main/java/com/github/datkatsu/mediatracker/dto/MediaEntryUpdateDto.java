package com.github.datkatsu.mediatracker.dto;

import com.github.datkatsu.mediatracker.model.*;
import jakarta.validation.constraints.NotNull;

public record MediaEntryUpdateDto(
        @NotNull(message = "Status is required") Status status,
        String notes
) { }
