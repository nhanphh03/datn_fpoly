package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "KhachHang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name ="id_KH")
    private UUID idKH;

    @ManyToOne
    @JoinColumn(name = "id_LKH")
    private LoaiKhachHang loaiKhachHang;

    @Column(name = "ma_Khach_Hang")
    private String maKH;

    @Column(name = "ho_Ten_KH")
    private String hoTenKH;

    @Column(name = "gioi_Tinh")
    private int gioiTinh;

    @Column(name = "ngay_Sinh")
    private Date ngaySinh;

    @Column(name = "SDT_KH")
    private String sdtKH;

    @Column(name = "Anh_KH")
    private String AnhKH;

    @Column(name = "Email_KH")
    private String emailKH;

    @Column(name = "diaChi_KH")
    private String diaChi;

    @Column(name = "mat_Khau_KH")
    private String matKhau;

    @Column(name = "CCCD_KH")
    private int CCCDKH;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;









}
