package com.jats.savy.savy.exception;

import com.jats.savy.savy.exception.error.ErrorCode;
import com.jats.savy.savy.exception.error.GlobalException;

public class UserNotFoundException extends GlobalException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
