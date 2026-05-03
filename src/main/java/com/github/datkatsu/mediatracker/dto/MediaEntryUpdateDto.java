package com.github.datkatsu.mediatracker.dto;

import com.github.datkatsu.mediatracker.model.*;
import jakarta.validation.constraints.Size;

public record MediaEntryUpdateDto(
        Status status,
        @Size(max = 1000) String notes
) { }
