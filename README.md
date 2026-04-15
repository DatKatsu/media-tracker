# Media Tracker

A personal media backlog tracker for keeping track of all types of media like games, anime, manga, light novels, web novels, series, and movies — all in one place.

---

## Motivation

Most tracking tools like MyAnimeList only cover one type of media. I wanted a single place to track everything I'm interested in, currently watching, or have finished without the bloat of social features I don't use.

---
## Features

- Track any type of media: games, anime, manga, light novels, web novels, series, movies
- Add items with a title, type, status, and optional notes
- Update status directly from the list
- Filter by type or status
- Delete items
- Data persists in a local SQLite database

---

## Tech Stack

**Backend**
- Java 21
- Spring Boot 4
- Spring Data JPA / Hibernate
- SQLite

**Frontend**
- Plain HTML, CSS, JavaScript (no frameworks)
- Fetch API for communicating with the backend

**Tools**
- Maven (build and dependency management)
- GitHub Codespaces (development environment)
  
---

## Running Locally

### Prerequisites
- Java 21 or higher

### Steps
1. Clone the repository and navigate into it
2. Run `./mvnw spring-boot:run`
3. Open `http://localhost:8080` in your browser

The SQLite database file is created automatically on first run.

---

## What I Learned

This project was built to practice:
- Designing and building a REST API from scratch with Spring Boot
- Connecting a Java backend to a SQLite database using JPA and Hibernate
- Understanding the full request lifecycle from HTTP to database and back
- Building a functional frontend with plain JavaScript and the Fetch API
- Working in a cloud development environment with GitHub Codespaces
