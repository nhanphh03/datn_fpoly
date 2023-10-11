package com.example.demo.service.impls;

import com.example.demo.model.KhachHang;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public KhachHang checkLoginSDT(String sdt, String pass) {
        return khachHangRepository.findBySdtKHAndTrangThaiAndMatKhau(sdt, 1, pass);
    }

    @Override
    public KhachHang checkLoginEmail(String email, String pass) {
        return khachHangRepository.findByEmailKHAndTrangThaiAndMatKhau(email, 1 ,pass);
    }

    @Override
    public KhachHang checkEmail(String email) {
        return khachHangRepository.findByEmailKH(email);
    }

    @Override
    public KhachHang addKhachHang(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }
}
