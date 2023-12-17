package com.example.demo.service;

import com.example.demo.model.KhachHang;
import com.example.demo.model.ThongBaoKhachHang;

import java.util.List;

public interface ThongBaoServices {

    void addThongBao(ThongBaoKhachHang thongBaoKhachHang);

    List<ThongBaoKhachHang> getAll();

    List<ThongBaoKhachHang> findByKhachHang(KhachHang khachHang);
}
