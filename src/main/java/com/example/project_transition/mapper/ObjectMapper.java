package com.example.project_transition.mapper;

import com.example.project_transition.dto.CategoryDto;
import com.example.project_transition.dto.CollectionsDto;
import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Collections;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {

    public static List<CollectionsDto> mapCollectionToCollectionReadDtoList(List<Collections> collections) {
        return collections.stream()
                .map(collection -> mapCollectionToCollectionDto(collection))
                .collect(Collectors.toList());
    }

    public static CollectionsDto mapCollectionToCollectionDto(Collections collections) {
        return CollectionsDto.CollectionsDtoBuilder.aCollectionsDto()
                .withId(collections.getId())
                .withName(collections.getName())
                .withDescription(collections.getDescription())
                .withImage(collections.getImage())
                .withCategory(mapCategoryToCategoryDto(collections.getCategory()))
                .build();
    }


    public static List<CategoryDto> mapCategoryToCategoryReadDtoList(List<Category> categories) {
        return categories.stream()
                .map(category-> mapCategoryToCategoryDto(category))
                .collect(Collectors.toList());
    }

    public static CategoryDto mapCategoryToCategoryDto(Category category) {
        return CategoryDto.CategoryDtoBuilder.aCategoryDto()
                .withName(category.getName())
                .withId(category.getId())
                .build();
    }
}
