package com.mtx.itemdirectory.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mtx.itemdirectory.model.Item;
import com.mtx.itemdirectory.repository.ItemRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link ItemService}. The repository is mocked with Mockito,
 * so these tests exercise only the service logic.
 */
@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    void createItem_trimsInputAndSavesViaRepository() {
        when(itemRepository.save(any(Item.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        itemService.createItem("  Atomic Habits  ", "  Book  ");

        ArgumentCaptor<Item> captor = ArgumentCaptor.forClass(Item.class);
        verify(itemRepository).save(captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo("Atomic Habits");
        assertThat(captor.getValue().getCategory()).isEqualTo("Book");
    }

    @Test
    void search_withQuery_filtersByName() {
        when(itemRepository.findByNameContainingIgnoreCaseOrderByNameAsc("atom"))
                .thenReturn(List.of(new Item("Atomic Habits", "Book")));

        List<Item> results = itemService.search("atom");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("Atomic Habits");
        verify(itemRepository).findByNameContainingIgnoreCaseOrderByNameAsc("atom");
    }

    @Test
    void search_withBlankQuery_returnsAllItems() {
        when(itemRepository.findAllByOrderByNameAsc())
                .thenReturn(List.of(
                        new Item("Atomic Habits", "Book"),
                        new Item("Inception", "Movie")));

        List<Item> results = itemService.search("   ");

        assertThat(results).hasSize(2);
        verify(itemRepository).findAllByOrderByNameAsc();
    }
}
