package com.jats.savy.savy.entity.user;

import com.jats.savy.savy.entity.chat.Chat;
import com.jats.savy.savy.entity.reservation.Reservation;
import com.jats.savy.savy.entity.room.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_tbl")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String userId;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String password;

    @Column(length = 8)
    private String nickname;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
    private Room room;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Reservation> reservations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Chat> chats;

}
