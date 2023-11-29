package com.example.messenger.controller;

import com.example.messenger.dto.request.SendMessageRequest;
import com.example.messenger.entity.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WebSocketMessageController {
    @MessageMapping("/chat/{chatId}")
    @SendTo("/topic/chat/{chatId}")
    public Message publishMessage(@DestinationVariable String chatId, SendMessageRequest messageRequest) {
        return Message.builder()
                .content(messageRequest.getContent())
                .senderId(messageRequest.getSenderId())
                .receiverId(messageRequest.getReceiverId())
                .time(LocalDateTime.now())
                .build();
    }
}
