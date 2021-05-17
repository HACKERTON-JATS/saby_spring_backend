package com.jats.savy.savy.exception;

import com.jats.savy.savy.exception.error.ErrorCode;
import com.jats.savy.savy.exception.error.GlobalException;

public class ReservationNotFoundException extends GlobalException {
    public ReservationNotFoundException() {
        super(ErrorCode.RESERVATION_NOT_FOUND);
    }
}
