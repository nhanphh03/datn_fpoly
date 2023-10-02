package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offline")
public class KhachHangController {

    @GetMapping("/index")
    public String hienThi(){
        return "offline/index";
    }
}
