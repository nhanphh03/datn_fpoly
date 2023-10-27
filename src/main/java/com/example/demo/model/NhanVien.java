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
@Table(name= "Nhan_Vien")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Nhan_Vien")
    private UUID idNV;

    @ManyToOne
    @JoinColumn( name = "id_Chuc_Vu")
    private ChucVu chucVu;

    @NotEmpty(message = "Mã NV không được trống")
    @Column(name = "ma_NV")
    private String maNV;

    @Column(name = "mk_NV")
    private String matKhau;

    @NotEmpty(message = "Tên NV không được trống")
    @Size(max = 255, message = "Tên NV không được quá 255 ký tự")
    @Column(name = "ho_Ten_NV")
    private String hoTenNV;

    @Column(name = "gioi_Tinh")
    private int gioiTinh;

    @Column(name = "ngay_Sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    @Column(name = "SDT_NV")
    private String sdtNV;

    @Column(name = "Anh_NV")
    private String AnhNV;

    @Column(name = "Email_NV")
    private String emailNV;

    @Column(name = "diaChi_NV")
    private String diaChi;

    @Column(name = "CCCD_NV")
    private String CCCDNV;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tgThem;

    @Column(name = "tg_Sua")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tgSua;



}
