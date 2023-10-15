package com.example.demo.repository;

import com.example.demo.model.Giay;
import com.example.demo.model.KhachHang;
import com.example.demo.model.LuotXemFA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface LuotXemFARepository extends JpaRepository<LuotXemFA, UUID> {

    List<LuotXemFA> findByKhachHangAndTrangThaiAndLoaiOrderByTgThemDesc(KhachHang khachHang, Integer trangThai, Integer loai);

    LuotXemFA findByKhachHangAndGiayAndTrangThaiAndLoai(KhachHang khachHang, Giay giay, int trangThai, int loai);
}
