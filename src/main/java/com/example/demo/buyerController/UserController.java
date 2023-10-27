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
public class UserController {

    @Autowired
    private HttpSession session;

    @Autowired
    private GHCTService ghctService;

    @GetMapping("/setting")
    private String getSettingAccount(Model model){

        UserForm(model);

        model.addAttribute("pagesettingAccount",true);
        return "online/user";
    }

    @RequestMapping(value = {"/purchase", "/purchase/all"})
    private String getPurchaseAccount(Model model){

        UserForm(model);

        model.addAttribute("pagePurchaseUser",true);
        model.addAttribute("purchaseAll",true);
        model.addAttribute("type1","active");

        return "online/user";
    }

    @GetMapping("/addresses")
    private String getAddressAccount(Model model){

        UserForm(model);

        model.addAttribute("pageAddressesUser",true);
        return "online/user";
    }

    @GetMapping("/notification")
    private String getNotidicationAccount(Model model){

        UserForm(model);

        model.addAttribute("pageNotificationUser",true);
        return "online/user";
    }

    @GetMapping("/voucher")
    private String getVoucherAccount(Model model){

        UserForm(model);

        model.addAttribute("pageVoucherUser",true);

        return "online/user";
    }

    @GetMapping("/coins")
    private String getCoinsAccount(Model model){

        UserForm(model);

        model.addAttribute("pageCoinsUser",true);

        return "online/user";
    }

    @GetMapping("/purchase/pay")
    private String getPurchasePay(Model model){

        UserForm(model);

        model.addAttribute("pagePurchaseUser",true);
        model.addAttribute("purchasePay",true);
        model.addAttribute("type2","active");
        return "online/user";
    }

    @GetMapping("/purchase/ship")
    private String getPurchaseShip(Model model){

        UserForm(model);

        model.addAttribute("pagePurchaseUser",true);
        model.addAttribute("purchaseShip",true);
        model.addAttribute("type3","active");
        return "online/user";
    }

    @GetMapping("/purchase/receive")
    private String getPurchaseReceive(Model model){

        UserForm(model);

        model.addAttribute("pagePurchaseUser",true);
        model.addAttribute("purchaseReceive",true);
        model.addAttribute("type4","active");
        return "online/user";
    }

    @GetMapping("/purchase/completed")
    private String getPurchaseCompleted(Model model){

        UserForm(model);

        model.addAttribute("pagePurchaseUser",true);
        model.addAttribute("purchaseCompleted",true);
        model.addAttribute("type5","active");
        return "online/user";
    }

    @GetMapping("/purchase/refund")
    private String getPurchaseRefund(Model model){

        UserForm(model);

        model.addAttribute("pagePurchaseUser",true);
        model.addAttribute("purchaseRefund",true);
        model.addAttribute("type6","active");
        return "online/user";
    }

    private void UserForm(Model model){
        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;
        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());

        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        Integer sumProductInCart = listGHCTActive.size();
        model.addAttribute("sumProductInCart", sumProductInCart);
    }
}

