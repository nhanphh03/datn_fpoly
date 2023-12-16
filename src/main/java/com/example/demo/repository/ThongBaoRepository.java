package com.example.demo.repository;

import com.example.demo.model.KhachHang;
import com.example.demo.model.ThongBaoKhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ThongBaoRepository extends JpaRepository<ThongBaoKhachHang, UUID> {

    List<ThongBaoKhachHang> findAllByOrderByTgTBDesc();

    List<ThongBaoKhachHang> findByKhachHangOrderByTgTBDesc(KhachHang khachHang);
}
