package com.github.datkatsu.mediatracker.controller;

import com.github.datkatsu.mediatracker.dto.MediaSearchResultDto;
import com.github.datkatsu.mediatracker.service.MediaSearchService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class MediaSearchController {

    private final MediaSearchService searchService;

    public MediaSearchController(MediaSearchService searchService)
    {
        this.searchService = searchService;
    }

    @GetMapping
    public List<MediaSearchResultDto> getSearchResults(@RequestParam @NotBlank @Size(min = 3, max = 100) String query)
    {
        return searchService.search(query);
    }

}
