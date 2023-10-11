package com.example.demo.service;

import com.example.demo.model.LoaiKhachHang;
import com.example.demo.repository.LoaiKhachHangRepository;

import java.util.List;
import java.util.UUID;

public interface LoaiKhachHangService {
    public List<LoaiKhachHang> getAllLoaiKhachHang();

    public void save(LoaiKhachHang loaiKhachHang);

    public void deleteByIdLoaiKhachHang(UUID id);

    public LoaiKhachHang getByIdLoaiKhachHang(UUID id);

}
