package com.example.demo.controller;


import com.example.demo.config.ExcelExporterChucVu;
import com.example.demo.config.ExcelExporterLoaiKhachHang;
import com.example.demo.config.PDFExporterChucVu;
import com.example.demo.config.PDFExporterLoaiKH;
import com.example.demo.model.*;
import com.example.demo.repository.LoaiKhachHangRepository;
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
public class LoaiKhachHangController {
    @Autowired
    private LoaiKhachHangService loaiKhachHangService;
    @Autowired
    private HttpSession session;
    @Autowired
    private KhachHangController khachHangController;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private LoaiKhachHangRepository loaiKhachHangRepository;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/loai-khach-hang")
    public String dsloaikhachhang(Model model, @ModelAttribute("message") String message
            , @ModelAttribute("error") String error, @ModelAttribute("userInput") LoaiKhachHang userInput, @ModelAttribute("Errormessage") String Errormessage) {
        List<LoaiKhachHang> loaikhachhang = loaiKhachHangService.getAllLoaiKhachHang();
        //
        model.addAttribute("loaiKhachHang", loaikhachhang);
        //
        model.addAttribute("loaiKhachHangAdd", new LoaiKhachHang());
        //
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("loaiKhachHangAdd", userInput);
        }
        //
        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/loai-khach-hang";
    }

    @PostMapping("/loai-khach-hang/viewAdd/add")
    public String addloaikhachhang(@Valid @ModelAttribute("loaiKhachHang") LoaiKhachHang loaiKhachHang, BindingResult result, RedirectAttributes redirectAttributes) {
        //
        LoaiKhachHang existingLKH = loaiKhachHangRepository.findByMaLKH(loaiKhachHang.getMaLKH());
        if (existingLKH != null) {
            redirectAttributes.addFlashAttribute("userInput", loaiKhachHang);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return "redirect:/manage/loai-khach-hang";
        }
        //
        LoaiKhachHang loaiKhachHang1 = new LoaiKhachHang();
        loaiKhachHang1.setMaLKH(loaiKhachHang.getMaLKH());
        loaiKhachHang1.setTenLKH(loaiKhachHang.getTenLKH());
        loaiKhachHang1.setSoDiem(loaiKhachHang.getSoDiem());
        loaiKhachHang1.setTgThem(new Date());
        loaiKhachHang1.setTrangThai(1);
        loaiKhachHangService.save(loaiKhachHang1);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/loai-khach-hang";
    }

    @GetMapping("/loai-khach-hang/delete/{id}")
    public String deleteloaikhachhang(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        LoaiKhachHang loaiKhachHang = loaiKhachHangService.getByIdLoaiKhachHang(id);
        loaiKhachHang.setTrangThai(0);
        loaiKhachHang.setTgSua(new Date());
        loaiKhachHangService.save(loaiKhachHang);
        // Cập nhật trạng thái của tất cả sản phẩm chi tiết của hãng thành 0
        List<KhachHang> khachHangs = khachHangService.findByLoaiKhachHang(loaiKhachHang);
        for (KhachHang khachHang : khachHangs) {
            khachHang.setTrangThai(0);
            khachHangService.save(khachHang);
            khachHangController.deleteKHById(khachHang.getIdKH());
        }
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/loai-khach-hang";
    }

    @GetMapping("/loai-khach-hang/viewUpdate/{id}")
    public String viewUpdateloaikhachhang(@PathVariable UUID id, Model model
            , @ModelAttribute("message") String message
            , @ModelAttribute("error") String error, @ModelAttribute("userInput") LoaiKhachHang userInput
            , @ModelAttribute("Errormessage") String Errormessage) {
        LoaiKhachHang loaiKhachHang = loaiKhachHangService.getByIdLoaiKhachHang(id);
        model.addAttribute("loaiKhachHang", loaiKhachHang);
        //
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
            // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
            if (userInput != null) {
                model.addAttribute("loaiKhachHangAdd", userInput);
            }
            //
            session.setAttribute("id", id);
            //
            if (Errormessage == null || !"true".equals(Errormessage)) {
                model.addAttribute("Errormessage", false);
            }
            return "manage/update-loai-khach-hang";
        }


    @PostMapping("/loai-khach-hang/viewUpdate/{id}")
    public String updateloaikhachhang(@PathVariable UUID id, @Valid @ModelAttribute("loaiKhachHang") LoaiKhachHang loaiKhachHang, BindingResult result, RedirectAttributes redirectAttributes) {
        LoaiKhachHang loaiKhachHangdb = loaiKhachHangService.getByIdLoaiKhachHang(id);
        UUID idLKH = (UUID) session.getAttribute("id");
        String link = "redirect:/manage/loai-khach-hang/viewUpdate/" + idLKH;
        //
        LoaiKhachHang existingLKH = loaiKhachHangRepository.findByMaLKH(loaiKhachHang.getMaLKH());
        if (existingLKH != null && !existingLKH.getIdLKH().equals(id)) {
            redirectAttributes.addFlashAttribute("userInput", loaiKhachHang);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return link;
        }
        //
        if (loaiKhachHangdb != null) {
            loaiKhachHangdb.setMaLKH(loaiKhachHang.getMaLKH());
            loaiKhachHangdb.setTenLKH(loaiKhachHang.getTenLKH());
            loaiKhachHangdb.setSoDiem(loaiKhachHang.getSoDiem());
            loaiKhachHangdb.setTgSua(new Date());
            loaiKhachHangdb.setTrangThai(loaiKhachHang.getTrangThai());
            loaiKhachHangService.save(loaiKhachHangdb);
            redirectAttributes.addFlashAttribute("message", true);
        }
        // Nếu trạng thái của chatLieu là 1, hãy cập nhật trạng thái của tất cả sản phẩm chi tiết của chatLieu thành 1.
        if (loaiKhachHangdb.getTrangThai() == 1) {
            List<KhachHang> khachHangs = khachHangService.findByLoaiKhachHang(loaiKhachHangdb);
            for (KhachHang khachHang : khachHangs) {
                khachHang.setTrangThai(1);
                khachHangService.save(khachHang);
            }
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
