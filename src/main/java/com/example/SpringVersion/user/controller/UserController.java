package com.example.SpringVersion.user.controller;

import com.example.SpringVersion.global.response.ResponseMessage;
import com.example.SpringVersion.global.security.UserDetailsImpl;
import com.example.SpringVersion.user.dto.*;
import com.example.SpringVersion.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/auth/signup")
    public ResponseEntity<ResponseMessage<Void>> signUp(@RequestBody UserRequestDto request){
        userService.signUp(request);
        return new ResponseEntity<>(new ResponseMessage<>("회원가입 성공",null), HttpStatus.CREATED); //(상태 코드 201) 이 응답은 새로운 리소스가 성공적으로 생성되었음을 나타냅니다. 주로 POST 메서드를 통한 리소스 생성 요청에 대한 응답으로 사용
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ResponseMessage>login(@RequestBody LoginRequestDto request, HttpServletResponse response){
        return userService.login(request,response);
    }

    @PostMapping("/auth/token")
    public ResponseEntity<ResponseMessage<Void>> reissueToken(HttpServletRequest request, HttpServletResponse response){
        userService.reissueToken(request, response);
        return new ResponseEntity<>(new ResponseMessage<>("토큰 재발행 성공", null), HttpStatus.ACCEPTED); //(상태 코드 202) 이 응답은 요청이 성공적으로 처리되었으며, 결과가 아직 즉시 사용 가능하지 않음을 나타냅니다. 주로 비동기 작업을 나타낼 때 사용됩니다. 클라이언트는 나중에 상태를 확인하거나 추가적인 작업을 수행할 필요가 있다.
    }

    @GetMapping("/profile")
    public UserResponseDto getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.getProfile(userDetails.getUser());
    }

    @DeleteMapping("/deleteuser")
    public ResponseEntity<ResponseMessage> deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.deleteUser(userDetails.getUser());
    }

    @PutMapping("/nickname")
    public ResponseEntity<ResponseMessage> updateNickname(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserUpdateRequestDto userUpdateRequestDto){
        return userService.updateNickname(userDetails.getUser(),userUpdateRequestDto);
    }

}

