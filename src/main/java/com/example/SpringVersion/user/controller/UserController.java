package com.example.SpringVersion.user.controller;

import com.example.SpringVersion.user.dto.LoginRequestDto;
import com.example.SpringVersion.user.dto.UserRequestDto;
import com.example.SpringVersion.user.dto.UserResponseDto;
import com.example.SpringVersion.user.dto.UserTokenResponseDto;
import com.example.SpringVersion.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody @Valid UserRequestDto userRequestDto) {
        return userService.register(userRequestDto);
    }

    // 로그인
    @PostMapping("/login")
    public UserTokenResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) throws Exception {
        return userService.login(loginRequestDto,response);
    }

}
