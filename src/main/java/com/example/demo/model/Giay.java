package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
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

    @NotEmpty(message = "Mã giày không được trống")
    @Column(name = "ma_Giay")
    private String maGiay;

    @NotEmpty(message = "Tên giày không được trống")
    @Size(max = 255, message = "Tên giày không được quá 255 ký tự")
    @Column(name = "ten_Giay")
    private String tenGiay;

    @Column(name = "mo_Ta")
    private String moTa;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "giay")
    private List<ChiTietGiay> chiTietGiayList;

}
