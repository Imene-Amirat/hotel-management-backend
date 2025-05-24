package com.example.hotelmanagementbackend.controller;

import com.example.hotelmanagementbackend.dto.LoginRequest;
import com.example.hotelmanagementbackend.dto.RegisterRequest;
import com.example.hotelmanagementbackend.dto.UserDTO;
import com.example.hotelmanagementbackend.model.User;
import com.example.hotelmanagementbackend.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Map<String,String>> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        User user = authService.login(loginRequest);
        //create session and store user
        request.getSession().setAttribute("user", user);
        return ResponseEntity.ok(Map.of("message", "Login successful"));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@Valid  @RequestBody RegisterRequest registerRequest, HttpServletRequest request){
        authService.register(registerRequest, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Registration successful"));
    }

    @GetMapping("/isAuthenticated")
    public ResponseEntity<Map<String, Object>> isAuth(HttpServletRequest request) {
        boolean loggedIn = authService.isAuth(request);
        return ResponseEntity.ok(Map.of("loggedIn", loggedIn));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO>  getCurrentUser(HttpServletRequest request){
        UserDTO userDTO =  authService.getCurrentUser(request);
        if(userDTO != null)
            return ResponseEntity.ok(userDTO);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request,response);
        return ResponseEntity.ok(Map.of("message", "Logged out"));
    }
}
