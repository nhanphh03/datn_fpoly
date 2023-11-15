package com.example.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Column(name = "ma_Dia_Chi")
    private String maDC;

    @NotBlank
    @Pattern(regexp = "0\\d{9}")
    @Column(name ="sdt_Nguoi_Nhan")
    private String sdtNguoiNhan;

    @NotBlank
    @Column(name="ten_Dia_Chi")
    private String tenDC;

    @NotBlank
    @Column(name="ten_Nguoi_Nhan")
    private String tenNguoiNhan;

    @NotBlank
    @Column(name="xa_Phuong")
    private String xaPhuong;

    @NotBlank
    @Column(name="quan_Huyen")
    private String quanHuyen;

    @NotBlank
    @Column(name="tinh_TP")
    private String tinhTP;

    @NotBlank
    @Column(name="mo_Ta")
    private String moTa;

    @NotBlank
    @Column(name= "dia_Chi_Chi_Tiet")
    private String diaChiChiTiet;


    @Column( name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;

    @NotBlank
    @Column(name = "mien")
    private String mien;

    @NotBlank
    @Column(name="loai_DC")
    private boolean loai;
}
