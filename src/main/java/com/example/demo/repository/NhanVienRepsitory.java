package com.example.demo.repository;

import com.example.demo.model.ChucVu;
import com.example.demo.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface NhanVienRepsitory extends JpaRepository<NhanVien, UUID> {

    NhanVien findByEmailNVAndMatKhauAndChucVuAndTrangThai(String email, String  pass, ChucVu chucVu, int trangThai);

    NhanVien findBySdtNVAndMatKhauAndChucVuAndTrangThai(String sdt, String  pass, ChucVu chucVu, int trangThai);

    List<NhanVien> findByChucVu(ChucVu chucVu);
    List<NhanVien> findByTrangThai(int trangThai);
    List<NhanVien> findByMaNVOrHoTenNV(String maNV, String tenNV);
}
