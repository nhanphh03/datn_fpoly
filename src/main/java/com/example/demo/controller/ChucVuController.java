package com.example.demo.controller;

import com.example.demo.model.ChatLieu;
import com.example.demo.model.ChucVu;
import com.example.demo.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("manage")
@Controller
public class ChucVuController {
    @Autowired
    private ChucVuService chucVuService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/chuc-vu")
    public String dsChucVu(Model model) {
        List<ChucVu> chucVu = chucVuService.getAllChucVu();
        model.addAttribute("chucVu", chucVu);
        return "manage/chuc-vu";
    }

    @GetMapping("/chuc-vu/viewAdd")
    public String viewAddchucVu(Model model) {
        model.addAttribute("chucVu", new ChucVu());
        return "manage/add-chuc-vu";
    }

    @PostMapping("/chuc-vu/viewAdd/add")
    public String addchucVu(@ModelAttribute("chucVu") ChucVu chucVu) {
        ChucVu chucvu1 = new ChucVu();
        chucvu1.setMaCV(chucVu.getMaCV());
        chucvu1.setTenCV(chucVu.getTenCV());
        chucvu1.setTgThem(new Date());
        chucvu1.setTrangThai(chucVu.getTrangThai());
        chucVuService.save(chucvu1);
        return "redirect:/manage/chuc-vu";
    }

    @GetMapping("/chuc-vu/delete/{id}")
    public String deletechucVu(@PathVariable UUID id) {
        ChucVu chucVu = chucVuService.getByIdChucVu(id);
        chucVu.setTrangThai(0);
        chucVu.setTgSua(new Date());
        chucVuService.save(chucVu);
        return "redirect:/manage/chuc-vu";
    }

    @GetMapping("/chuc-vu/viewUpdate/{id}")
    public String viewUpdatechucVu(@PathVariable UUID id, Model model) {
        ChucVu chucVu = chucVuService.getByIdChucVu(id);
        model.addAttribute("chucVu", chucVu);
        return "manage/update-chuc-vu";
    }

    @PostMapping("/chuc-vu/viewUpdate/{id}")
    public String updateChucVu(@PathVariable UUID id, @ModelAttribute("chucVu") ChucVu chucVu) {
        ChucVu chucVuDb = chucVuService.getByIdChucVu(id);
        if (chucVuDb != null) {
            chucVuDb.setMaCV(chucVu.getMaCV());
            chucVuDb.setTenCV(chucVu.getTenCV());
            chucVuDb.setTgSua(new Date());
            chucVuDb.setTrangThai(chucVu.getTrangThai());
            chucVuService.save(chucVuDb);
        }
        return "redirect:/manage/chuc-vu";
    }


}
