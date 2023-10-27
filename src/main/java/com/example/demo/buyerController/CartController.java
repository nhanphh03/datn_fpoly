package com.example.demo.buyerController;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.GioHang;
import com.example.demo.model.GioHangChiTiet;
import com.example.demo.model.KhachHang;
import com.example.demo.service.GHCTService;
import com.example.demo.service.GiayChiTietService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/buyer")
public class CartController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private GHCTService ghctService;

    @Autowired
    private GiayChiTietService gctService;

    @GetMapping("/cart")
    private String getShoppingCart(Model model){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        Integer sumProductInCart = listGHCTActive.size();

        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());
        model.addAttribute("sumProductInCart", sumProductInCart);
        model.addAttribute("listCartDetail", listGHCTActive);


        return "/online/shopping-cart";
    }

    @GetMapping("/cart/options/{idProduct}")
    private String getOptionProduct(Model model, @PathVariable UUID idProuduct){

//        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(idProuduct);
//        Integer sumProductInCart = listGHCTActive.size();

        return "/online/shopping-cart";
    }

    @GetMapping("/cart/delete/{idCTG}")
    private String deleteInCard(Model model, @PathVariable UUID idCTG){

        ChiTietGiay chiTietGiay = gctService.getByIdChiTietGiay(idCTG);
        GioHangChiTiet gioHangChiTiet = ghctService.findByCTSPActive(chiTietGiay);
        gioHangChiTiet.setTrangThai(0);
        ghctService.addNewGHCT(gioHangChiTiet);

        return "redirect:/buyer/cart";
    }
}
