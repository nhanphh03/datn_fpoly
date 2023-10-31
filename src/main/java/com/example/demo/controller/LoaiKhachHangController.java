package com.example.demo.controller;


import com.example.demo.config.ExcelExporterChucVu;
import com.example.demo.config.ExcelExporterLoaiKhachHang;
import com.example.demo.config.PDFExporterChucVu;
import com.example.demo.config.PDFExporterLoaiKH;
import com.example.demo.model.ChucVu;
import com.example.demo.model.LoaiKhachHang;
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

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("manage")
@Controller
public class LoaiKhachHangController {
    @Autowired
    private LoaiKhachHangService loaiKhachHangService;
    @Autowired
    private HttpSession session;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/loai-khach-hang")
    public String dsloaikhachhang(Model model) {
        List<LoaiKhachHang> loaikhachhang = loaiKhachHangService.getAllLoaiKhachHang();
        Collections.sort(loaikhachhang, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("loaiKhachHang", loaikhachhang);
        model.addAttribute("loaiKhachHangAdd", new LoaiKhachHang());
        return "manage/loai-khach-hang";
    }

//    @GetMapping("/loai-khach-hang/viewAdd")
//    public String viewAddloaikhachhang(Model model) {
//        model.addAttribute("loaiKhachHang", new LoaiKhachHang());
//        return "manage/add-loai-khach-hang";
//    }

    @PostMapping("/loai-khach-hang/viewAdd/add")
    public String addloaikhachhang(@Valid @ModelAttribute("loaiKhachHang") LoaiKhachHang loaiKhachHang, BindingResult result) {
        if (result.hasErrors()){
            return "mannage/add-loai-khach-hang";
        }
        LoaiKhachHang loaiKhachHang1 = new LoaiKhachHang();
        loaiKhachHang1.setMaLKH(loaiKhachHang.getMaLKH());
        loaiKhachHang1.setTenLKH(loaiKhachHang.getTenLKH());
        loaiKhachHang1.setSoDiem(loaiKhachHang.getSoDiem());
        loaiKhachHang1.setTgThem(new Date());
        loaiKhachHang1.setTrangThai(loaiKhachHang.getTrangThai());
        loaiKhachHangService.save(loaiKhachHang1);
        return "redirect:/manage/loai-khach-hang";
    }

    @GetMapping("/loai-khach-hang/delete/{id}")
    public String deleteloaikhachhang(@PathVariable UUID id) {
        LoaiKhachHang loaiKhachHang = loaiKhachHangService.getByIdLoaiKhachHang(id);
        loaiKhachHang.setTrangThai(0);
        loaiKhachHang.setTgSua(new Date());
        loaiKhachHangService.save(loaiKhachHang);
        return "redirect:/manage/loai-khach-hang";
    }

    @GetMapping("/loai-khach-hang/viewUpdate/{id}")
    public String viewUpdateloaikhachhang(@PathVariable UUID id, Model model) {
        LoaiKhachHang loaiKhachHang = loaiKhachHangService.getByIdLoaiKhachHang(id);
        model.addAttribute("loaiKhachHang", loaiKhachHang);
        return "manage/update-loai-khach-hang";
    }

    @PostMapping("/loai-khach-hang/viewUpdate/{id}")
    public String updateloaikhachhang(@PathVariable UUID id, @ModelAttribute("loaiKhachHang") LoaiKhachHang loaiKhachHang) {
        LoaiKhachHang loaiKhachHangdb = loaiKhachHangService.getByIdLoaiKhachHang(id);
        if (loaiKhachHangdb != null) {
            loaiKhachHangdb.setMaLKH(loaiKhachHang.getMaLKH());
            loaiKhachHangdb.setTenLKH(loaiKhachHang.getTenLKH());
            loaiKhachHangdb.setSoDiem(loaiKhachHang.getSoDiem());
            loaiKhachHangdb.setTgSua(new Date());
            loaiKhachHangdb.setTrangThai(loaiKhachHang.getTrangThai());
            loaiKhachHangService.save(loaiKhachHangdb);
        }
        return "redirect:/manage/loai-khach-hang";
    }
    @GetMapping("/loaiKhachHang/export/pdf")
    public void exportToPDFChatLieu(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=loaiKhachHang_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<LoaiKhachHang> listLKH = loaiKhachHangService.getAllLoaiKhachHang();

        PDFExporterLoaiKH exporter = new PDFExporterLoaiKH(listLKH);
        exporter.export(response);
    }

    @GetMapping("/loaiKhachHang/export/excel")
    public void exportToExcelSize(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=loaiKhachHang_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<LoaiKhachHang> listLKH = loaiKhachHangService.getAllLoaiKhachHang();

        ExcelExporterLoaiKhachHang excelExporter = new ExcelExporterLoaiKhachHang(listLKH);

        excelExporter.export(response);
    }

    @GetMapping("/LoaiKhachHang/filter")
    public String filterData(Model model,
                             @RequestParam(value = "maLKH", required = false) String maLKH,
                             @RequestParam(value = "tenLKH", required = false) String tenLKH) {
        // Thực hiện lọc dữ liệu dựa trên selectedSize (và trạng thái nếu cần)
        List<LoaiKhachHang> filteredLoaiKhachHangs;
        if ("Mã Loại Khách Hàng".equals(maLKH) && "Tên Loại Khách Hàng".equals(tenLKH)) {
            // Nếu người dùng chọn "Tất cả", hiển thị tất cả dữ liệu
            filteredLoaiKhachHangs = loaiKhachHangService.getAllLoaiKhachHang();
        } else {
            // Thực hiện lọc dữ liệu dựa trên selectedSize
            filteredLoaiKhachHangs = loaiKhachHangService.fillterLKH(maLKH, tenLKH);
        }
        model.addAttribute("loaiKhachHang", filteredLoaiKhachHangs);
        model.addAttribute("loaiKhachHangAll", loaiKhachHangService.getAllLoaiKhachHang());

        return "manage/chat-lieu"; // Trả về mẫu HTML chứa bảng dữ liệu sau khi lọc
    }

    @PostMapping("/loaiKhachHang/import")
    public String importData(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                InputStream excelFile = file.getInputStream();
                loaiKhachHangService.importDataFromExcel(excelFile); // Gọi phương thức nhập liệu từ Excel
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi
            }
        }
        return "redirect:/manage/loai-khach-hang"; // Chuyển hướng sau khi nhập liệu thành công hoặc không thành công
    }

}
