package com.example.messenger.service;

import com.example.messenger.entity.User;
import com.example.messenger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    public User save(User user){
        return repository.save(user);
    }
    public List<User> getAllUsers(){
        return repository.findAll();
    }
    public boolean exists(String name){
        return repository.existsByName(name);
    }
    public User findByName(String name){
        return repository.findByName(name).orElseThrow(()-> new RuntimeException());
    }
    public User findById(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException());
    }
}
