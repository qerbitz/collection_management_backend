package com.example.project_transition.dto;

import com.example.project_transition.entity.Address;
import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    private Category category;
    private List<Image> images;
    private List<String> imagesToSave;
    private Address address;
    private Date createDate;
    private int visits;
    private String technicalState;


    public static final class ItemDtoBuilder {
        private Long id;
        private String name;
        private Double price;
        private String description;
        private String iconImage;
        private Category category;
        private List<Image> images;
        private List<String> imagesToSave;
        private Address address;
        private Date createDate;
        private int visits;
        private String technicalState;

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

        public ItemDtoBuilder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public ItemDtoBuilder withImages(List<Image> images) {
            this.images = images;
            return this;
        }

        public ItemDtoBuilder withImagesToSave(List<String> imagesToSave) {
            this.imagesToSave = imagesToSave;
            return this;
        }

        public ItemDtoBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public ItemDtoBuilder withCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public ItemDtoBuilder withVisits(int visits) {
            this.visits = visits;
            return this;
        }

        public ItemDtoBuilder withTechnicalState(String technicalState) {
            this.technicalState = technicalState;
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
            itemDto.setImagesToSave(imagesToSave);
            itemDto.setAddress(address);
            itemDto.setCreateDate(createDate);
            itemDto.setVisits(visits);
            itemDto.setTechnicalState(technicalState);
            return itemDto;
        }
    }
}
