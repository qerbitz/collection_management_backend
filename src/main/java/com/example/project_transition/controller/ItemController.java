package com.example.project_transition.controller;

import com.example.project_transition.dto.CategoryDto;
import com.example.project_transition.dto.ItemDto;
import com.example.project_transition.entity.Item;
import com.example.project_transition.service.interfac.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.project_transition.mapper.ObjectMapper.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {


    private ItemService itemService;

    @Autowired
    public ItemController(ItemService collectionService) {
        this.itemService = collectionService;
    }


    @GetMapping("/userItemsList")
    public ResponseEntity<List<ItemDto>> userItemsList(@RequestParam("user") String user){
        List<Item> itemsListByUser = itemService.getItemsListByUser(user);
        return new ResponseEntity<>(mapItemToItemReadDtoList(itemsListByUser), OK);
    }

    @GetMapping("/allItemsByCategory/{category_name}")
    public ResponseEntity<List<ItemDto>> allItemsByCategory(@PathVariable String category_name){
        List<Item> itemsListByCategory = itemService.getItemsListByCategory(category_name);
        return new ResponseEntity<>(mapItemToItemReadDtoList(itemsListByCategory), OK);
    }

    @GetMapping("/categoriesList")
    public ResponseEntity<List<CategoryDto>> allCategoriesList(){
        return new ResponseEntity<>(mapCategoryToCategoryReadDtoList(itemService.getCategoriesList()), OK);
    }

    @PostMapping("/addNewItem")
    public ResponseEntity<ItemDto> addNewItem(@RequestBody ItemDto itemDto) throws JsonProcessingException {
        itemService.addNewItem(itemDto);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/deleteItem")
    public ResponseEntity<List<Long>> deleteitem(@RequestParam("item_id") Long item_id){
        itemService.deleteItem(item_id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable Long id){
        Item itemById = itemService.getItemById(id);
        System.out.println("hihi");
        return new ResponseEntity<>(mapItemToItemDto(itemById), OK);
    }

}
