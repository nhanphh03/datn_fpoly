package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class KhachHangController {

    @GetMapping("/khach-hang")
    public String dsKhachHang() {
        return "manage/khach-hang";
    }
}
