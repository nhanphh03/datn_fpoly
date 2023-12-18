package com.example.demo.controller;

import com.example.demo.model.NhanVien;
import com.example.demo.model.ThongBaoKhachHang;
import com.example.demo.service.ThongBaoServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class AdminController {
    @Autowired
    private HttpSession session;

    @Autowired
    private ThongBaoServices thongBaoServices;

    @RequestMapping(value = {"", "/", "/home"})
    public String hienThi(Model model) {
        showThongBao(model);
        return "manage/activities";
    }

    private void showThongBao(Model model){
        int soThongBao = 0;

        List<ThongBaoKhachHang> thongBaoKhachHangs =  thongBaoServices.getAll();
        for (ThongBaoKhachHang x: thongBaoKhachHangs) {
            if (x.getTrangThai() == 3){
                soThongBao++;
            }
        }

        model.addAttribute("soThongBao", soThongBao);
        model.addAttribute("listThongBao", thongBaoKhachHangs);
    }
}
