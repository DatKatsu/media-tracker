package com.github.datkatsu.mediatracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MalMangaDto(int id,
                          String title,
                          @JsonProperty("main_picture") MalPictureDto mainPicture,
                          @JsonProperty("media_type") String mediaType,
                          @JsonProperty("mean") Float meanScore,
                          @JsonProperty("num_chapters") Integer chapters,
                          @JsonProperty("start_date") String startDate)
{

}
