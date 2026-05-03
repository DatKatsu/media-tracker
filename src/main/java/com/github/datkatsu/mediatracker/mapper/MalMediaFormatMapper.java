package com.github.datkatsu.mediatracker.mapper;

import com.github.datkatsu.mediatracker.model.MediaFormat;
import com.github.datkatsu.mediatracker.service.MalApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MalMediaFormatMapper {

    private MalMediaFormatMapper() {}
    private static final Logger log = LoggerFactory.getLogger(MalMediaFormatMapper.class);

    public static MediaFormat fromMal(String malValue)
    {
        if(malValue == null || malValue.equals("unknown"))
            return MediaFormat.UNKNOWN;

        return switch (malValue.toLowerCase())
        {
            case "tv", "ona", "special", "tv_special"         -> MediaFormat.ANIME_SERIES;
            case "movie"                        -> MediaFormat.ANIME_MOVIE;
            case "ova"                          -> MediaFormat.ANIME_OVA;
            case "music"                        -> MediaFormat.ANIME_MUSIC;
            case "manga", "one_shot", "oel"     -> MediaFormat.MANGA;
            case "manhwa"                       -> MediaFormat.MANHWA;
            case "manhua"                       -> MediaFormat.MANHUA;
            case "novel", "light_novel"         -> MediaFormat.LIGHT_NOVEL;
            case "doujinshi"                    -> MediaFormat.DOUJINSHI;
            default                             ->
                    {
                        log.warn("Unknown MAL media type: {}", malValue);
                        yield MediaFormat.OTHER;
                    }
        };
    }
}
