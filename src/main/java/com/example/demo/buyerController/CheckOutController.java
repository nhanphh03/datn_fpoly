package com.example.demo.buyerController;

import com.example.demo.model.KhachHang;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyer")
public class CheckOutController {

    @Autowired
    private HttpSession session;

    @GetMapping("/checkout")
    public String checkOutPage(Model model){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());
        return "online/checkout";
    }
}
