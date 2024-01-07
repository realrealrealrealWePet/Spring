package com.example.SpringVersion.user.dto;

import com.example.SpringVersion.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String email;
    private final String username;
    private final String nickname;
    private final String gender;
    private final String birthday;
    private final String phoneNumber;


    @Builder
    public UserResponseDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.gender = user.getGender();
        this.birthday = user.getBirthday();
        this.phoneNumber = user.getPhoneNumber();
    }

    public static UserResponseDto from(User user){
        return UserResponseDto.builder()
                .user(user)
                .build();
    }

}
