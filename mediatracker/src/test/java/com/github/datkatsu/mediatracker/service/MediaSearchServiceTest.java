package com.github.datkatsu.mediatracker.service;

import com.github.datkatsu.mediatracker.dto.MediaSearchResultDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("local")
class MediaSearchServiceTest {

    @Autowired
    private MediaSearchService mediaSearchService;

    @Test
    void search() {
        List<MediaSearchResultDto> results = mediaSearchService.search("naruto");
        System.out.println(results);
    }
}