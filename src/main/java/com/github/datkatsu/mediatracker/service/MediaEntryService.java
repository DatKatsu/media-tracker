package com.github.datkatsu.mediatracker.service;

import java.util.List;

import com.github.datkatsu.mediatracker.dto.MediaEntryRequestDto;
import com.github.datkatsu.mediatracker.dto.MediaEntryResponseDto;
import com.github.datkatsu.mediatracker.dto.MediaEntryUpdateDto;
import com.github.datkatsu.mediatracker.exception.MediaEntryNotFoundException;
import com.github.datkatsu.mediatracker.mapper.MediaEntryMapper;
import com.github.datkatsu.mediatracker.model.MediaEntry;
import com.github.datkatsu.mediatracker.model.MediaFormat;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.datkatsu.mediatracker.repository.MediaEntryRepository;
import com.github.datkatsu.mediatracker.specification.MediaEntrySpecification;
import com.github.datkatsu.mediatracker.model.Status;
import com.github.datkatsu.mediatracker.model.MediaCategory;

@Service
public class MediaEntryService
{
    private final MediaEntryRepository repository;
    private final MediaEntryMapper mapper;

    public MediaEntryService(MediaEntryRepository repository, MediaEntryMapper mapper)
    {
        this.repository = repository;
        this.mapper = mapper;
    }

    public MediaEntry addEntry(MediaEntryRequestDto dto)
    {
        return repository.save(mapper.toEntity(dto));
    }

    public List<MediaEntry> getFilteredEntries(MediaFormat format, Status status)
    {
        Specification<MediaEntry> spec = MediaEntrySpecification.filterBy(format, status);
        return repository.findAll(spec);
    }

    public MediaEntryResponseDto updateEntry(Long id, MediaEntryUpdateDto mediaEntryUpdateDto)
    {
        MediaEntry mediaEntry = repository.findById(id).orElseThrow(() -> new MediaEntryNotFoundException(id));
        if(mediaEntryUpdateDto.status() != null)
            mediaEntry.setStatus(mediaEntryUpdateDto.status());
        if(mediaEntryUpdateDto.notes() != null)
            mediaEntry.setNotes(mediaEntryUpdateDto.notes());
        return mapper.toResponseDto(repository.save(mediaEntry));
    }

    public void deleteEntryById(Long id)
    {
        repository.deleteById(id);
    }


}