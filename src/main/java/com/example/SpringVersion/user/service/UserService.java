package com.example.SpringVersion.user.service;

import com.example.SpringVersion.global.exception.ErrorCode;
import com.example.SpringVersion.global.exception.RequestException;
import com.example.SpringVersion.global.jwt.JwtTokenProvider;
import com.example.SpringVersion.user.dto.UserRequestDto;
import com.example.SpringVersion.user.dto.UserResponseDto;
import com.example.SpringVersion.user.entity.User;
import com.example.SpringVersion.user.repository.AuthRepository;
import com.example.SpringVersion.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public UserResponseDto register(UserRequestDto userRequestDto) {

        Optional<User> found = userRepository.findByEmail(userRequestDto.getEmail());
        if (found.isPresent()) {
            throw new RequestException(ErrorCode.LOGINID_DUPLICATION_409);
        }
        Optional<User> foundByNickname = userRepository.findByNickname(userRequestDto.getNickname());
        if (foundByNickname.isPresent()) {
            throw new RequestException(ErrorCode.DUPLICATE_NICKNAME);
        }
        User user = User.builder()
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .name(userRequestDto.getName())
                .nickname(userRequestDto.getNickname())
                .gender(userRequestDto.getGender())
                .birthday(userRequestDto.getBirthday())
                .phoneNumber(userRequestDto.getPhoneNumber())
                .build();
        userRepository.save(user);

        return new UserResponseDto(user);

    }
}
