package com.github.datkatsu.mediatracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "media_entries")
public class MediaEntry
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private MediaFormat format;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String notes;

    private String coverUrl;

    public MediaEntry() {   }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public MediaFormat getFormat() { return format; }
    public void setFormat(MediaFormat format) { this.format = format; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getCoverUrl() {return coverUrl;}
    public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }

    @Transient public MediaCategory getCategory() { return format.getCategory(); }
}
