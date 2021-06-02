package com.jats.savy.savy.entity.reservation;

import com.jats.savy.savy.entity.kidinformation.KidInformation;
import com.jats.savy.savy.entity.user.User;
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
@Table(name = "reservation_tbl")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`hh:mm:SS")
    private LocalDateTime time;

    @Column(nullable = false)
    private Boolean isTake;

    @Column(nullable = false)
    private Boolean isReservation;

    @OneToOne(mappedBy = "reservation", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private KidInformation kidInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
