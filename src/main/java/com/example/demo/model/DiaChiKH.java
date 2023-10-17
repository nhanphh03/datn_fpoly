package com.example.demo.model;


import jakarta.persistence.*;
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
}
