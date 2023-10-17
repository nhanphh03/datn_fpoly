package com.example.demo.service;

import com.example.demo.model.ChucVu;
import com.example.demo.model.NhanVien;

import java.util.List;
import java.util.UUID;

public interface NhanVienService {

    NhanVien checkByEmailAndChucVuAndPass(String email,String pass, ChucVu chucVu);

    NhanVien checkBySDTAndChucVuAndPass(String sdt, String pass, ChucVu chucVu);

    public List<NhanVien> getAllNhanVien();
    public void save(NhanVien nhanVien);
    public void deleteByIdNhanVien(UUID id);
    public NhanVien getByIdNhanVien(UUID id);
}
