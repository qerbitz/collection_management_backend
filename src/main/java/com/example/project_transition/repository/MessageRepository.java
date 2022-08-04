package com.example.project_transition.repository;

import com.example.project_transition.entity.Inboxes;
import com.example.project_transition.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByInbox(Inboxes inboxes);
}
