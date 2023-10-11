package com.example.demo.service.impls;

import com.example.demo.model.LoaiKhachHang;
import com.example.demo.repository.LoaiKhachHangRepository;
import com.example.demo.service.LoaiKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoaiKhachHangServiceImpl implements LoaiKhachHangService {
    @Autowired
    public LoaiKhachHangRepository loaiKhachHangRepository;

    @Override
    public List<LoaiKhachHang> getAllLoaiKhachHang() {
        return loaiKhachHangRepository.findAll();
    }

    @Override
    public void save(LoaiKhachHang loaiKhachHang) {
        loaiKhachHangRepository.save(loaiKhachHang);
    }

    @Override
    public void deleteByIdLoaiKhachHang(UUID id) {
        loaiKhachHangRepository.deleteById(id);
    }

    @Override
    public LoaiKhachHang getByIdLoaiKhachHang(UUID id) {
        return loaiKhachHangRepository.findById(id).orElse(null);
    }
}
