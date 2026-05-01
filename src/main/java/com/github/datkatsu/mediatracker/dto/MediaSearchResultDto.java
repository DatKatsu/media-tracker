package com.github.datkatsu.mediatracker.dto;

import com.github.datkatsu.mediatracker.model.MediaFormat;

public record MediaSearchResultDto(String title,
                                   MediaFormat format,
                                   Float meanScore,
                                   String releaseDate,
                                   String imageUrl,
                                   String sourceUrl,
                                   // fields that might be null depending on the type of media (e.g. games will have no episodes/chapters)
                                   Integer episodes,
                                   Integer chapters,
                                   Integer durationMin)
{

}
