package com.example.SpringVersion.user.dto;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private Boolean isExistUsername;

    public UserLoginResponseDto(Boolean isExistUsername){
        this.isExistUsername = isExistUsername;
    }
}
