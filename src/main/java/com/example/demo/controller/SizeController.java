package com.example.demo.controller;

import com.example.demo.model.Size;
import com.example.demo.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@RequestMapping("/manage")
@Controller
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/size")
    public String dsSize(Model model) {
        List<Size> size = sizeService.getAllSize();
        model.addAttribute("size", size);
        return "manage/size-giay";
    }

    @GetMapping("/size/viewAdd")
    public String viewAddSize(Model model) {
        model.addAttribute("size", new Size());
        return "manage/add-size";
    }

    @PostMapping("/size/viewAdd/add")
    public String addSize(@ModelAttribute("size") Size size) {
        sizeService.save(size);
        return "redirect:/size";
    }

    @GetMapping("/size/delete/{id}")
    public String deleteSize(@PathVariable UUID id) {
        sizeService.deleteByIdSize(id);
        return "redirect:/size";
    }

    @GetMapping("/size/viewUpdate/{id}")
    public String viewUpdateSize(@PathVariable UUID id, Model model) {
        Size size = sizeService.getByIdSize(id);
        model.addAttribute("size", size);
        return "manage/update-size";
    }

    @PostMapping("/size/viewUpdate/{id}")
    public String updateSize(@PathVariable UUID id, @ModelAttribute("size") Size size) {
        Size sizeDb = sizeService.getByIdSize(id);
        if (sizeDb != null) {
            sizeDb.setMaSize(size.getMaSize());
            sizeDb.setTenSize(size.getTenSize());
            sizeDb.setTgSua(size.getTgSua());
            sizeDb.setTgThem(size.getTgThem());
            sizeService.save(sizeDb);
        }
        return "redirect:/size";
    }
}
