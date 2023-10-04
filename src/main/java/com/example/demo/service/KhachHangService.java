package com.example.demo.service;

import com.example.demo.model.KhachHang;

public interface KhachHangService{
    KhachHang checkLoginSDT(String sdt, String pass);

    KhachHang checkLoginEmail(String email, String pass);

    KhachHang checkEmail(String email);
}
