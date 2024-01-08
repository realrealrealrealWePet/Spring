package com.example.SpringVersion.user.dto;

import com.example.SpringVersion.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserUpdateRequestDto {
    @Size(min = 1, max = 6, message = "회원 이름은 1글자 이상 6글자 이하 입니다.")
    String nickname;

    public UserUpdateRequestDto(User user){
        this.nickname = user.getNickname();
    }
}
