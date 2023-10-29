package com.example.demo.repository;
import com.example.demo.model.ChucVu;
import com.example.demo.model.KhachHang;
import com.example.demo.model.LoaiKhachHang;
import com.example.demo.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {

    KhachHang findByEmailKHAndTrangThaiAndMatKhau(String email,int trangThai, String pass);

    KhachHang findBySdtKHAndTrangThaiAndMatKhau(String sdt,int trangThai, String pass);

    KhachHang findByEmailKH(String email);

    List<KhachHang> findByLoaiKhachHang(LoaiKhachHang loaiKhachHang);
    List<KhachHang> findByTrangThai(int trangThai);
    List<KhachHang> findByMaKHOrHoTenKH(String maKH, String tenKH);
    KhachHang findByHoTenKH(String name);

}
