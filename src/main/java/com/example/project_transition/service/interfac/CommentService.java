package com.example.project_transition.service.interfac;

import com.example.project_transition.dto.CommentDto;
import com.example.project_transition.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByItem(Long id);

    void addComment(CommentDto commentDto);
}
