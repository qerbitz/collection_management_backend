package com.example.project_transition.service.interfac;

import com.example.project_transition.dto.ItemDto;
import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Item;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {

    List<Category> getCategoriesList();

    List<Item> getItemsListByUser(String user);

    List<Item> getNewestItems();

    void addNewItem(ItemDto itemDto);

    void deleteItem(Long item_id);

    Item getItemById(Long id);

    Page<Item> getItemsListByCategory(String category_name, int page, int sort_option, Double price_min, Double price_max, String technicalState);
}
