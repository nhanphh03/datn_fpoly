package com.example.demo.controller;

import com.example.demo.model.ChatLieu;
import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.Giay;
import com.example.demo.model.Hang;
import com.example.demo.service.ChatLieuService;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.GiayService;
import com.example.demo.service.HangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/manage")
@Controller
public class GiayController {
    @Autowired
    private GiayService giayService;
    @Autowired
    private HangService hangService;
    @Autowired
    private ChatLieuService chatLieuService;
    @Autowired
    private GiayChiTietService giayChiTietService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/giay")
    public String dsGiay(Model model) {
        List<Giay> giay = giayService.getAllGiay();
        List<Hang> hangs = hangService.getALlHang();
        List<ChatLieu> chatLieus = chatLieuService.getAllChatLieu();
        model.addAttribute("giay", giay);
        model.addAttribute("hang", hangs);
        model.addAttribute("chatLieu", chatLieus);
        return "manage/giay";
    }

    @GetMapping("/giay/viewAdd")
    public String viewAddGiay(Model model) {
        List<Hang> hangs = hangService.getALlHang();
        List<ChatLieu> chatLieus = chatLieuService.getAllChatLieu();
        model.addAttribute("giay", new Giay());
        model.addAttribute("hang", hangs);
        model.addAttribute("chatLieu", chatLieus);
        System.out.println(hangs);
        System.out.println(chatLieus);
        return "manage/add-giay";
    }

    @PostMapping("/giay/viewAdd/add")
    public String addGiay(@ModelAttribute("giay") Giay giay) {
        Giay giay1 = new Giay();
        giay1.setMaGiay(giay.getMaGiay());
        giay1.setTenGiay(giay.getTenGiay());
        giay1.setTgThem(new Date());
        giay1.setHang(giay.getHang());
        giay1.setChatLieu(giay.getChatLieu());
        giay1.setTrangThai(giay.getTrangThai());
        giayService.save(giay1);
        return "redirect:/manage/giay";
    }

    @GetMapping("/giay/delete/{id}")
    public String deleteGiay(@PathVariable UUID id) {
        giayService.deleteByIdGiay(id);
        return "redirect:/manage/giay";
    }

    @GetMapping("/giay/viewUpdate/{id}")
    public String viewUpdateGiay(@PathVariable UUID id, Model model) {
        Giay giay = giayService.getByIdGiay(id);
        List<Hang> hang = hangService.getALlHang();
        List<ChatLieu> chatLieu = chatLieuService.getAllChatLieu();
        model.addAttribute("giay", giay);
        model.addAttribute("hang", hang);
        model.addAttribute("chatLieu", chatLieu);
        return "manage/update-giay";
    }

    @PostMapping("/giay/viewUpdate/{id}")
    public String updateGiay(@PathVariable UUID id, @ModelAttribute("giay") Giay giay) {
        Giay giayDb = giayService.getByIdGiay(id);
        if (giayDb != null) {
            giayDb.setMaGiay(giay.getMaGiay());
            giayDb.setTenGiay(giay.getTenGiay());
            giayDb.setTgSua(giay.getTgSua());
            giayDb.setTrangThai(giay.getTrangThai());
            giayDb.setChatLieu(giay.getChatLieu());
            giayDb.setHang(giay.getHang());
            giayService.save(giayDb);
        }
        return "redirect:/manage/giay";
    }

    @GetMapping("/giay/detail/{id}")
    public String detailGiay(@PathVariable UUID id, Model model) {

        Giay giay = giayService.getByIdGiay(id);
        List<ChiTietGiay> listCTGByGiay = giayChiTietService.getCTGByGiay(giay);

        model.addAttribute("chiTietGiayList", listCTGByGiay);
        return "manage/giay-detail";
    }
}
