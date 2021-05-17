package com.jats.savy.savy.service.reservation;

import com.jats.savy.savy.payload.response.ReservationInfo;
import com.jats.savy.savy.payload.response.ReservationList;
import org.springframework.data.domain.Pageable;

public interface ReservationService {
    ReservationList getReservationList(Pageable pageable);

    ReservationInfo getReservationInfo(Long reservationId);
}
