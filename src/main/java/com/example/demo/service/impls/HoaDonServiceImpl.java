package com.example.demo.service.impls;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhachHang;
import com.example.demo.repository.HoaDonRepository;
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
    public List<HoaDon> findByKhachHang(KhachHang khachHang) {
        return hoaDonRepository.findByKhachHangAndLoaiHD(khachHang, 0);
    }
}
