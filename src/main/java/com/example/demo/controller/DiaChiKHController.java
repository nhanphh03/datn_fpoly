package com.example.demo.controller;

import com.example.demo.model.ChucVu;
import com.example.demo.model.DiaChiKH;
import com.example.demo.model.KhachHang;
import com.example.demo.model.LoaiKhachHang;
import com.example.demo.service.DiaChiKHService;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("manage")
@Controller
public class DiaChiKHController {
    @Autowired
    private DiaChiKHService diaChiKHService;

    @Autowired
    private KhachHangService khachHangService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/dia-chi")
    public String dsDiaChiKH(Model model) {
        List<DiaChiKH> diaChiKHS = diaChiKHService.getAllDiaChiKH();
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        model.addAttribute("diaChi", diaChiKHS);
        model.addAttribute("khachHang", khachHangs);
        return "manage/dia-chi";
    }

    @GetMapping("/dia-chi/viewAdd")
    public String viewAddDiaChi(Model model) {
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        model.addAttribute("diaChi", new DiaChiKH());
        model.addAttribute("khachHang", khachHangs);
        return "manage/add-dia-chi";
    }

    @PostMapping("/dia-chi/viewAdd/add")
    public String adddiachi(@ModelAttribute("diaChi") DiaChiKH diaChiKH) {
        DiaChiKH diaChiKH1 = new DiaChiKH();
        diaChiKH1.setMaDC(diaChiKH.getMaDC());
        diaChiKH1.setTenDC(diaChiKH.getTenDC());
        diaChiKH1.setQuanHuyen(diaChiKH.getQuanHuyen());
        diaChiKH1.setTinhTP(diaChiKH.getTinhTP());
        diaChiKH1.setMoTa(diaChiKH.getMoTa());
        diaChiKH1.setMien(diaChiKH.getMien());
        diaChiKH1.setDiaChiChiTiet(diaChiKH.getDiaChiChiTiet());
        diaChiKH1.setQuanHuyen(diaChiKH.getQuanHuyen());
        diaChiKH1.setTgThem(new Date());
        diaChiKH1.setTrangThai(diaChiKH.getTrangThai());
        diaChiKH1.setKhachHang(diaChiKH.getKhachHang());
        diaChiKHService.save(diaChiKH1);
        return "redirect:/manage/dia-chi";
    }

    @GetMapping("/dia-chi/delete/{id}")
    public String deleteDiaChi(@PathVariable UUID id) {
        DiaChiKH diaChiKH = diaChiKHService.getByIdDiaChikh(id);
        diaChiKH.setTrangThai(0);
        diaChiKH.setTgSua(new Date());
        diaChiKHService.save(diaChiKH);
        return "redirect:/manage/dia-chi";
    }

    @GetMapping("/dia-chi/viewUpdate/{id}")
    public String viewUpdatediaChi(@PathVariable UUID id, Model model) {
        DiaChiKH diaChiKH = diaChiKHService.getByIdDiaChikh(id);
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        model.addAttribute("diaChi", diaChiKH);
        model.addAttribute("khachHang", khachHangs);
        return "manage/update-dia-chi";
    }

    @PostMapping("/dia-chi/viewUpdate/{id}")
    public String updatediaChi(@PathVariable UUID id, @ModelAttribute("diaChi") DiaChiKH diaChiKH) {
        DiaChiKH diaChiKHdb = diaChiKHService.getByIdDiaChikh(id);
        if (diaChiKHdb != null) {
            diaChiKHdb.setMaDC(diaChiKH.getMaDC());
            diaChiKHdb.setTenDC(diaChiKH.getTenDC());
            diaChiKHdb.setXaPhuong(diaChiKH.getXaPhuong());
            diaChiKHdb.setQuanHuyen(diaChiKH.getQuanHuyen());
            diaChiKHdb.setTinhTP(diaChiKH.getTinhTP());
            diaChiKHdb.setMoTa(diaChiKH.getMoTa());
            diaChiKHdb.setMien(diaChiKH.getMien());
            diaChiKHdb.setDiaChiChiTiet(diaChiKH.getDiaChiChiTiet());
            diaChiKHdb.setTrangThai(diaChiKH.getTrangThai());
            diaChiKHdb.setTgSua(new Date());
            diaChiKHdb.setKhachHang(diaChiKH.getKhachHang());
            diaChiKHService.save(diaChiKHdb);
        }
        return "redirect:/manage/dia-chi";
    }

}
