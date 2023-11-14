package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Giay")
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
    @NotNull
    private Hang hang;

    @ManyToOne
    @JoinColumn(name = "id_ChatLieu")
    @NotNull
    private ChatLieu chatLieu;

    @NotBlank
    @Column(name = "ma_Giay")
    private String maGiay;

    @NotBlank
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

    @Column(name = "tg_NhapHang")
    private Date namNhapHang;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "giay")
    private List<ChiTietGiay> chiTietGiayList;

}
