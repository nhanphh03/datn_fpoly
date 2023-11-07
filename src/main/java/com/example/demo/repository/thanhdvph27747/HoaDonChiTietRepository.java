package com.example.demo.repository.thanhdvph27747;

import com.example.demo.model.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<com.example.demo.model.HoaDonChiTiet, UUID> {
    @Query("select g from HoaDonChiTiet g where g.hoaDon.trangThai = 1 OR g.hoaDon.trangThai=2")
    List<HoaDonChiTiet> getAllHoaDonDaThanhToan();

    @Query("select g from HoaDonChiTiet g where g.hoaDon.trangThai = 4 OR g.hoaDon.trangThai=3")
    List<HoaDonChiTiet> getAllHoaDonChoThanhToan();

//    select g from HoaDonChiTiet g where g.hoaDon.trangThai = 4 OR g.hoaDon.trangThai=3
}
