package com.jats.savy.savy.entity.kidinformation;

import com.jats.savy.savy.entity.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "kid_information_tbl")
public class KidInformation {

    @Id
    private Long reservationId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`hh:mm:SS")
    private LocalDateTime birthDate;

    @Column(nullable = false)
    private String kidName;

    @Column(nullable = false)
    private String vaccination;

    private String fetusName;

    private String request;

    private String caution;

    private String giveLater;

}
