package com.example.project_transition.service.impl;

import com.example.project_transition.dto.CommentDto;
import com.example.project_transition.entity.Comment;
import com.example.project_transition.entity.Item;
import com.example.project_transition.repository.CommentRepository;
import com.example.project_transition.repository.ItemRepository;
import com.example.project_transition.repository.UserRepository;
import com.example.project_transition.service.interfac.CommentService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Comment> getCommentsByItem(Long id) {
        return commentRepository.getCommentsByItemId(id);
    }

    @Override
    public void addComment(CommentDto commentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setDate_posted(new Date());
        Optional<Item> itembyId = itemRepository.findById(commentDto.getItem_id());
        comment.setItem(itembyId.get());
        comment.setUser(userRepository.findUserByUsername(authentication.getName()));

        commentRepository.save(comment);
    }
}
