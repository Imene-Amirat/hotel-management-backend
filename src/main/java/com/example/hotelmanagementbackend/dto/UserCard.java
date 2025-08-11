package com.example.hotelmanagementbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserCard {
    private Long userId;
    private String userName;
    private String email;
    private Date lastConnexion;
    private String status;
}
