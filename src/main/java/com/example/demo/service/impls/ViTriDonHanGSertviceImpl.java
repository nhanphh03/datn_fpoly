package com.example.demo.service.impls;

import com.example.demo.model.GiaoHang;
import com.example.demo.model.ViTriDonHang;
import com.example.demo.repository.ViTriDonHangRepository;
import com.example.demo.service.ViTriDonHangServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViTriDonHanGSertviceImpl implements ViTriDonHangServices {

    @Autowired
    private ViTriDonHangRepository viTriDonHangRepository;


    @Override
    public List<ViTriDonHang> getAll() {
        return viTriDonHangRepository.findAll();
    }

    @Override
    public List<ViTriDonHang> findByGiaoHang(GiaoHang giaoHang) {
        return viTriDonHangRepository.findByGiaoHangOrderByThoiGianDesc(giaoHang);
    }

    @Override
    public void addViTriDonHang(ViTriDonHang viTriDonHang) {
        viTriDonHangRepository.save(viTriDonHang);
    }
}
