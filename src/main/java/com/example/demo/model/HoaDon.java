package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
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

    @OneToMany(mappedBy = "hoaDon")
    private List<HoaDonChiTiet> hoaDonChiTiets;

    @OneToMany(mappedBy = "hoaDon")
    private List<GiaoHang> giaoHangs;

    @ManyToOne
    @JoinColumn(name = "id_NV")
    private NhanVien nhanVien;

    @Column(name = "ma_HD")
    private String maHD;

    @Column(name="tong_Tien")
    private Double tongTien;

    @Column(name="tong_SP")
    private Integer tongSP;

    @Column(name = "tien_Ship")
    private Double tienShip;

    @Column(name ="giam_Gia_Ship")
    private Double giamGiaShip;

    @Column(name = "giam_gia_hd")
    private Double giamGiaHoaDon;

    @Column(name ="tong_Tien_Da_Giam")
    private Double tongTienDG;

    @Column(name = "tg_Tao")
    private Date tgTao;

    @Column(name = "tg_ThanhToan")
    private Date tgThanhToan;

    @Column(name = "tg_Ship")
    private Date tgShip;

    @Column(name = "tg_Nhan")
    private Date tgNhan;

    @Column(name = "tg_Huy")
    private Date tgHuy;

    @Column(name = "ly_do_huy")
    private String lyDoHuy;

    @Column(name = "tg_Nhan_DK")
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

    @Column(name="hinh_Thuc_Thanh_Toan")
    private Integer hinhThucThanhToan;

    @Column(name = "loi_nhan")
    private String loiNhan;

    @Column(name = "id_HDOld")
    private UUID idHDOld;

    @Column(name = "ma_HDOld")
    private String maHDOld;

    @Column(name = "trang_Thai_Hoan")
    private int trangThaiHoan;

}
