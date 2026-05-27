package com.mtx.itemdirectory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Incoming payload for POST /items.
 *
 * <p>Validation rules: name is required and limited to 100 characters;
 * category is required and limited to 50 characters.
 */
public record CreateItemRequest(

        @NotBlank(message = "name must not be empty")
        @Size(max = 100, message = "name must be at most 100 characters")
        String name,

        @NotBlank(message = "category must not be empty")
        @Size(max = 50, message = "category must be at most 50 characters")
        String category
) {
}
