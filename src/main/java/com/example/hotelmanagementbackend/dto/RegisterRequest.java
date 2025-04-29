package com.example.hotelmanagementbackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class RegisterRequest {
    private String username;
    private String email;
    private  String password;
}
