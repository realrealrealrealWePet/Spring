package com.example.SpringVersion.user.dto;

import com.example.SpringVersion.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String email;
    private final String name;
    private final String nickname;
    private final String gender;
    private final String birthday;
    private final String phoneNumber;



    public UserResponseDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.gender = user.getGender();
        this.birthday = user.getBirthday();
        this.phoneNumber = user.getPhoneNumber();
    }
}
