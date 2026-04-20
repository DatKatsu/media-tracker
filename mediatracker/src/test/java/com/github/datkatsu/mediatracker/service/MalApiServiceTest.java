package com.github.datkatsu.mediatracker.service;

import com.github.datkatsu.mediatracker.dto.MalAnimeDto;
import com.github.datkatsu.mediatracker.dto.MalMangaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("local")
class MalApiServiceTest {


    @Autowired
    private MalApiService malApiService;

    @Test
    void testSearchAnime() {
        List<MalAnimeDto> results;
        results = malApiService.fetchAnime("naruto");
        System.out.println(results);
        results = malApiService.fetchAnime("fyxfxaxtxdptsfs");
        System.out.println(results);
    }

    @Test
    void testSearchManga() {
        List<MalMangaDto> results;
        results = malApiService.fetchManga("naruto");
        System.out.println(results);
        results = malApiService.fetchManga("fyxfxaxtxdptsfs");
        System.out.println(results);
    }

}