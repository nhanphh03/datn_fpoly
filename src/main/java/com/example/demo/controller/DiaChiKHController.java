package com.example.demo.controller;

import com.example.demo.config.ExcelExporterDiaChi;
import com.example.demo.config.PDFExporterDiaChi;
import com.example.demo.model.DiaChiKH;
import com.example.demo.model.KhachHang;
import com.example.demo.model.LoaiKhachHang;
import com.example.demo.repository.DiaChiRepsitory;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.service.DiaChiKHService;
import com.example.demo.service.KhachHangService;
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

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/dia-chi")
    public String dsDiaChiKH(Model model,@ModelAttribute("message") String message) {
        List<DiaChiKH> diaChiKHS = diaChiKHService.getAllDiaChiKH();
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
            , @ModelAttribute("maDCError") String maDCError
            , @ModelAttribute("tenDCError") String tenDCError
            , @ModelAttribute("tenNguoiNhanError") String tenNguoiNhanError
            , @ModelAttribute("xaPhuongError") String xaPhuongError
            , @ModelAttribute("quanHuyenError") String quanHuyenError
            , @ModelAttribute("tinhTPError") String tinhTPError
            , @ModelAttribute("moTaError") String moTaError
            , @ModelAttribute("diaChiChiTietError") String diaChiChiTietError
            , @ModelAttribute("mienError") String mienError
            , @ModelAttribute("loaiError") String loaiError
            , @ModelAttribute("sdtNguoiNhanError") String sdtNguoiNhanError
            , @ModelAttribute("khachHangError") String khachHangError
            , @ModelAttribute("errorDC") String errorDC, @ModelAttribute("userInput") DiaChiKH userInputDC

            , @ModelAttribute("messageKH") String messageKH
            , @ModelAttribute("maKHError") String maKHError
            , @ModelAttribute("tenKHError") String tenKHError
            , @ModelAttribute("ngaySinhError") String ngaySinhError
            , @ModelAttribute("sdtKHError") String sdtKHError
            , @ModelAttribute("emailKHError") String emailKHError
            , @ModelAttribute("diaChiError") String diaChiError
            , @ModelAttribute("CCCDKHError") String CCCDKHError
            , @ModelAttribute("matKhauError") String matKhauError
            , @ModelAttribute("errorKH") String errorKH, @ModelAttribute("userInput") KhachHang userInputKH
            , @ModelAttribute("Errormessage") String Errormessage
            , @ModelAttribute("ErrormessageKH") String ErrormessageKH) {
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        Collections.sort(khachHangs, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("khachHang", khachHangs);
        //
        model.addAttribute("diaChi", new DiaChiKH());
        model.addAttribute("khachHangAdd", new KhachHang());
        //
        if (maDCError == null || !"maDCError".equals(errorDC)) {
            model.addAttribute("maDCError", false);
        }
        if (tenDCError == null || !"tenDCError".equals(errorDC)) {
            model.addAttribute("tenDCError", false);
        }
        if (tenNguoiNhanError == null || !"tenNguoiNhanError".equals(errorDC)) {
            model.addAttribute("tenNguoiNhanError", false);
        }
        if (xaPhuongError == null || !"xaPhuongError".equals(errorDC)) {
            model.addAttribute("xaPhuongError", false);
        }

        if (quanHuyenError == null || !"quanHuyenError".equals(errorDC)) {
            model.addAttribute("quanHuyenError", false);
        }
        if (tinhTPError == null || !"tinhTPError".equals(errorDC)) {
            model.addAttribute("tinhTPError", false);
        }
        if (moTaError == null || !"moTaError".equals(errorDC)) {
            model.addAttribute("moTaError", false);
        }
        if (diaChiChiTietError == null || !"diaChiChiTietError".equals(errorDC)) {
            model.addAttribute("diaChiChiTietError", false);
        }
        if (mienError == null || !"mienError".equals(errorDC)) {
            model.addAttribute("mienError", false);
        }
        if (loaiError == null || !"loaiError".equals(errorDC)) {
            model.addAttribute("loaiError", false);
        }
        if (sdtNguoiNhanError == null || !"sdtNguoiNhanError".equals(errorDC)) {
            model.addAttribute("sdtNguoiNhanError", false);
        }
        if (khachHangError == null || !"khachHangError".equals(errorDC)) {
            model.addAttribute("khachHangError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputDC != null) {
            model.addAttribute("diaChi", userInputDC);
        }
        //add KH
        if (messageKH == null || !"true".equals(messageKH)) {
            model.addAttribute("messageKH", false);
        }
        if (maKHError == null || !"maKHError".equals(errorKH)) {
            model.addAttribute("maKHError", false);
        }
        if (tenKHError == null || !"tenKHError".equals(errorKH)) {
            model.addAttribute("tenKHError", false);
        }
        if (ngaySinhError == null || !"ngaySinhError".equals(errorKH)) {
            model.addAttribute("ngaySinhError", false);
        }
        if (sdtKHError == null || !"sdtKHError".equals(errorKH)) {
            model.addAttribute("sdtKHError", false);
        }
        if (emailKHError == null || !"emailKHError".equals(errorKH)) {
            model.addAttribute("emailKHError", false);
        }
        if (diaChiError == null || !"diaChiError".equals(errorKH)) {
            model.addAttribute("diaChiError", false);
        }
        if (CCCDKHError == null || !"CCCDKHError".equals(errorKH)) {
            model.addAttribute("CCCDKHError", false);
        }
        if (matKhauError == null || !"matKhauError".equals(errorKH)) {
            model.addAttribute("matKhauError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputKH != null) {
            model.addAttribute("khachHangAdd", userInputKH);
        }
        //
        if (ErrormessageKH == null || !"true".equals(ErrormessageKH)) {
            model.addAttribute("ErrormessageKH", false);
        }

        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/add-dia-chi";
    }

    @PostMapping("/dia-chi/viewAdd/add")
    public String adddiachi(@Valid  @ModelAttribute("diaChi") DiaChiKH diaChiKH, BindingResult result, Model model
            , RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
            Collections.sort(khachHangs, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
            model.addAttribute("khachHang", khachHangs);
            //
            model.addAttribute("diacChi", new DiaChiKH());
            model.addAttribute("khachHangAdd", new KhachHang());
            //
            if (result.hasFieldErrors("maDC")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "maDCError");
            }
            if (result.hasFieldErrors("tenDC")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "tenDCError");
            }
            if (result.hasFieldErrors("tenNguoiNhan")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "tenNguoiNhanError");
            }
            if (result.hasFieldErrors("xaPhuong")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "xaPhuongError");
            }
            if (result.hasFieldErrors("quanHuyen")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "quanHuyenError");
            }
            if (result.hasFieldErrors("tinhTP")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "tinhTPError");
            }
            if (result.hasFieldErrors("moTa")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "moTaError");
            }
            if (result.hasFieldErrors("diaChiChiTiet")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "diaChiChiTietError");
            }
            if (result.hasFieldErrors("mien")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "mienError");
            }
            if (result.hasFieldErrors("loai")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "loaiError");
            }
            if (result.hasFieldErrors("sdtNguoiNhan")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "sdtNguoiNhanError");
            }
            if (result.hasFieldErrors("khachHang")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "khachHangError");
            }
            return "redirect:/manage/dia-chi/viewAdd";
        }

        diaChiKH.setTgThem(new Date());
        diaChiKH.setTrangThai(1);
        diaChiKHService.save(diaChiKH);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/dia-chi";
    }
    @PostMapping("/dia-chi/khach-hang/viewAdd/add")
    public String addkhachHang(@Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result,Model model
            , RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            if (result.hasFieldErrors("matKhau")) {
                redirectAttributes.addFlashAttribute("userInput", khachHang);
                redirectAttributes.addFlashAttribute("errorKH", "matKhauError");
            }
            if (result.hasFieldErrors("CCCDKH")) {
                redirectAttributes.addFlashAttribute("userInput", khachHang);
                redirectAttributes.addFlashAttribute("errorKH", "CCCDKHError");
            }
            if (result.hasFieldErrors("diaChi")) {
                redirectAttributes.addFlashAttribute("userInput", khachHang);
                redirectAttributes.addFlashAttribute("errorKH", "diaChiError");
            }
            if (result.hasFieldErrors("emailKH")) {
                redirectAttributes.addFlashAttribute("userInput", khachHang);
                redirectAttributes.addFlashAttribute("errorKH", "emailKHError");
            }
            if (result.hasFieldErrors("sdtKH")) {
                redirectAttributes.addFlashAttribute("userInput", khachHang);
                redirectAttributes.addFlashAttribute("errorKH", "sdtKHError");
            }
            if (result.hasFieldErrors("ngaySinh")) {
                redirectAttributes.addFlashAttribute("userInput", khachHang);
                redirectAttributes.addFlashAttribute("errorKH", "ngaySinhError");
            }
            if (result.hasFieldErrors("hoTenKH")) {
                redirectAttributes.addFlashAttribute("userInput", khachHang);
                redirectAttributes.addFlashAttribute("errorKH", "tenKHError");
            }
            if (result.hasFieldErrors("maKH")) {
                redirectAttributes.addFlashAttribute("userInput", khachHang);
                redirectAttributes.addFlashAttribute("errorKH", "maKHError");
            }
            return "redirect:/manage/dia-chi/viewAdd";
        }
        //
        KhachHang existingKH = khachHangRepository.findByMaKH(khachHang.getMaKH());
        if (existingKH != null) {
            redirectAttributes.addFlashAttribute("userInput", khachHang);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return "redirect:/manage/dia-chi/viewAdd";
        }
        KhachHang existingKH1 = khachHangRepository.findByEmailKH(khachHang.getEmailKH());
        if (existingKH1 != null) {
            redirectAttributes.addFlashAttribute("userInput", khachHang);
            redirectAttributes.addFlashAttribute("ErrormessageeEmail", true);
            return "redirect:/manage/dia-chi/viewAdd";
        }
        //
        khachHang.setTgThem(new Date());
        khachHang.setTrangThai(1);
        khachHangService.save(khachHang);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/dia-chi/viewAdd";
    }

    @GetMapping("/dia-chi/delete/{id}")
    public String deleteDiaChi(@PathVariable UUID id) {
        DiaChiKH diaChiKH = diaChiKHService.getByIdDiaChikh(id);
        diaChiKH.setTrangThai(0);
        diaChiKH.setTgSua(new Date());
        diaChiKHService.save(diaChiKH);
        return "redirect:/manage/dia-chi";
    }

    @GetMapping("/dia-chi/viewUpdate/{id}")
    public String viewUpdatediaChi(@PathVariable UUID id, Model model
            , @ModelAttribute("maDCError") String maDCError
            , @ModelAttribute("tenDCError") String tenDCError
            , @ModelAttribute("tenNguoiNhanError") String tenNguoiNhanError
            , @ModelAttribute("xaPhuongError") String xaPhuongError
            , @ModelAttribute("quanHuyenError") String quanHuyenError
            , @ModelAttribute("tinhTPError") String tinhTPError
            , @ModelAttribute("moTaError") String moTaError
            , @ModelAttribute("diaChiChiTietError") String diaChiChiTietError
            , @ModelAttribute("mienError") String mienError
            , @ModelAttribute("loaiError") String loaiError
            , @ModelAttribute("sdtNguoiNhanError") String sdtNguoiNhanError
            , @ModelAttribute("khachHangError") String khachHangError
            , @ModelAttribute("errorDC") String errorDC, @ModelAttribute("userInput") DiaChiKH userInputDC

            , @ModelAttribute("messageKH") String messageKH
            , @ModelAttribute("maKHError") String maKHError
            , @ModelAttribute("tenKHError") String tenKHError
            , @ModelAttribute("ngaySinhError") String ngaySinhError
            , @ModelAttribute("sdtKHError") String sdtKHError
            , @ModelAttribute("emailKHError") String emailKHError
            , @ModelAttribute("diaChiError") String diaChiError
            , @ModelAttribute("CCCDKHError") String CCCDKHError
            , @ModelAttribute("matKhauError") String matKhauError
            , @ModelAttribute("errorKH") String errorKH, @ModelAttribute("userInput") KhachHang userInputKH
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
        //
        if (maDCError == null || !"maDCError".equals(errorDC)) {
            model.addAttribute("maDCError", false);
        }
        if (tenDCError == null || !"tenDCError".equals(errorDC)) {
            model.addAttribute("tenDCError", false);
        }
        if (tenNguoiNhanError == null || !"tenNguoiNhanError".equals(errorDC)) {
            model.addAttribute("tenNguoiNhanError", false);
        }
        if (xaPhuongError == null || !"xaPhuongError".equals(errorDC)) {
            model.addAttribute("xaPhuongError", false);
        }

        if (quanHuyenError == null || !"quanHuyenError".equals(errorDC)) {
            model.addAttribute("quanHuyenError", false);
        }
        if (tinhTPError == null || !"tinhTPError".equals(errorDC)) {
            model.addAttribute("tinhTPError", false);
        }
        if (moTaError == null || !"moTaError".equals(errorDC)) {
            model.addAttribute("moTaError", false);
        }
        if (diaChiChiTietError == null || !"diaChiChiTietError".equals(errorDC)) {
            model.addAttribute("diaChiChiTietError", false);
        }
        if (mienError == null || !"mienError".equals(errorDC)) {
            model.addAttribute("mienError", false);
        }
        if (loaiError == null || !"loaiError".equals(errorDC)) {
            model.addAttribute("loaiError", false);
        }
        if (sdtNguoiNhanError == null || !"sdtNguoiNhanError".equals(errorDC)) {
            model.addAttribute("sdtNguoiNhanError", false);
        }
        if (khachHangError == null || !"khachHangError".equals(errorDC)) {
            model.addAttribute("khachHangError", false);
        }
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

    @PostMapping("/dia-chi/viewUpdate/{id}")
    public String updatediaChi(@PathVariable UUID id, @ModelAttribute("diaChi") DiaChiKH diaChiKH
            , BindingResult result, RedirectAttributes redirectAttributes) {
        DiaChiKH diaChiKHdb = diaChiKHService.getByIdDiaChikh(id);
        UUID idDC = (UUID) session.getAttribute("id");
        String link = "redirect:/manage/dia-chi/viewUpdate/" + idDC;
        if (result.hasErrors()) {
            if (result.hasFieldErrors("maDC")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "maDCError");
            }
            if (result.hasFieldErrors("tenDC")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "tenDCError");
            }
            if (result.hasFieldErrors("tenNguoiNhan")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "tenNguoiNhanError");
            }
            if (result.hasFieldErrors("xaPhuong")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "xaPhuongError");
            }
            if (result.hasFieldErrors("quanHuyen")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "quanHuyenError");
            }
            if (result.hasFieldErrors("tinhTP")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "tinhTPError");
            }
            if (result.hasFieldErrors("moTa")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "moTaError");
            }
            if (result.hasFieldErrors("diaChiChiTiet")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "diaChiChiTietError");
            }
            if (result.hasFieldErrors("mien")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "mienError");
            }
            if (result.hasFieldErrors("loai")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "loaiError");
            }
            if (result.hasFieldErrors("sdtNguoiNhan")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "sdtNguoiNhanError");
            }
            if (result.hasFieldErrors("khachHang")) {
                redirectAttributes.addFlashAttribute("userInput", diaChiKH);
                redirectAttributes.addFlashAttribute("errorDC", "khachHangError");
            }
            return link;
        }

        if (diaChiKHdb != null) {
            diaChiKHdb.setMaDC(diaChiKH.getMaDC());
            diaChiKHdb.setTenDC(diaChiKH.getTenDC());
            diaChiKHdb.setXaPhuong(diaChiKH.getXaPhuong());
            diaChiKHdb.setQuanHuyen(diaChiKH.getQuanHuyen());
            diaChiKHdb.setTinhTP(diaChiKH.getTinhTP());
            diaChiKHdb.setMoTa(diaChiKH.getMoTa());
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
