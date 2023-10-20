package com.example.demo.buyerController;


import com.example.demo.model.*;
import com.example.demo.service.*;
import com.example.demo.viewModel.CTGViewModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/buyer")
public class ShopController {

    @Autowired
    private HangService hangService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private CTGViewModelService ctgViewModelService;

    @Autowired
    private GHCTService ghctService;

    @Autowired
    private HttpSession session;

    @GetMapping("/shop")
    private String getShopBuyer(Model model,
                                @RequestParam(name= "pageSize", defaultValue = "9") Integer pageSize,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum){
        showDataBuyerShop(model);
        checkKhachHangLogged(model);
        model.addAttribute("pageNumber", true);

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<CTGViewModel> page = ctgViewModelService.getAllPage(pageable);

        int sumProduct = page.getSize();
        System.out.println(sumProduct);

        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("listCTGModel", page.getContent());

        List<CTGViewModel> listCTGModelSoldOff = ctgViewModelService.getAllSoldOff();
        model.addAttribute("listCTGModelSoldOff", listCTGModelSoldOff);

        return "online/shop";
    }

    @GetMapping("/shop/brand/{idHang}")
    private String getShopBrandBuyer(Model model,@PathVariable String idHang){
        showDataBuyerShop(model);
        checkKhachHangLogged(model);

        List<CTGViewModel> listCTGByBrand = ctgViewModelService.findByIDHang(idHang);
        int sumProductByHang = listCTGByBrand.size();
        model.addAttribute("sumProduct", sumProductByHang);

        model.addAttribute("listCTGModel", listCTGByBrand);

        List<CTGViewModel> listCTGModelSoldOff = ctgViewModelService.getAllSoldOff();
        model.addAttribute("listCTGModelSoldOff", listCTGModelSoldOff);
        return "online/shop";
    }

    @GetMapping("/shop/highToLow")
    private String getShopByPriceHighToLow(Model model,
                                           @RequestParam(name= "pageSize", defaultValue = "9") Integer pageSize,
                                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum){
        showDataBuyerShop(model);
        checkKhachHangLogged(model);
        model.addAttribute("pageNumberHTL", true);

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<CTGViewModel> page = ctgViewModelService.getAllByPriceHighToLow(pageable);

        model.addAttribute("totalPageHTL", page.getTotalPages());
        model.addAttribute("listCTGModel", page.getContent());

        List<CTGViewModel> listCTGModelSoldOff = ctgViewModelService.getAllSoldOff();
        model.addAttribute("listCTGModelSoldOff", listCTGModelSoldOff);

        return "online/shop";
    }

    @GetMapping("/shop/lowToHigh")
    private String getShopByPriceLowToHigh(Model model,
                                           @RequestParam(name= "pageSize", defaultValue = "9") Integer pageSize,
                                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum){
        showDataBuyerShop(model);
        checkKhachHangLogged(model);

        model.addAttribute("pageNumberLTH", true);

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<CTGViewModel> page = ctgViewModelService.getAllByPriceLowToHigh(pageable);

        model.addAttribute("totalPageLTH", page.getTotalPages());
        model.addAttribute("listCTGModel", page.getContent());

        List<CTGViewModel> listCTGModelSoldOff = ctgViewModelService.getAllSoldOff();
        model.addAttribute("listCTGModelSoldOff", listCTGModelSoldOff);

        return "online/shop";
    }



    private void showDataBuyerShop(Model model){

        List<Hang> listHang = hangService.getAllActive();
        model.addAttribute("listBrand", listHang);

        List<Size> listSize = sizeService.getAllSizeActiveOrderByName();
        model.addAttribute("listSize", listSize);

        List<MauSac> listColor = mauSacService.getMauSacActive();
        model.addAttribute("listColor", listColor);
    }

    private void checkKhachHangLogged(Model model){
        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        if (khachHang != null){
            String fullName = khachHang.getHoTenKH();
            model.addAttribute("fullNameLogin", fullName);

            GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

            List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);

            Integer sumProductInCart = listGHCTActive.size();
            model.addAttribute("heartLogged", true);
            model.addAttribute("sumProductInCart", sumProductInCart);

        }else {
            model.addAttribute("messageLoginOrSignin", true);
        }
    }
}
