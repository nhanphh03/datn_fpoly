package com.example.demo.repository;

import com.example.demo.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {

    KhachHang findByEmailKHAndTrangThaiAndMatKhau(String email,int trangThai, String pass);

    KhachHang findBySdtKHAndTrangThaiAndMatKhau(String sdt,int trangThai, String pass);

    KhachHang findByEmailKH(String email);
}
