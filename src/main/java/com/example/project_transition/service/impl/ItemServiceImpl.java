package com.example.project_transition.service.impl;

import com.example.project_transition.dto.ItemDto;
import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Item;
import com.example.project_transition.entity.User;
import com.example.project_transition.repository.CategoryRepository;
import com.example.project_transition.repository.ItemRepository;
import com.example.project_transition.repository.UserRepository;
import com.example.project_transition.service.interfac.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public ItemServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public Item getItemById(Long id) {
        return itemRepository.getItemById(id);
    }

    @Override
    public List<Item> getItemsListByCategory(String category_name) {

        Category category = categoryRepository.findCategoryByName(category_name);
        return itemRepository.findAllByCategory(category);
    }

    @Override
    public List<Category> getCategoriesList() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Item> getItemsListByUser(String user) {
        return itemRepository.findAllByUser(userRepository.findUserByUsername(user));
    }

    @Override
    public void addNewItem(ItemDto itemDto) throws JsonProcessingException {

    }

    @Override
    public void deleteItem(Long item_id) {
        Optional<Item> itemToDelete = itemRepository.findById(item_id);

        if(itemToDelete!=null){
            itemRepository.delete(itemToDelete.get());
        }
    }

    public String uploadImageToCloud(String image64) throws JsonProcessingException {


        final String uri = "https://api.imgbb.com/1/upload?key=4ddbd8e0aff381645594a058d20c206b";

        MultiValueMap<String, Object> image = new LinkedMultiValueMap<String, Object>();
        image.add("image", image64);


        ResponseEntity<String> response = restTemplate
                .postForEntity(uri, image, String.class);

        String responseJson = response.getBody();

        JsonObject jsonObject = JsonParser.parseString(responseJson).getAsJsonObject();
        JsonObject jsonImageUrl = jsonObject.getAsJsonObject("data");

        String imageUrl = jsonImageUrl.get("display_url").toString();
        String imageUrlFinal = imageUrl.substring(1, imageUrl.length()-1);

        return imageUrlFinal;
    }
}
