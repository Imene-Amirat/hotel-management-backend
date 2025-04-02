package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.LoginRequest;
import com.example.hotelmanagementbackend.exception.InvalidPasswordException;
import com.example.hotelmanagementbackend.exception.UserNotFoundException;
import com.example.hotelmanagementbackend.model.User;
import com.example.hotelmanagementbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(LoginRequest loginRequest) {
        List<User> userFound = userRepository.findByEmail(loginRequest.getEmail());

        if(userFound.isEmpty()){
            throw new UserNotFoundException("Invalid email");
        } else if(!userFound.get(0).getPassword().equals(loginRequest.getPassword())){
            throw new InvalidPasswordException("Invalid password");
        }
    }
}
