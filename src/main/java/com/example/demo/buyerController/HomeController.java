package com.example.demo.buyerController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyer")
public class HomeController {

    @RequestMapping(value = {"", "/", "/indexBuyer", "/homeBuyer"})
    public String home(){
        return "online/index";
    }
}
