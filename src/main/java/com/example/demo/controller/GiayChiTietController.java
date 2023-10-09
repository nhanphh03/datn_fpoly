package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@RequestMapping("/manage")
@Controller
public class GiayChiTietController {
    @Autowired
    private GiayChiTietService giayChiTietService;
    @Autowired
    private GiayService giayService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private HinhAnhService hinhAnhService;
    @Autowired
    private HangService hangService;
    @Autowired
    private ChatLieuService chatLieuService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    private String getReferer() {
        // Lấy đường dẫn trang trước đó từ header "referer"
        // Trong trường hợp không có header này, bạn có thể xác định một đích mặc định để chuyển hướng
        String referer = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        return StringUtils.isEmpty(referer) ? "/default" : referer;
    }

    @GetMapping("/giay-chi-tiet")
    public String dsGiayChiTiet(Model model) {
        List<ChiTietGiay> items = giayChiTietService.getAllChiTietGiay();
        model.addAttribute("items", items);
        return "manage/giay-chi-tiet";
    }

    @GetMapping("/giay-chi-tiet/detail/{id}")
    public String detailGiayChiTiet(@PathVariable UUID id, Model model) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        model.addAttribute("giayChiTietDetail", chiTietGiay);
        return "manage/giay-chi-tiet-detail";
    }

    @GetMapping("/chi-tiet-giay/detail/{id}")
    public String detailChiTietGiay(@PathVariable UUID id, Model model) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        model.addAttribute("giayChiTietDetail", chiTietGiay);
        return "manage/giay-chi-tiet-detail";
    }

    @GetMapping("/giay-chi-tiet/viewAdd")
    public String viewAddGiayChiTiet(Model model) {
        model.addAttribute("giayChiTiet", new ChiTietGiay());
        model.addAttribute("giay", giayService.getAllGiay());
        model.addAttribute("mauSac", mauSacService.getALlMauSac());
        model.addAttribute("size", sizeService.getAllSize());
        model.addAttribute("hinhAnh", hinhAnhService.getAllHinhAnh());
        model.addAttribute("giayAdd", new Giay());
        model.addAttribute("mauSacAdd", new MauSac());
        model.addAttribute("sizeAdd", new Size());
        model.addAttribute("hinhAnhAdd", new HinhAnh());
        model.addAttribute("hangAdd", new Hang());
        model.addAttribute("chatLieuAdd", new ChatLieu());
        model.addAttribute("hang", hangService.getALlHang());
        model.addAttribute("chatLieu", chatLieuService.getAllChatLieu());
        return "manage/add-giay-chi-tiet";
    }

    @GetMapping("/chi-tiet-giay/viewAdd/{id}")
    public String viewAddChiTietGiay(@PathVariable UUID id, Model model) {
        Giay idGiay = giayService.getByIdGiay(id);
        model.addAttribute("chiTietGiay", new ChiTietGiay());
        model.addAttribute("giay", idGiay);
        model.addAttribute("mauSac", mauSacService.getALlMauSac());
        model.addAttribute("size", sizeService.getAllSize());
        model.addAttribute("hinhAnh", hinhAnhService.getAllHinhAnh());
        model.addAttribute("giayAdd", new Giay());
        model.addAttribute("mauSacAdd", new MauSac());
        model.addAttribute("sizeAdd", new Size());
        model.addAttribute("hinhAnhAdd", new HinhAnh());
        model.addAttribute("hangAdd", new Hang());
        model.addAttribute("chatLieuAdd", new ChatLieu());
        model.addAttribute("hang", hangService.getALlHang());
        model.addAttribute("chatLieu", chatLieuService.getAllChatLieu());
        return "manage/add-chi-tiet-giay";
    }


    @PostMapping("/giay-chi-tiet/viewAdd/add")
    public String addGiayChiTiet(@ModelAttribute("giayChiTiet") ChiTietGiay chiTietGiay) {
        ChiTietGiay chiTietGiay1 = new ChiTietGiay();
        chiTietGiay1.setGiay(chiTietGiay.getGiay());
        chiTietGiay1.setNamSX(chiTietGiay.getNamSX());
        chiTietGiay1.setNamBH(chiTietGiay.getNamBH());
        chiTietGiay1.setTrongLuong(chiTietGiay.getTrongLuong());
        chiTietGiay1.setGiaBan(chiTietGiay.getGiaBan());
        chiTietGiay1.setSoLuong(chiTietGiay.getSoLuong());
        chiTietGiay1.setTrangThai(chiTietGiay.getTrangThai());
        chiTietGiay1.setMauSac(chiTietGiay.getMauSac());
        chiTietGiay1.setHinhAnh(chiTietGiay.getHinhAnh());
        chiTietGiay1.setSize(chiTietGiay.getSize());
        chiTietGiay1.setTgThem(new Date());
        giayChiTietService.save(chiTietGiay1);

        return "redirect:/manage/giay-chi-tiet";
    }

    @PostMapping("/chi-tiet-giay/viewAdd/add")
    public String addChiTietGiay( @ModelAttribute("chiTietGiay") ChiTietGiay chiTietGiay, Model model) {
        ChiTietGiay chiTietGiay2 = new ChiTietGiay();
        chiTietGiay2.setGiay(chiTietGiay.getGiay());
        chiTietGiay2.setNamSX(chiTietGiay.getNamSX());
        chiTietGiay2.setNamBH(chiTietGiay.getNamBH());
        chiTietGiay2.setTrongLuong(chiTietGiay.getTrongLuong());
        chiTietGiay2.setGiaBan(chiTietGiay.getGiaBan());
        chiTietGiay2.setSoLuong(chiTietGiay.getSoLuong());
        chiTietGiay2.setTrangThai(chiTietGiay.getTrangThai());
        chiTietGiay2.setMauSac(chiTietGiay.getMauSac());
        chiTietGiay2.setHinhAnh(chiTietGiay.getHinhAnh());
        chiTietGiay2.setSize(chiTietGiay.getSize());
        chiTietGiay2.setTgThem(new Date());
        giayChiTietService.save(chiTietGiay2);

        return "redirect:/manage/giay-chi-tiet";
    }

    @PostMapping("/chi-tiet-giay/giay/viewAdd/add")
    public String addGiay(@ModelAttribute("giayAdd") Giay giay, Model model) {
        Giay giay1 = new Giay();
        giay1.setMaGiay(giay.getMaGiay());
        giay1.setTenGiay(giay.getTenGiay());
        giay1.setTgThem(new Date());
        giay1.setHang(giay.getHang());
        giay1.setChatLieu(giay.getChatLieu());
        giay1.setTrangThai(giay.getTrangThai());
        giayService.save(giay1);
        return "redirect:/manage/chi-tiet-giay/viewAdd";
    }

    @PostMapping("/giay-chi-tiet/giay/viewAdd/add")
    public String addGiayCT(@ModelAttribute("giayAdd") Giay giay, Model model) {
        Giay giay1 = new Giay();
        giay1.setMaGiay(giay.getMaGiay());
        giay1.setTenGiay(giay.getTenGiay());
        giay1.setTgThem(new Date());
        giay1.setHang(giay.getHang());
        giay1.setChatLieu(giay.getChatLieu());
        giay1.setTrangThai(giay.getTrangThai());
        giayService.save(giay1);
        return "redirect:/manage/giay-chi-tiet/viewAdd";
    }

    @PostMapping("/chi-tiet-giay/giay/hang/viewAdd/add")
    public String addHang(@PathVariable UUID id, @ModelAttribute("hangAdd") Hang hang) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        Hang hang1 = new Hang();
        hang1.setLogoHang(hang.getLogoHang());
        hang1.setMaHang(hang.getMaHang());
        hang1.setTenHang(hang.getTenHang());
        hang1.setTgThem(new Date());
        hang1.setTrangThai(hang.getTrangThai());
        hangService.save(hang1);
        return "redirect:/manage/chi-tiet-giay/viewAdd/" + chiTietGiay;
    }

    @PostMapping("/giay-chi-tiet/giay/hang/viewAdd/add")
    public String addHangCT(@ModelAttribute("hangAdd") Hang hang) {
        Hang hang1 = new Hang();
        hang1.setLogoHang(hang.getLogoHang());
        hang1.setMaHang(hang.getMaHang());
        hang1.setTenHang(hang.getTenHang());
        hang1.setTgThem(new Date());
        hang1.setTrangThai(hang.getTrangThai());
        hangService.save(hang1);
        return "redirect:/manage/giay-chi-tiet/viewAdd";
    }

    @PostMapping("/chi-tiet-giay/giay/chat-lieu/viewAdd/add")
    public String addChatLieu(@PathVariable UUID id, @ModelAttribute("chatLieuAdd") ChatLieu chatLieu) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        ChatLieu chatLieu1 = new ChatLieu();
        chatLieu1.setMaChatLieu(chatLieu.getMaChatLieu());
        chatLieu1.setTenChatLieu(chatLieu.getTenChatLieu());
        chatLieu1.setTgThem(new Date());
        chatLieu1.setTrangThai(chatLieu.getTrangThai());
        chatLieuService.save(chatLieu1);
        return "redirect:/manage/chi-tiet-giay/viewAdd/" + chiTietGiay;
    }

    @PostMapping("/giay-chi-tiet/giay/chat-lieu/viewAdd/add")
    public String addChatLieuCT(@ModelAttribute("chatLieuAdd") ChatLieu chatLieu) {
        ChatLieu chatLieu1 = new ChatLieu();
        chatLieu1.setMaChatLieu(chatLieu.getMaChatLieu());
        chatLieu1.setTenChatLieu(chatLieu.getTenChatLieu());
        chatLieu1.setTgThem(new Date());
        chatLieu1.setTrangThai(chatLieu.getTrangThai());
        chatLieuService.save(chatLieu1);
        return "redirect:/manage/giay-chi-tiet/viewAdd";
    }

    @PostMapping("/chi-tiet-giay/mau-sac/viewAdd/add")
    public String addMauSac(@PathVariable UUID id, @ModelAttribute("mauSacAdd") MauSac mauSac) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        MauSac mauSac1 = new MauSac();
        mauSac1.setMaMau(mauSac.getMaMau());
        mauSac1.setTenMau(mauSac.getTenMau());
        mauSac1.setTgThem(new Date());
        mauSac1.setTrangThai(mauSac.getTrangThai());
        mauSacService.save(mauSac1);
        return "redirect:/manage/chi-tiet-giay/viewAdd/" + chiTietGiay;
    }

    @PostMapping("/giay-chi-tiet/mau-sac/viewAdd/add")
    public String addMauSacCT(@ModelAttribute("mauSacAdd") MauSac mauSac) {
        MauSac mauSac1 = new MauSac();
        mauSac1.setMaMau(mauSac.getMaMau());
        mauSac1.setTenMau(mauSac.getTenMau());
        mauSac1.setTgThem(new Date());
        mauSac1.setTrangThai(mauSac.getTrangThai());
        mauSacService.save(mauSac1);
        return "redirect:/manage/giay-chi-tiet/viewAdd";
    }

    @PostMapping("/chi-tiet-giay/size/viewAdd/add")
    public String addSize(@PathVariable UUID id, @ModelAttribute("sizeAdd") Size size) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        Size sizeAdd = new Size();
        sizeAdd.setMaSize(size.getMaSize());
        sizeAdd.setSoSize(size.getSoSize());
        sizeAdd.setTgThem(new Date());
        sizeAdd.setTrangThai(size.getTrangThai());
        sizeService.save(sizeAdd);
        return "redirect:/manage/chi-tiet-giay/viewAdd/" + chiTietGiay;
    }

    @PostMapping("/giay-chi-tiet/size/viewAdd/add")
    public String addSizeCT(@ModelAttribute("sizeAdd") Size size) {
        Size sizeAdd = new Size();
        sizeAdd.setMaSize(size.getMaSize());
        sizeAdd.setSoSize(size.getSoSize());
        sizeAdd.setTgThem(new Date());
        sizeAdd.setTrangThai(size.getTrangThai());
        sizeService.save(sizeAdd);
        return "redirect:/manage/giay-chi-tiet/viewAdd";
    }

    @PostMapping("/chi-tiet-giay/hinh-anh/viewAdd/add")
    public String addHinhAnh(@PathVariable UUID id, @ModelAttribute("hinhAnhAdd") HinhAnh hinhAnh) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        HinhAnh hinhAnh1 = new HinhAnh();
        hinhAnh1.setUrl1(hinhAnh.getUrl1());
        hinhAnh1.setUrl2(hinhAnh.getUrl2());
        hinhAnh1.setUrl3(hinhAnh.getUrl3());
        hinhAnh1.setUrl4(hinhAnh.getUrl4());
        hinhAnh1.setTgThem(new Date());
        hinhAnh1.setTrangThai(hinhAnh.getTrangThai());
        hinhAnhService.save(hinhAnh1);
        return "redirect:/manage/chi-tiet-giay/viewAdd/" + chiTietGiay;
    }

    @PostMapping("/giay-chi-tiet/hinh-anh/viewAdd/add")
    public String addHinhAnh(@ModelAttribute("hinhAnhAdd") HinhAnh hinhAnh) {
        HinhAnh hinhAnh1 = new HinhAnh();
        hinhAnh1.setUrl1(hinhAnh.getUrl1());
        hinhAnh1.setUrl2(hinhAnh.getUrl2());
        hinhAnh1.setUrl3(hinhAnh.getUrl3());
        hinhAnh1.setUrl4(hinhAnh.getUrl4());
        hinhAnh1.setTgThem(new Date());
        hinhAnh1.setTrangThai(hinhAnh.getTrangThai());
        hinhAnhService.save(hinhAnh1);
        return "redirect:/manage/giay-chi-tiet/viewAdd";
    }


    @GetMapping("/giay-chi-tiet/delete/{id}")
    public String deleteGiayChiTiet(@PathVariable UUID id) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        chiTietGiay.setTrangThai(0);
        chiTietGiay.setTgSua(new Date());
        giayChiTietService.save(chiTietGiay);
        return "redirect:/manage/giay-chi-tiet";
    }

    @GetMapping("/chi-tiet-giay/delete/{id}")
    public String deleteChiTietGiay(@PathVariable UUID id) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        chiTietGiay.setTrangThai(0);
        chiTietGiay.setTgSua(new Date());
        giayChiTietService.save(chiTietGiay);
        return "redirect:/manage/chi-tiet-giay";
    }

    @GetMapping("/giay-chi-tiet/viewUpdate/{id}")
    public String viewUpdateGiayChiTiet(@PathVariable UUID id, Model model) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        model.addAttribute("giayChiTiet", chiTietGiay);
        model.addAttribute("giay", giayService.getAllGiay());
        model.addAttribute("mauSac", mauSacService.getALlMauSac());
        model.addAttribute("size", sizeService.getAllSize());
        model.addAttribute("hinhAnh", hinhAnhService.getAllHinhAnh());
        model.addAttribute("giayAdd", new Giay());
        model.addAttribute("mauSacAdd", new MauSac());
        model.addAttribute("sizeAdd", new Size());
        model.addAttribute("hinhAnhAdd", new HinhAnh());
        model.addAttribute("hangAdd", new Hang());
        model.addAttribute("chatLieuAdd", new ChatLieu());
        model.addAttribute("hang", hangService.getALlHang());
        model.addAttribute("chatLieu", chatLieuService.getAllChatLieu());
        return "manage/update-giay-chi-tiet";
    }

    @PostMapping("/giay-chi-tiet/viewUpdate/{id}")
    public String updateGiayChiTiet(@PathVariable UUID id, @ModelAttribute("giayChiTiet") ChiTietGiay chiTietGiay) {
        ChiTietGiay chiTietGiayDb = giayChiTietService.getByIdChiTietGiay(id);
        if (chiTietGiayDb != null) {
            chiTietGiayDb.setGiay(chiTietGiay.getGiay());
            chiTietGiayDb.setHinhAnh(chiTietGiay.getHinhAnh());
            chiTietGiayDb.setMauSac(chiTietGiay.getMauSac());
            chiTietGiayDb.setSize(chiTietGiay.getSize());
            chiTietGiayDb.setGiaBan(chiTietGiay.getGiaBan());
            chiTietGiayDb.setMaNVSua(chiTietGiay.getMaNVSua());
            chiTietGiayDb.setNamBH(chiTietGiay.getNamBH());
            chiTietGiayDb.setNamSX(chiTietGiay.getNamSX());
            chiTietGiayDb.setSoLuong(chiTietGiay.getSoLuong());
            chiTietGiayDb.setTgSua(new Date());
            chiTietGiayDb.setTrangThai(chiTietGiay.getTrangThai());
            chiTietGiayDb.setTrongLuong(chiTietGiay.getTrongLuong());
            giayChiTietService.save(chiTietGiayDb);
        }
        return "redirect:/manage/giay-chi-tiet";
    }
}
