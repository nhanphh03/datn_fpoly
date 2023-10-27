package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Chi_Tiet_Giay")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ChiTietGiay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Chi_Tiet_Giay")
    private UUID idCTG;

    @ManyToOne
    @JoinColumn(name = "id_Size")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "id_Giay")
    private Giay giay;

    @ManyToOne
    @JoinColumn(name = "id_HinhAnh")
    private HinhAnh hinhAnh;

    @ManyToOne
    @JoinColumn(name = "id_Mau")
    private MauSac mauSac;

    @Column(name = "nam_San_Xuat")
    private int namSX;

    @Column(name = "nam_Bao_Hanh")
    private int namBH;

    @Column(name = "trong_Luong")
    private int trongLuong;

    @Column(name = "gia_Ban")
    private double giaBan;

    @Column(name = "so_Luong")
    private int soLuong;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;

    @Column(name = "ma_NV_Sua")
    private String maNVSua;

    @Column(name = "LD_Sua")
    private String lyDoSua;

    @Column(name = "barCode")
    private String barcode;
}
