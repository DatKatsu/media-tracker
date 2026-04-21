package com.github.datkatsu.mediatracker.service;

import com.github.datkatsu.mediatracker.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class MalApiService {

    private final String apiBaseUrl;
    private final String siteBaseUrl;
    private final String clientId;

    private final RestClient client;

    public MalApiService(@Value("${mal.api.base-url}") String apiBaseUrl,
                         @Value("${mal.site.base-url}") String siteBaseUrl,
                         @Value("${mal.api.client-id}") String clientId)
    {
        this.apiBaseUrl = apiBaseUrl;
        this.siteBaseUrl = siteBaseUrl;
        this.clientId = clientId;

        client = RestClient.create();
    }

    public List<MalAnimeDto> fetchAnime(String query)
    {
        MalAnimeSearchResponseWrapper response =
                client.get()
                        .uri(apiBaseUrl + "/anime?q=" + query + "&limit=10")
                        .header("X-MAL-CLIENT-ID", clientId)
                        .accept(APPLICATION_JSON)
                        .retrieve()
                        .body(MalAnimeSearchResponseWrapper.class);
        if(response == null)
            return List.of();
        return response.data().stream().limit(5)
                .map(MalAnimeNodeWrapper::node)
                .filter(Objects::nonNull).toList();
    }

    public List<MalMangaDto> fetchManga(String query)
    {
        MalMangaSearchResponseWrapper response =
                client.get()
                        .uri(apiBaseUrl + "/manga?q=" + query + "&limit=10")
                        .header("X-MAL-CLIENT-ID", clientId)
                        .accept(APPLICATION_JSON)
                        .retrieve()
                        .body(MalMangaSearchResponseWrapper.class);
        if(response == null)
            return List.of();
        return response.data().stream().limit(5)
                .map(MalMangaNodeWrapper::node)
                .filter(Objects::nonNull).toList();
    }

}
