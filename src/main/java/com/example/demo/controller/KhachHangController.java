package com.example.demo.controller;

import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("/khach-hang")
    public String dsKhachHang() {
        return "manage/khach-hang";
    }
}
