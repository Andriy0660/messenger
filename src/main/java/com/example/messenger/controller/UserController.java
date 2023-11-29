package com.example.messenger.controller;

import com.example.messenger.dto.request.GetAllUsersRequest;
import com.example.messenger.dto.request.RegisterRequest;
import com.example.messenger.dto.response.RegisterResponse;
import com.example.messenger.entity.User;
import com.example.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest request){
        User user = userService.save(User.builder()
                .name(request.getName())
                .build());
        return ResponseEntity.ok(new RegisterResponse(user.getId()));
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestBody GetAllUsersRequest request){
        Long idOfCurrentUser = request.getIdOfCurrentUser();
        return ResponseEntity.ok(userService.getAllUsers()
                .stream()
                .filter(e -> !e.getId().equals(idOfCurrentUser))
                .toList());
    }
}
