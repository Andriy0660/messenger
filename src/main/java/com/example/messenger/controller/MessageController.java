package com.example.messenger.controller;

import com.example.messenger.dto.request.GetMessagesRequest;
import com.example.messenger.dto.request.SendMessageRequest;
import com.example.messenger.entity.Message;
import com.example.messenger.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    @PostMapping
    public ResponseEntity<Void> saveMessage(@RequestBody SendMessageRequest request){
        messageService.save(Message.builder()
                .content(request.getContent())
                .senderId(request.getSenderId())
                .receiverId(request.getReceiverId())
                .time(LocalDateTime.now())
                .build());
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessagesForUsers(@RequestBody GetMessagesRequest request){
        List<Message> messageList = messageService.getAllMessagesForUsers(request.getIdOfFirstUser(),
                request.getIdOfSecondUser());
        return ResponseEntity.ok(messageList);
    }
}
