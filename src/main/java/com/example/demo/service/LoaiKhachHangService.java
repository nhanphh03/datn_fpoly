package com.example.demo.service;

import com.example.demo.model.LoaiKhachHang;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public interface LoaiKhachHangService {
    public List<LoaiKhachHang> getAllLoaiKhachHang();

    public void save(LoaiKhachHang loaiKhachHang);

    public void deleteByIdLoaiKhachHang(UUID id);

    public LoaiKhachHang getByIdLoaiKhachHang(UUID id);

    public List<LoaiKhachHang> getAllActive();

    public List<LoaiKhachHang> fillterLKH(String maLKH, String tenLKH);

    LoaiKhachHang findByMaLKH(String maLKH);

    public void importDataFromExcel(InputStream excelFile);
}
