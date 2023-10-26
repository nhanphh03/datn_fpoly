package com.example.demo.controller;

import com.example.demo.model.ChucVu;
import com.example.demo.model.LoaiKhachHang;
import com.example.demo.model.NhanVien;
import com.example.demo.service.LoaiKhachHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("manage")
@Controller
public class LoaiKhachHangController {
    @Autowired
    private LoaiKhachHangService loaiKhachHangService;
    @Autowired
    private HttpSession session;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

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
        LoaiKhachHang loaiKhachHang1 = new LoaiKhachHang();
        loaiKhachHang1.setMaLKH(loaiKhachHang.getMaLKH());
        loaiKhachHang1.setTenLKH(loaiKhachHang.getTenLKH());
        loaiKhachHang1.setSoDiem(loaiKhachHang.getSoDiem());
        loaiKhachHang1.setTgThem(new Date());
        loaiKhachHang1.setTrangThai(loaiKhachHang.getTrangThai());
        loaiKhachHangService.save(loaiKhachHang1);
        return "redirect:/manage/loai-khach-hang";
    }

    @GetMapping("/loai-khach-hang/delete/{id}")
    public String deleteloaikhachhang(@PathVariable UUID id) {
        LoaiKhachHang loaiKhachHang = loaiKhachHangService.getByIdLoaiKhachHang(id);
        loaiKhachHang.setTrangThai(0);
        loaiKhachHang.setTgSua(new Date());
        loaiKhachHangService.save(loaiKhachHang);
        return "redirect:/manage/loai-khach-hang";
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
            loaiKhachHangdb.setTenLKH(loaiKhachHang.getTenLKH());
            loaiKhachHangdb.setSoDiem(loaiKhachHang.getSoDiem());
            loaiKhachHangdb.setTgSua(new Date());
            loaiKhachHangdb.setTrangThai(loaiKhachHang.getTrangThai());
            loaiKhachHangService.save(loaiKhachHangdb);
        }
        return "redirect:/manage/loai-khach-hang";
    }


}
