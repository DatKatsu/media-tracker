package com.github.datkatsu.mediatracker.service;

import com.github.datkatsu.mediatracker.dto.MalAnimeDto;
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
        List<MalAnimeDto> results = malApiService.fetchAnime("naruto");
        System.out.println(results);
    }

}