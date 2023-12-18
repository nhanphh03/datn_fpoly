package com.example.demo.controller;

import com.example.demo.model.HoaDon;
import com.example.demo.model.LichSuThanhToan;
import com.example.demo.model.ThongBaoKhachHang;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/manage/history/")
@Controller
public class LichSuThanhToanController {

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private GiaoHangService giaoHangService;

    @Autowired
    private GiayChiTietService giayChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LSThanhToanService lsThanhToanService;

    @Autowired
    private ThongBaoServices thongBaoServices;

    @GetMapping("pay")
    private String payHistory(Model model){

        List<LichSuThanhToan> lichSuThanhToanList = lsThanhToanService.getAllLSTT();

        model.addAttribute("lichSuThanhToanList", lichSuThanhToanList);
        showThongBao(model);

        int tongHoaDon = 0;
        int huyHoanTien = 0;
        Double tongTienQRCode = 0.0;

        List<HoaDon> hoaDonList = hoaDonService.listHoaDonOnline();

        for (HoaDon x: hoaDonList) {
            if (x.getTrangThai() == 6 || x.getTrangThai() == 7){
            }else{
                tongTienQRCode += x.getTongTienDG();
                if (x.getHinhThucThanhToan() == 1 && x.getIdHDOld() == null){
                    tongHoaDon ++;
                }
                if (x.getIdHDOld() != null){
                    huyHoanTien ++;
                }
                if (x.getTrangThai() == 4 && x.getIdHDOld() == null){
                    huyHoanTien ++;
                    tongHoaDon ++;
                }

            }
        }


        model.addAttribute("tongHoaDon", tongHoaDon);
        model.addAttribute("tongTienQRCode", tongTienQRCode);
        model.addAttribute("huyHoanTien", huyHoanTien);
        return "manage/manage-pay";
    }

    @GetMapping("delivery")
    private String deliveryHistory(Model model){

        List<LichSuThanhToan> lichSuThanhToanList = lsThanhToanService.getAllLSTT();

        model.addAttribute("lichSuThanhToanList", lichSuThanhToanList);
        showThongBao(model);

        return "manage/manage-delivery";
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
