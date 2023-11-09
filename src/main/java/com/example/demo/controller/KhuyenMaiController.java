package com.example.demo.controller;

import com.example.demo.model.KhuyenMai;
import com.example.demo.service.KhuyenMaiService;
import com.example.demo.service.LoaiKhuyenMaiService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/manage/")
@Controller
public class KhuyenMaiController {

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @Autowired
    private LoaiKhuyenMaiService loaiKhuyenMaiService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("voucher")
    private String getPageVoucher(Model model){

        List<KhuyenMai> khuyenMaiList = khuyenMaiService.getAllKhuyenMai();

        model.addAttribute("", khuyenMaiList);

        return "manage/manageVoucher";
    }

    @GetMapping("voucher-bill")
    private String getPageVoucherBill(Model model){
        return "manage/manageVoucherBill";
    }

    @GetMapping("voucher-product")
    private String getPageVoucherProduct(Model model){
        return "manage/manageVoucherSP";
    }
}
