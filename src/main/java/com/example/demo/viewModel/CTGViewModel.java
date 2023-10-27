package com.example.demo.viewModel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "min_price")
    private Double minPrice;

    @Column(name = "ten_giay")
    private String tenGiay;

    @Column(name = "slTon")
    private Long slTon;

    @Column(name = "url1")
    private String hinhAnh;

    @Column(name = "so_Luong_Da_Ban")
    private Long soLuongDaBan;

    public CTGViewModel(UUID idMau,UUID idGiay, Double minPrice, String tenGiay, Long slTon, String hinhAnh, Long soLuongDaBan) {
        this.idMau = idMau;
        this.idGiay = idGiay;
        this.minPrice = minPrice.doubleValue();
        this.tenGiay = tenGiay;
        this.slTon = slTon;
        this.hinhAnh = hinhAnh;
        this.soLuongDaBan = soLuongDaBan;
    }

}




