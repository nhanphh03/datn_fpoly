package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.ChucVuService;
import com.example.demo.service.NhanVienService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("manage")
@Controller
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private ChucVuService chucVuService;

    @Autowired
    private HttpSession session;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @ModelAttribute("dsGioiTinh")
    public Map<Integer, String> getDsGioiTinh() {
        Map<Integer, String> dsGioiTinh = new HashMap<>();
        dsGioiTinh.put(1, "Nam");
        dsGioiTinh.put(0, "Nữ");
        return dsGioiTinh;
    }

    @GetMapping("/nhan-vien")
    public String dsNhanVien(Model model) {

        List<NhanVien> nhanViens = nhanVienService.getAllNhanVien();
        List<ChucVu> chucVus = chucVuService.getAllChucVu();
        model.addAttribute("nhanVien", nhanViens);
        model.addAttribute("chucVu", chucVus);
        return "manage/nhan-vien";
    }

    @GetMapping("/nhan-vien/viewAdd")
    public String viewAddNhanVien(Model model) {
        List<ChucVu> chucVus = chucVuService.getAllChucVu();
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("chucVu", chucVus);
        return "manage/add-nhan-vien";
    }

    @PostMapping("/nhan-vien/viewAdd/add")
    public String addNhanVien(@ModelAttribute("nhanVien") NhanVien nhanVien) {
        nhanVien.setTgThem(new Date());
        nhanVienService.save(nhanVien);
        return "redirect:/manage/nhan-vien";
    }

    @GetMapping("/nhan-vien/delete/{id}")
    public String deleteNhanVien(@PathVariable UUID id) {
        NhanVien nhanVien = nhanVienService.getByIdNhanVien(id);
        nhanVien.setTrangThai(0);
        nhanVien.setTgSua(new Date());
        nhanVienService.save(nhanVien);
        return "redirect:/manage/nhan-vien";
    }

    @GetMapping("/nhan-vien/viewUpdate/{id}")
    public String viewUpdatenhanVien(@PathVariable UUID id, Model model) {
        NhanVien nhanVien = nhanVienService.getByIdNhanVien(id);
        List<ChucVu> chucVus = chucVuService.getAllChucVu();
        model.addAttribute("nhanVien", nhanVien);
        model.addAttribute("chucVu", chucVus);
        return "manage/update-nhan-vien";
    }

    @PostMapping("/nhan-vien/viewUpdate/{id}")
    public String updatenhanVien(@PathVariable UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        NhanVien nhanViendb = nhanVienService.getByIdNhanVien(id);
        if (nhanViendb != null) {
            nhanViendb.setMaNV(nhanVien.getMaNV());
            nhanViendb.setHoTenNV(nhanVien.getHoTenNV());
            nhanViendb.setAnhNV(nhanVien.getAnhNV());
            nhanViendb.setCCCDNV(nhanVien.getCCCDNV());
            nhanViendb.setDiaChi(nhanVien.getDiaChi());
            nhanViendb.setEmailNV(nhanVien.getEmailNV());
            nhanViendb.setMatKhau(nhanVien.getMatKhau());
            nhanViendb.setSdtNV(nhanVien.getSdtNV());
            nhanViendb.setGioiTinh(nhanVien.getGioiTinh());
            nhanViendb.setNgaySinh(nhanVien.getNgaySinh());
            nhanViendb.setTgSua(new Date());
            nhanViendb.setTrangThai(nhanVien.getTrangThai());
            nhanViendb.setChucVu(nhanVien.getChucVu());
            nhanVienService.save(nhanViendb);
        }
        return "redirect:/manage/nhan-vien";
    }
}
