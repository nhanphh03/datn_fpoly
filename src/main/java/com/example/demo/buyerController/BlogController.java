package com.example.demo.buyerController;


import com.example.demo.model.GioHang;
import com.example.demo.model.GioHangChiTiet;
import com.example.demo.model.KhachHang;
import com.example.demo.service.GHCTService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/buyer")
public class BlogController {

    @Autowired
    private HttpSession session;

    @Autowired
    private GHCTService ghctService;

    @GetMapping("/blog/blog-details")
    private String getBlogDetail(Model model){

        checkLogin(model);

        return "/online/blog-details";
    }

    @GetMapping("/about")
    private String getAbout(Model model){

        checkLogin(model);

        return "/online/about";
    }

    @GetMapping("/contact")
    private String getContact(Model model){

        checkLogin(model);

        return "online/contact";
    }

    @GetMapping("/blog")
    private String getBlog(Model model){

        checkLogin(model);

        return "/online/blog";
    }


    private void checkLogin(Model model){
        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");

        if (khachHang != null){
            String fullName = khachHang.getHoTenKH();
            model.addAttribute("fullNameLogin", fullName);
            GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

            List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
            model.addAttribute("heartLogged", true);
            model.addAttribute("buyNowAddCartLogged", true);
            Integer sumProductInCart = listGHCTActive.size();
            model.addAttribute("sumProductInCart", sumProductInCart);

        }else {
            model.addAttribute("messageLoginOrSignin", true);
        }
    }
}
