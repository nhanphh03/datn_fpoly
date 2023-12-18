package com.example.demo.controller;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.GiaoHang;
import com.example.demo.model.ThongBaoKhachHang;
import com.example.demo.service.GiaoHangService;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.ThongBaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/manage/")
@Controller
public class QuanLyGiaoHangController {

    @Autowired
    private GiaoHangService giaoHangService;

    @Autowired
    private ThongBaoServices thongBaoServices;

    @Autowired
    private GiayChiTietService giayChiTietService;

    @GetMapping("giao-hang")
    private String showGiaoHang(Model model){

        List<GiaoHang> giaoHangList = giaoHangService.getAllGiaoHangs();

        model.addAttribute("listGiaoHang", giaoHangList);
        showThongBao(model);

        return "manage/manage-giao-hang";
    }

    @GetMapping("giay/old")
    private String shoCTGOld(Model model){

        List<ChiTietGiay> listCTGOld = giayChiTietService.getAllChiTietGiay();

        model.addAttribute("listCTGOld", listCTGOld);
        showThongBao(model);

        return "manage/manage-giay-old";
    }
    private void showThongBao(Model model){
        int soThongBao = 0;

        List<ThongBaoKhachHang> thongBaoKhachHangs =  thongBaoServices.getAll();
        for (ThongBaoKhachHang x: thongBaoKhachHangs) {
            if (x.getTrangThai() == 3){
                soThongBao++;
            }
        }

        model.addAttribute("soThongBao", soThongBao);
        model.addAttribute("listThongBao", thongBaoKhachHangs);
    }
}
