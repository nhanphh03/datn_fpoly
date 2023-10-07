package com.example.demo.controller;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.Giay;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@RequestMapping("/manage")
@Controller
public class GiayChiTietController {
    @Autowired
    private GiayChiTietService giayChiTietService;
    @Autowired
    private GiayService giayService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private HinhAnhService hinhAnhService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }
    private String getReferer() {
        // Lấy đường dẫn trang trước đó từ header "referer"
        // Trong trường hợp không có header này, bạn có thể xác định một đích mặc định để chuyển hướng
        String referer = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        return StringUtils.isEmpty(referer) ? "/default" : referer;
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

    @GetMapping("/giay-chi-tiet/viewAdd/{id}")
    public String viewAddChiTietGiay(@PathVariable UUID id, Model model) {
        Giay giay = giayService.getByIdGiay(id);
        model.addAttribute("giayChiTiet", new ChiTietGiay());
        model.addAttribute("giay", giayService.getAllGiay());
        model.addAttribute("mauSac", mauSacService.getALlMauSac());
        model.addAttribute("size", sizeService.getAllSize());
        model.addAttribute("hinhAnh", hinhAnhService.getAllHinhAnh());
        return "manage/add-giay-chi-tiet";
    }

    @GetMapping("/chi-tiet-giay/viewAdd")
    public String viewAddGiayChiTiet( Model model) {
        model.addAttribute("chiTietGiay", new ChiTietGiay());
        model.addAttribute("giay", giayService.getAllGiay());
        model.addAttribute("mauSac", mauSacService.getALlMauSac());
        model.addAttribute("size", sizeService.getAllSize());
        model.addAttribute("hinhAnh", hinhAnhService.getAllHinhAnh());
        return "manage/add-chi-tiet-giay";
    }


    @PostMapping("/giay-chi-tiet/viewAdd/add")
    public String addGiayChiTiet(@ModelAttribute("giayChiTiet") ChiTietGiay chiTietGiay) {
//        String tenGiay = httpServletRequest.getParameter("tenGiay");
        ChiTietGiay chiTietGiay1 = new ChiTietGiay();
        chiTietGiay1.setGiay(chiTietGiay.getGiay());
        chiTietGiay1.setNamSX(chiTietGiay.getNamSX());
        chiTietGiay1.setNamBH(chiTietGiay.getNamBH());
        chiTietGiay1.setTrongLuong(chiTietGiay.getTrongLuong());
        chiTietGiay1.setGiaBan(chiTietGiay.getGiaBan());
        chiTietGiay1.setSoLuong(chiTietGiay.getSoLuong());
        chiTietGiay1.setTrangThai(chiTietGiay.getTrangThai());
        chiTietGiay1.setMauSac(chiTietGiay.getMauSac());
        chiTietGiay1.setHinhAnh(chiTietGiay.getHinhAnh());
        chiTietGiay1.setSize(chiTietGiay.getSize());
        chiTietGiay1.setTgThem(new Date());
        giayChiTietService.save(chiTietGiay1);

        return "redirect:/manage/giay-chi-tiet";
    }

    @PostMapping("/chi-tiet-giay/viewAdd/add")
    public String addChiTietGiay(@ModelAttribute("chiTietGiay") ChiTietGiay chiTietGiay) {
        ChiTietGiay chiTietGiay2 = new ChiTietGiay();
        chiTietGiay2.setGiay(chiTietGiay.getGiay());
        chiTietGiay2.setNamSX(chiTietGiay.getNamSX());
        chiTietGiay2.setNamBH(chiTietGiay.getNamBH());
        chiTietGiay2.setTrongLuong(chiTietGiay.getTrongLuong());
        chiTietGiay2.setGiaBan(chiTietGiay.getGiaBan());
        chiTietGiay2.setSoLuong(chiTietGiay.getSoLuong());
        chiTietGiay2.setTrangThai(chiTietGiay.getTrangThai());
        chiTietGiay2.setMauSac(chiTietGiay.getMauSac());
        chiTietGiay2.setHinhAnh(chiTietGiay.getHinhAnh());
        chiTietGiay2.setSize(chiTietGiay.getSize());
        chiTietGiay2.setTgThem(new Date());
        giayChiTietService.save(chiTietGiay2);

        return "redirect:/manage/giay-chi-tiet";
    }

    @GetMapping("/giay-chi-tiet/delete/{id}")
    public String deleteGiayChiTiet(@PathVariable UUID id) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        chiTietGiay.setTrangThai(0);
        chiTietGiay.setTgSua(new Date());
        giayChiTietService.save(chiTietGiay);
        return "redirect:/manage/giay-chi-tiet";
    }

    @GetMapping("/giay-chi-tiet/viewUpdate/{id}")
    public String viewUpdateGiayChiTiet(@PathVariable UUID id, Model model) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        model.addAttribute("giayChiTiet", chiTietGiay);
        model.addAttribute("giay", giayService.getAllGiay());
        model.addAttribute("mauSac", mauSacService.getALlMauSac());
        model.addAttribute("size", sizeService.getAllSize());
        model.addAttribute("hinhAnh", hinhAnhService.getAllHinhAnh());
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
            chiTietGiayDb.setTgSua(new Date());
            chiTietGiayDb.setTrangThai(chiTietGiay.getTrangThai());
            chiTietGiayDb.setTrongLuong(chiTietGiay.getTrongLuong());
            giayChiTietService.save(chiTietGiayDb);
        }
        return "redirect:/manage/giay-chi-tiet";
    }
}
