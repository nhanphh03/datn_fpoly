package com.example.demo.service;

import com.example.demo.model.GioHang;
import com.example.demo.model.GioHangChiTiet;

import java.util.List;
import java.util.UUID;

public interface GHCTService {

    List<GioHangChiTiet> findByGHActive(GioHang gioHang);


}
