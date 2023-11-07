package com.example.demo.controller;

import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequestMapping("/manage/don-hang")
@Controller
public class DonHangController {
    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @GetMapping("/hien-thi")
    public String getAll(Model model){
        List<HoaDon> hoaDonList = hoaDonService.getAllHoaDon();
        model.addAttribute("hoaDonList",hoaDonList);
        return "manage/quan-li-hoa-don";
    }

    @GetMapping("/detail/{idHoaDon}")
    public String detailHoaDon(@PathVariable("idHoaDon")UUID idHoaDon,Model model){
        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
        model.addAttribute("hoaDonChiTiet",findByIdHoaDon);
        model.addAttribute("hoaDon",hoaDon);
        return "manage/quan-li-hoa-don-detail";
    }
}
