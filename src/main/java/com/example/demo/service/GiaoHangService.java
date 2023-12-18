package com.example.demo.service;

import com.example.demo.model.GiaoHang;
import com.example.demo.model.HoaDon;

import java.util.List;

public interface GiaoHangService {

    List<GiaoHang> listGiaoHangByHoaDon(HoaDon hoaDon);

    List<GiaoHang> findByHoaDonAndTrangThai(HoaDon hoaDon, int trangThai);

    void saveGiaoHang(GiaoHang giaoHang);

    List<GiaoHang> getAllGiaoHangs();
}