package com.uu.spring.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    @Getter
    private ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

    public UnauthorizedException(String message) {
        super(message);
    }

    public static UnauthorizedException of(String message) {
        return new UnauthorizedException(message);
    }
}