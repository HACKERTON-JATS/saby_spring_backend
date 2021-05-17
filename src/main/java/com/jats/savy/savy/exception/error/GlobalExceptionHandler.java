package com.jats.savy.savy.exception.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(GlobalException e) {
        final ErrorCode code = e.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(code.getCode(), code.getMessage()),
                HttpStatus.valueOf(code.getCode()));
    }

}
