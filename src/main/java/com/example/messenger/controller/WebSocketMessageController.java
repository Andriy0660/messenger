package com.example.messenger.controller;

import com.example.messenger.dto.request.SendMessageRequest;
import com.example.messenger.dto.response.MessageResponse;
import com.example.messenger.entity.User;
import com.example.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketMessageController {
    private final UserService userService;
    @MessageMapping("/chat/{chatId}")
    @SendTo("/topic/chat/{chatId}")
    public MessageResponse publishMessage(@DestinationVariable String chatId,
                                          @Payload SendMessageRequest messageRequest) {
        User user = userService.findById(messageRequest.getSenderId());
        return MessageResponse.builder()
                .username(user.getName())
                .content(messageRequest.getContent())
                .build();
//

    }
}
