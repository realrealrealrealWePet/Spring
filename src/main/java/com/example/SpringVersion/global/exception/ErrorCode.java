package com.example.SpringVersion.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    COMMON_BAD_REQUEST_400(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    COMMON_INTERNAL_ERROR_500(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생하였습니다."),

    // JWT 관련
    JWT_BAD_TOKEN_401(HttpStatus.UNAUTHORIZED, "Token이 유효하지 않습니다."),

    // 회원가입 관련
    LOGINID_DUPLICATION_409(HttpStatus.CONFLICT, "이미 가입된 회원입니다."),


    // 로그인 관련
    USER_NOT_FOUND_404(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    PASSWORD_NOT_ACCEPTABLE_406(HttpStatus.NOT_ACCEPTABLE, "비밀번호 형식이 맞지 않습니다."),
    PASSWORD_NOT_MATCH_406(HttpStatus.NOT_ACCEPTABLE, "비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    EMAIL_NOT_ACCEPTABLE_406(HttpStatus.NOT_ACCEPTABLE, "이메일 형식이 맞지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
