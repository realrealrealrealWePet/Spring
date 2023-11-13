package com.example.SpringVersion.user.dto;

import com.example.SpringVersion.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long userId;
    private final String email;

    public UserResponseDto(User user){
        this.userId = user.getUserId();
        this.email = user.getEmail();
    }
}
