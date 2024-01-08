package com.example.SpringVersion.user.service;

import com.example.SpringVersion.global.exception.RequestException;
import com.example.SpringVersion.user.entity.User;
import com.example.SpringVersion.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.SpringVersion.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserValidator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void validateAccess(User user , User other){
        if(!user.getId().equals(other.getId())){
            throw new RequestException(NOT_VALID_ACCESS);
        }
    }
    public void validateEmail(String email){
        userRepository.findByEmail(email)
                .ifPresent( m -> {
                    throw new RequestException(DUPLICATED_EMAIL);
                });
    }
    public void validateMatchPassword(String storedPassword, String password){
        if(!passwordEncoder.matches(storedPassword, password)){
            throw new RequestException(NOT_VALID_PASSWORD);
        }
    }
    public void validateNickname(String username){
        if(userRepository.existsByUsername(username)){
            throw new RequestException(DUPLICATED_NICKNAME);
        }
    }

    public boolean validateExistNickname(User user){
        boolean isExistNickname = true;
        if(user.getUsername()==null){
            isExistNickname = false;
        }
        return isExistNickname;
    }
}
