package com.jats.savy.savy.controller;

import com.jats.savy.savy.payload.response.ReservationInfo;
import com.jats.savy.savy.payload.response.ReservationList;
import com.jats.savy.savy.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public ReservationList getReservationList(Pageable pageable) {
        return reservationService.getReservationList(pageable);
    }

    @GetMapping("/{reservationId}")
    public ReservationInfo getReservationInfo(@PathVariable Long reservationId) {
        return reservationService.getReservationInfo(reservationId);
    }

    @PatchMapping("/{reservationId}")
    public void updateIsTaken(@PathVariable Long reservationId) {
        reservationService.updateIsTaken(reservationId);
    }
}
