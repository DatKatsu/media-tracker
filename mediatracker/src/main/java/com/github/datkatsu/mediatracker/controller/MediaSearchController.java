package com.github.datkatsu.mediatracker.controller;

import com.github.datkatsu.mediatracker.dto.MediaSearchResultDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/media")
public class MediaSearchController {

    public MediaSearchController()
    {

    }

    @GetMapping
    public MediaSearchResultDto getSearchResults(@RequestParam String query)
    {
        return null;
    }

}
