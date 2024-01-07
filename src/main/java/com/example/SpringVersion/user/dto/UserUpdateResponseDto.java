package com.example.SpringVersion.user.dto;

import com.example.SpringVersion.user.entity.User;
import lombok.Getter;

@Getter
public class UserUpdateResponseDto {
    private String username;
    public UserUpdateResponseDto(User user){
        this.username = user.getUsername();
    }
}
