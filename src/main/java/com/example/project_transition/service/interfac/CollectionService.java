package com.example.project_transition.service.interfac;

import com.example.project_transition.dto.CollectionsDto;
import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Collections;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CollectionService {

    List<Category> getCategoriesList();

    List<Collections> getCollectionsListByUser(String user);

    void addNewCollection(CollectionsDto collectionDto) throws JsonProcessingException;

    void deleteCollection(Long collection_id);
}
