package com.jats.savy.savy.exception.error;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    private final ErrorCode errorCode;

    public GlobalException(ErrorCode code) {
        super(code.getMessage());
        this.errorCode = code;
    }
}
