package com.example.project_transition.controller;

import com.example.project_transition.dto.CommentDto;
import com.example.project_transition.service.interfac.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.project_transition.mapper.ObjectMapper.mapCommentToCommentReadDtoList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<List<CommentDto>> allCommentsByItem(@PathVariable Long id){
        return new ResponseEntity<>(mapCommentToCommentReadDtoList(commentService.getCommentsByItem(id)), OK);
    }

    @PostMapping("/addComment")
    public ResponseEntity<CommentDto> addNewComment(@RequestBody CommentDto commentDto){
        commentService.addComment(commentDto);
        return new ResponseEntity<>(CREATED);
    }
}
