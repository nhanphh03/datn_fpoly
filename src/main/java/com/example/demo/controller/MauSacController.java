package com.example.demo.controller;

import com.example.demo.model.MauSac;
import com.example.demo.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/manage")
@Controller
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/mau-sac")
    public String dsMauSac(Model model) {
        List<MauSac> mauSac = mauSacService.getALlMauSac();
        model.addAttribute("mauSac", mauSac);
        return "manage/mau-sac";
    }

    @GetMapping("/mau-sac/viewAdd")
    public String viewAddMauSac(Model model) {
        model.addAttribute("mauSac", new MauSac());
        return "manage/add-mau-sac";
    }

    @PostMapping("/mau-sac/viewAdd/add")
    public String addMauSac(@ModelAttribute("mauSac") MauSac mauSac) {
        MauSac mauSac1 = new MauSac();
        mauSac1.setMaMau(mauSac.getMaMau());
        mauSac1.setTenMau(mauSac.getTenMau());
        mauSac1.setTgThem(new Date());
        mauSac1.setTrangThai(mauSac.getTrangThai());
        mauSacService.save(mauSac1);
        return "redirect:/manage/mau-sac";
    }

    @GetMapping("/mau-sac/delete/{id}")
    public String deleteMauSac(@PathVariable UUID id) {
        mauSacService.deleteByIdMauSac(id);
        return "redirect:/manage/mau-sac";
    }

    @GetMapping("/mau-sac/viewUpdate/{id}")
    public String viewUpdateMauSac(@PathVariable UUID id, Model model) {
        MauSac mauSac = mauSacService.getByIdMauSac(id);
        model.addAttribute("mauSac", mauSac);
        return "manage/update-mau-sac";
    }

    @PostMapping("/mau-sac/viewUpdate/{id}")
    public String updateMauSac(@PathVariable UUID id, @ModelAttribute("mauSac") MauSac mauSac) {
        MauSac mauSacDb = mauSacService.getByIdMauSac(id);
        if (mauSacDb != null) {
            mauSacDb.setMaMau(mauSac.getMaMau());
            mauSacDb.setTenMau(mauSac.getTenMau());
            mauSacDb.setTgSua(new Date());
            mauSacDb.setTrangThai(mauSac.getTrangThai());
            mauSacService.save(mauSacDb);
        }
        return "redirect:/manage/mau-sac";
    }
}
