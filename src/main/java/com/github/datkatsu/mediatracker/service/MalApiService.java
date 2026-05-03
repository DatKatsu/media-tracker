package com.github.datkatsu.mediatracker.service;

import com.github.datkatsu.mediatracker.dto.*;
import com.github.datkatsu.mediatracker.exception.ExternalApiException;
import com.github.datkatsu.mediatracker.exception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class MalApiService {

    private final String apiBaseUrl;
    private final String clientId;

    private final RestClient client;

    private static final Logger log = LoggerFactory.getLogger(MalApiService.class);

    public MalApiService(@Value("${mal.api.base-url}") String apiBaseUrl,
                         @Value("${mal.api.client-id}") String clientId)
    {
        this.apiBaseUrl = apiBaseUrl;
        this.clientId = clientId;

        client = RestClient.create();
    }

    public List<MalAnimeDto> fetchAnime(String query)
    {
        long start = System.currentTimeMillis();
        MalAnimeSearchResponseWrapper response =
                client.get()
                        .uri(apiBaseUrl + "/anime?q=" + query + "&limit=10&fields=start_date,media_type,mean,num_episodes")
                        .header("X-MAL-CLIENT-ID", clientId)
                        .accept(APPLICATION_JSON)
                        .retrieve()
                        .body(MalAnimeSearchResponseWrapper.class);

        long duration = System.currentTimeMillis() - start;
        log.info("MAL Anime Response time: {}ms", duration);
        if(response == null)
            return List.of();

        return response.data().stream().limit(10)
                .map(MalAnimeNodeWrapper::node)
                .filter(Objects::nonNull).toList();
    }

    public List<MalMangaDto> fetchManga(String query)
    {
        long start = System.currentTimeMillis();
        MalMangaSearchResponseWrapper response =
                client.get()
                        .uri(apiBaseUrl + "/manga?q=" + query + "&limit=10&fields=start_date,media_type,mean, num_chapters")
                        .header("X-MAL-CLIENT-ID", clientId)
                        .accept(APPLICATION_JSON)
                        .retrieve()
                        .body(MalMangaSearchResponseWrapper.class);

        long duration = System.currentTimeMillis() - start;
        log.info("MAL Manga Response time: {}ms", duration);

        if(response == null)
            return List.of();
        return response.data().stream().limit(10)
                .map(MalMangaNodeWrapper::node)
                .filter(Objects::nonNull).toList();
    }

}
