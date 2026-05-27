package com.mtx.itemdirectory.dto;

import com.mtx.itemdirectory.model.Item;

/**
 * Outgoing representation of an item.
 */
public record ItemResponse(Long id, String name, String category) {

    public static ItemResponse from(Item item) {
        return new ItemResponse(item.getId(), item.getName(), item.getCategory());
    }
}
