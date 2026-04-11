package com.github.datkatsu.mediatracker;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/items")
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

    @PatchMapping("/{id}/status")
    public Item updateStatus(@PathVariable Long id, @RequestBody StatusUpdate statusUpdate)
    {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        item.setStatus(statusUpdate.status());
        return itemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }
}