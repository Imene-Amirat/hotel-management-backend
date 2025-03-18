package com.example.hotelmanagementbackend.repository;

import com.example.hotelmanagementbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
