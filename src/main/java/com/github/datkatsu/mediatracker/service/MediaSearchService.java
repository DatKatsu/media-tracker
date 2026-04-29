package com.github.datkatsu.mediatracker.service;

import com.github.datkatsu.mediatracker.dto.MalAnimeDto;
import com.github.datkatsu.mediatracker.dto.MalMangaDto;
import com.github.datkatsu.mediatracker.dto.MediaSearchResultDto;
import com.github.datkatsu.mediatracker.mapper.MalMapper;
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
    private final MalMapper mapper;

    public MediaSearchService(MalApiService malApiService,
                              @Value("${mal.site.base-url}") String malSiteBaseUrl,
                              @Value("${placeholderImage.url}") String placeholderImageUrl,
                                MalMapper mapper)
    {
        this.malApiService = malApiService;
        this.malSiteBaseUrl = malSiteBaseUrl;
        this.placeholderImageUrl = placeholderImageUrl;
        this.mapper = mapper;
    }

    //ToDo: Change Enums to include mediatypes and implement mapping logic for categories
    public List<MediaSearchResultDto> search(String query)
    {
        List<MediaSearchResultDto> resultDtoList = new ArrayList<>();
        resultDtoList.addAll(malApiService.fetchAnime(query).stream().map(mapper::toSearchResultDto).toList());
        resultDtoList.addAll(malApiService.fetchManga(query).stream().map(mapper::toSearchResultDto).toList());
        return resultDtoList;
    }

}
