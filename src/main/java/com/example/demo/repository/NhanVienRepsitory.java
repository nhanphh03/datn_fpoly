package com.example.demo.repository;

import com.example.demo.model.ChucVu;
import com.example.demo.model.KhachHang;
import com.example.demo.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface NhanVienRepsitory extends JpaRepository<NhanVien, UUID> {

    NhanVien findByEmailNVAndMatKhauAndTrangThai(String email, String  pass, int trangThai);

    NhanVien findBySdtNVAndMatKhauAndTrangThai(String sdt, String  pass, int trangThai);

    List<NhanVien> findByChucVu(ChucVu chucVu);
    List<NhanVien> findByTrangThai(int trangThai);
    List<NhanVien> findByMaNVOrHoTenNV(String maNV, String tenNV);

    NhanVien findByMaNV(String maNV);
    NhanVien findByEmailNV(String Email);
    NhanVien findByCCCDNV(String CCCDNV);
    NhanVien findBySdtNV(String sdtNV);

    @Query(value = "select nv from NhanVien nv order by nv.tgThem desc")
    List<NhanVien> getAllNhanVien();

}
