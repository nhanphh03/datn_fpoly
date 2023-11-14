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
@Table(name = "KhachHang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_KH")
    private UUID idKH;

    @OneToOne
    @JoinColumn(name = "id_GH")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "id_LKH")
    @NotNull
    private LoaiKhachHang loaiKhachHang;

    @NotBlank
    @Column(name = "ma_Khach_Hang")
    private String maKH;

    @NotBlank
    @Column(name = "ho_Ten_KH")
    private String hoTenKH;

    @Column(name = "gioi_Tinh")
    private int gioiTinh;

    @NotNull
    @Column(name = "ngay_Sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    @NotBlank
    @Pattern(regexp = "0\\d{9}")
    @Column(name = "SDT_KH")
    private String sdtKH;

    @Column(name = "Anh_KH")
    private String AnhKH;

    @NotBlank
    @Email
    @Pattern(regexp = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b")
    @Column(name = "Email_KH")
    private String emailKH;

    @NotBlank
    @Column(name = "diaChi_KH")
    private String diaChi;

    @NotBlank
    @Column(name = "mat_Khau_KH")
    private String matKhau;

    @Column(name = "anhKH_cccd")
    private String Anhcccd;

    @NotBlank
    @Pattern(regexp = "^(\\d{6})(\\d{6})$")
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
