package com.github.datkatsu.mediatracker.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.datkatsu.mediatracker.repository.ItemRepository;
import com.github.datkatsu.mediatracker.specification.ItemSpecification;
import com.github.datkatsu.mediatracker.model.Item;
import com.github.datkatsu.mediatracker.model.Status;
import com.github.datkatsu.mediatracker.model.MediaType;

@Service
public class ItemService
{
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }

    public List<Item> getFilteredItems(MediaType type, Status status)
    {
        Specification<Item> spec = ItemSpecification.filterBy(type, status);
        return itemRepository.findAll(spec);

    }
}