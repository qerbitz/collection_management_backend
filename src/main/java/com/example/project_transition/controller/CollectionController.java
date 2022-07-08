package com.example.project_transition.controller;

import com.example.project_transition.dto.CategoryDto;
import com.example.project_transition.dto.CollectionsDto;
import com.example.project_transition.entity.Collections;
import com.example.project_transition.service.interfac.CollectionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.project_transition.mapper.ObjectMapper.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin
@RequestMapping("/collections")
public class CollectionController {


    private CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }


    @GetMapping("/userCollectionsList")
    public ResponseEntity<List<CollectionsDto>> allCollectionsByUser(@RequestParam("user") String user){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        //if(!authentication.getName().equals(user)){
        //    return new ResponseEntity<>(FORBIDDEN);
       // }

        System.out.println(user);
        List<Collections> allUserCollections = collectionService.getCollectionsListByUser(user);
        return new ResponseEntity<>(mapCollectionToCollectionReadDtoList(allUserCollections), OK);
    }

    @GetMapping("/categoriesList")
    public ResponseEntity<List<CategoryDto>> allCategoriesList(){
        return new ResponseEntity<>(mapCategoryToCategoryReadDtoList(collectionService.getCategoriesList()), OK);
    }

    @PostMapping("/addNewCollection")
    public ResponseEntity<CollectionsDto> addNewCollection(@RequestBody CollectionsDto collectionDto) throws JsonProcessingException {
        System.out.println(collectionDto);
        collectionService.addNewCollection(collectionDto);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/deleteCollection")
    public ResponseEntity<List<Long>> deleteUsers(@RequestParam("collection_id") Long collection_id){
        collectionService.deleteCollection(collection_id);
        return new ResponseEntity<>(OK);
    }


}
