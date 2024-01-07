package com.example.SpringVersion.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestException extends RuntimeException{
    private ErrorCode errorCode;
}
