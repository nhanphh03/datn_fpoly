package com.example.demo.service;

import com.example.demo.model.KhachHang;
import com.example.demo.model.LoaiKhachHang;
import org.springframework.stereotype.Service;


public interface LoaiKHService {

    LoaiKhachHang findByTenLKH(String nameLKH);
}
