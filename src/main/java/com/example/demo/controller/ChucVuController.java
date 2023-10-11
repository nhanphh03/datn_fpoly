package com.example.demo.controller;

import com.example.demo.model.ChucVu;
import com.example.demo.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("manage")
@Controller
public class ChucVuController {
    @Autowired
    private ChucVuService chucVuService;

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
        chucVuService.save(chucVu);
        return "redirect:/chuc-vu";
    }

    @GetMapping("/chuc-vu/delete/{id}")
    public String deletechucVu(@PathVariable UUID id) {
        chucVuService.deleteByIdChucVu(id);
        return "redirect:/chuc-vu";
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
            chucVuDb.setTgSua(chucVu.getTgSua());
            chucVuDb.setTgThem(chucVu.getTgThem());
            chucVuDb.setTrangThai(chucVu.getTrangThai());
            chucVuService.save(chucVuDb);
        }
        return "redirect:/chuc-vu";
    }


}
