package com.example.project_transition.service.impl;

import com.example.project_transition.dto.ItemDto;
import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Image;
import com.example.project_transition.entity.Item;
import com.example.project_transition.repository.CategoryRepository;
import com.example.project_transition.repository.ItemRepository;
import com.example.project_transition.repository.UserRepository;
import com.example.project_transition.service.interfac.ItemService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;
    private RestTemplate restTemplate;
    private final int page_size = 5;

    @Autowired
    public ItemServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ItemRepository itemRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Item getItemById(Long id) {
        Item itemById = itemRepository.getItemById(id);
        itemById.setVisits(itemById.getVisits()+1);
        itemRepository.save(itemById);
        return itemById;
    }

    @Override
    public Page<Item> getItemsListByCategory(String category_name, int page, int sort_option, Double price_min, Double price_max, String technicalState) {
        Category category = categoryRepository.findCategoryByName(category_name);
        Pageable pageable = handlePageResult(sort_option, page, page_size);

        if(technicalState.equals("All")){
            technicalState=null;
        }
        return itemRepository.findAllByCategory(pageable, category, price_min, price_max, technicalState);
    }

    @Override
    public List<Category> getCategoriesList() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Item> getItemsListByUser(String user) {
        return itemRepository.findAllByUserOrderByCreateDateDesc(userRepository.findUserByUsername(user));
    }

    @Override
    public List<Item> getNewestItems() {
        return itemRepository.findNewestItems();
    }

    @Override
    public void addNewItem(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setCategory(categoryRepository.findCategoryByName(itemDto.getCategory().getName()));
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        item.setCreateDate(new Date());
        item.setVisits(1);

        List<Image> imageList = new ArrayList<>();

        if(itemDto.getImagesToSave()!=null){
            item.setIconImage(uploadImageToCloud(itemDto.getImagesToSave().get(0)));

            for(int i=0; i<itemDto.getImagesToSave().size(); i++){
                Image imageToDb = new Image();
                imageToDb.setUrl(uploadImageToCloud(itemDto.getImagesToSave().get(i)));
                imageList.add(imageToDb);
            }
        }
        item.setImages(imageList);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        item.setUser(userRepository.findUserByUsername(authentication.getName()));
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long item_id) {
        Optional<Item> itemToDelete = itemRepository.findById(item_id);

        if(itemToDelete!=null){
            itemRepository.delete(itemToDelete.get());
        }
    }

    public String uploadImageToCloud(String image64) {


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

    public Pageable handlePageResult(int sort_option, int page_number, int page_size){
        Pageable page = PageRequest.of(page_number, page_size);

        switch (sort_option){
            case 0:{
                page = PageRequest.of(page_number, page_size, Sort.by("createDate").descending());
                break;
            }
            case 1:{
                page = PageRequest.of(page_number, page_size, Sort.by("name").ascending());
                break;
            }
            case 2:{
                page = PageRequest.of(page_number, page_size, Sort.by("name").descending());
                break;
            }
            case 3:{
                page = PageRequest.of(page_number, page_size, Sort.by("price").ascending());
                break;
            }
            case 4:{
                page = PageRequest.of(page_number, page_size, Sort.by("price").descending());
                break;
            }
        }
        return page;
    }

}
