package com.uu.spring.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    @Getter
    private ErrorCode errorCode = ErrorCode.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException of(String message) {
        return new NotFoundException(message);
    }
}
