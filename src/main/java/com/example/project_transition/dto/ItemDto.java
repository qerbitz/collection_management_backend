package com.example.project_transition.dto;

import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private String iconImage;
    private CategoryDto category;
    private List<Image> images;


    public static final class ItemDtoBuilder {
        private Long id;
        private String name;
        private Double price;
        private String description;
        private String iconImage;
        private CategoryDto category;
        private List<Image> images;

        private ItemDtoBuilder() {
        }

        public static ItemDtoBuilder anItemDto() {
            return new ItemDtoBuilder();
        }

        public ItemDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ItemDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemDtoBuilder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public ItemDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemDtoBuilder withIconImage(String iconImage) {
            this.iconImage = iconImage;
            return this;
        }

        public ItemDtoBuilder withCategoryDto(CategoryDto category) {
            this.category = category;
            return this;
        }

        public ItemDtoBuilder withImages(List<Image> images) {
            this.images = images;
            return this;
        }

        public ItemDto build() {
            ItemDto itemDto = new ItemDto();
            itemDto.setId(id);
            itemDto.setName(name);
            itemDto.setPrice(price);
            itemDto.setDescription(description);
            itemDto.setIconImage(iconImage);
            itemDto.setCategory(category);
            itemDto.setImages(images);
            return itemDto;
        }
    }
}
