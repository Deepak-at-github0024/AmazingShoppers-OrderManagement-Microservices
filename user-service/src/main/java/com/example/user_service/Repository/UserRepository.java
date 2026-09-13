package com.example.user_service.Repository;

import com.example.user_service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);
}
