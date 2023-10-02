package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ban-hang")
public class BanHangController {
    @GetMapping("/hien-thi")
    public String hienthi(){
        return "offline/index";
    }
}
