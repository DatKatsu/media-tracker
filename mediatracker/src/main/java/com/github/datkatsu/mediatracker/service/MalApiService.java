package com.github.datkatsu.mediatracker.service;

import com.github.datkatsu.mediatracker.dto.MalAnimeDto;
import com.github.datkatsu.mediatracker.dto.MalAnimeNodeWrapper;
import com.github.datkatsu.mediatracker.dto.MalMangaDto;
import com.github.datkatsu.mediatracker.dto.MalSearchResponseWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

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
        MalSearchResponseWrapper<MalAnimeNodeWrapper> response =
                client.get()
                        .uri(apiBaseUrl + "/anime?q=" + query + "&limit=5")
                        .header("X-MAL-CLIENT-ID", clientId)
                        .accept(APPLICATION_JSON)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
        if(response == null)
            return List.of();
        return response.data().stream().map(MalAnimeNodeWrapper::node).toList();
    }

}
