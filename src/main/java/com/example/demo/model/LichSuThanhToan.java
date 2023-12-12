package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Thanh_Toan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LichSuThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idHTTT")
    private UUID idHTTT;

    @Column(name="maLSTT")
    private String maLSTT;

    @ManyToOne
    @JoinColumn(name="idKH")
    private KhachHang khachHang;

    @Column(name = "tgThanhToan")
    private Date tgThanhToan;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "loai_thanh_toan")
    private int loaiTT;

    @Column(name="noiDungThanhToan")
    private String noiDungThanhToan;

    @Column(name = "soTienTT")
    private Double soTienThanhToan;

    @ManyToOne
    @JoinColumn(name="idHD")
    private HoaDon hoaDon;
}
