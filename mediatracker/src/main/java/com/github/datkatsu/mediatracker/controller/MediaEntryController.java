package com.github.datkatsu.mediatracker.controller;

import org.springframework.web.bind.annotation.*;

import com.github.datkatsu.mediatracker.dto.MediaEntryUpdateDto;
import com.github.datkatsu.mediatracker.model.Item;
import com.github.datkatsu.mediatracker.model.MediaType;
import com.github.datkatsu.mediatracker.model.Status;
import com.github.datkatsu.mediatracker.repository.MediaEntryRepository;
import com.github.datkatsu.mediatracker.service.MediaEntryService;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class MediaEntryController
{
    private final MediaEntryRepository mediaEntryRepository;
    private final MediaEntryService mediaEntryService;

    public MediaEntryController(MediaEntryRepository mediaEntryRepository, MediaEntryService mediaEntryService)
    {
        this.mediaEntryRepository = mediaEntryRepository;
        this.mediaEntryService = mediaEntryService;
    }

    @GetMapping
    public List<Item> getEntries(
        @RequestParam(required = false) MediaType type,
        @RequestParam(required = false) Status status) 
    {
        return mediaEntryService.getFilteredEntries(type, status);
    }

    @PostMapping
    public Item addEntry(@RequestBody Item item)
    {
        return mediaEntryRepository.save(item);
    }

    @PatchMapping("/{id}")
    public Item updateEntry(@PathVariable Long id, @RequestBody MediaEntryUpdateDto mediaEntryUpdateDto)
    {
        return mediaEntryService.updateEntry(id, mediaEntryUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        mediaEntryRepository.deleteById(id);
    }


}
