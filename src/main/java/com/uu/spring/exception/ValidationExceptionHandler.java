package com.uu.spring.exception;

import com.uu.spring.common.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<CommonResponse> handleConflict(ValidationException ex, WebRequest request) {
        return new ResponseEntity<>(CommonResponse.builder()
                .errorCode(ex.getErrorCode().value)
                .message(ex.getMessage())
                .build(),
                HttpStatus.BAD_REQUEST);
    }
}
