package com.example.demo.controller;

import com.example.demo.model.ChucVu;
import com.example.demo.model.KhachHang;
import com.example.demo.model.LoaiKhachHang;
import com.example.demo.model.NhanVien;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.LoaiKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/manage")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private LoaiKhachHangService loaiKhachHangService;

//    @GetMapping("/khach-hang")
//    public String dsKhachHang() {
//
//        return "manage/khach-hang";
//    }
            @ModelAttribute("dsTrangThai")
            public Map<Integer, String> getDsTrangThai() {
                Map<Integer, String> dsTrangThai = new HashMap<>();
                dsTrangThai.put(1, "Hoạt động");
                dsTrangThai.put(0, "Không hoạt động");
                return dsTrangThai;
            }

        @GetMapping("/khach-hang")
        public String dsKhachHang(Model model) {
            List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
            List<LoaiKhachHang> loaiKhachHangs = loaiKhachHangService.getAllLoaiKhachHang();
            model.addAttribute("khachHang", khachHangs);
            model.addAttribute("loaiKhachHang", loaiKhachHangs);
            return "manage/khach-hang";
        }

        @GetMapping("/khach-hang/viewAdd")
        public String viewAddkhachHang(Model model) {
            List<LoaiKhachHang> loaiKhachHangs = loaiKhachHangService.getAllLoaiKhachHang();
            model.addAttribute("khachHang", new KhachHang());
            model.addAttribute("loaiKhachHang", loaiKhachHangs);
            return "manage/add-khach-hang";
        }

            @PostMapping("/khach-hang/viewAdd/add")
            public String addkhachHang(@ModelAttribute("khachHang") KhachHang khachHang) {
                khachHang.setTgThem(new Date());
                khachHangService.save(khachHang);
                return "redirect:/manage/khach-hang";
            }


        @GetMapping("/khach-hang/delete/{id}")
        public String deleteNhanVien(@PathVariable UUID id) {
            KhachHang khachHang = khachHangService.getByIdKhachHang(id);
            khachHang.setTrangThai(0);
            khachHang.setTgSua(new Date());
            khachHangService.save(khachHang);
            return "redirect:/manage/khach-hang";
        }

        @GetMapping("/khach-hang/viewUpdate/{id}")
        public String viewUpdatekhachhang(@PathVariable UUID id, Model model) {
            KhachHang khachHang = khachHangService.getByIdKhachHang(id);
            List<LoaiKhachHang> loaiKhachHangs = loaiKhachHangService.getAllLoaiKhachHang();
            model.addAttribute("khachHang", khachHang);
            model.addAttribute("loaiKhachHang", loaiKhachHangs);
            return "manage/update-khach-hang";
        }

            @PostMapping("/khach-hang/viewUpdate/{id}")
            public String updatekhachhang(@PathVariable UUID id, @ModelAttribute("khachHang") KhachHang khachHang) {
                KhachHang khachHangdb = khachHangService.getByIdKhachHang(id);
                if (khachHangdb != null) {
                    khachHangdb.setMaKH(khachHang.getMaKH());
                    khachHangdb.setHoTenKH(khachHang.getHoTenKH());
                    khachHangdb.setAnhKH(khachHang.getAnhKH());
                    khachHangdb.setDiaChi(khachHang.getDiaChi());
                    khachHangdb.setEmailKH(khachHang.getEmailKH());
                    khachHangdb.setMatKhau(khachHang.getMatKhau());
                    khachHangdb.setSdtKH(khachHang.getSdtKH());
                    khachHangdb.setCCCDKH(khachHang.getCCCDKH());
                    khachHangdb.setGioiTinh(khachHang.getGioiTinh());
                    khachHangdb.setNgaySinh(khachHang.getNgaySinh());
                    khachHangdb.setTgSua(new Date());
                    khachHangdb.setTrangThai(khachHang.getTrangThai());
                    khachHangdb.setLoaiKhachHang(khachHang.getLoaiKhachHang());
                    khachHangService.save(khachHangdb);
                }
                return "redirect:/manage/khach-hang";
            }
}
