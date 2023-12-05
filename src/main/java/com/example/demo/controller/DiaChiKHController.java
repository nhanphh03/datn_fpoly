package com.example.demo.controller;

import com.example.demo.config.ExcelExporterDiaChi;
import com.example.demo.config.PDFExporterDiaChi;
import com.example.demo.model.DiaChiKH;
import com.example.demo.model.KhachHang;
import com.example.demo.model.LoaiKhachHang;
import com.example.demo.repository.DiaChiRepsitory;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.LoaiKhachHangRepository;
import com.example.demo.service.DiaChiKHService;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.LoaiKhachHangService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("manage")
@Controller
public class DiaChiKHController {
    @Autowired
    private DiaChiKHService diaChiKHService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private HttpSession session;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private DiaChiRepsitory diaChiRepsitory;

    @Autowired
    private LoaiKhachHangRepository loaiKhachHangRepository;

    @Autowired
    private LoaiKhachHangService loaiKhachHangService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @ModelAttribute("dsGioiTinh")
    public Map<Integer, String> getDsGioiTinh() {
        Map<Integer, String> dsGioiTinh = new HashMap<>();
        dsGioiTinh.put(1, "Nam");
        dsGioiTinh.put(0, "Nữ");
        dsGioiTinh.put(2, "Khác");
        return dsGioiTinh;
    }

    @ModelAttribute("dsLoaiDC")
    public Map<Boolean, String> getDsLoaiDC() {
        Map<Boolean, String> dsLoaiDC = new HashMap<>();
        dsLoaiDC.put(true, "Mặc định");
        dsLoaiDC.put(false, "Không mặc định");

        return dsLoaiDC;
    }


