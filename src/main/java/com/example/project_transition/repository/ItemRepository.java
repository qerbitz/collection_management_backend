package com.example.project_transition.repository;

import com.example.project_transition.entity.Category;
import com.example.project_transition.entity.Item;
import com.example.project_transition.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item getItemById(Long id);


    //@Query(value = "SELECT * FROM item where item.price > :price_min and item.price < :price_max and category_id=:category", nativeQuery = true)
    @Query("SELECT i FROM Item i where i.category=:category and (:price_min is null or i.price > :price_min) and (:price_max is null or i.price < :price_max) and (:technicalState is null or i.technicalState=:technicalState)")
    Page<Item> findAllByCategory(Pageable pageable, Category category, @Param("price_min") Double price_min, @Param("price_max")Double price_max, @Param("technicalState") String technicalState);

    List<Item> findAllByUserOrderByCreateDateDesc(User user);

    @Query(value = "SELECT * FROM item order by create_date desc limit 6;", nativeQuery = true)
    List<Item> findNewestItems();

}
