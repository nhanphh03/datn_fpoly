package com.example.demo.service.impls;

import com.example.demo.model.GioHang;
import com.example.demo.model.KhachHang;
import com.example.demo.repository.GioHangRepository;
import com.example.demo.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GioHangServiceImpl implements GioHangService {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public GioHang findByKhachHang(KhachHang khachHang) {
        return gioHangRepository.findByKhachHang(khachHang);
    }

    @Override
    public void saveGH(GioHang gh) {
         gioHangRepository.save(gh);
    }
}
