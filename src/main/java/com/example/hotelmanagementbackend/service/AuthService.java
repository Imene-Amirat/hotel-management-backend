package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.LoginRequest;
import com.example.hotelmanagementbackend.exception.InvalidPasswordException;
import com.example.hotelmanagementbackend.exception.UserNotFoundException;
import com.example.hotelmanagementbackend.model.User;
import com.example.hotelmanagementbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(LoginRequest loginRequest) {
        Optional<User> userFound = userRepository.findByEmail(loginRequest.getEmail());

        if(userFound.isEmpty()){
            throw new UserNotFoundException("Invalid email");
        } else if(!userFound.get().getPassword().equals(loginRequest.getPassword())){
            throw new InvalidPasswordException("Invalid password");
        }

        return userFound.get();
    }
}
