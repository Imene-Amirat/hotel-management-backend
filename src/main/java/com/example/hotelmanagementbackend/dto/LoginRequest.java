package com.example.hotelmanagementbackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
