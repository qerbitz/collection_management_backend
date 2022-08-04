package com.example.project_transition.service.impl;

import com.example.project_transition.entity.Inboxes;
import com.example.project_transition.entity.Message;
import com.example.project_transition.repository.InboxesRepository;
import com.example.project_transition.repository.MessageRepository;
import com.example.project_transition.repository.UserRepository;
import com.example.project_transition.service.interfac.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private InboxesRepository inboxesRepository;
    private MessageRepository messageRepository;
    private UserRepository userRepository;

    @Autowired
    public MessageServiceImpl(InboxesRepository inboxesRepository, MessageRepository messageRepository, UserRepository userRepository) {
        this.inboxesRepository = inboxesRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Inboxes> getInboxByUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return inboxesRepository.getAllByOwner(userRepository.findUserByUsername(authentication.getName()));
    }

    @Override
    public List<Message> getMessagesByInbox() {

        Optional<Inboxes> inboxes = inboxesRepository.findById(1L);

        return messageRepository.findAllByInbox(inboxes.get());
    }
}
