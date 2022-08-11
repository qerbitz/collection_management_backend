package com.example.project_transition.mapper;

import com.example.project_transition.dto.*;
import com.example.project_transition.entity.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {


    public static UserDto mapUsertoUserDto(User user){
        return UserDto.UserDtoBuilder.anUserDto()
                .withId(user.getId())
                //.withUsername(user.getUsername())
                .build();
    }

/////////////////////////////////////////////////////////////////////////////////////
    public static Page<ItemDto> mapItemPageToItemPageDtoList(Page<Item> items) {
        return items
                .map(item -> mapItemToItemPageDto(item));
    }

    public static List<ItemDto> mapItemToItemReadDtoList(List<Item> items) {
        return items.stream()
                .map(item -> mapItemToItemPageDto(item))
                .collect(Collectors.toList());
    }

    public static ItemDto mapItemToItemDto(Item item) {
        return ItemDto.ItemDtoBuilder.anItemDto()
                .withId(item.getId())
                .withName(item.getName())
                .withPrice(item.getPrice())
                .withIconImage(item.getIconImage())
                .withDescription(item.getDescription())
                .withCategory(item.getCategory())
                .withImages(item.getImages())
                .withCreateDate(item.getCreateDate())
                //.withAddress(item.getUser().getAddress())
                .withVisits(item.getVisits())
                .withTechnicalState(item.getTechnicalState())
                .build();
    }

    public static ItemDto mapItemToItemPageDto(Item item) {
        return ItemDto.ItemDtoBuilder.anItemDto()
                .withId(item.getId())
                .withName(item.getName())
                .withPrice(item.getPrice())
                .withIconImage(item.getIconImage())
                .withDescription(item.getDescription())
                .withCategory(item.getCategory())
                .withCreateDate(item.getCreateDate())
                //.withAddress(item.getUser().getAddress())
                .withTechnicalState(item.getTechnicalState())
                .withVisits(item.getVisits())
                .build();
    }

/////////////////////////////////////////////////////////////////////////////////////////////
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

//////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<CommentDto> mapCommentToCommentReadDtoList(List<Comment> comments) {
        return comments.stream()
                .map(comment -> mapCommentToCommentDto(comment))
                .collect(Collectors.toList());
    }

    public static CommentDto mapCommentToCommentDto(Comment comment) {
        return CommentDto.CommentDtoBuilder.aCommentDto()
                .withId(comment.getId())
                //.withUser(comment.getUser().getUsername())
                .withDate(comment.getDate_posted())
                .build();
    }

}
