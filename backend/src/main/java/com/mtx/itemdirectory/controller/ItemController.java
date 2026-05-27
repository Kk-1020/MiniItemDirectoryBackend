package com.mtx.itemdirectory.controller;

import com.mtx.itemdirectory.dto.CreateItemRequest;
import com.mtx.itemdirectory.dto.ItemResponse;
import com.mtx.itemdirectory.model.Item;
import com.mtx.itemdirectory.service.ItemService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoints for the Mini Item Directory.
 */
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Creates an item. Returns 201 on success, or 400 if validation fails
     * (see {@code CreateItemRequest} and the global exception handler).
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse create(@Valid @RequestBody CreateItemRequest request) {
        Item saved = itemService.createItem(request.name(), request.category());
        return ItemResponse.from(saved);
    }

    /**
     * Returns all items, or items filtered by name when {@code q} is provided.
     */
    @GetMapping
    public List<ItemResponse> search(@RequestParam(name = "q", required = false) String q) {
        return itemService.search(q).stream()
                .map(ItemResponse::from)
                .toList();
    }
}
