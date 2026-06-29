# Media Tracker
A personal media backlog tracker for keeping track of all types of media like games, anime, manga, light novels, web novels, series, and movies — all in one place.

## Motivation
Most popular tracking tools like MyAnimeList only cover one type of media (e.g. animes & mangas, but no video games or books). I wanted a single place to track everything I'm interested in, currently watching, or have finished without the bloat of social features I don't use. (external APIs other than MyAnimeList are still a work in progress)

## Screenshot
![Media Tracker screenshot](readme_image_1.png)
![Search Recommendation screenshot](readme_image_search.png)
 
## Features
- Track any type of media: games, anime, manga, light novels, web novels, series, movies
- Add items with a title, type, status, and optional notes
- Prefill the form through a search recommendation that calls external APIs (MyAnimeList, IGDB*) and sorts mediatypes by format and category
- Tab-based filtering of the search recommendations
- Filter by type or status
- Edit status/notes and delete entries directly in the table
- Data persists in a local SQLite database

*IGDB not implemented yet

## Tech Stack
- **Backend:** Java 21, Spring Boot 4, Spring Data JPA / Hibernate, SQLite
- **Frontend:** HTML, CSS, JavaScript (no frameworks)
- **Tools** Maven, IntelliJ

## ToDo
- Add users and accounts
- Host the project somewhere
