package com.example.demo.repository;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.GioHang;
import com.example.demo.model.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GHCTRepository extends JpaRepository<GioHangChiTiet, UUID> {

    GioHangChiTiet findByChiTietGiayAndTrangThai(ChiTietGiay chiTietGiay, int trangThai);

    GioHangChiTiet findByChiTietGiay(ChiTietGiay chiTietGiay);

    List<GioHangChiTiet> findByTrangThaiAndGioHangOrderByTgThemDesc(int trangThai, GioHang gh);

    GioHangChiTiet findByChiTietGiayAndTrangThaiAndGioHang(ChiTietGiay chiTietGiay, int trangThai, GioHang gioHang);
}
