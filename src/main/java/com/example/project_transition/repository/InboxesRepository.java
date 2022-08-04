package com.example.project_transition.repository;

import com.example.project_transition.entity.Inboxes;
import com.example.project_transition.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InboxesRepository extends JpaRepository<Inboxes, Long> {

    @Query(value = "SELECT i FROM Inboxes i where i.owner=:user")
    List<Inboxes> getAllByOwner(User user);
}
