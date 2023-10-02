package com.example.demo.controller;

import com.example.demo.model.ChatLieu;
import com.example.demo.model.Giay;
import com.example.demo.service.GiayService;
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
public class GiayController {
    @Autowired
    private GiayService giayService;

    @GetMapping("/giay")
    public String dsGiay(Model model) {
        List<Giay> giay = giayService.getAllGiay();
        model.addAttribute("giay", giay);
        return "manage/giay";
    }

    @GetMapping("/giay/viewAdd")
    public String viewAddGiay(Model model) {
        model.addAttribute("giay", new Giay());
        return "manage/add-giay";
    }

    @PostMapping("/giay/viewAdd/add")
    public String addGiay(@ModelAttribute("giay") Giay giay) {
        giayService.save(giay);
        return "redirect:/giay";
    }

    @GetMapping("/giay/delete/{id}")
    public String deleteGiay(@PathVariable UUID id) {
        giayService.deleteByIdGiay(id);
        return "redirect:/giay";
    }

    @GetMapping("/giay/viewUpdate/{id}")
    public String viewUpdateGiay(@PathVariable UUID id, Model model) {
        Giay giay = giayService.getByIdGiay(id);
        model.addAttribute("giay", giay);
        return "manage/update-giay";
    }

    @PostMapping("/giay/viewUpdate/{id}")
    public String updateGiay(@PathVariable UUID id, @ModelAttribute("giay") Giay giay) {
        Giay giayDb = giayService.getByIdGiay(id);
        if (giayDb != null) {
            giayDb.setMaGiay(giay.getMaGiay());
            giayDb.setTenGiay(giay.getTenGiay());
            giayDb.setTgSua(giay.getTgSua());
            giayDb.setTgThem(giay.getTgThem());
            giayDb.setTrangThai(giay.getTrangThai());
            giayDb.setChatLieu(giay.getChatLieu());
            giayDb.setHang(giay.getHang());
            giayService.save(giayDb);
        }
        return "redirect:/giay";
    }
}
