package com.example.demo.buyerController;


import com.example.demo.model.Hang;
import com.example.demo.model.MauSac;
import com.example.demo.model.Size;
import com.example.demo.service.CTGViewModelService;
import com.example.demo.service.HangService;
import com.example.demo.service.MauSacService;
import com.example.demo.service.SizeService;
import com.example.demo.viewModel.CTGViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buyer")
public class ShopController {

//    @Autowired
//    private CT
    @Autowired
    private HangService hangService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private CTGViewModelService ctgViewModelService;

    @GetMapping("/shop")
    private String getShopBuyer(Model model){
        List<Hang> listHang = hangService.getAllActive();
        model.addAttribute("listBrand", listHang);

        List<Size> listSize = sizeService.getAllSize();
        model.addAttribute("listSize", listSize);

        List<MauSac> listColor = mauSacService.getMauSacActive();
        model.addAttribute("listColor", listColor);

        List<CTGViewModel> listCTGViewModel = ctgViewModelService.getAll();
        model.addAttribute("listCTGModel",listCTGViewModel);

        //listHang
        return "online/shop";
    }
}
