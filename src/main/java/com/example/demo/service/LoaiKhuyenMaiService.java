package com.example.demo.service;

import com.example.demo.model.LoaiKhuyenMai;

import java.util.List;
import java.util.UUID;

public interface LoaiKhuyenMaiService {

    List<LoaiKhuyenMai> getAllLKM();
    void createLKM(LoaiKhuyenMai loaiKhuyenMai);
    LoaiKhuyenMai findByMaLKM(String maLKH);
    LoaiKhuyenMai findByIDLKM(UUID idLKM);
}
