package com.jats.savy.savy.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(404, "user not found"),
    INVALID_TOKEN(401, "invalid token");

    private final Integer code;
    private final String message;
}
