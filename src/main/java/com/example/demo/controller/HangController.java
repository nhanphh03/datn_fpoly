package com.example.demo.controller;

import com.example.demo.model.Giay;
import com.example.demo.model.Hang;
import com.example.demo.service.HangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class HangController {
    @Autowired
    private HangService hangService;

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
        hangService.save(hang);
        return "redirect:/hang";
    }

    @GetMapping("/hang/delete/{id}")
    public String deleteHang(@PathVariable UUID id) {
        hangService.deleteByIdHang(id);
        return "redirect:/hang";
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
            hangDb.setMaHang(hang.getMaHang());
            hangDb.setTenHang(hang.getTenHang());
            hangDb.setTgSua(hang.getTgSua());
            hangDb.setTgThem(hang.getTgThem());
            hangDb.setTrangThai(hang.getTrangThai());
            hangService.save(hangDb);
        }
        return "redirect:/hang";
    }
}
