package com.example.hotelmanagementbackend.controller;

import com.example.hotelmanagementbackend.dto.LoginRequest;
import com.example.hotelmanagementbackend.model.User;
import com.example.hotelmanagementbackend.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    private AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        User user = authService.login(loginRequest);
        //create session and store user
        request.getSession().setAttribute("user", user);


        return ResponseEntity.ok(Map.of("message", "Login successful"));
    }

    @GetMapping("/isAuthenticated")
    public ResponseEntity<Map<String,Object>> isAuth(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");

        if(user != null) {
            return ResponseEntity.ok(Map.of("loggedIn", true));
        }
        else{
            return ResponseEntity.ok(Map.of("loggedIn", false));
        }
    }




}
