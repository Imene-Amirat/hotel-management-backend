package com.example.hotelmanagementbackend.controller;

import com.example.hotelmanagementbackend.dto.UserCard;
import com.example.hotelmanagementbackend.model.User;
import com.example.hotelmanagementbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserCard> getUsers() {
        return  userService.getAllUsers();
    }
}
