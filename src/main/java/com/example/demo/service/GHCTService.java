package com.example.demo.service;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.GioHang;
import com.example.demo.model.GioHangChiTiet;
import com.example.demo.model.KhachHang;

import java.util.List;
import java.util.UUID;

public interface GHCTService {

    List<GioHangChiTiet> findByGHActive(GioHang gioHang);

    void addNewGHCT(GioHangChiTiet gioHangChiTiet);

    GioHangChiTiet findByCTSPActive(ChiTietGiay chiTietGiay);

    GioHangChiTiet findByCTSP(ChiTietGiay chiTietGiay);

    GioHangChiTiet findByCTGActiveAndKhachHangAndTrangThai(ChiTietGiay chiTietGiay, GioHang gioHang);




}
