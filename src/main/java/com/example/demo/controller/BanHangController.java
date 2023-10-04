package com.example.demo.controller;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.HoaDon;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.HangService;
import com.example.demo.service.HoaDonService;
import com.example.demo.viewModel.CTSPViewModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ban-hang")
public class BanHangController {

    @Autowired
    private HttpSession session;

    @Autowired
    private HangService hangService;

    @Autowired
    private GiayChiTietService giayChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/offline")
    public String offline(){
        return "manage/activities";
    }
    @GetMapping("/hien-thi")
    public String hienThi(Model model){
        List<ChiTietGiay> getAll = giayChiTietService.getAllChiTietGiay();
        model.addAttribute("listSanPham",getAll);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        model.addAttribute("listHang", hangService.getALlHang());
        return "offline/index";
    }

    @GetMapping("/add-cart")
    public String taoHoaDon(Model model) {
        List<HoaDon> listHD = hoaDonService.getListHoaDonChuaThanhToan();
        if (listHD.size() < 3) {
            String ma = String.valueOf(Math.floor(((Math.random() * 899999) + 100000)));
            Date ngayTao = new Date();
            int tinhTrang = 1;
            HoaDon hd = new HoaDon();
            hd.setMaHD("hd" + ma);
            hd.setTgTao(ngayTao);
            hd.setTrangThai(tinhTrang);
            hoaDonService.add(hd);
            session.setAttribute("message", "Tạo hóa đơn thành công");
        } else if (listHD.size() == -1) {
            session.setAttribute("message", "Hãy tạo hóa đơn");
        } else {
            session.setAttribute("message", "Quá số lượng");
        }
        model.addAttribute("listHoaDon", listHD);
        return "offline/index";
    }

}
