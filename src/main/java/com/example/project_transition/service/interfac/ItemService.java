package com.example.project_transition.service.interfac;

import com.example.project_transition.dto.ItemDto;
import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Item;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ItemService {

    List<Category> getCategoriesList();

    List<Item> getItemsListByUser(String user);

    void addNewItem(ItemDto itemDto) throws JsonProcessingException;

    void deleteItem(Long item_id);

    Item getItemById(Long id);

    List<Item> getItemsListByCategory(String category_name);
}
