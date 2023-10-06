package com.example.demo.controller;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.service.GiayChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@RequestMapping("/manage")
@Controller
public class GiayChiTietController {
    @Autowired
    private GiayChiTietService giayChiTietService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/giay-chi-tiet")
    public String dsGiayChiTiet(Model model) {
        List<ChiTietGiay> items = giayChiTietService.getAllChiTietGiay();
        model.addAttribute("items", items);
        return "manage/giay-chi-tiet";
    }

    @GetMapping("/giay-chi-tiet/detail/{id}")
    public String detailGiayChiTiet(@PathVariable UUID id, Model model) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        model.addAttribute("giayChiTietDetail", chiTietGiay);
        return "manage/giay-chi-tiet-detail";
    }

    @GetMapping("/giay-chi-tiet/viewAdd")
    public String viewAddGiayChiTiet(Model model) {
        model.addAttribute("giayChiTiet", new ChiTietGiay());
        return "manage/add-giay-chi-tiet";
    }

    @PostMapping("/giay-chi-tiet/viewAdd/add")
    public String addGiayChiTiet(@ModelAttribute("giayChiTiet") ChiTietGiay chiTietGiay) {
        giayChiTietService.save(chiTietGiay);
        return "redirect:/manage/giay-chi-tiet";
    }

    @GetMapping("/giay-chi-tiet/delete/{id}")
    public String deleteGiayChiTiet(@PathVariable UUID id) {
        giayChiTietService.deleteByIdChiTietGiay(id);
        return "redirect:/manage/giay-chi-tiet";
    }

    @GetMapping("/giay-chi-tiet/viewUpdate/{id}")
    public String viewUpdateGiayChiTiet(@PathVariable UUID id, Model model) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        model.addAttribute("giayChiTiet", chiTietGiay);
//        model.addAttribute("giay", giayRepository.findAll());
//        model.addAttribute("chatLieu", chatLieuRepository.findAll());
//        model.addAttribute("loaiGiay", loaiGiayRepository.findAll());
//        model.addAttribute("mauSac", mauSacRepository.findAll());
//        model.addAttribute("nhaSanXuat", nhaSanXuatRepository.findAll());
//        model.addAttribute("sizeGiay", sizeGiayRepository.findAll());
        return "manage/update-giay-chi-tiet";
    }

    @PostMapping("/giay-chi-tiet/viewUpdate/{id}")
    public String updateGiayChiTiet(@PathVariable UUID id, @ModelAttribute("giayChiTiet") ChiTietGiay chiTietGiay) {
        ChiTietGiay chiTietGiayDb = giayChiTietService.getByIdChiTietGiay(id);
        if (chiTietGiayDb != null) {
            chiTietGiayDb.setGiay(chiTietGiay.getGiay());
            chiTietGiayDb.setHinhAnh(chiTietGiay.getHinhAnh());
            chiTietGiayDb.setMauSac(chiTietGiay.getMauSac());
            chiTietGiayDb.setSize(chiTietGiay.getSize());
            chiTietGiayDb.setGiaBan(chiTietGiay.getGiaBan());
            chiTietGiayDb.setMaNVSua(chiTietGiay.getMaNVSua());
            chiTietGiayDb.setNamBH(chiTietGiay.getNamBH());
            chiTietGiayDb.setNamSX(chiTietGiay.getNamSX());
            chiTietGiayDb.setSoLuong(chiTietGiay.getSoLuong());
            chiTietGiayDb.setTgSua(chiTietGiay.getTgSua());
            chiTietGiayDb.setTgThem(chiTietGiay.getTgThem());
            chiTietGiayDb.setTrangThai(chiTietGiay.getTrangThai());
            chiTietGiayDb.setTrongLuong(chiTietGiay.getTrongLuong());
            giayChiTietService.save(chiTietGiayDb);
        }
        return "redirect:/manage/giay-chi-tiet";
    }
}
