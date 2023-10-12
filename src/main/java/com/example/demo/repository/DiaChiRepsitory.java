package com.example.demo.repository;

import com.example.demo.model.DiaChiKH;
import com.example.demo.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiaChiRepsitory extends JpaRepository<DiaChiKH, UUID>{
//    List<DiaChiKH> findByTrangThaiAndKhachHang(int trangthai, KhachHang khachHang);
    List<DiaChiKH> findByTrangThai(int trangthai);
}
