package com.example.project_transition.service.impl;

import com.example.project_transition.dto.CollectionsDto;
import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Collections;
import com.example.project_transition.repository.CategoryRepository;
import com.example.project_transition.repository.CollectionRepository;
import com.example.project_transition.repository.UserRepository;
import com.example.project_transition.service.interfac.CollectionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {


    private CollectionRepository collectionRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public CollectionServiceImpl(CollectionRepository collectionRepository, UserRepository userRepository,CategoryRepository categoryRepository) {
        this.collectionRepository = collectionRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Collections> getCollectionsListByUser(String user) {
        return collectionRepository.getCollectionsByUser(userRepository.findUserByUsername(user));
    }

    @Override
    public void addNewCollection(CollectionsDto collectionDto) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collections collection = new Collections();
        collection.setName(collectionDto.getName());
        collection.setDescription(collectionDto.getDescription());
        collection.setCategory(categoryRepository.findCategoryByName(collectionDto.getCategory().getName()));

        if(collectionDto.getImage()!=null){
            collection.setImage(uploadImageToCloud(collectionDto.getImage()));
        }
        collection.setUser(userRepository.findUserByUsername(authentication.getName()));

        collectionRepository.save(collection);
    }

    @Override
    public void deleteCollection(Long collection_id) {
        Optional<Collections> collectionToDelete = collectionRepository.findById(collection_id);

        if(collectionToDelete!=null){
            collectionRepository.delete(collectionToDelete.get());
        }
    }

    @Override
    public List<Category> getCategoriesList() {
        return categoryRepository.findAll();
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
