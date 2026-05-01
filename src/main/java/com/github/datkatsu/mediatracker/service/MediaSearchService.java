package com.github.datkatsu.mediatracker.service;

import com.github.datkatsu.mediatracker.dto.MediaSearchResultDto;
import com.github.datkatsu.mediatracker.exception.ExternalApiException;
import com.github.datkatsu.mediatracker.mapper.MalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaSearchService
{
    private final static Logger log = LoggerFactory.getLogger(MediaSearchService.class);
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
        if(query.isBlank())
            throw new IllegalArgumentException("Search query cannot be empty");
        if(query.length() < 3)
            throw new IllegalArgumentException("Search query must be at least 3 characters long");

        List<MediaSearchResultDto> results = new ArrayList<>();
        try {
            results.addAll(malApiService.fetchAnime(query).stream().map(mapper::toSearchResultDto).toList());
            results.addAll(malApiService.fetchManga(query).stream().map(mapper::toSearchResultDto).toList());
        } catch(ExternalApiException ex)
        {
            log.warn("MAL API unavailable: {}", ex.getMessage());
        }



        return results;
    }

}
