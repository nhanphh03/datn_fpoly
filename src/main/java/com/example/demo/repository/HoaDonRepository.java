package com.example.demo.repository;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon , UUID> {
    @Query(value = "select * from Hoa_Don where trang_thai = 0 and loai_hd = 1 ",nativeQuery = true)
    List<HoaDon> listChuaThanhToan();

    List<HoaDon> findAllByOrderByTgTaoDesc();

    List<HoaDon> findByKhachHangAndLoaiHD(KhachHang khachHang, int loaiHD);

    @Query(value = "select COUNT(id_hd)\n" +
            "from hoa_don where trang_thai =0",nativeQuery = true)
    Integer getAllHoaDonCho();

    @Query(value = "select COUNT(id_hd)\n" +
            "from hoa_don where trang_thai =1 ",nativeQuery = true)
    Integer getAllHoaDonDaThanhToan();

    List<HoaDon> findByKhachHangAndLoaiHDAndTrangThaiOrderByTgTaoDesc(KhachHang khachHang,int loaiHD, int trangThai);

    List<HoaDon> findByKhachHangAndLoaiHDAndTrangThaiAndHinhThucThanhToanOrderByTgTaoDesc(KhachHang khachHang, int loaiHD, int trangThai, int hinhThucThanhToan);

    List<HoaDon> findByLoaiHDAndTrangThaiOrTrangThaiOrTrangThaiOrTrangThaiOrTrangThaiOrTrangThaiOrderByTgTaoDesc(int loaiHD, int trangThai1, int trangThai2, int trangThai3, int trangThai4, int trangThai5, int trangThai6);

    List<HoaDon> findByKhachHangAndLoaiHDAndTrangThaiOrTrangThaiOrTrangThaiOrTrangThaiOrTrangThaiOrTrangThaiOrderByTgTaoDesc(KhachHang khachHang,int loaiHD, int trangThai1, int trangThai2, int trangThai3, int trangThai4, int trangThai5, int trangThai6);

    List<HoaDon> findHoaDonByLoaiHDOrderByTgTaoDesc(int loaiHoaDon);

    List<HoaDon> findByLoaiHDAndTrangThaiOrTrangThaiOrderByTgTaoDesc(int loaiHD, int trangThai1, int trangThai2);

    List<HoaDon> findByLoaiHDOrderByTgTaoDesc(int loaiHD);

    List<HoaDon> findByLoaiHDAndTrangThaiOrderByTgTaoDesc(int loaiHD, int trangThai);

    List<HoaDon> findByLoaiHDAndHinhThucThanhToan(int loaiHD, int httt);

    List<HoaDon> findByLoaiHDAndTrangThaiAndHinhThucThanhToan(int loaiHD, int trangThai, int httt);

}
