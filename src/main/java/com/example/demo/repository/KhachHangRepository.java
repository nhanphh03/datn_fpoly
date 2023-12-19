package com.example.demo.repository;
import com.example.demo.model.ChucVu;
import com.example.demo.model.KhachHang;
import com.example.demo.model.LoaiKhachHang;
import com.example.demo.model.NhanVien;
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


    List<KhachHang> findByLoaiKhachHang(LoaiKhachHang loaiKhachHang);
    List<KhachHang> findByTrangThai(int trangThai);
    List<KhachHang> findByMaKHOrHoTenKH(String maKH, String tenKH);
    KhachHang findByHoTenKH(String name);
    KhachHang findByMaKH(String maKH);
    KhachHang findByCCCDKH(String CCCDKH);
    KhachHang findBySdtKH(String sdtKH);



    @Query(value = "select kh from KhachHang kh order by kh.tgThem desc")
    List<KhachHang> getAllKhachHang();

    @Query(value = "select * from khach_hang where trang_thai = 1",nativeQuery = true)
    List<KhachHang> getKhachHangByTrangThai();

    @Query(value = "select * from khach_hang where trang_thai=1 and ho_ten_kh like %?1% or sdt_kh like %?1%",nativeQuery = true)
    List<KhachHang> findByHoTenKHOrSdtKH(String keyword);

    @Query(value = "select count(id_kh) from khach_hang" ,nativeQuery = true)
    Integer getTongKH();

    boolean existsBySdtKH(String sdtKH);
}
