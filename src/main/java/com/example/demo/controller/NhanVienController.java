package com.example.demo.controller;

import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("manage")
@Controller
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

}
