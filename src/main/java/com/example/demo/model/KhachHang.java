package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @OneToOne
    @JoinColumn(name = "id_GH")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "id_LKH")
    private LoaiKhachHang loaiKhachHang;

    @NotEmpty(message = "Mã KH không được trống")
    @Column(name = "ma_Khach_Hang")
    private String maKH;

    @NotEmpty(message = "Tên KH không được trống")
    @Size(max = 255, message = "Tên KH không được quá 255 ký tự")
    @Column(name = "ho_Ten_KH")
    private String hoTenKH;

    @Column(name = "gioi_Tinh")
    private int gioiTinh;

    @Column(name = "ngay_Sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    private String CCCDKH;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tgThem;

    @Column(name = "tg_Sua")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tgSua;









}
