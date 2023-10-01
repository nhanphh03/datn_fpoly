package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Size")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Size {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO )
    @Column(name ="id_Size")
    private UUID idSize;

    @Column(name = "ma_Size")
    private String maSize;

    @Column(name="ten_Size")
    private String tenSize;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;
}
