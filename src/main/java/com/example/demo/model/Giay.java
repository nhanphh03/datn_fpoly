package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Giay")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Giay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Giay")
    private UUID idGiay;

    @ManyToOne
    @JoinColumn(name = "id_Hang")
    private Hang hang;

    @ManyToOne
    @JoinColumn(name = "id_ChatLieu")
    private ChatLieu chatLieu;

    @Column(name = "ma_Giay")
    private String maGiay;

    @Column(name = "ten_Giay")
    private String tenGiay;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;


}
