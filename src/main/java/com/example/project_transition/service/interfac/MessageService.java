package com.example.project_transition.service.interfac;

import com.example.project_transition.entity.Inboxes;
import com.example.project_transition.entity.Message;

import java.util.List;

public interface MessageService {

    List<Inboxes> getInboxByUser();

    List<Message> getMessagesByInbox();
}
