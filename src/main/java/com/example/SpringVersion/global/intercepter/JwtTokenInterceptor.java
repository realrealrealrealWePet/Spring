package com.example.SpringVersion.global.intercepter;

import com.example.SpringVersion.global.exception.ErrorCode;
import com.example.SpringVersion.global.exception.RequestException;
import com.example.SpringVersion.global.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtTokenInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        System.out.println("JwtToken 호출");
        String accessToken = jwtTokenProvider.resolveAccessToken(request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        if (jwtTokenProvider.isValidAccessToken(accessToken) && jwtTokenProvider.isValidRefreshToken(refreshToken)) {
            Authentication auth = jwtTokenProvider.getAuthentication(accessToken);    // 인증 객체 생성
            SecurityContextHolder.getContext().setAuthentication(auth); // SecurityContextHolder에 인증 객체 저장
            return true;
        }else{
            throw new RequestException(ErrorCode.JWT_BAD_TOKEN_401);
        }
    }

}