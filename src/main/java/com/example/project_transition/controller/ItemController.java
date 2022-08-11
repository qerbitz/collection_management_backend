package com.example.project_transition.controller;

import com.example.project_transition.dto.ItemDto;
import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Item;
import com.example.project_transition.service.interfac.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/userItemsList")
    public ResponseEntity<List<ItemDto>> userItemsList(@RequestParam("user") String user){
        List<Item> itemsListByUser = itemService.getItemsListByUser(user);
        return new ResponseEntity<>(mapItemToItemReadDtoList(itemsListByUser), OK);
    }

    @GetMapping("/allItemsByCategory/{category_name}")
    public ResponseEntity<Page<ItemDto>> allItemsByCategory(@PathVariable String category_name,
                                                            @RequestParam("page") int page,
                                                            @RequestParam("sort_option") int sort_option,
                                                            @RequestParam(value = "price_min" , required=false) Double price_min,
                                                            @RequestParam(value = "price_max", required=false) Double price_max,
                                                            @RequestParam(value = "technicalState", required = false) String technicalState){
        Page<Item> itemsPageByCategory = itemService.getItemsListByCategory(category_name, page, sort_option, price_min, price_max, technicalState);
        return new ResponseEntity<>(mapItemPageToItemPageDtoList(itemsPageByCategory), OK);
    }

    @GetMapping("/newestItems")
    public ResponseEntity<List<ItemDto>> newestItems(){
        List<Item> newestItems = itemService.getNewestItems();
        return new ResponseEntity<>(mapItemToItemReadDtoList(newestItems), OK);
    }

    @GetMapping("/categoriesList")
    public ResponseEntity<List<Category>> allCategoriesList(){
        return new ResponseEntity<>(itemService.getCategoriesList(), OK);
    }

    @PostMapping("/addNewItem")
    public ResponseEntity<ItemDto> addNewItem(@RequestBody ItemDto itemDto){
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
        return new ResponseEntity<>(mapItemToItemDto(itemById), OK);
    }


}
