package com.example.demo.controller;

import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ban-hang")
public class ThongKeController {
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private GiayChiTietRepository giayChiTietRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @GetMapping("/thongke")
    private String getTong(Model model){
        model.addAttribute("tong",khachHangRepository.getTongKH());
        model.addAttribute("tonggiay",giayChiTietRepository.getTongGiay());

        //làm tròn doanh thu
        Double Resulttdt = Math.ceil(hoaDonChiTietRepository.getTongTien()*100.0)/100.0;
        Double Resultdtt = Math.ceil(hoaDonChiTietRepository.getDoanhThuThang()*100.0)/100.0;
        model.addAttribute("tdt",Resulttdt);
        model.addAttribute("dtt",Resultdtt);
//
//        // top 5 sp bán chạy
//
//        //chỉ số ở hóa đơn
        model.addAttribute("hdht",hoaDonRepository.getAllHoaDonDaThanhToan());
        model.addAttribute("hdc",hoaDonRepository.getAllHoaDonCho());
////  hoa don
//        model.addAttribute("hoaDon",hoaDonRepository.findAll());
//        model.addAttribute("hoaDonCT2",hoaDonChiTietService.getAllHDChuaThanhToan());
//        model.addAttribute("hoaDonCT1",hoaDonChiTietService.getAllHDDaThanhToan());
        return "manage/ThongKe/index";
    }








//    @GetMapping("/hoadoncho/filter")
//    public String searchHoaDonCho(Model model, @RequestParam(name = "searchTerm") String searchTerm) {
//        List<HoaDon> filteredHoaDonCho;
//        if ("Giày".equals(searchTerm) && "Size".equals(searchTerm) && "Màu Sắc".equals(searchTerm)) {
//
//            filteredHoaDonCho = hoaDonCho.getAll();
//        } else {
//
//            filteredHoaDonCho = hoaDonCho.fillterHoaDonCho(searchTerm);
//        }
//        model.addAttribute("HoaDon", filteredHoaDonCho);
//        model.addAttribute("HoaDonAll", hoaDonCho.getAll());
//        return "manage/giay";
//    }
}
