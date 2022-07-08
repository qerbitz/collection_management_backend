package com.example.project_transition.repository;

import com.example.project_transition.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByName(String name);
}
