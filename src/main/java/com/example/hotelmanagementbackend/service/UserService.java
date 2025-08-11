package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.UserCard;
import com.example.hotelmanagementbackend.model.User;
import com.example.hotelmanagementbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserCard> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserCard> userCards = new ArrayList<>();
        for(User user : users){
            userCards.add(mapToUserCard(user));
        }
        return userCards;
    }

    private UserCard mapToUserCard(User user){
        UserCard userCard = new UserCard();

        userCard.setUserId(user.getId());
        userCard.setUserName(user.getUsername());
        userCard.setEmail(user.getEmail());

        return userCard;
    }
}
