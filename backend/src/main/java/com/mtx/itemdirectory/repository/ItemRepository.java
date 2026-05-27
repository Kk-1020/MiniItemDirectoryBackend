package com.mtx.itemdirectory.repository;

import com.mtx.itemdirectory.model.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access layer for {@link Item} entities.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * Finds items whose name contains the given text, case-insensitively.
     */
    List<Item> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

    List<Item> findAllByOrderByNameAsc();
}
