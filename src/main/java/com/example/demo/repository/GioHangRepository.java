package com.example.demo.repository;

import com.example.demo.model.GioHang;
import com.example.demo.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, UUID> {

    GioHang findByKhachHang(KhachHang kh);




}
