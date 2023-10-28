package com.example.demo.repository;

import com.example.demo.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {

    KhachHang findByEmailKHAndTrangThaiAndMatKhau(String email,int trangThai, String pass);

    KhachHang findBySdtKHAndTrangThaiAndMatKhau(String sdt,int trangThai, String pass);

    KhachHang findByEmailKH(String email);

    @Query(value = "select * from khach_hang where trang_thai = 1",nativeQuery = true)
    List<KhachHang> getKhachHangByTrangThai();

    @Query(value = "select * from khach_hang where trang_thai=1 and ho_ten_kh like %?1% or sdt_kh like %?1%",nativeQuery = true)
    List<KhachHang> findByHoTenKHOrSdtKH(String keyword);
}
