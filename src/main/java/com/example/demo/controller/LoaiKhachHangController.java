package com.example.demo.controller;

import com.example.demo.model.ChucVu;
import com.example.demo.model.LoaiKhachHang;
import com.example.demo.service.LoaiKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("manage")
@Controller
public class LoaiKhachHangController {
    @Autowired
    private LoaiKhachHangService loaiKhachHangService;

    @GetMapping("/loai-khach-hang")
    public String dsloaikhachhang(Model model) {
        List<LoaiKhachHang> loaikhachhang = loaiKhachHangService.getAllLoaiKhachHang();
        model.addAttribute("loaiKhachHang", loaikhachhang);
        return "manage/loai-khach-hang";
    }

    @GetMapping("/loai-khach-hang/viewAdd")
    public String viewAddloaikhachhang(Model model) {
        model.addAttribute("loaiKhachHang", new LoaiKhachHang());
        return "manage/add-loai-khach-hang";
    }

    @PostMapping("/loai-khach-hang/viewAdd/add")
    public String addloaikhachhang(@ModelAttribute("loaiKhachHang") LoaiKhachHang loaiKhachHang) {
        loaiKhachHangService.save(loaiKhachHang);
        return "redirect:/loai-khach-hang";
    }

    @GetMapping("/loai-khach-hang/delete/{id}")
    public String deleteloaikhachhang(@PathVariable UUID id) {
        loaiKhachHangService.deleteByIdLoaiKhachHang(id);
        return "redirect:/loai-khach-hang";
    }

    @GetMapping("/loai-khach-hang/viewUpdate/{id}")
    public String viewUpdateloaikhachhang(@PathVariable UUID id, Model model) {
        LoaiKhachHang loaiKhachHang = loaiKhachHangService.getByIdLoaiKhachHang(id);
        model.addAttribute("loaiKhachHang", loaiKhachHang);
        return "manage/update-loai-khach-hang";
    }

    @PostMapping("/loai-khach-hang/viewUpdate/{id}")
    public String updateloaikhachhang(@PathVariable UUID id, @ModelAttribute("loaiKhachHang") LoaiKhachHang loaiKhachHang) {
        LoaiKhachHang loaiKhachHangdb = loaiKhachHangService.getByIdLoaiKhachHang(id);
        if (loaiKhachHangdb != null) {
            loaiKhachHangdb.setMaLKH(loaiKhachHang.getMaLKH());
            loaiKhachHangdb.setSoDiem(loaiKhachHang.getSoDiem());
            loaiKhachHangdb.setTenLKH(loaiKhachHang.getTenLKH());
            loaiKhachHangdb.setTgSua(loaiKhachHang.getTgSua());
            loaiKhachHangdb.setTgThem(loaiKhachHang.getTgThem());
            loaiKhachHangdb.setTrangThai(loaiKhachHang.getTrangThai());
            loaiKhachHangService.save(loaiKhachHangdb);
        }
        return "redirect:/loai-khach-hang";
    }


}
