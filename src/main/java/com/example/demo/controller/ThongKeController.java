package com.example.demo.controller;

import com.example.demo.repository.GiayChiTietRepository;
import com.example.demo.repository.GiayRepository;
import com.example.demo.repository.HinhAnhRepository;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.thanhdvph27747.HoaDonChiTietRepository;
import com.example.demo.repository.thanhdvph27747.SPBanChay;
import com.example.demo.service.thanhdvph27747.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("ban-hang")
public class ThongKeController {
    @Autowired
    private TongKhachHangSer tong;

    @Autowired
    private TongGiaySer tonggiay;

    @Autowired
    private TongDoanhThuSer tdt;

    @Autowired
    private DoanhThuThangSer dtt;

   // qly hóa đơn
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonChiTietRepository hdct;

    @Autowired
    private HoaDonChoService hoadoncho;

    @Autowired
    private GiayChiTietRepository giayChiTietRepository;
    @Autowired
    private SPBanChaySer spBanChaySer;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private GiayRepository giayRepository;

    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @GetMapping("/thongke")
    private String getTong(Model model){
        model.addAttribute("tong",tong.getTong());
        model.addAttribute("tonggiay",tonggiay.getTongGiay());

        //làm tròn doanh thu
        Double Resulttdt = Math.ceil(tdt.TongDoanhThu()*100.0)/100.0;
        Double Resultdtt = Math.ceil(dtt.getDoanhThuThang()*100.0)/100.0;
        model.addAttribute("tdt",Resulttdt);
        model.addAttribute("dtt",Resultdtt);

        // top 5 sp bán chạy

        //chỉ số ở hóa đơn
        model.addAttribute("hdht",hoadoncho.getAllHoaDon());
        model.addAttribute("hdc",hoadoncho.getAllHoaDonCho());
//  hoa don
        model.addAttribute("hoaDon",hoaDonRepository.findAll());
        model.addAttribute("hoaDonCT2",hoaDonChiTietService.getAllHDChuaThanhToan());
        model.addAttribute("hoaDonCT1",hoaDonChiTietService.getAllHDDaThanhToan());
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
