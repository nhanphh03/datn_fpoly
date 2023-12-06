package com.example.demo.repository;

import com.example.demo.model.ChucVu;
import com.example.demo.model.DiaChiKH;
import com.example.demo.model.KhachHang;
import com.example.demo.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiaChiRepsitory extends JpaRepository<DiaChiKH, UUID>{
    List<DiaChiKH> findByTrangThaiAndKhachHang(int trangthai, KhachHang khachHang);
    List<DiaChiKH> findByTrangThai(int trangthai);
    List<DiaChiKH> findByKhachHang(KhachHang khachHang);

    List<DiaChiKH> findByKhachHangAndLoaiAndTrangThai(KhachHang khachHang, boolean loai, int trangThai);

    List<DiaChiKH> findByMaDCOrTenDC(String maDC, String tenDC);

    DiaChiKH findByKhachHangAndLoai(KhachHang khachHang, boolean loai);

    List<DiaChiKH> findByKhachHangAndTrangThai(KhachHang khachHang, Integer trangThai);

    DiaChiKH findByMaDC(String maDC);

    @Query(value = "select dc from DiaChiKH dc order by dc.tgThem desc")
    List<DiaChiKH> getAllDiaChi();
}
