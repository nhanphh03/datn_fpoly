package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Email;
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
    @NotNull
    @JoinColumn( name = "id_Chuc_Vu")
    private ChucVu chucVu;

    @NotBlank
    @Column(name = "ma_NV")
    private String maNV;

    @NotBlank
    @Column(name = "ho_Ten_NV")
    private String hoTenNV;

    @NotBlank
    @Column(name = "mk_NV")
    private String matKhau;

    @Column(name = "gioi_Tinh")
    private int gioiTinh;

    @NotNull
    @Column(name = "ngay_Sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    @NotBlank
    @Pattern(regexp = "0\\d{9}")
    @Column(name = "SDT_NV")
    private String sdtNV;

    @Column(name = "Anh_NV")
    private String AnhNV;

    @NotBlank
    @Email
    @Pattern(regexp = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b")
    @Column(name = "Email_NV")
    private String emailNV;

    @NotBlank
    @Column(name = "diaChi_NV")
    private String diaChi;

    @NotBlank
    @Pattern(regexp = "^(\\d{6})(\\d{6})$")
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
