package com.jats.savy.savy.entity.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Page<Reservation> findAllByOrderByTimeDesc(Pageable pageable);
}
