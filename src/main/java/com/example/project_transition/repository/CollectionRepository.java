package com.example.project_transition.repository;

import com.example.project_transition.entity.Collections;
import com.example.project_transition.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collections, Long> {

    List<Collections> getCollectionsByUser(User user);
}
