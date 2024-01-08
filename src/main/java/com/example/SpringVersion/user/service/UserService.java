package com.example.SpringVersion.user.service;

import com.example.SpringVersion.global.exception.ErrorCode;
import com.example.SpringVersion.global.exception.RequestException;
import com.example.SpringVersion.global.jwt.JwtTokenProvider;
import com.example.SpringVersion.global.response.ResponseMessage;
import com.example.SpringVersion.user.dto.LoginRequestDto;
import com.example.SpringVersion.user.dto.UserRequestDto;
import com.example.SpringVersion.user.dto.UserResponseDto;
import com.example.SpringVersion.user.entity.User;
import com.example.SpringVersion.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.SpringVersion.global.exception.ErrorCode.NOT_FOUND_MEMBER;
import static com.example.SpringVersion.global.exception.ErrorCode.NOT_VALID_PASSWORD;
import static com.example.SpringVersion.global.jwt.JwtTokenProvider.AUTHORIZATION_ACCESS;
import static com.example.SpringVersion.global.jwt.JwtTokenProvider.AUTHORIZATION_REFRESH;

@Service
@Slf4j
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signUp(UserRequestDto request) {
        userValidator.validateEmail(request.getEmail());
        String password = passwordEncoder.encode(request.getPassword());
        User user = request.toEntity(password);
        userRepository.save(user);
    }

    //    @Transactional
//    public UserLoginResponseDto login(LoginRequestDto request, HttpServletResponse response) {
//
//        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
//                () -> new RequestException(NOT_FOUND_MEMBER));
//        userValidator.validateMatchPassword(request.getPassword(), user.getPassword());
//        issueTokens(response, user.getEmail());
//        boolean isExistUsername = userValidator.validateExistUsername(user);
//        return new UserLoginResponseDto(isExistUsername);
//    }
    public ResponseEntity<ResponseMessage> login(LoginRequestDto request, HttpServletResponse response) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new RequestException(NOT_FOUND_MEMBER));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RequestException(NOT_VALID_PASSWORD); // 해당 이메일의 비밀번호가 일치하지 않음
        }
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail());
        response.addHeader(JwtTokenProvider.AUTHORIZATION_ACCESS, accessToken);
        return ResponseEntity.ok(new ResponseMessage("로그인 성공", HttpStatus.OK.value()));

    }


    @Transactional
    public void issueTokens(HttpServletResponse response, String email) {
        String accessToken = jwtTokenProvider.createAccessToken(email);
        String refreshToken = jwtTokenProvider.createRefreshToken();
        response.addHeader(AUTHORIZATION_ACCESS, accessToken);
        response.addHeader(AUTHORIZATION_REFRESH, refreshToken);
        System.out.println(accessToken);
    }

    @Transactional
    public void reissueToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshTokenFromRequest = request.getHeader(AUTHORIZATION_REFRESH); //요청헤더에서 온 RTK
        String token = jwtTokenProvider.resolveToken(request, AUTHORIZATION_ACCESS); //요청헤더에서 온 ATK(bearer 제외)
        Claims info = jwtTokenProvider.getUserInfoFromToken(token, true); //ATK에서 body가지고 옴
        String email = info.getSubject(); //가지고온 body에서 subject 빼오기 = email
        jwtTokenProvider.validateRefreshToken(request, email);
        issueTokens(response, email);
        jwtTokenProvider.validateRefreshToken(request, email);
        issueTokens(response, email);
    }

    public UserResponseDto getProfile(User user) {
        return new UserResponseDto(user);
    }

//    public UserUpdateResponseDto updateNickname(User user, UserUpdateRequestDto userUpdateRequestDto) {
//        if (requestDto.getNickname() != null) {
//            userValidator.validateNickname(requestDto.getNickname());
//            user.setNickname(requestDto.getNickname());
//        }
//
//        userValidator.validateNickname(user.getNickname());
//        System.out.println(user.getNickname()+"2222");
//        System.out.println(userUpdateRequestDto.getNickname()+"11111111111111");
//        return new UserUpdateResponseDto(user);
//    }

    @Transactional
    public ResponseEntity<ResponseMessage> deleteUser(User user) {

        user = userRepository.findById(user.getId()).orElseThrow(() -> new RequestException(ErrorCode.NOT_FOUND_MEMBER));
        user.deleteUser();
        userRepository.delete(user);
        return ResponseEntity.ok(new ResponseMessage("회원탈퇴", HttpStatus.OK.value()));
    }
}