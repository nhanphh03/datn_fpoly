package com.example.demo.service.impls;

import com.example.demo.model.LoaiKhachHang;
import com.example.demo.repository.LoaiKHRepository;
import com.example.demo.service.LoaiKHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoaiKHServiceImpl implements LoaiKHService {

    @Autowired
    private LoaiKHRepository loaiKHRepository;

    @Override
    public LoaiKhachHang findByTenLKH(String nameLKH) {
        return loaiKHRepository.findByTenLKH(nameLKH);
    }
}
