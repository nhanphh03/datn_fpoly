package com.example.demo.viewModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class CTHDViewModel {
    @Column(name = "gia_ban")
    private Double giaBan;
    @Column(name = "ten_giay")
    private String tenGiay;
    @Column(name = "don_gia")
    private Double donGia;
    @Column(name = "so_luong")
    private Integer soLuong;
    @Column(name = "url1")
    private String url1;
    @Column(name = "url1")
    private String ten;
    private String sdt;
    private String diaChi;





}
