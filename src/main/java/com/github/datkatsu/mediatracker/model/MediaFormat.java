package com.github.datkatsu.mediatracker.model;

/**
 * Specific media format used for display badges and tab filtering.
 * Each format belongs to a {@link MediaCategory} for broader grouping.
 */

public enum MediaFormat {
    // Shows
    ANIME_SERIES (MediaCategory.TV, "Anime"),
    ANIME_OVA (MediaCategory.TV, "Anime OVA"),
    TV_SHOW (MediaCategory.TV, "TV Show"),
    DOCUMENTARY (MediaCategory.TV, "Documentary"),

    // Movie / Film
    ANIME_MOVIE (MediaCategory.MOVIE, "Anime Movie"),
    MOVIE (MediaCategory.MOVIE, "Movie"),

    // Comics
    MANGA (MediaCategory.COMIC, "Manga"),
    MANHWA (MediaCategory.COMIC, "Manhwa"),
    MANHUA (MediaCategory.COMIC, "Manhua"),
    DOUJINSHI (MediaCategory.COMIC, "Doujinshi"),
    COMIC (MediaCategory.COMIC, "Comic"),

    // Books
    NOVEL (MediaCategory.BOOK, "Novel"),
    LIGHT_NOVEL (MediaCategory.BOOK, "Light Novel"),
    WEB_NOVEL (MediaCategory.BOOK, "Web Novel"),

    // Games
    GAME (MediaCategory.GAME, "Game"),
    VISUAL_NOVEL (MediaCategory.GAME, "Visual Novel"),

    // Music
    MUSIC (MediaCategory.MUSIC, "Music"),
    ANIME_MUSIC (MediaCategory.MUSIC, "Anime Music"),

    // The rest
    OTHER (MediaCategory.OTHER, "Other"), // specifically for user adding media-types that don't exist or don't fit anywhere else
    UNKNOWN (MediaCategory.OTHER, "Unknown"); // unresolved API mapping or unknown category

    private final MediaCategory category;
    private final String displayName;

    MediaFormat(MediaCategory category, String displayName)
    {
        this.category = category;
        this.displayName = displayName;
    }

    public MediaCategory getCategory() { return category; }
    public String getDisplayName() { return displayName; }


}
