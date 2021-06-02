package com.jats.savy.savy.entity.admin;

import com.jats.savy.savy.entity.room.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "admin_tbl")
public class Admin {

    @Id
    private String code;

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private List<Room> rooms;

}
