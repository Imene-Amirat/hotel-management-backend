package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.LoginRequest;
import com.example.hotelmanagementbackend.dto.RegisterRequest;
import com.example.hotelmanagementbackend.exception.InvalidPasswordException;
import com.example.hotelmanagementbackend.exception.UserNotFoundException;
import com.example.hotelmanagementbackend.model.Role;
import com.example.hotelmanagementbackend.model.User;
import com.example.hotelmanagementbackend.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

    public void register(RegisterRequest registerRequest, HttpServletRequest request){
        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new EmailDuplicatedException("Email already in use");
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setRole(Role.CLIENT);

        userRepository.save(user);
        request.getSession().setAttribute("user", user);
    }

    public boolean isAuth(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null && session.isNew()){
            User user = (User) session.getAttribute("user");
            return user != null;
        }
        return false;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        //delete the JSESSIONID cookie manually
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setSecure(false);

        response.addCookie(cookie);
    }
}
