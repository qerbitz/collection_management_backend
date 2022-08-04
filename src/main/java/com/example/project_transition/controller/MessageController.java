package com.example.project_transition.controller;

import com.example.project_transition.dto.InboxesDto;
import com.example.project_transition.dto.MessageDto;
import com.example.project_transition.service.interfac.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.project_transition.mapper.ObjectMapper.mapInboxToInboxReadDtoList;
import static com.example.project_transition.mapper.ObjectMapper.mapMessageToMessageReadDtoList;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/messages")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/userInbox")
    public List<InboxesDto> getInboxByUser(){
        return mapInboxToInboxReadDtoList(messageService.getInboxByUser());
    }

    @GetMapping("/inboxMessages")
    public List<MessageDto> getMessagesByInbox(){
        return mapMessageToMessageReadDtoList(messageService.getMessagesByInbox());
    }
}
