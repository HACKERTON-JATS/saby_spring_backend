package com.jats.savy.savy.exception;

import com.jats.savy.savy.exception.error.ErrorCode;
import com.jats.savy.savy.exception.error.GlobalException;

public class InvalidTokenException extends GlobalException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
