package com.github.datkatsu.mediatracker.controller;

import org.springframework.web.bind.annotation.*;

import com.github.datkatsu.mediatracker.dto.ItemUpdateDto;
import com.github.datkatsu.mediatracker.exception.ItemNotFoundException;
import com.github.datkatsu.mediatracker.model.Item;
import com.github.datkatsu.mediatracker.model.MediaType;
import com.github.datkatsu.mediatracker.model.Status;
import com.github.datkatsu.mediatracker.repository.ItemRepository;
import com.github.datkatsu.mediatracker.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController 
{
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    public ItemController(ItemRepository itemRepository, ItemService itemService)
    {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems(
        @RequestParam(required = false) MediaType type,
        @RequestParam(required = false) Status status) 
    {
        return itemService.getFilteredItems(type, status);
    }

    @PostMapping
    public Item addItem(@RequestBody Item item)
    {
        return itemRepository.save(item);
    }

    @PatchMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody ItemUpdateDto itemUpdateDto)
    {
        return itemService.updateItem(id, itemUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }


}
