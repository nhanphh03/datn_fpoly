package com.example.demo.service;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhachHang;
import com.example.demo.model.KhuyenMai;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HoaDonService {
    List<HoaDon> getListHoaDonChuaThanhToan();
    void add(HoaDon hoaDon);
    HoaDon getOne(UUID id);
    List<HoaDon> getAllHoaDon();

    List<HoaDon> findByKhachHang(KhachHang khachHang);

    Double tinhTienGiamGiaTaiQuay(HoaDon hoaDon, KhuyenMai khuyenMai);

    Boolean checkHoaDonGiamGia(HoaDon hoaDon, KhuyenMai khuyenMai);

    void saveKhuyenMai(KhuyenMai khuyenMai, HoaDon hoaDon, Double soTienGiam);
}
