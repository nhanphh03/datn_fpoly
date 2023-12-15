package com.example.demo.service;

import com.example.demo.model.DanhGiaKhachHang;
import com.example.demo.model.Giay;
import com.example.demo.model.HoaDon;

import java.util.List;

public interface DanhGiaServices {

    void addDanhGia(DanhGiaKhachHang danhGiaKhachHang);

    List<DanhGiaKhachHang> findByGiay(Giay giay);
    DanhGiaKhachHang findByHoaDon(HoaDon hoaDon);
}
