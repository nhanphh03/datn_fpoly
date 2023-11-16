package com.example.demo.service;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhuyenMai;
import com.example.demo.model.KhuyenMaiChiTietHoaDon;

import java.util.List;
import java.util.UUID;

public interface KhuyenMaiChiTietHoaDonService {

    List<KhuyenMaiChiTietHoaDon> khuyenMaiChiTietHoaDonList();

    List<KhuyenMaiChiTietHoaDon> listKhuyenMaiChiTietHoaDonByTrangThai(int trangThai);

    List<KhuyenMaiChiTietHoaDon> listKhuyenMaiChiTietHoaDonByHoaDon(HoaDon hoaDon);

    KhuyenMaiChiTietHoaDon addKMCTHD(KhuyenMaiChiTietHoaDon khuyenMaiChiTietHoaDon);

    KhuyenMaiChiTietHoaDon findByHoaDonAndKhuyenMai(HoaDon hoaDon, KhuyenMai khuyenMai);

    KhuyenMaiChiTietHoaDon findByID(UUID idKMCTHD);
}
