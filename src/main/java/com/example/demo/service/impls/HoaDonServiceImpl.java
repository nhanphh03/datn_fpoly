package com.example.demo.service.impls;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhachHang;
import com.example.demo.model.KhuyenMai;
import com.example.demo.model.KhuyenMaiChiTietHoaDon;
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
    public List<HoaDon> findByKhachHang(KhachHang khachHang) {
        return hoaDonRepository.findByKhachHangAndLoaiHD(khachHang, 0);
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
}
