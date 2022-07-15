package com.example.project_transition.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String comment;
    private String user;
    private Long item_id;
    private Date date;


    public static final class CommentDtoBuilder {
        private Long id;
        private String comment;
        private String user;
        private Long item_id;
        private Date date;

        private CommentDtoBuilder() {
        }

        public static CommentDtoBuilder aCommentDto() {
            return new CommentDtoBuilder();
        }

        public CommentDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CommentDtoBuilder withComment(String comment) {
            this.comment = comment;
            return this;
        }

        public CommentDtoBuilder withUser(String user) {
            this.user = user;
            return this;
        }

        public CommentDtoBuilder withItem_id(Long item_id) {
            this.item_id = item_id;
            return this;
        }

        public CommentDtoBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public CommentDto build() {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(id);
            commentDto.setComment(comment);
            commentDto.setUser(user);
            commentDto.setItem_id(item_id);
            commentDto.setDate(date);
            return commentDto;
        }
    }
}
