package com.example.demo.service.impls;

import com.example.demo.model.*;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.KMCTHDRepository;
import com.example.demo.repository.KhuyenMaiRepository;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private KMCTHDRepository kmcthdRepository;

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    @Override
    public List<HoaDon> getListHoaDonChuaThanhToan() {
        return hoaDonRepository.listChuaThanhToan();
    }

    @Override
    public void add(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon getOne(UUID id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.findAllByOrderByTgTaoDesc();
    }

    @Override
    public List<HoaDon> listAllHoaDonKhachHangOnline(KhachHang khachHang) {
        return hoaDonRepository.findByKhachHangAndLoaiHDAndTrangThaiOrTrangThaiOrTrangThaiOrTrangThaiOrTrangThaiOrTrangThaiOrderByTgTaoDesc(khachHang, 0, 0, 1, 2, 3, 4, 5);
    }

    @Override
    public List<HoaDon> listHoaDonKhachHangAndTrangThaiOnline(KhachHang khachHang, int trangThai) {
        return hoaDonRepository.findByKhachHangAndLoaiHDAndTrangThaiOrderByTgTaoDesc(khachHang, 0 , trangThai);
    }

    @Override
    public List<HoaDon> listHoaDonKhachHangAndTrangThaiOnlineAndLoaiThanhToan(KhachHang khachHang, int trangThai, int loaiThanhToan) {
        return hoaDonRepository.findByKhachHangAndLoaiHDAndTrangThaiAndHinhThucThanhToanOrderByTgTaoDesc(khachHang, 0, trangThai, loaiThanhToan);
    }

    @Override
    public List<HoaDon> findHoaDonByKhachHang(KhachHang khachHang) {
        return hoaDonRepository.findByKhachHangAndLoaiHDOrderByTgTaoDesc(khachHang, 0);
    }

    @Override
    public Double tinhTienGiamGiaTaiQuay(HoaDon hoaDon, KhuyenMai khuyenMai) {


        Double tienGiamGiaTaiQuay = 0.0;

        Boolean loaiGiamGia = khuyenMai.isLoaiGiam();
        Double totalPrice = hoaDon.getTongTien();

        if (loaiGiamGia == false){
            Double soTienGiam = totalPrice * khuyenMai.getPhanTramGiam();
            if (soTienGiam < khuyenMai.getGiaTienGiamToiDaPT()){
                soTienGiam = tienGiamGiaTaiQuay;
            }else{
                tienGiamGiaTaiQuay = khuyenMai.getGiaTienGiamToiDaPT();
            }
        }else{
            tienGiamGiaTaiQuay = khuyenMai.getGiaTienGiam();
        }

        return tienGiamGiaTaiQuay;
    }

    @Override
    public Boolean checkHoaDonGiamGia(HoaDon hoaDon, KhuyenMai khuyenMai) {

        Boolean dieuKien = false;
        Double totalPrice = hoaDon.getTongTien();

        if (totalPrice > khuyenMai.getDieuKienKMBill()){
            dieuKien = true;
        }
        return dieuKien;
    }

    @Override
    public void saveKhuyenMai(KhuyenMai khuyenMai, HoaDon hoaDon, Double soTienGiam) {
        KhuyenMaiChiTietHoaDon khuyenMaiChiTietHoaDon = new KhuyenMaiChiTietHoaDon();
        khuyenMaiChiTietHoaDon.setKhuyenMai(khuyenMai);
        khuyenMaiChiTietHoaDon.setHoaDon(hoaDon);
        khuyenMaiChiTietHoaDon.setSoTienGiam(soTienGiam);
        kmcthdRepository.save(khuyenMaiChiTietHoaDon);

        khuyenMai.setSoLuongDaDung(khuyenMai.getSoLuongDaDung() + 1);
        khuyenMaiRepository.save(khuyenMai);

    }

    @Override
    public List<HoaDon> getAllHoaDonOffLine() {
        return hoaDonRepository.findHoaDonByLoaiHDOrderByTgTaoDesc(1);
    }

    @Override
    public List<HoaDon> listHoaDonOnline() {
        return hoaDonRepository.findByLoaiHDOrderByTgTaoDesc(0);
    }

    @Override
    public List<HoaDon> listAllHoaDonOnline() {
        return hoaDonRepository.findByLoaiHDAndTrangThaiOrTrangThaiOrTrangThaiOrTrangThaiOrTrangThaiOrTrangThaiOrderByTgTaoDesc(0, 0, 1 ,2 ,3 ,4 ,5 );
    }

    @Override
    public List<HoaDon> listHoaDonOnlineAndTrangThai(int trangThai) {
        return hoaDonRepository.findByLoaiHDAndTrangThaiOrderByTgTaoDesc(0, trangThai);
    }

    @Override
    public List<HoaDon> listHoaDonOnlineGiaoHang(int trangThai1, int trangThai2) {
        return hoaDonRepository.findByLoaiHDAndTrangThaiOrTrangThaiOrderByTgTaoDesc(0, 1, 2);
    }

    @Override
    public List<HoaDon> listHoaDonOnlineAndHTTT(int httt) {
        return hoaDonRepository.findByLoaiHDAndHinhThucThanhToanOrderByTgTaoDesc(0, httt);
    }

    @Override
    public List<HoaDon> listHoaDonByNhanVienAndTrangThai(NhanVien nhanVien, int trangThai) {
        return hoaDonRepository.findByNhanVienAndLoaiHDAndTrangThaiOrderByTgTaoDesc(nhanVien, 0, trangThai);
    }

    @Override
    public List<HoaDon> listHoaDonHuyAndThanhCongByNhanVien(NhanVien nhanVien) {
        return hoaDonRepository.findByNhanVienAndLoaiHDAndTrangThaiOrTrangThaiOrderByTgTaoDesc(nhanVien,  0 , 3,4);
    }

    @Override
    public List<HoaDon> listAllHoaDonByNhanVien(NhanVien nhanVien) {
        return hoaDonRepository.findByNhanVienOrderByTgTaoDesc(nhanVien);
    }

    @Override
    public List<HoaDon> listHoaDonOnlineAndHTTTAndTrangThai(int httt, int trangThai) {
        return hoaDonRepository.findByLoaiHDAndTrangThaiAndHinhThucThanhToanOrderByTgTaoDesc(0, trangThai, httt);
    }

    @Override
    public HoaDon findByIdHoaDonOld(UUID idHDOld) {
        return hoaDonRepository.findByIdHDOld(idHDOld);
    }


}
