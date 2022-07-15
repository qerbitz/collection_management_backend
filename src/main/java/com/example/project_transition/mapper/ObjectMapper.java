package com.example.project_transition.mapper;

import com.example.project_transition.dto.CategoryDto;
import com.example.project_transition.dto.CommentDto;
import com.example.project_transition.dto.ItemDto;
import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Comment;
import com.example.project_transition.entity.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {


    public static List<ItemDto> mapItemToItemReadDtoList(List<Item> items) {
        return items.stream()
                .map(item -> mapItemToItemDto(item))
                .collect(Collectors.toList());
    }

    public static ItemDto mapItemToItemDto(Item item) {
        return ItemDto.ItemDtoBuilder.anItemDto()
                .withId(item.getId())
                .withName(item.getName())
                .withPrice(item.getPrice())
                .withIconImage(item.getIconImage())
                .withDescription(item.getDescription())
                .withCategoryDto(mapCategoryToCategoryDto(item.getCategory()))
                .withImages(item.getImages())
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
                .withImage(category.getImage())
                .withId(category.getId())
                .build();
    }


    public static List<CommentDto> mapCommentToCommentReadDtoList(List<Comment> comments) {
        return comments.stream()
                .map(comment -> mapCommentToCommentDto(comment))
                .collect(Collectors.toList());
    }

    public static CommentDto mapCommentToCommentDto(Comment comment) {
        return CommentDto.CommentDtoBuilder.aCommentDto()
                .withId(comment.getId())
                .withUser(comment.getUser().getUsername())
                .withDate(comment.getDate_posted())
                .build();
    }
}
