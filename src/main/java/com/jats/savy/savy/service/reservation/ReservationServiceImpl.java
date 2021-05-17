package com.jats.savy.savy.service.reservation;

import com.jats.savy.savy.entity.kidinformation.KidInformationRepository;
import com.jats.savy.savy.entity.reservation.Reservation;
import com.jats.savy.savy.entity.reservation.ReservationRepository;
import com.jats.savy.savy.exception.ReservationNotFoundException;
import com.jats.savy.savy.payload.response.ReservationInfo;
import com.jats.savy.savy.payload.response.ReservationList;
import com.jats.savy.savy.payload.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public ReservationList getReservationList(Pageable pageable) {
        Page<Reservation> reservations = reservationRepository.findAllBy(pageable);

        return ReservationList.builder()
                .reservationResponses(reservations.getContent().stream().map(ReservationResponse::of).collect(Collectors.toList()))
                .totalPages(reservations.getTotalPages())
                .build();
    }

    @Override
    public ReservationInfo getReservationInfo(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .map(ReservationInfo::of)
                .orElseThrow(ReservationNotFoundException::new);
    }
}