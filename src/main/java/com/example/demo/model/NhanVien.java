package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    @Column(name = "ma_NV")
    private String maNV;

    @NotBlank
    @Column(name = "mk_NV")
    private String matKhau;

    @NotBlank
    @Column(name = "ho_Ten_NV")
    private String hoTenNV;

    @Column(name = "gioi_Tinh")
    private int gioiTinh;

    @NotNull
    @Column(name = "ngay_Sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    @NotBlank
    @Column(name = "SDT_NV")
    private String sdtNV;

    @NotBlank
    @Column(name = "Anh_NV")
    private String AnhNV;

    @NotBlank
    @Column(name = "Email_NV")
    private String emailNV;

    @NotBlank
    @Column(name = "diaChi_NV")
    private String diaChi;

    @NotBlank
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
