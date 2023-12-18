package com.example.demo.service.impls;

import com.example.demo.model.GiaoHang;
import com.example.demo.model.HoaDon;
import com.example.demo.repository.GiaoHangRepository;
import com.example.demo.service.GiaoHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiaoHangServiceImpls implements GiaoHangService {

    @Autowired
    private GiaoHangRepository giaoHangRepsitory;

    @Override
    public List<GiaoHang> listGiaoHangByHoaDon(HoaDon hoaDon) {
        return giaoHangRepsitory.findByHoaDonOrderByThoiGianDesc(hoaDon);
    }

    @Override
    public List<GiaoHang> findByHoaDonAndTrangThai(HoaDon hoaDon, int trangThai) {
        return giaoHangRepsitory.findByHoaDonAndTrangThaiOrderByThoiGianDesc(hoaDon, trangThai);
    }

    @Override
    public void saveGiaoHang(GiaoHang giaoHang) {
        giaoHangRepsitory.save(giaoHang);
    }

    @Override
    public List<GiaoHang> getAllGiaoHangs() {
        return giaoHangRepsitory.findAll();
    }
}
