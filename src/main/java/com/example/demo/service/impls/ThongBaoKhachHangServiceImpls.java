package com.example.demo.service.impls;

import com.example.demo.model.KhachHang;
import com.example.demo.model.ThongBaoKhachHang;
import com.example.demo.repository.ThongBaoRepository;
import com.example.demo.service.ThongBaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThongBaoKhachHangServiceImpls implements ThongBaoServices {

    @Autowired
    private ThongBaoRepository thongBaoRepository;

    @Override
    public void addThongBao(ThongBaoKhachHang thongBaoKhachHang) {
        thongBaoRepository.save(thongBaoKhachHang);
    }

    @Override
    public List<ThongBaoKhachHang> getAll() {
        return thongBaoRepository.findAllByOrderByTgTBDesc();
    }

    @Override
    public List<ThongBaoKhachHang> findByKhachHang(KhachHang khachHang) {
        return thongBaoRepository.findByKhachHangOrderByTgTBDesc(khachHang);
    }
}
