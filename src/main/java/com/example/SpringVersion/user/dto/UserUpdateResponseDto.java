package com.example.SpringVersion.user.dto;

import com.example.SpringVersion.user.entity.User;
import lombok.Getter;

@Getter
public class UserUpdateResponseDto {
    private String nickname;
    public UserUpdateResponseDto(User user){
        this.nickname = user.getNickname();
    }
}
