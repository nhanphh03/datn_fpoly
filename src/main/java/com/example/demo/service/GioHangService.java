package com.example.demo.service;

import com.example.demo.model.GioHang;
import com.example.demo.model.KhachHang;

public interface GioHangService {

    GioHang findByKhachHang(KhachHang khachHang);

    void saveGH(GioHang gh);


}
