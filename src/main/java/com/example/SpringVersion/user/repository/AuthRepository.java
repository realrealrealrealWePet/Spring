package com.example.SpringVersion.user.repository;

import com.example.SpringVersion.user.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findByUserId(Long userId);
}
