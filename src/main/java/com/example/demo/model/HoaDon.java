package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Hoa_Don")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_HD")
    private UUID idHD;

    @ManyToOne
    @JoinColumn(name = "id_KH")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "id_NV")
    private NhanVien nhanVien;

    @OneToOne
    @JoinColumn(name = "id_KM")
    private KhuyenMai khuyenMai;

    @Column(name = "ma_HD")
    private String maHD;

    @Column(name="tong_Tien")
    private Double tongTien;

    @Column(name="tong_SP")
    private Integer tongSP;

    @Column(name = "tien_Ship")
    private Double tienShip;

    @Column(name ="tong_Tien_Da_Giam")
    private Double tongTienDG;

    @Column(name = "tg_Tao")
    private Date tgTao;

    @Column(name = "tg_ThanhToan")
    private Date tgThanhToan;

    @Column(name = "tg_Ship")
    private Date tgShip;

    @Column(name = "tg_Nhan")
    private Date tgNhanDK;

    @Column(name = "ten_Nguoi_Nhan")
    private String tenNguoiNhan;

    @Column(name = "dia_Chi_Nguoi_Nhan")
    private String diaChiNguoiNhan;

    @Column(name = "sdt_Nguoi_Nhan")
    private String sdtNguoiNhan;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name="loai_HD")
    private Integer loaiHD;

}
