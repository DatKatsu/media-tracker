package com.github.datkatsu.mediatracker.mapper;

import com.github.datkatsu.mediatracker.model.MediaFormat;

public class MalMediaFormatMapper {

    private MalMediaFormatMapper() {}

    public static MediaFormat fromMal(String malValue)
    {
        if(malValue == null || malValue.equals("unknown"))
            return MediaFormat.UNKNOWN;

        return switch (malValue.toLowerCase())
        {
            case "tv", "ona", "special"         -> MediaFormat.ANIME_SERIES;
            case "movie"                        -> MediaFormat.ANIME_MOVIE;
            case "ova"                          -> MediaFormat.ANIME_OVA;
            case "music"                        -> MediaFormat.ANIME_MUSIC;
            case "manga", "one_shot", "oel"     -> MediaFormat.MANGA;
            case "manhwa"                       -> MediaFormat.MANHWA;
            case "manhua"                       -> MediaFormat.MANHUA;
            case "novel"                        -> MediaFormat.LIGHT_NOVEL;
            case "doujinshi"                    -> MediaFormat.DOUJINSHI;
            default                             -> MediaFormat.OTHER;
        };
    }
}
