package com.github.datkatsu.mediatracker;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public List<Item> getAllItems()
    {
        return itemRepository.findAll();
    }

    @PostMapping
    public Item addItem(@RequestBody Item item)
    {
        return itemRepository.save(item);
    }

    @PatchMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody ItemUpdate itemUpdate)
    {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        if(itemUpdate.status() != null)
            item.setStatus(itemUpdate.status());
        if(itemUpdate.notes() != null)
            item.setNotes(itemUpdate.notes());
        return itemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }


}