package com.example.demo.buyerController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyer")
public class ShopController {

//    @Autowired
//    private CT

    @GetMapping("/shop")
    private String getShopBuyer(Model model){
        return "online/shop";
    }
}
