package com.example.demo.buyerController;


import com.example.demo.model.GioHang;
import com.example.demo.model.GioHangChiTiet;
import com.example.demo.model.KhachHang;
import com.example.demo.service.CTGViewModelService;
import com.example.demo.service.GHCTService;
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

import java.util.List;

@Controller
@RequestMapping("/buyer")
public class HomeController {

    @Autowired
    private CTGViewModelService ctgViewModelService;

    @Autowired
    private HttpSession session;

    @Autowired
    private GHCTService ghctService;

    @RequestMapping(value = {"", "/", "/indexBuyer", "/homeBuyer"})
    public String home(Model model){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        if (khachHang != null){
            String fullName = khachHang.getHoTenKH();
            model.addAttribute("fullNameLogin", fullName);
            GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

            List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);

            Integer sumProductInCart = listGHCTActive.size();
            model.addAttribute("sumProductInCart", sumProductInCart);

        }else {
            model.addAttribute("messageLoginOrSignin", true);
        }

        List<CTGViewModel> listCTGViewModel = ctgViewModelService.getAll();
        model.addAttribute("listCTGModel",listCTGViewModel);
        return "online/index";
    }
}
