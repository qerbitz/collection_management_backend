package com.example.project_transition.repository;

import com.example.project_transition.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
