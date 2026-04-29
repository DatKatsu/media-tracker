package com.github.datkatsu.mediatracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Value;

import com.github.datkatsu.mediatracker.dto.MalAnimeDto;
import com.github.datkatsu.mediatracker.dto.MalMangaDto;
import com.github.datkatsu.mediatracker.dto.MediaSearchResultDto;

@Mapper(componentModel = "spring")
public abstract class MalMapper {

    @Value("${mal.site.base-url}")
    protected String malSiteBaseUrl;

    @Value("${placeholderImage.url}") 
    protected String placeholderImageUrl;


    @Mapping(target = "imageUrl", expression = "java(dto.mainPicture() == null ? placeholderImageUrl : dto.mainPicture().medium())")
    @Mapping(target = "sourceUrl", expression = "java(malSiteBaseUrl + \"/anime/\" + dto.id())")
    public abstract MediaSearchResultDto toSearchResultDto(MalAnimeDto dto);

    @Mapping(target = "imageUrl", expression = "java(dto.mainPicture() == null ? placeholderImageUrl : dto.mainPicture().medium())")
    @Mapping(target = "sourceUrl", expression = "java(malSiteBaseUrl + \"/manga/\" + dto.id())")
    public abstract MediaSearchResultDto toSearchResultDto(MalMangaDto dto);

}
