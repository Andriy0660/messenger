package com.example.messenger.service;

import com.example.messenger.dto.response.MessageResponse;
import com.example.messenger.entity.Message;
import com.example.messenger.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository repository;
    private final UserService userService;
    public void save(Message message){
        repository.save(message);
    }
    public List<MessageResponse> getAllMessagesForUsers(Long idOfFirstUser, Long idOfSecondUser){
        List<MessageResponse> allMessagesForFirstUser = repository.findAllBySenderIdIsAndReceiverIdIs(idOfFirstUser, idOfSecondUser)
                .stream()
                .map(e -> new MessageResponse(e.getContent(), userService.findById(idOfFirstUser).getName(), e.getTime()))
                .toList();

        List<MessageResponse> allMessagesForSecondUser = repository.findAllBySenderIdIsAndReceiverIdIs(idOfSecondUser, idOfFirstUser)
                .stream()
                .map(e -> new MessageResponse(e.getContent(), userService.findById(idOfSecondUser).getName(), e.getTime()))
                .toList();
        allMessagesForFirstUser.addAll(allMessagesForSecondUser);
        return allMessagesForFirstUser.stream()
                .sorted(Comparator.comparing(MessageResponse::getTime))
                .toList();
    }
}
