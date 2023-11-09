package com.example.demo.service;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhachHang;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HoaDonService {
    List<HoaDon> getListHoaDonChuaThanhToan();
    void add(HoaDon hoaDon);
    HoaDon getOne(UUID id);
    List<HoaDon> getAllHoaDon();

    List<HoaDon> findByKhachHang(KhachHang khachHang);
}
