package com.example.demo.service.impls;

import com.example.demo.model.HoaDon;
import com.example.demo.model.PhieuTraHang;
import com.example.demo.repository.PhieuTraHangRepository;
import com.example.demo.service.PhieuTraHangServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhieuTraHangServiceImpls implements PhieuTraHangServices {

    @Autowired
    private PhieuTraHangRepository phieuTraHangRepository;

    @Override
    public List<PhieuTraHang> getAll() {
        return phieuTraHangRepository.findAll();
    }

    @Override
    public void savePTH(PhieuTraHang phieuTraHang) {
        phieuTraHangRepository.save(phieuTraHang);
    }

    @Override
    public PhieuTraHang findByHoaDon(HoaDon hoaDon) {
        return phieuTraHangRepository.findByHoaDon(hoaDon);
    }
}
