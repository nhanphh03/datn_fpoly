package com.example.demo.service;

import com.example.demo.model.HoaDon;
import com.example.demo.model.PhieuTraHang;

import java.util.List;

public interface PhieuTraHangServices {

    List<PhieuTraHang> getAll();

    void savePTH(PhieuTraHang phieuTraHang);

    PhieuTraHang findByHoaDon(HoaDon hoaDon);



}
