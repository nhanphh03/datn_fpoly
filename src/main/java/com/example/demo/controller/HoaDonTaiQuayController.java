package com.example.demo.controller;

import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.model.KhuyenMaiChiTietHoaDon;
import com.example.demo.repository.KMCTHDRepository;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.KhuyenMaiChiTietHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequestMapping("/manage/hoa-don")
@Controller
public class HoaDonTaiQuayController {
    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private KhuyenMaiChiTietHoaDonService khuyenMaiChiTietHoaDonService;

    @GetMapping("/hien-thi")
    public String getAll(Model model){
        List<HoaDon> hoaDonList = hoaDonService.getAllHoaDonOffLine();
        model.addAttribute("hoaDonList",hoaDonList);
        return "manage/quan-li-hoa-don-tai-quay";
    }

    @GetMapping("/detail/{idHoaDon}")
    public String detailHoaDon(@PathVariable("idHoaDon")UUID idHoaDon, Model model){
        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
        KhuyenMaiChiTietHoaDon khuyenMaiChiTietHoaDon = khuyenMaiChiTietHoaDonService.findByHoaDon(hoaDon);
        model.addAttribute("hoaDonChiTiet",findByIdHoaDon);
        model.addAttribute("hoaDon",hoaDon);
        model.addAttribute("khuyenMai",khuyenMaiChiTietHoaDon);
        model.addAttribute("modalHoaDon",true);
        return "manage/quan-li-hoa-don-tai-quay";
    }
}
