package com.github.datkatsu.mediatracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MalAnimeDto(int id, String title, @JsonProperty("main_picture") MalPictureDto mainPicture) {

}
