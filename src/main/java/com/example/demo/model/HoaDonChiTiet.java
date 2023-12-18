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
    private Double donGia;

    @Column(name = "so_Luong")
    private int soLuong;

    @Column(name = "don_Gia_Truoc_Khi_Giam")
    private Double donGiaTruocGiam;

    @Column(name = "so_Tien_Giam")
    private Double soTienGiam;

    @Column(name = "trang_Thai")
    private Integer trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;

    @Column(name = "mo_ta")
    private String moTa;


}
