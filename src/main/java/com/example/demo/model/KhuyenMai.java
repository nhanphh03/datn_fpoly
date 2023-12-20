package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "Khuyen_Mai")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class KhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Khuyen_Mai")
    private UUID idKM;

    @ManyToOne
    @JoinColumn(name = "id_LKM")
    private LoaiKhuyenMai loaiKhuyenMai;

    @OneToMany(mappedBy = "khuyenMai")
    private List<KhuyenMaiChiTietCTG> khuyenMaiChiTietCTGList;

    @OneToMany(mappedBy = "khuyenMai")
    private List<KhuyenMaiChiTietHoaDon> khuyenMaiChiTietHoaDons;

    @Column(name = "ma_KM")
    private String maKM;

    @Column(name = "ten_KM")
    private String tenKM;

    @Column(name = "tg_Bat_Dau")
    private Date tgBatDau;

    @Column(name = "tg_Ket_Thuc")
    private Date tgKetThuc;

    @Column(name = "mo_Ta")
    private String moTa;

    @Column(name = "so_Luong")
    private int soLuong;

    @Column(name = "so_Luong_Da_Dung")
    private int soLuongDaDung;

    @Column(name = "phan_Tram_Giam")
    private double phanTramGiam;

    @Column(name = "gia_Tien_Giam_Toi_Da")
    private double giaTienGiamToiDaPT;

    @Column(name = "gia_Tien_Giam")
    private double giaTienGiam;

    @Column(name = "dk_KM")
    private Double dieuKienKMBill;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "ten_NV_Sua")
    private String tenNV;

    @Column(name = "Ly_Do_Sua")
    private String lyDoSua;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;

    @Column(name = "loai_Giam")
    private boolean loaiGiam;

}
