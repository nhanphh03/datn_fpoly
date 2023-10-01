package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Hoa_Don_Chi_Tiet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_HDCT")
    private UUID idHDCT;

    @ManyToOne
    @JoinColumn(name = "id_CTG")
    private ChiTietGiay chiTietGiay;

    @ManyToOne
    @JoinColumn(name = "id_HD")
    private HoaDon hoaDon;

    @Column(name = "don_Gia")
    private double donGia;

    @Column(name = "so_Luong")
    private int soLuong;

    @Column(name = "don_Gia_Khi_Giam")
    private double donGiaKhiGiam;

    @Column(name = "so_Tien_Giam")
    private double soTienGiam;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;


}
