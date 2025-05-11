package org.example.rest.controller;

import jakarta.validation.Valid;
import org.example.rest.domain.User;
import org.example.rest.dto.request.UserRequest;
import org.example.rest.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(Pageable page) { //api/v1/users?size1&page=10&sort
        return List.of();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
}
