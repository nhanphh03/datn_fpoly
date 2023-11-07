package com.example.demo.service;

import com.example.demo.model.KhachHang;
import com.example.demo.model.LoaiKhachHang;
import com.example.demo.model.NhanVien;

import java.io.InputStream;
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

    public void importDataFromExcel(InputStream excelFile);

    List<KhachHang> findByLoaiKhachHang(LoaiKhachHang loaiKhachHang);
    public List<KhachHang> findByTrangThai(int trangThai);
    public List<KhachHang> fillterKhachHang(String maKH, String tenKH);


    List<KhachHang> findKhachHangByTrangThai();

    List<KhachHang> findKhachHangByKeyword(String keyword);

}
