package com.example.SpringVersion.user.service;

import com.example.SpringVersion.global.exception.ErrorCode;
import com.example.SpringVersion.global.exception.RequestException;
import com.example.SpringVersion.global.jwt.JwtTokenProvider;
import com.example.SpringVersion.user.dto.LoginRequestDto;
import com.example.SpringVersion.user.dto.UserRequestDto;
import com.example.SpringVersion.user.dto.UserResponseDto;
import com.example.SpringVersion.user.dto.UserTokenResponseDto;
import com.example.SpringVersion.user.entity.Auth;
import com.example.SpringVersion.user.entity.User;
import com.example.SpringVersion.user.repository.AuthRepository;
import com.example.SpringVersion.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
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

    @Transactional
    public UserTokenResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) throws Exception {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new RequestException(ErrorCode.USER_NOT_FOUND_404));

        if (!isPasswordValid(loginRequestDto.getPassword(), user.getPassword())) {
            throw new RequestException(ErrorCode.INVALID_PASSWORD_401);
        }

        String accessToken = jwtTokenProvider.createAccessToken(loginRequestDto.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken(loginRequestDto.getEmail());

        Auth auth = authRepository.findByUserId(user.getId());
        if (auth == null) {
            auth = Auth.builder()
                    .user(user)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

            authRepository.save(auth);
            System.out.println("첫 로그인");
        } else {
            auth.accessUpdate(accessToken);
            auth.refreshUpdate(refreshToken);
            authRepository.save(auth);
        }

        response.addHeader("ACCESS_TOKEN", accessToken);
        response.addHeader("REFRESH_TOKEN", refreshToken);

        return new UserTokenResponseDto(user, accessToken, refreshToken);
    }

    private boolean isPasswordValid(String rawPassword, String encodedPassword) {
        // 비밀번호가 BCrypt 형식으로 인코딩되었는지 확인
        if (!encodedPassword.startsWith("$2a$")) {
            // 인코딩되지 않은 경우, 평문 비밀번호와 저장된 비밀번호를 직접 비교
            return rawPassword.equals(encodedPassword);
        }

        // BCrypt 형식으로 인코딩된 경우, BCryptPasswordEncoder를 사용하여 비밀번호 비교
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}