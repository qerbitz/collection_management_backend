package com.example.project_transition.repository;

import com.example.project_transition.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment where item=:ajdi", nativeQuery = true)
    List<Comment> getCommentsByItemId(@Param("ajdi")Long ajdi);
}
