package com.example.demo.service.impls;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhuyenMai;
import com.example.demo.model.KhuyenMaiChiTietHoaDon;
import com.example.demo.repository.KMCTHDRepository;
import com.example.demo.service.KhuyenMaiChiTietHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class KhuyenMaiChiTietHoaDonServiceImpl implements KhuyenMaiChiTietHoaDonService {

    @Autowired
    private KMCTHDRepository kmcthdRepository;

    @Override
    public List<KhuyenMaiChiTietHoaDon> khuyenMaiChiTietHoaDonList() {
        return kmcthdRepository.findAll();
    }

    @Override
    public List<KhuyenMaiChiTietHoaDon> listKhuyenMaiChiTietHoaDonByTrangThai(int trangThai) {
        return kmcthdRepository.findByTrangThai(trangThai);
    }

    @Override
    public List<KhuyenMaiChiTietHoaDon> listKhuyenMaiChiTietHoaDonByHoaDon(HoaDon hoaDon) {
        return kmcthdRepository.findByHoaDon(hoaDon);
    }

    @Override
    public KhuyenMaiChiTietHoaDon addKMCTHD(KhuyenMaiChiTietHoaDon khuyenMaiChiTietHoaDon) {
        return kmcthdRepository.save(khuyenMaiChiTietHoaDon);
    }

    @Override
    public KhuyenMaiChiTietHoaDon findByHoaDonAndKhuyenMai(HoaDon hoaDon, KhuyenMai khuyenMai) {
        return kmcthdRepository.findByKhuyenMaiAndAndHoaDon(khuyenMai, hoaDon);
    }

    @Override
    public KhuyenMaiChiTietHoaDon findByID(UUID idKMCTHD) {
        return kmcthdRepository.findById(idKMCTHD).orElse(null);
    }
}
