package com.example.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Dia_Chi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiaChiKH {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name ="id_Dia_Chi")
    private UUID idDC;

    @ManyToOne
    @JoinColumn(name = "id_KH")
    private KhachHang khachHang;

    @Column(name = "ma_Dia_Chi")
    private String maDC;

    @Column(name ="sdt_Nguoi_Nhan")
    private String sdtNguoiNhan;

    @Column(name="ten_Dia_Chi")
    private String tenDC;

    @Column(name="ten_Nguoi_Nhan")
    private String tenNguoiNhan;

    @Column(name="xa_Phuong")
    private String xaPhuong;

    @Column(name="quan_Huyen")
    private String quanHuyen;

    @Column(name="tinh_TP")
    private String tinhTP;

    @Column(name="mo_Ta")
    private String moTa;

    @Column(name= "dia_Chi_Chi_Tiet")
    private String diaChiChiTiet;

    @Column( name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;

    @Column(name = "mien")
    private String mien;

    @Column(name="loai_DC")
    private boolean loai;

    @Override
    public String toString() {
        return "DiaChiKH{" +
                "idDC=" + idDC +
                ", khachHang=" + khachHang +
                ", maDC='" + maDC + '\'' +
                ", sdtNguoiNhan='" + sdtNguoiNhan + '\'' +
                ", tenDC='" + tenDC + '\'' +
                ", tenNguoiNhan='" + tenNguoiNhan + '\'' +
                ", xaPhuong='" + xaPhuong + '\'' +
                ", quanHuyen='" + quanHuyen + '\'' +
                ", tinhTP='" + tinhTP + '\'' +
                ", moTa='" + moTa + '\'' +
                ", diaChiChiTiet='" + diaChiChiTiet + '\'' +
                ", trangThai=" + trangThai +
                ", tgThem=" + tgThem +
                ", tgSua=" + tgSua +
                ", mien='" + mien + '\'' +
                ", loai=" + loai +
                '}';
    }
}
