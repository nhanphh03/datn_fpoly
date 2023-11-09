package com.example.demo.service;

import com.example.demo.model.KhuyenMai;
import com.example.demo.model.LoaiKhuyenMai;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface KhuyenMaiService {

    void createKhuyenMais(KhuyenMai khuyenMai);
    KhuyenMai findByIDAndTrangThai(UUID idKhuyenMai);
    List<KhuyenMai> getAllKhuyenMai();
    List<KhuyenMai> findByTrangThai(int trangThai);
    List<KhuyenMai> findByLoaiKMAndTrangThai(LoaiKhuyenMai loaiKhuyenMai, int trangThai);
    List<KhuyenMai> findByNgayBatDauAndNgayKetThuc(Date ngayBatDau, Date ngayKetThuc);
    KhuyenMai findByMaKM(String maKM);
}
