package com.example.demo.service;

import com.example.demo.model.GiaoHang;
import com.example.demo.model.ViTriDonHang;

import java.util.List;

public interface ViTriDonHangServices {

    List<ViTriDonHang> getAll();
    List<ViTriDonHang> findByGiaoHang(GiaoHang giaoHang);
    void addViTriDonHang(ViTriDonHang viTriDonHang);

}
