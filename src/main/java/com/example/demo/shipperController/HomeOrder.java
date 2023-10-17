package com.example.demo.shipperController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class HomeOrder {

    @RequestMapping(value = {"", "/", "/home"})
    private String getHomeShipping(Model model){
        return "transportation/index";
    }
}
