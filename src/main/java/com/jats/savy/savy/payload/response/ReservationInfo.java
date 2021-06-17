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
public class ReservationInfo {

    @DateTimeFormat(pattern = "yyyy-MM-dd`T`hh:mm:SS")
    private LocalDateTime time;

    private boolean isReservation;

    private boolean isTake;

    private LocalDateTime birthDate;

    private String kidName;

    private String fetusName;

    private String request;

    private String caution;

    private String giveLater;

    public static ReservationInfo of(Reservation reservation) {
        return ReservationInfo.builder()
                .isReservation(reservation.getIsReservation())
                .birthDate(reservation.getKidInformation().getBirthDate())
                .fetusName(reservation.getKidInformation().getFetusName())
                .time(reservation.getTime())
                .isTake(reservation.getIsTake())
                .kidName(reservation.getKidInformation().getKidName())
                .request(reservation.getKidInformation().getRequest())
                .caution(reservation.getKidInformation().getCaution())
                .giveLater(reservation.getKidInformation().getGiveLater())
                .build();
    }

}
