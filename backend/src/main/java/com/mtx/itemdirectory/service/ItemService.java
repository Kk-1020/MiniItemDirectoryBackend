package com.mtx.itemdirectory.service;

import com.mtx.itemdirectory.model.Item;
import com.mtx.itemdirectory.repository.ItemRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Business logic for creating and searching items.
 */
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Persists a new item. Inputs are expected to be already validated
     * at the controller boundary.
     */
    public Item createItem(String name, String category) {
        Item item = new Item(name.trim(), category.trim());
        return itemRepository.save(item);
    }

    /**
     * Returns all items, or only those whose name contains {@code query}
     * (case-insensitive) when a non-blank query is supplied.
     */
    public List<Item> search(String query) {
        if (query == null || query.isBlank()) {
            return itemRepository.findAllByOrderByNameAsc();
        }
        return itemRepository.findByNameContainingIgnoreCaseOrderByNameAsc(query.trim());
    }
}
