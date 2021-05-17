package com.jats.savy.savy.payload.response;

import com.jats.savy.savy.entity.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd`T`hh:mm:SS")
    private LocalDateTime time;

    private boolean isTake;

    private boolean isReservation;

    public static ReservationResponse of(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .time(reservation.getTime())
                .isTake(reservation.getIsTake())
                .isReservation(reservation.getIsReservation())
                .build();
    }

}
