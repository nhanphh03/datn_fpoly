package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Chi_Tiet_Giay")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ChiTietGiay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Chi_Tiet_Giay")
    private UUID idCTG;

    @ManyToOne
    @JoinColumn(name = "id_Size")
    @NotNull
    private Size size;

    @ManyToOne
    @JoinColumn(name = "id_Giay")
    @NotNull
    private Giay giay;

    @ManyToOne
    @JoinColumn(name = "id_Hinh_Anh")
    @NotNull
    private HinhAnh hinhAnh;

    @ManyToOne
    @JoinColumn(name = "id_Mau")
    @NotNull
    private MauSac mauSac;

    @Min(value = 1)
    @Column(name = "nam_San_Xuat")
    private int namSX;

    @Min(value = 1)
    @Column(name = "nam_Bao_Hanh")
    private int namBH;

    @Min(value = 1)
    @Column(name = "trong_Luong")
    private int trongLuong;

    @Min(value = 1)
    @Column(name = "gia_Ban")
    private double giaBan;

    @Min(value = 0)
    @Column(name = "so_Luong")
    private int soLuong;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;

    @Column(name = "ma_NV_Sua")
    private String maNVSua;

    @Column(name="so_tien_truoc_khi_giam")
    private Double soTienTruocKhiGiam;

    @Column(name = "LD_Sua")
    private String lyDoSua;

    @Column(name = "barCode")
    private String barcode;

    public String getFormattedGiaBan() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VNĐ");
        return decimalFormat.format(giaBan);
    }
}
