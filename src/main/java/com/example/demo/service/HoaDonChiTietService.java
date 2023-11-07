package com.example.demo.service;

import com.example.demo.model.HoaDonChiTiet;

import java.util.List;
import java.util.UUID;

public interface HoaDonChiTietService {

    HoaDonChiTiet getOne(UUID idHoaDon, UUID idChiTietGiay);
    void add(HoaDonChiTiet hoaDonChiTiet);

    List<HoaDonChiTiet> findByIdHoaDon(UUID id);

    Double tongTien(List<HoaDonChiTiet> list);
}
