package com.example.demo.controller;

import com.example.demo.config.ExcelExporterKhachHang;
import com.example.demo.config.ExcelExporterNhanVien;
import com.example.demo.config.PDFExporterKhachHang;
import com.example.demo.config.PDFExporterNhanVien;
import com.example.demo.model.ChucVu;
import com.example.demo.model.KhachHang;
import com.example.demo.model.LoaiKhachHang;
import com.example.demo.model.NhanVien;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.LoaiKhachHangService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletRequest;
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

@Controller
@RequestMapping("/manage")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;
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

    @ModelAttribute("dsGioiTinh")
    public Map<Integer, String> getDsGioiTinh() {
        Map<Integer, String> dsGioiTinh = new HashMap<>();
        dsGioiTinh.put(1, "Nam");
        dsGioiTinh.put(0, "Nữ");
        return dsGioiTinh;
    }

    @GetMapping("/khach-hang")
    public String dsKhachHang(Model model) {
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        List<LoaiKhachHang> loaiKhachHangs = loaiKhachHangService.getAllLoaiKhachHang();
        for (KhachHang khachHangItem : khachHangs) {
            if (khachHangItem.getLoaiKhachHang().getTrangThai() == 0) {
                khachHangItem.setTrangThai(0);
                khachHangService.save(khachHangItem);
            }
        }
        Collections.sort(khachHangs, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("khachHang", khachHangs);
        model.addAttribute("loaiKhachHang", loaiKhachHangs);
        return "manage/khach-hang";
    }

    @GetMapping("/khach-hang/viewAdd")
    public String viewAddkhachHang(Model model) {
        List<LoaiKhachHang> loaiKhachHangs = loaiKhachHangService.getAllLoaiKhachHang();
        Collections.sort(loaiKhachHangs, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("loaiKhachHang", loaiKhachHangs);

        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("loaiKhachHangAdd", new LoaiKhachHang());
        return "manage/add-khach-hang";
    }

    @PostMapping("/khach-hang/viewAdd/add")
    public String addkhachHang(@Valid @ModelAttribute("khachHang") KhachHang khachHang, Model model, BindingResult result) {
        if(result.hasErrors()){
            List<LoaiKhachHang> loaiKhachHangList = loaiKhachHangService.getAllLoaiKhachHang();
            Collections.sort(loaiKhachHangList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
            model.addAttribute("loaiKhachHang", loaiKhachHangList);

            model.addAttribute("khachHang", new KhachHang());
            model.addAttribute("loaiKhachHangAdd", new LoaiKhachHang());
            return "manage/add-khach-hang";
        }
        khachHang.setTgThem(new Date());
        khachHangService.save(khachHang);
        return "redirect:/manage/khach-hang";
    }


    @GetMapping("/khach-hang/delete/{id}")
    public String deleteNhanVien(@PathVariable UUID id) {
        KhachHang khachHang = khachHangService.getByIdKhachHang(id);
        khachHang.setTrangThai(0);
        khachHang.setTgSua(new Date());
        khachHangService.save(khachHang);
        return "redirect:/manage/khach-hang";
    }
    public void deleteKHById(UUID idKH) {
        KhachHang khachHang = khachHangService.getByIdKhachHang(idKH);
        khachHang.setTrangThai(0);
        khachHang.setTgSua(new Date());
        khachHangService.save(khachHang);
    }

    @GetMapping("/khach-hang/viewUpdate/{id}")
    public String viewUpdatekhachhang(@PathVariable UUID id, Model model) {
        KhachHang khachHang = khachHangService.getByIdKhachHang(id);
        List<LoaiKhachHang> loaiKhachHangs = loaiKhachHangService.getAllLoaiKhachHang();
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("loaiKhachHang", loaiKhachHangs);
        return "manage/update-khach-hang";
    }

    @PostMapping("/khach-hang/viewUpdate/{id}")
    public String updatekhachhang(@PathVariable UUID id, @ModelAttribute("khachHang") KhachHang khachHang) {
        KhachHang khachHangdb = khachHangService.getByIdKhachHang(id);
        if (khachHangdb != null) {
            khachHangdb.setMaKH(khachHang.getMaKH());
            khachHangdb.setHoTenKH(khachHang.getHoTenKH());
            khachHangdb.setAnhKH(khachHang.getAnhKH());
            khachHangdb.setDiaChi(khachHang.getDiaChi());
            khachHangdb.setEmailKH(khachHang.getEmailKH());
            khachHangdb.setMatKhau(khachHang.getMatKhau());
            khachHangdb.setSdtKH(khachHang.getSdtKH());
            khachHangdb.setCCCDKH(khachHang.getCCCDKH());
            khachHangdb.setGioiTinh(khachHang.getGioiTinh());
            khachHangdb.setNgaySinh(khachHang.getNgaySinh());
            khachHangdb.setTgSua(new Date());
            khachHangdb.setTrangThai(khachHang.getTrangThai());
            khachHangdb.setLoaiKhachHang(khachHang.getLoaiKhachHang());
            khachHangService.save(khachHangdb);
        }
        return "redirect:/manage/khach-hang";
    }
    @GetMapping("/khachHang/export/pdf")
    public void exportToPDFChatLieu(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=khachHang_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<KhachHang> listKhachHang = khachHangService.getAllKhachHang();

        PDFExporterKhachHang exporter = new PDFExporterKhachHang(listKhachHang);
        exporter.export(response);
    }
    @GetMapping("/khachHang/export/excel")
    public void exportToExcelSize(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=khachHang_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<KhachHang> listkhachHang = khachHangService.getAllKhachHang();

        ExcelExporterKhachHang excelExporter = new ExcelExporterKhachHang(listkhachHang);

        excelExporter.export(response);
    }

    @GetMapping("/khachHang/filter")
    public String filterData(Model model,
                             @RequestParam(value = "maKH", required = false) String maKH,
                             @RequestParam(value = "tenKH", required = false) String tenKH) {
        // Thực hiện lọc dữ liệu dựa trên selectedSize (và trạng thái nếu cần)
        List<KhachHang> filteredKhachHangs;
        if ("Mã Khách Hàng".equals(maKH) && "Tên Khách Hàng".equals(tenKH)) {
            // Nếu người dùng chọn "Tất cả", hiển thị tất cả dữ liệu
            filteredKhachHangs = khachHangService.getAllKhachHang();
        } else {
            // Thực hiện lọc dữ liệu dựa trên selectedSize
            filteredKhachHangs = khachHangService.fillterKhachHang(maKH, tenKH);
        }
        model.addAttribute("khachHang", filteredKhachHangs);
        model.addAttribute("khachHangAll", khachHangService.getAllKhachHang());

        return "manage/khach-hang"; // Trả về mẫu HTML chứa bảng dữ liệu sau khi lọc
    }

    @PostMapping("/khachHang/import")
    public String importData(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                InputStream excelFile = file.getInputStream();
                khachHangService.importDataFromExcel(excelFile); // Gọi phương thức nhập liệu từ Excel
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi
            }
        }
        return "redirect:/manage/khach-hang"; // Chuyển hướng sau khi nhập liệu thành công hoặc không thành công
    }
}
