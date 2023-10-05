package com.example.demo.controller;

import com.example.demo.model.Giay;
import com.example.demo.model.Hang;
import com.example.demo.service.HangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/manage")
@Controller
public class HangController {
    @Autowired
    private HangService hangService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/hang")
    public String dsHang(Model model) {
        List<Hang> hang = hangService.getALlHang();
        model.addAttribute("hang", hang);
        return "manage/hang";
    }

    @GetMapping("/hang/viewAdd")
    public String viewAddHang(Model model) {
        model.addAttribute("hang", new Hang());
        return "manage/add-hang";
    }

    @PostMapping("/hang/viewAdd/add")
    public String addHang(@ModelAttribute("hang") Hang hang) {
        Hang hang1 = new Hang();
        hang1.setLogoHang(hang.getLogoHang());
        hang1.setMaHang(hang.getMaHang());
        hang1.setTenHang(hang.getTenHang());
        hang1.setTgThem(new Date());
        hang1.setTrangThai(hang.getTrangThai());
        hangService.save(hang1);
        return "redirect:/manage/hang";
    }

    @GetMapping("/hang/delete/{id}")
    public String deleteHang(@PathVariable UUID id) {
        hangService.deleteByIdHang(id);
        return "redirect:/manage/hang";
    }

    @GetMapping("/hang/viewUpdate/{id}")
    public String viewUpdateHang(@PathVariable UUID id, Model model) {
        Hang hang = hangService.getByIdHang(id);
        model.addAttribute("hang", hang);
        return "manage/update-hang";
    }

    @PostMapping("/hang/viewUpdate/{id}")
    public String updateHang(@PathVariable UUID id, @ModelAttribute("hang") Hang hang) {
        Hang hangDb = hangService.getByIdHang(id);
        if (hangDb != null) {
            hangDb.setLogoHang(hang.getLogoHang());
            hangDb.setMaHang(hang.getMaHang());
            hangDb.setTenHang(hang.getTenHang());
            hangDb.setTgSua(new Date());
            hangDb.setTrangThai(hang.getTrangThai());
            hangService.save(hangDb);
        }
        return "redirect:/manage/hang";
    }
}
