package com.example.messenger.service;

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
    public void save(Message message){
        repository.save(message);
    }
    public List<Message> getAllMessagesForUsers(Long idOfFirstUser, Long idOfSecondUser){
        List<Message> allMessages = repository.findAllBySenderIdIsAndReceiverIdIs(idOfFirstUser, idOfSecondUser);
        allMessages.addAll(repository.findAllBySenderIdIsAndReceiverIdIs(idOfSecondUser, idOfFirstUser));
        return allMessages.stream()
                .sorted(Comparator.comparing(Message::getTime))
                .toList();
    }
}
