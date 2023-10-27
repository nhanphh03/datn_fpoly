package com.example.demo.service;

import com.example.demo.model.HanhViKhachHang;
import com.example.demo.model.KhachHang;

public interface HanhViKHService {

    void addNewHanhViKH(HanhViKhachHang hanhViKhachHang);

    HanhViKhachHang checkByKH(KhachHang khachHang);
}
