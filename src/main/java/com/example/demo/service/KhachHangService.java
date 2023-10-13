package com.example.demo.service;

import com.example.demo.model.KhachHang;

import java.util.List;
import java.util.UUID;

public interface KhachHangService{
    KhachHang checkLoginSDT(String sdt, String pass);

    KhachHang checkLoginEmail(String email, String pass);

    KhachHang checkEmail(String email);

    public List<KhachHang> getAllKhachHang();

    public void save(KhachHang khachHang);

    public void deteleByIdKhachHang(UUID id);

    public KhachHang getByIdKhachHang(UUID id);

    void addKhachHang(KhachHang khachHang);
}
