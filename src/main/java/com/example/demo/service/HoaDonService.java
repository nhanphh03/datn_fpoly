package com.example.demo.service;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhachHang;
import com.example.demo.model.KhuyenMai;
import com.example.demo.model.NhanVien;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HoaDonService {
    List<HoaDon> getListHoaDonChuaThanhToan();

    void add(HoaDon hoaDon);

    HoaDon getOne(UUID id);

    List<HoaDon> getAllHoaDon();

    List<HoaDon> listAllHoaDonKhachHangOnline(KhachHang khachHang);

    List<HoaDon> listHoaDonKhachHangAndTrangThaiOnline(KhachHang khachHang, int trangThai);

    List<HoaDon> listHoaDonKhachHangAndTrangThaiOnlineAndLoaiThanhToan(KhachHang khachHang, int trangThai, int loaiThanhToan);

    List<HoaDon> findHoaDonByKhachHang(KhachHang khachHang);

    Double tinhTienGiamGiaTaiQuay(HoaDon hoaDon, KhuyenMai khuyenMai);

    Boolean checkHoaDonGiamGia(HoaDon hoaDon, KhuyenMai khuyenMai);

    void saveKhuyenMai(KhuyenMai khuyenMai, HoaDon hoaDon, Double soTienGiam);

    List<HoaDon>  getAllHoaDonOffLine();

    List<HoaDon>  listHoaDonOnline();

    List<HoaDon>  listAllHoaDonOnline();

    List<HoaDon>  listHoaDonOnlineAndTrangThai(int trangThai);

    List<HoaDon>  listHoaDonOnlineGiaoHang(int trangThai1, int trangThai2);

    List<HoaDon> listHoaDonOnlineAndHTTT(int httt);

    List<HoaDon> listHoaDonByNhanVienAndTrangThai(NhanVien nhanVien, int trangThai);

    List<HoaDon> listHoaDonHuyAndThanhCongByNhanVien(NhanVien nhanVien);

    List<HoaDon> listAllHoaDonByNhanVien(NhanVien nhanVien);

    List<HoaDon> listAllHoaDonByNhanVienHienTai(NhanVien nhanVien);

    List<HoaDon> listHoaDonOnlineAndHTTTAndTrangThai(int httt, int trangThai);

    HoaDon findByIdHoaDonOld(UUID idHDOld);
}
