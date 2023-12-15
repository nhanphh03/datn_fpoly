package com.example.demo.viewModel;

import com.example.demo.model.KhuyenMai;
import com.example.demo.model.KhuyenMaiChiTietCTG;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class CTGViewModel {
    @Id
    @Column(name = "id_giay")
    private UUID idGiay;

    @Column(name = "id_mau")
    private UUID idMau;

    @Column(name = "min_price_truoc_giam")
    private Double minPriceTruocGiam;

    @Column(name = "min_price")
    private Double minPrice;

    @Column(name = "ten_giay")
    private String tenGiay;

    @Column(name = "ten_mau")
    private String tenMau;

    @Column(name = "slTon")
    private Long slTon;

    @Column(name = "url1")
    private String hinhAnh;

    @Column(name = "so_Luong_Da_Ban")
    private Long soLuongDaBan;

    @Column(name = "ten_KM")
    private String tenKM;

    @Column(name = "tg_NhapHang")
    private Date tgNhapHang;


    public CTGViewModel(UUID idMau,UUID idGiay, Double minPriceTruocGiam, Double minPrice, String tenGiay, String tenMau, Long slTon, String hinhAnh, Long soLuongDaBan, String tenKM, Date tgNhapHang) {
        this.idMau = idMau;
        this.idGiay = idGiay;
        this.minPriceTruocGiam = minPriceTruocGiam.doubleValue();
        this.minPrice = minPrice.doubleValue();
        this.tenGiay = tenGiay;
        this.tenMau = tenMau;
        this.slTon = slTon;
        this.hinhAnh = hinhAnh;
        this.soLuongDaBan = soLuongDaBan;
        this.tenKM= tenKM;
        this.tgNhapHang = tgNhapHang;
    }

}




