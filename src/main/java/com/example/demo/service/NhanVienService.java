package com.example.demo.service;

import com.example.demo.model.NhanVien;

import java.util.List;
import java.util.UUID;

public interface NhanVienService {
    public List<NhanVien> getAllNhanVien();
    public void save(NhanVien nhanVien);
    public void deleteByIdNhanVien(UUID id);
    public NhanVien getByIdNhanVien(UUID id);
}
