package com.example.demo.viewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CTSPViewModel {
    private UUID idChiTietGiay;
    private String maGiay;
    private String tenGiay;
    private String hinhAnh;
    private String mauSac;
    private int size;
    private String hang;
    private int soLuong;
    private double giaBan;
}
