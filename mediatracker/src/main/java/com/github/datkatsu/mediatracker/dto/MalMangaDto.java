package com.github.datkatsu.mediatracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MalMangaDto(int id, String title, @JsonProperty("main_picture") MalPictureDto mainPicture) {

}