    @GetMapping("/dia-chi")
    public String dsDiaChiKH(Model model, @ModelAttribute("message") String message) {
        List<DiaChiKH> diaChiKHS = diaChiRepsitory.getAllDiaChi();
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        for (DiaChiKH diaChiItem : diaChiKHS) {
            if (diaChiItem.getKhachHang().getTrangThai() == 0) {
                diaChiItem.setTrangThai(0);
                diaChiKHService.save(diaChiItem);
            }
        }
        Collections.sort(diaChiKHS, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("diaChi", diaChiKHS);
        model.addAttribute("khachHang", khachHangs);
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        return "manage/dia-chi";
    }

    @GetMapping("/dia-chi/viewAdd")
    public String viewAddDiaChi(Model model
            , @ModelAttribute("errorDC") String errorDC, @ModelAttribute("userInput") DiaChiKH userInputDC
            , @ModelAttribute("messageKH") String messageKH
            , @ModelAttribute("errorKH") String errorKH, @ModelAttribute("userInput") KhachHang userInputKH
            , @ModelAttribute("messageLKH") String messageLKH
            , @ModelAttribute("errorLKH") String errorLKH, @ModelAttribute("userInput") LoaiKhachHang userInputLKH
            , @ModelAttribute("Errormessage") String Errormessage
            , @ModelAttribute("ErrormessageKH") String ErrormessageKH
            , @ModelAttribute("ErrormessageLKH") String ErrormessageLKH) {
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        Collections.sort(khachHangs, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("khachHang", khachHangs);
        //
        List<LoaiKhachHang> loaiKhachHangs = loaiKhachHangService.getAllLoaiKhachHang();
        Collections.sort(loaiKhachHangs, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("loaiKhachHang", loaiKhachHangs);
        //
        model.addAttribute("diaChi", new DiaChiKH());
        model.addAttribute("loaiKhachHangAdd", new LoaiKhachHang());
        model.addAttribute("khachHangAdd", new KhachHang());

        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputDC != null) {
            model.addAttribute("diaChi", userInputDC);
        }
        //add KH
        if (messageKH == null || !"true".equals(messageKH)) {
            model.addAttribute("messageKH", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputKH != null) {
            model.addAttribute("khachHangAdd", userInputKH);
        }
        //add LKH
        if (messageLKH == null || !"true".equals(messageLKH)) {
            model.addAttribute("messageLKH", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputLKH != null) {
            model.addAttribute("loaiKhachHangAdd", userInputLKH);
        }
        //CHECK MÃ
        if (ErrormessageKH == null || !"true".equals(ErrormessageKH)) {
            model.addAttribute("ErrormessageKH", false);
        }
        if (ErrormessageLKH == null || !"true".equals(ErrormessageLKH)) {
            model.addAttribute("ErrormessageLKH", false);
        }

        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/add-dia-chi";
    }

    @GetMapping("/View-add-dia-chi/viewAdd/{id}")
    public String viewAddDiaChiKH(@PathVariable UUID id, Model model
            , @ModelAttribute("errorDC") String errorDC, @ModelAttribute("userInput") DiaChiKH userInputDC
            , @ModelAttribute("messageKH") String messageKH
            , @ModelAttribute("errorKH") String errorKH, @ModelAttribute("userInput") KhachHang userInputKH
            , @ModelAttribute("messageLKH") String messageLKH
            , @ModelAttribute("errorLKH") String errorLKH, @ModelAttribute("userInput") LoaiKhachHang userInputLKH
            , @ModelAttribute("Errormessage") String Errormessage
            , @ModelAttribute("ErrormessageKH") String ErrormessageKH
            , @ModelAttribute("ErrormessageLKH") String ErrormessageLKH) {
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        Collections.sort(khachHangs, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("khachHang", khachHangs.stream().filter((item) -> (item.getIdKH().equals(id))));
        model.addAttribute("diaChi", new DiaChiKH());
        model.addAttribute("khachHangAdd", new KhachHang());

        session.removeAttribute("idViewAddDC");
        session.setAttribute("idViewAddDC", id);
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputDC != null) {
            model.addAttribute("diaChi", userInputDC);
        }
        //add KH
        if (messageKH == null || !"true".equals(messageKH)) {
            model.addAttribute("messageKH", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputKH != null) {
            model.addAttribute("khachHangAdd", userInputKH);
        }
        //add LKH
        if (messageLKH == null || !"true".equals(messageLKH)) {
            model.addAttribute("messageLKH", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputLKH != null) {
            model.addAttribute("loaiKhachHangAdd", userInputLKH);
        }
        //CHECK MÃ
        if (ErrormessageKH == null || !"true".equals(ErrormessageKH)) {
            model.addAttribute("ErrormessageKH", false);
        }
        if (ErrormessageLKH == null || !"true".equals(ErrormessageLKH)) {
            model.addAttribute("ErrormessageLKH", false);
        }

        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/add-dia-chi-kh";
    }

    @PostMapping("/View-add-dia-chi/viewAdd/add")
    public String adddiachiKH(@Valid @ModelAttribute("diaChi") DiaChiKH diaChiKH, BindingResult result, Model model
            , RedirectAttributes redirectAttributes) {
        UUID idViewAddDC = (UUID) session.getAttribute("idViewAddDC");
        String link1 = "redirect:/manage/View-add-dia-chi/viewAdd/" + idViewAddDC;

        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        Collections.sort(khachHangs, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("khachHang", khachHangs);
        //

        model.addAttribute("diaChi", new DiaChiKH());
        model.addAttribute("khachHangAdd", new KhachHang());
        //
        DiaChiKH existingDChi = diaChiRepsitory.findByMaDC(diaChiKH.getMaDC());
        if (existingDChi != null) {
            redirectAttributes.addFlashAttribute("userInput", diaChiKH);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return link1;
        }
        //
        DiaChiKH diaChiKHdb = new DiaChiKH();
        diaChiKHdb.setMaDC(diaChiKH.getMaDC());
        diaChiKHdb.setTenDC(diaChiKH.getTenDC());
        diaChiKHdb.setTenNguoiNhan(diaChiKH.getTenNguoiNhan());
        diaChiKHdb.setXaPhuong(diaChiKH.getXaPhuong());
        diaChiKHdb.setQuanHuyen(diaChiKH.getQuanHuyen());
        diaChiKHdb.setTinhTP(diaChiKH.getTinhTP());
        diaChiKHdb.setMoTa(diaChiKH.getMoTa());
        diaChiKHdb.setSdtNguoiNhan(diaChiKH.getSdtNguoiNhan());
        diaChiKHdb.setMien(diaChiKH.getMien());
        diaChiKHdb.setDiaChiChiTiet(diaChiKH.getDiaChiChiTiet());
        diaChiKHdb.setTrangThai(1);
        diaChiKHdb.setKhachHang(diaChiKH.getKhachHang());
        diaChiKHdb.setTgThem(new Date());
        if(diaChiKH.isLoai() == true){
            for (DiaChiKH x: diaChiKHService.findByKhachHang(diaChiKH.getKhachHang())){
                x.setLoai(false);
                diaChiKHService.save(x);
            }
            diaChiKHdb.setLoai(true);
        }else{
            diaChiKHdb.setLoai(false);
        }
        diaChiKHService.save(diaChiKHdb);
        redirectAttributes.addFlashAttribute("message", true);
        String link2 = "redirect:/manage/khach-hang/detail/" + idViewAddDC;
        return link2;
    }

    @PostMapping("/dia-chi/viewAdd/add")
    public String adddiachi(@Valid @ModelAttribute("diaChi") DiaChiKH diaChiKH, BindingResult result, Model model
            , RedirectAttributes redirectAttributes) {
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        Collections.sort(khachHangs, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("khachHang", khachHangs);
        //
        List<LoaiKhachHang> loaiKhachHangList = loaiKhachHangService.getAllLoaiKhachHang();
        Collections.sort(loaiKhachHangList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("loaiKhachHang", loaiKhachHangList);
        //
        model.addAttribute("diaChi", new DiaChiKH());
        model.addAttribute("khachHangAdd", new KhachHang());
        model.addAttribute("loaiKhachHangAdd", new LoaiKhachHang());

        //
        DiaChiKH existingDChi = diaChiRepsitory.findByMaDC(diaChiKH.getMaDC());
        if (existingDChi != null) {
            redirectAttributes.addFlashAttribute("userInput", diaChiKH);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return "redirect:/manage/dia-chi/viewAdd";
        }
        //
        diaChiKH.setTgThem(new Date());
        diaChiKH.setTrangThai(1);
        diaChiKHService.save(diaChiKH);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/dia-chi";
    }

    @PostMapping("/dia-chi/khach-hang/viewAdd/add")
    public String addkhachHang(@Valid @ModelAttribute("khachHangAdd") KhachHang khachHang
            , BindingResult result, RedirectAttributes redirectAttributes) {
        KhachHang existingKH = khachHangRepository.findByMaKH(khachHang.getMaKH());
        if (existingKH != null) {
            redirectAttributes.addFlashAttribute("userInput", khachHang);
            redirectAttributes.addFlashAttribute("ErrormessageKH", true);
            return "redirect:/manage/dia-chi/viewAdd";
        }
        KhachHang existingKH1 = khachHangRepository.findByEmailKH(khachHang.getEmailKH());
        if (existingKH1 != null) {
            redirectAttributes.addFlashAttribute("userInput", khachHang);
            redirectAttributes.addFlashAttribute("ErrormessageeEmail", true);
            return "redirect:/manage/dia-chi/viewAdd";
        }
        KhachHang existingKH2 = khachHangRepository.findByCCCDKH(khachHang.getCCCDKH());
        if (existingKH2 != null) {
            redirectAttributes.addFlashAttribute("userInput", khachHang);
            redirectAttributes.addFlashAttribute("ErrormessageCCCD", true);
            return "redirect:/manage/dia-chi/viewAdd";
        }
        KhachHang existingKH3 = khachHangRepository.findBySdtKH(khachHang.getSdtKH());
        if (existingKH3 != null) {
            redirectAttributes.addFlashAttribute("userInput", khachHang);
            redirectAttributes.addFlashAttribute("ErrormessageSDT", true);
            return "redirect:/manage/dia-chi/viewAdd";
        }
        //
        KhachHang khachHang1 = new KhachHang();
        khachHang1.setMaKH(khachHang.getMaKH());
        khachHang1.setHoTenKH(khachHang.getHoTenKH());
        khachHang1.setDiaChi(khachHang.getDiaChi());
        khachHang1.setEmailKH(khachHang.getEmailKH());
        khachHang1.setMatKhau(khachHang.getMatKhau());
        khachHang1.setSdtKH(khachHang.getSdtKH());
        khachHang1.setAnhKH(khachHang.getAnhKH());
        khachHang1.setAnhcccd(khachHang.getAnhcccd());
        khachHang1.setGioiTinh(khachHang.getGioiTinh());
        khachHang1.setNgaySinh(khachHang.getNgaySinh());
        khachHang1.setTgThem(new Date());
        khachHang1.setTrangThai(1);
        khachHang1.setLoaiKhachHang(khachHang.getLoaiKhachHang());
        khachHangService.save(khachHang1);
        redirectAttributes.addFlashAttribute("messageKH", true);
        return "redirect:/manage/dia-chi/viewAdd";
    }

    @PostMapping("/dia-chi/khach-hang/loai-khach-hang/viewAdd/add")
    public String addLoaiKhachHang(@Valid @ModelAttribute("loaiKhachHangAdd") LoaiKhachHang loaiKhachHang
            , BindingResult result, RedirectAttributes redirectAttributes) {
        //
        LoaiKhachHang existingLKH = loaiKhachHangRepository.findByMaLKH(loaiKhachHang.getMaLKH());
        if (existingLKH != null) {
            redirectAttributes.addFlashAttribute("userInput", loaiKhachHang);
            redirectAttributes.addFlashAttribute("ErrormessageLKH", true);
            return "redirect:/manage/dia-chi/viewAdd";
        }
        //
        loaiKhachHang.setTgThem(new Date());
        loaiKhachHang.setTrangThai(1);
        loaiKhachHangService.save(loaiKhachHang);
        redirectAttributes.addFlashAttribute("messageLKH", true);
        return "redirect:/manage/dia-chi/viewAdd";
    }

    @GetMapping("/dia-chi/delete/{id}")
    public String deleteDiaChi(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        DiaChiKH diaChiKH = diaChiKHService.getByIdDiaChikh(id);
        diaChiKH.setTrangThai(0);
        diaChiKH.setTgSua(new Date());
        diaChiKHService.save(diaChiKH);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/dia-chi";
    }

    @GetMapping("/dia-chi-kh/delete/{id}")
    public String deleteDiaChiKH(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        UUID idDC = (UUID) session.getAttribute("idDC");
        String link1 = "redirect:/manage/khach-hang/detail/" + idDC;
        DiaChiKH diaChiKH = diaChiKHService.getByIdDiaChikh(id);
        diaChiKH.setTrangThai(0);
        diaChiKH.setTgSua(new Date());
        diaChiKHService.save(diaChiKH);
        redirectAttributes.addFlashAttribute("message", true);
        return link1;
    }

    @GetMapping("/dia-chi/viewUpdate/{id}")
    public String viewUpdatediaChi(@PathVariable UUID id, Model model
            , @ModelAttribute("errorDC") String errorDC, @ModelAttribute("userInput") DiaChiKH userInputDC
            , @ModelAttribute("Errormessage") String Errormessage
            , @ModelAttribute("ErrormessageKH") String ErrormessageKH) {
        DiaChiKH diaChiKH = diaChiKHService.getByIdDiaChikh(id);
        model.addAttribute("diaChi", diaChiKH);
        //
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        Collections.sort(khachHangs, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("khachHang", khachHangs);
        //
        model.addAttribute("khachHangAdd", new KhachHang());
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputDC != null) {
            model.addAttribute("diaChiUpdate", userInputDC);
        }
        session.setAttribute("id", id);
        //
        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/update-dia-chi";
    }

    @GetMapping("/dia-chi-kh/viewUpdate/{id}")
    public String viewUpdatediaChikh(@PathVariable UUID id, Model model
            , @ModelAttribute("errorDC") String errorDC, @ModelAttribute("userInput") DiaChiKH userInputDC
            , @ModelAttribute("Errormessage") String Errormessage
            , @ModelAttribute("ErrormessageKH") String ErrormessageKH) {
        DiaChiKH diaChiKH = diaChiKHService.getByIdDiaChikh(id);
        model.addAttribute("diaChi", diaChiKH);
        //
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        Collections.sort(khachHangs, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("khachHang", khachHangs);
        //
        model.addAttribute("khachHangAdd", new KhachHang());
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputDC != null) {
            model.addAttribute("diaChiUpdate", userInputDC);
        }
        //
        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        session.setAttribute("id", id);
        return "manage/update-dia-chi-kh";
    }

    @PostMapping("/dia-chi/viewUpdate/{id}")
    public String updatediaChi(@PathVariable UUID id, @Valid @ModelAttribute("diaChi") DiaChiKH diaChiKH
            , RedirectAttributes redirectAttributes) {
        DiaChiKH diaChiKHdb = diaChiKHService.getByIdDiaChikh(id);

        if (diaChiKHdb != null) {
            diaChiKHdb.setMaDC(diaChiKH.getMaDC());
            diaChiKHdb.setTenDC(diaChiKH.getTenDC());
            diaChiKHdb.setTenNguoiNhan(diaChiKH.getTenNguoiNhan());
            diaChiKHdb.setXaPhuong(diaChiKH.getXaPhuong());
            diaChiKHdb.setQuanHuyen(diaChiKH.getQuanHuyen());
            diaChiKHdb.setTinhTP(diaChiKH.getTinhTP());
            diaChiKHdb.setMoTa(diaChiKH.getMoTa());
            diaChiKHdb.setSdtNguoiNhan(diaChiKH.getSdtNguoiNhan());
            diaChiKHdb.setMien(diaChiKH.getMien());
            diaChiKHdb.setDiaChiChiTiet(diaChiKH.getDiaChiChiTiet());
            diaChiKHdb.setTrangThai(diaChiKH.getTrangThai());
            diaChiKHdb.setTgSua(new Date());
            diaChiKHdb.setKhachHang(diaChiKH.getKhachHang());
            diaChiKHService.save(diaChiKHdb);
            redirectAttributes.addFlashAttribute("message", true);
        }
        return "redirect:/manage/dia-chi";
    }

    @PostMapping("/dia-chi-kh/viewUpdate/{id}")
    public String updatediaChikh(@PathVariable UUID id, @Valid @ModelAttribute("diaChi") DiaChiKH diaChiKH
            , RedirectAttributes redirectAttributes) {
        DiaChiKH diaChiKHdb = diaChiKHService.getByIdDiaChikh(id);

        UUID idDC = (UUID) session.getAttribute("idViewAddDC");
        String link = "redirect:/manage/dia-chi-kh/viewUpdate" + idDC;
        UUID idDC1 = (UUID) session.getAttribute("idDC");
        String link1 = "redirect:/manage/khach-hang/detail/" + idDC1;

        DiaChiKH existingDC = diaChiRepsitory.findByMaDC(diaChiKH.getMaDC());
        if (existingDC != null && !existingDC.getIdDC().equals(id)) {
            redirectAttributes.addFlashAttribute("userInput", diaChiKH);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return link;
        }
        if (diaChiKHdb != null) {
            diaChiKHdb.setMaDC(diaChiKH.getMaDC());
            diaChiKHdb.setTenDC(diaChiKH.getTenDC());
            diaChiKHdb.setTenNguoiNhan(diaChiKH.getTenNguoiNhan());
            diaChiKHdb.setXaPhuong(diaChiKH.getXaPhuong());
            diaChiKHdb.setQuanHuyen(diaChiKH.getQuanHuyen());
            diaChiKHdb.setTinhTP(diaChiKH.getTinhTP());
            diaChiKHdb.setMoTa(diaChiKH.getMoTa());
            diaChiKHdb.setSdtNguoiNhan(diaChiKH.getSdtNguoiNhan());
            diaChiKHdb.setMien(diaChiKH.getMien());
            diaChiKHdb.setDiaChiChiTiet(diaChiKH.getDiaChiChiTiet());
            diaChiKHdb.setTrangThai(diaChiKH.getTrangThai());
            diaChiKHdb.setTgSua(new Date());
            diaChiKHdb.setKhachHang(diaChiKH.getKhachHang());

            if(diaChiKH.isLoai() == true){
                for (DiaChiKH x: diaChiKHService.findByKhachHang(diaChiKH.getKhachHang())){
                    x.setLoai(false);
                    diaChiKHService.save(x);
                }
                diaChiKHdb.setLoai(true);
            }else{
                diaChiKHdb.setLoai(false);
            }

            diaChiKHService.save(diaChiKHdb);
            redirectAttributes.addFlashAttribute("message", true);
        }
        return link1;
    }

    @GetMapping("/diaChi/export/pdf")
    public void exportToPDFChatLieu(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=diaChi_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        List<DiaChiKH> listDiaChi = diaChiKHService.getAllDiaChiKH();
        PDFExporterDiaChi exporter = new PDFExporterDiaChi(listDiaChi);
        exporter.export(response);
    }

    @GetMapping("/diaChi/export/excel")
    public void exportToExcelSize(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=diaChi_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<DiaChiKH> listDiaChi = diaChiKHService.getAllDiaChiKH();
        ExcelExporterDiaChi excelExporter = new ExcelExporterDiaChi(listDiaChi);
        excelExporter.export(response);
    }

    @GetMapping("/diaChi/filter")
    public String filterData(Model model,
                             @RequestParam(value = "maDC", required = false) String maDC,
                             @RequestParam(value = "tenDC", required = false) String tenDC) {
        // Thực hiện lọc dữ liệu dựa trên selectedSize (và trạng thái nếu cần)
        List<DiaChiKH> filteredDiaChiKHS;
        if ("Mã Địa Chỉ".equals(maDC) && "Tên Địa Chỉ".equals(tenDC)) {
            // Nếu người dùng chọn "Tất cả", hiển thị tất cả dữ liệu
            filteredDiaChiKHS = diaChiKHService.getAllDiaChiKH();
        } else {
            // Thực hiện lọc dữ liệu dựa trên selectedSize
            filteredDiaChiKHS = diaChiKHService.fillterDiaChiKH(maDC, tenDC);
        }
        model.addAttribute("diaChi", filteredDiaChiKHS);
        model.addAttribute("diaChiAll", diaChiKHService.getAllDiaChiKH());

        return "manage/dia-chi"; // Trả về mẫu HTML chứa bảng dữ liệu sau khi lọc
    }

    @PostMapping("/diaChi/import")
    public String importData(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                InputStream excelFile = file.getInputStream();
                diaChiKHService.importDataFromExcel(excelFile); // Gọi phương thức nhập liệu từ Excel
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi
            }
        }
        return "redirect:/manage/dia-chi"; // Chuyển hướng sau khi nhập liệu thành công hoặc không thành công
    }


}
