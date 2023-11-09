package com.example.demo.controller;

import com.example.demo.config.ExcelExporterDiaChi;
import com.example.demo.config.PDFExporterDiaChi;
import com.example.demo.model.DiaChiKH;
import com.example.demo.model.KhachHang;
import com.example.demo.service.DiaChiKHService;
import com.example.demo.service.KhachHangService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(0, "Hoạt động");
        dsTrangThai.put(1, "Không hoạt động");
        return dsTrangThai;

    }



    @GetMapping("/dia-chi")
    public String dsDiaChiKH(Model model) {
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
        return "manage/dia-chi";
    }

    @GetMapping("/dia-chi/viewAdd")
    public String viewAddDiaChi(Model model) {
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        model.addAttribute("diaChi", new DiaChiKH());
        model.addAttribute("khachHang", khachHangs);
        return "manage/add-dia-chi";
    }

    @PostMapping("/dia-chi/viewAdd/add")
    public String adddiachi(@ModelAttribute("diaChi") DiaChiKH diaChiKH) {
        DiaChiKH diaChiKH1 = new DiaChiKH();
        diaChiKH1.setMaDC(diaChiKH.getMaDC());
        diaChiKH1.setTenDC(diaChiKH.getTenDC());
        diaChiKH1.setXaPhuong(diaChiKH.getXaPhuong());
        diaChiKH1.setTinhTP(diaChiKH.getTinhTP());
        diaChiKH1.setMoTa(diaChiKH.getMoTa());
        diaChiKH1.setMien(diaChiKH.getMien());
        diaChiKH1.setDiaChiChiTiet(diaChiKH.getDiaChiChiTiet());
        diaChiKH1.setQuanHuyen(diaChiKH.getQuanHuyen());
        diaChiKH1.setTgThem(new Date());
        diaChiKH1.setTrangThai(diaChiKH.getTrangThai());
        diaChiKH1.setKhachHang(diaChiKH.getKhachHang());
        diaChiKHService.save(diaChiKH1);
        return "redirect:/manage/dia-chi";
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
    public String viewUpdatediaChi(@PathVariable UUID id, Model model) {
        DiaChiKH diaChiKH = diaChiKHService.getByIdDiaChikh(id);
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        model.addAttribute("diaChi", diaChiKH);
        model.addAttribute("khachHang", khachHangs);
        return "manage/update-dia-chi";
    }

    @PostMapping("/dia-chi/viewUpdate/{id}")
    public String updatediaChi(@PathVariable UUID id, @ModelAttribute("diaChi") DiaChiKH diaChiKH) {
        DiaChiKH diaChiKHdb = diaChiKHService.getByIdDiaChikh(id);
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
