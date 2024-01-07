package com.example.SpringVersion.global.security;

import com.example.SpringVersion.global.exception.RequestException;
import com.example.SpringVersion.user.entity.User;
import com.example.SpringVersion.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.SpringVersion.global.exception.ErrorCode.NOT_FOUND_MEMBER;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RequestException(NOT_FOUND_MEMBER)
        );
        return new UserDetailsImpl(user, user.getUsername());
    }
}