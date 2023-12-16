package com.example.demo.buyerController;


import com.example.demo.model.*;
import com.example.demo.service.CTGViewModelService;
import com.example.demo.service.GHCTService;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.ThongBaoServices;
import com.example.demo.viewModel.CTGViewModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/buyer")
public class HomeController {

    @Autowired
    private CTGViewModelService ctgViewModelService;

    @Autowired
    private HttpSession session;

    @Autowired
    private GHCTService ghctService;

    @Autowired
    private GiayChiTietService giayChiTietService;

    @Autowired
    private ThongBaoServices thongBaoServices;

    @RequestMapping(value = {"", "/", "/home"})
    public String home(Model model){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        if (khachHang != null){
            String fullName = khachHang.getHoTenKH();
            model.addAttribute("fullNameLogin", fullName);
            GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

            int soThongBao = 0;
            List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);

            List<ThongBaoKhachHang> thongBaoKhachHangs =  thongBaoServices.findByKhachHang(khachHang);
            for (ThongBaoKhachHang x: thongBaoKhachHangs) {
                if (x.getTrangThai() == 1){
                    soThongBao++;
                }
            }
            Integer sumProductInCart = listGHCTActive.size();
            model.addAttribute("sumProductInCart", sumProductInCart);
            model.addAttribute("heartLogged", true);
            model.addAttribute("soThongBao", soThongBao);
            model.addAttribute("listThongBao", thongBaoKhachHangs);

        }else {
            model.addAttribute("messageLoginOrSignin", true);
        }

        List<CTGViewModel> listCTGModelNew = ctgViewModelService.getAllOrderTgNhap();
        List<CTGViewModel> listCTGModelBestSeller = ctgViewModelService.getAllOrderBestSeller();

        List<CTGViewModel> top12CTGModelNew = listCTGModelNew.subList(0, Math.min(listCTGModelNew.size(), 12));
        List<CTGViewModel> top12CTGModelBestSeller = listCTGModelBestSeller.subList(0, Math.min(listCTGModelBestSeller.size(), 12));

        model.addAttribute("listCTGModelNew",top12CTGModelNew);
        model.addAttribute("listCTGModelBestSeller",top12CTGModelBestSeller);
        return "online/index";

    }
}
