package com.github.datkatsu.mediatracker.service;

import com.github.datkatsu.mediatracker.dto.MalAnimeDto;
import com.github.datkatsu.mediatracker.dto.MalMangaDto;
import com.github.datkatsu.mediatracker.dto.MediaSearchResultDto;
import com.github.datkatsu.mediatracker.model.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaSearchService
{
    private final MalApiService malApiService;
    private final String malSiteBaseUrl;
    private final String placeholderImageUrl;

    public MediaSearchService(MalApiService malApiService,
                              @Value("${mal.site.base-url}") String malSiteBaseUrl,
                              @Value("${placeholderImage.url}") String placeholderImageUrl)
    {
        this.malApiService = malApiService;
        this.malSiteBaseUrl = malSiteBaseUrl;
        this.placeholderImageUrl = placeholderImageUrl;
    }

    public List<MediaSearchResultDto> fetchMedia(String query)
    {
        List<MediaSearchResultDto> resultDtoList = new ArrayList<>();
        resultDtoList.addAll(malApiService.fetchAnime(query).stream().map(this::toSearchResult).toList());
        resultDtoList.addAll(malApiService.fetchManga(query).stream().map(this::toSearchResult).toList());
        return resultDtoList;
    }

    private MediaSearchResultDto toSearchResult(MalAnimeDto malAnimeDto)
    {
        String sourceUrl = malSiteBaseUrl + "/anime/" + malAnimeDto.id();
        String imageUrl = malAnimeDto.mainPicture() == null ? placeholderImageUrl : malAnimeDto.mainPicture().medium();

        return new MediaSearchResultDto(malAnimeDto.title(), MediaType.ANIME, imageUrl, sourceUrl);
    }

    //ToDo: replace MediaType with result from MAL Api
    private MediaSearchResultDto toSearchResult(MalMangaDto malMangaDto)
    {
        String sourceUrl = malSiteBaseUrl + "/manga/" + malMangaDto.id();
        String imageUrl = malMangaDto.mainPicture() == null ? placeholderImageUrl : malMangaDto.mainPicture().medium();

        return new MediaSearchResultDto(malMangaDto.title(), MediaType.MANGA, imageUrl, sourceUrl);
    }
}
