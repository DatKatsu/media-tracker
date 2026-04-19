package com.github.datkatsu.mediatracker.service;

import java.util.List;

import com.github.datkatsu.mediatracker.dto.MediaEntryUpdateDto;
import com.github.datkatsu.mediatracker.exception.ItemNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.datkatsu.mediatracker.repository.MediaEntryRepository;
import com.github.datkatsu.mediatracker.specification.MediaEntrySpecification;
import com.github.datkatsu.mediatracker.model.Item;
import com.github.datkatsu.mediatracker.model.Status;
import com.github.datkatsu.mediatracker.model.MediaType;

@Service
public class MediaEntryService
{
    private final MediaEntryRepository mediaEntryRepository;

    public MediaEntryService(MediaEntryRepository mediaEntryRepository)
    {
        this.mediaEntryRepository = mediaEntryRepository;
    }

    public Item updateEntry(Long id, MediaEntryUpdateDto mediaEntryUpdateDto)
    {
        Item item = mediaEntryRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        if(mediaEntryUpdateDto.status() != null)
            item.setStatus(mediaEntryUpdateDto.status());
        if(mediaEntryUpdateDto.notes() != null)
            item.setNotes(mediaEntryUpdateDto.notes());
        return mediaEntryRepository.save(item);
    }

    public List<Item> getFilteredEntries(MediaType type, Status status)
    {
        Specification<Item> spec = MediaEntrySpecification.filterBy(type, status);
        return mediaEntryRepository.findAll(spec);

    }
}