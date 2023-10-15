package com.example.demo.buyerController;

import com.example.demo.model.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/buyer")
public class ShopFAController {

    @Autowired
    private HttpSession session;

    @Autowired
    private GHCTService ghctService;

    @Autowired
    private HangService hangService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private LuotXemFAService luotXemFAService;

    @GetMapping("/fa&recently")
    private String getShopFA(Model model){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");

        showDataBuyerShop(model);

        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;
        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());

        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        Integer sumProductInCart = listGHCTActive.size();
        model.addAttribute("sumProductInCart", sumProductInCart);

        List<LuotXemFA> listRencently = luotXemFAService.getAllActiveByFAOrRV(khachHang, 1);

        List<LuotXemFA> listFA = luotXemFAService.getAllActiveByFAOrRV(khachHang, 0);

        model.addAttribute("listRencently", listRencently);

        model.addAttribute("listFA", listFA);

        //model.addAttribute("listCartDetail", listGHCTActive);


        return "/online/shopFA";
    }

    private void showDataBuyerShop(Model model){
        List<Hang> listHang = hangService.getAllActive();
        model.addAttribute("listBrand", listHang);

        List<Size> listSize = sizeService.getAllSizeActiveOrderByName();
        model.addAttribute("listSize", listSize);

        List<MauSac> listColor = mauSacService.getMauSacActive();
        model.addAttribute("listColor", listColor);
    }


}
