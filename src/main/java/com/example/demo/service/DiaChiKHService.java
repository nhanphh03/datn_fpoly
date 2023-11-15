package com.example.demo.service;

import com.example.demo.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;


public interface DiaChiKHService {
    public List<DiaChiKH> getAllDiaChiKH();

    public void save(DiaChiKH diaChiKH);

    public void deleteByIdDiaChiKH(UUID id);

    public DiaChiKH getByIdDiaChikh(UUID id);

    public void importDataFromExcel(InputStream excelFile);

    List<DiaChiKH> findbyKhachHangAndLoai(KhachHang khachHang, Boolean loai);

    DiaChiKH findDCKHDefaulByKhachHang(KhachHang khachHang);

    public List<DiaChiKH> findByKhachHang(KhachHang khachHang);

    public List<DiaChiKH> findByTrangThai(int trangThai);

    public List<DiaChiKH> fillterDiaChiKH(String maDC, String tenDC);


}
