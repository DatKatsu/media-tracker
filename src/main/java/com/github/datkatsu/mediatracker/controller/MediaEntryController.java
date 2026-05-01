package com.github.datkatsu.mediatracker.controller;

import com.github.datkatsu.mediatracker.dto.MediaEntryRequestDto;
import com.github.datkatsu.mediatracker.dto.MediaEntryResponseDto;
import com.github.datkatsu.mediatracker.mapper.MediaEntryMapper;
import com.github.datkatsu.mediatracker.model.MediaCategory;
import com.github.datkatsu.mediatracker.model.MediaFormat;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.datkatsu.mediatracker.dto.MediaEntryUpdateDto;
import com.github.datkatsu.mediatracker.model.Status;
import com.github.datkatsu.mediatracker.service.MediaEntryService;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaEntryController
{
    private final MediaEntryService service;
    private final MediaEntryMapper mapper;

    public MediaEntryController(MediaEntryService service, MediaEntryMapper mapper)
    {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MediaEntryResponseDto> getEntries(
        @RequestParam(required = false) MediaFormat format,
        @RequestParam(required = false) Status status) 
    {
        return mapper.toResponseDtoList(service.getFilteredEntries(format, status));
    }

    @PostMapping
    public MediaEntryResponseDto addEntry(@Valid @RequestBody MediaEntryRequestDto dto)
    {
        return mapper.toResponseDto(service.addEntry(dto));
    }

    @PatchMapping("/{id}")
    public MediaEntryResponseDto updateEntry(@PathVariable Long id, @RequestBody MediaEntryUpdateDto mediaEntryUpdateDto)
    {
        return service.updateEntry(id, mediaEntryUpdateDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        service.deleteEntryById(id);
        return ResponseEntity.noContent().build();
    }


}
