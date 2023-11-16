package com.example.demo.repository;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhuyenMai;
import com.example.demo.model.KhuyenMaiChiTietHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface KMCTHDRepository extends JpaRepository<KhuyenMaiChiTietHoaDon, UUID> {

    List<KhuyenMaiChiTietHoaDon> findByTrangThai(int trangThai);

    List<KhuyenMaiChiTietHoaDon> findByHoaDon(HoaDon hoaDon);

    KhuyenMaiChiTietHoaDon findByKhuyenMaiAndAndHoaDon(KhuyenMai khuyenMai, HoaDon hoaDon);

}
