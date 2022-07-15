package com.example.project_transition.repository;

import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Item;
import com.example.project_transition.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item getItemById(Long id);

    List<Item> findAllByCategory(Category category);

    List<Item> findAllByUser(User user);



}
