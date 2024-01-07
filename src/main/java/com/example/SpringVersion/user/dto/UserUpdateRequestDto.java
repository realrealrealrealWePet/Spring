package com.example.SpringVersion.user.dto;

import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class UserUpdateRequestDto {
    @Size(min = 1, max = 6, message = "회원 이름은 1글자 이상 6글자 이하 입니다.")
    String username;
}
