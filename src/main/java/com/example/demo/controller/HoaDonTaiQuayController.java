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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/manage/bill")
@Controller
public class HoaDonTaiQuayController {
    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private KhuyenMaiChiTietHoaDonService khuyenMaiChiTietHoaDonService;

    @GetMapping("/offline")
    public String getAll(Model model){
        List<HoaDon> hoaDonList = hoaDonService.getAllHoaDonOffLine();

        List<HoaDon> listHoaDonThanhCong = new ArrayList<>();
        List<HoaDon> listHoaDonCho = new ArrayList<>();
        List<HoaDon> listHoaDonHuy = new ArrayList<>();

        int tongHoaDon = hoaDonList.size();
        Double soTienDaThanhToan = 0.0;
        int soLuongHoaDonCho = 0;
        int soLuongHoaDonThanhCong = 0;
        int soLuongHoaDonHuy = 0;
        int soLuongSanPhamDaBan = 0;

        for (HoaDon x: hoaDonList) {
            if (x.getTrangThai() == 0){
                soLuongHoaDonCho ++;
                listHoaDonCho.add(x);
            }
            if (x.getTrangThai() == 1){
                soLuongHoaDonThanhCong ++;
                soTienDaThanhToan += x.getTongTienDG();
                listHoaDonThanhCong.add(x);
                soLuongSanPhamDaBan += x.getTongSP();
            }
            if(x.getTrangThai() == 2){
                soLuongHoaDonHuy ++;
                listHoaDonHuy.add(x);
            }
        }

        model.addAttribute("soLuongHoaDonTaiQuay",tongHoaDon);
        model.addAttribute("tongTienBanTaiQuay",soTienDaThanhToan);
        model.addAttribute("tongSanPhamDaBan",soLuongSanPhamDaBan);
        model.addAttribute("hoaDonThanhCong",soLuongHoaDonThanhCong);
        model.addAttribute("hoaDonChoTaiQuay",soLuongHoaDonCho);
        model.addAttribute("hoaDonHuy",soLuongHoaDonHuy);

        model.addAttribute("listHoaDonThanhCong",listHoaDonThanhCong);
        model.addAttribute("listHoaDonCho",listHoaDonCho);
        model.addAttribute("listHoaDonHuy",listHoaDonHuy);
        model.addAttribute("hoaDonList",hoaDonList);
        return "manage/manage-bill-offline";
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

    @GetMapping("/offline/print/{idHD}")
    private String printBillOnline(Model model, @PathVariable UUID idHD){

        HoaDon hoaDon = hoaDonService.getOne(idHD);

        model.addAttribute("billPrint", hoaDon);

        return "manage/managePrintBillOffline";
    }

}
