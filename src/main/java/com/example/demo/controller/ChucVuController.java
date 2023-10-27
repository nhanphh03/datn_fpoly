package com.example.demo.controller;

import com.example.demo.config.*;
import com.example.demo.model.*;
import com.example.demo.config.PDFExporterMauSac;
import com.example.demo.model.ChatLieu;
import com.example.demo.model.ChucVu;
import com.example.demo.model.MauSac;
import com.example.demo.model.NhanVien;
import com.example.demo.service.ChucVuService;
import com.example.demo.service.NhanVienService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpSession;
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
public class ChucVuController {
    @Autowired
    private ChucVuService chucVuService;
    @Autowired
    private HttpSession session;

    @Autowired
    private NhanVienController nhanVienController;

    @Autowired
    private NhanVienService nhanVienService;

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
        Collections.sort(chucVu, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("chucVu", chucVu);
        model.addAttribute("chucVuAdd", new ChucVu());
        return "manage/chuc-vu";
    }

//    @GetMapping("/chuc-vu/viewAdd")
//    public String viewAddchucVu(Model model) {
//        model.addAttribute("chucVu", new ChucVu());
//        return "manage/add-chuc-vu";
//    }

    @PostMapping("/chuc-vu/viewAdd/add")
    public String addchucVu(@Valid  @ModelAttribute("chucVu") ChucVu chucVu, BindingResult result) {
        if (result.hasErrors()){
            return "mannage/add-chuc-vu";
        }
        ChucVu chucvu1 = new ChucVu();
        chucvu1.setMaCV(chucVu.getMaCV());
        chucvu1.setTenCV(chucVu.getTenCV());
        chucvu1.setTgThem(new Date());
        chucvu1.setTrangThai(1);
        chucVuService.save(chucvu1);
        return "redirect:/manage/chuc-vu";
    }

    @GetMapping("/chuc-vu/delete/{id}")
    public String deletechucVu(@PathVariable UUID id) {
        ChucVu chucVu = chucVuService.getByIdChucVu(id);
        chucVu.setTrangThai(0);
        chucVu.setTgSua(new Date());
        chucVuService.save(chucVu);
        // Cập nhật trạng thái của tất cả sản phẩm chi tiết của hãng thành 0
        List<NhanVien> nhanViens = nhanVienService.findByChucVu(chucVu);
        for (NhanVien nhanVien : nhanViens) {
            nhanVien.setTrangThai(0);
            nhanVienService.save(nhanVien);
            nhanVienController.deleteNVById(nhanVien.getIdNV());
        }
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

    @GetMapping("/chucVu/export/pdf")
    public void exportToPDFChatLieu(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=chucVu_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<ChucVu> listChucVu = chucVuService.getAllChucVu();

        PDFExporterChucVu exporter = new PDFExporterChucVu(listChucVu);
        exporter.export(response);
    }

    @GetMapping("/chucVu/export/excel")
    public void exportToExcelSize(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=chucVu_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<ChucVu> listChucVu = chucVuService.getAllChucVu();

        ExcelExporterChucVu excelExporter = new ExcelExporterChucVu(listChucVu);

        excelExporter.export(response);
    }

    @GetMapping("/chucVu/filter")
    public String filterData(Model model,
                             @RequestParam(value = "maCV", required = false) String maCV,
                             @RequestParam(value = "tenCV", required = false) String tenCV) {
        // Thực hiện lọc dữ liệu dựa trên selectedSize (và trạng thái nếu cần)
        List<ChucVu> filteredChucVus;
        if ("Mã Chức Vụ".equals(maCV) && "Tên Chức Vụ".equals(tenCV)) {
            // Nếu người dùng chọn "Tất cả", hiển thị tất cả dữ liệu
            filteredChucVus = chucVuService.getAllChucVu();
        } else {
            // Thực hiện lọc dữ liệu dựa trên selectedSize
            filteredChucVus = chucVuService.fillterChucVu(maCV, tenCV);
        }
        model.addAttribute("chucVu", filteredChucVus);
        model.addAttribute("chucVuAll", chucVuService.getAllChucVu());

        return "manage/chat-lieu"; // Trả về mẫu HTML chứa bảng dữ liệu sau khi lọc
    }

    @PostMapping("/chucVu/import")
    public String importData(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                InputStream excelFile = file.getInputStream();
                chucVuService.importDataFromExcel(excelFile); // Gọi phương thức nhập liệu từ Excel
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi
            }
        }
        return "redirect:/manage/chuc-vu"; // Chuyển hướng sau khi nhập liệu thành công hoặc không thành công
    }
}
