package com.example.hotelmanagementbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity //generates getters automatically
@Getter
@Setter
@ToString
@AllArgsConstructor  //generates an all-args constructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)   // save enum(client,admin) as string in DB
    @Column(nullable = false)
    private Role role;
}
