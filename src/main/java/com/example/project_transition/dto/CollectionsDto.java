package com.example.project_transition.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionsDto {

    private Long id;
    private String name;
    private String description;
    private String image;
    private CategoryDto category;


    public static final class CollectionsDtoBuilder {
        private Long id;
        private String name;
        private String description;
        private String image;
        private CategoryDto category;

        private CollectionsDtoBuilder() {
        }

        public static CollectionsDtoBuilder aCollectionsDto() {
            return new CollectionsDtoBuilder();
        }

        public CollectionsDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CollectionsDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CollectionsDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CollectionsDtoBuilder withImage(String image) {
            this.image = image;
            return this;
        }

        public CollectionsDtoBuilder withCategory(CategoryDto category) {
            this.category = category;
            return this;
        }

        public CollectionsDto build() {
            CollectionsDto collectionsDto = new CollectionsDto();
            collectionsDto.setId(id);
            collectionsDto.setName(name);
            collectionsDto.setDescription(description);
            collectionsDto.setImage(image);
            collectionsDto.setCategory(category);
            return collectionsDto;
        }
    }
}
