package com.example.demo.repository;

import com.example.demo.model.GiaoHang;
import com.example.demo.model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GiaoHangRepository extends JpaRepository<GiaoHang, UUID> {

    List<GiaoHang> findByHoaDonAndTrangThaiOrderByThoiGianDesc(HoaDon hoaDon, int trangThai);

    List<GiaoHang> findByHoaDonOrderByThoiGianDesc(HoaDon hoaDon);
}
