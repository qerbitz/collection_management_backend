package com.example.project_transition.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private String image;


    public static final class CategoryDtoBuilder {
        private Long id;
        private String name;
        private String image;

        private CategoryDtoBuilder() {
        }

        public static CategoryDtoBuilder aCategoryDto() {
            return new CategoryDtoBuilder();
        }

        public CategoryDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CategoryDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CategoryDtoBuilder withImage(String image) {
            this.image = image;
            return this;
        }

        public CategoryDto build() {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(id);
            categoryDto.setName(name);
            categoryDto.setImage(image);
            return categoryDto;
        }
    }
}
