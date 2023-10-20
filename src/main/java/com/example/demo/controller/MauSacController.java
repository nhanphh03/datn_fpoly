package com.example.demo.controller;

import com.example.demo.config.ExcelExporterMauSac;
import com.example.demo.config.ExcelExporterSize;
import com.example.demo.config.PDFExporterMauSac;
import com.example.demo.config.PDFExporterSizes;
import com.example.demo.model.MauSac;
import com.example.demo.model.Size;
import com.example.demo.service.MauSacService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
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

@RequestMapping("/manage")
@Controller
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/mau-sac")
    public String dsMauSac(Model model) {
        List<MauSac> mauSac = mauSacService.getALlMauSac();
        Collections.sort(mauSac, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("mauSac", mauSac);
        return "manage/mau-sac";
    }

    @GetMapping("/mau-sac/viewAdd")
    public String viewAddMauSac(Model model) {
        model.addAttribute("mauSac", new MauSac());
        return "manage/add-mau-sac";
    }

    @PostMapping("/mau-sac/viewAdd/add")
    public String addMauSac(@Valid @ModelAttribute("mauSac") MauSac mauSac, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "manage/add-mau-sac";
        }
        MauSac mauSac1 = new MauSac();
        mauSac1.setMaMau(mauSac.getMaMau());
        mauSac1.setTenMau(mauSac.getTenMau());
        mauSac1.setTgThem(new Date());
        mauSac1.setTrangThai(mauSac.getTrangThai());
        mauSacService.save(mauSac1);
        return "redirect:/manage/mau-sac";
    }

    @GetMapping("/mau-sac/delete/{id}")
    public String deleteMauSac(@PathVariable UUID id) {
        MauSac mauSac = mauSacService.getByIdMauSac(id);
        mauSac.setTrangThai(0);
        mauSac.setTgSua(new Date());
        mauSacService.save(mauSac);
        return "redirect:/manage/mau-sac";
    }

    @GetMapping("/mau-sac/viewUpdate/{id}")
    public String viewUpdateMauSac(@PathVariable UUID id, Model model) {
        MauSac mauSac = mauSacService.getByIdMauSac(id);
        model.addAttribute("mauSac", mauSac);
        return "manage/update-mau-sac";
    }

    @PostMapping("/mau-sac/viewUpdate/{id}")
    public String updateMauSac(@PathVariable UUID id, @ModelAttribute("mauSac") MauSac mauSac) {
        MauSac mauSacDb = mauSacService.getByIdMauSac(id);
        if (mauSacDb != null) {
            mauSacDb.setMaMau(mauSac.getMaMau());
            mauSacDb.setTenMau(mauSac.getTenMau());
            mauSacDb.setTgSua(new Date());
            mauSacDb.setTrangThai(mauSac.getTrangThai());
            mauSacService.save(mauSacDb);
        }
        return "redirect:/manage/mau-sac";
    }

    @GetMapping("/mauSac/export/pdf")
    public void exportToPDFMauSac(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=mauSac_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<MauSac> listMauSac = mauSacService.getALlMauSac();

        PDFExporterMauSac exporter = new PDFExporterMauSac(listMauSac);
        exporter.export(response);
    }

    @GetMapping("/mauSac/export/excel")
    public void exportToExcelSize(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=mauSac_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<MauSac> listMauSac = mauSacService.getALlMauSac();

        ExcelExporterMauSac excelExporter = new ExcelExporterMauSac(listMauSac);

        excelExporter.export(response);
    }

    @GetMapping("/mauSac/filter")
    public String filterData(Model model,
                             @RequestParam(value = "maMau", required = false) String maMau,
                             @RequestParam(value = "tenMau", required = false) String tenMau) {
        // Thực hiện lọc dữ liệu dựa trên selectedSize (và trạng thái nếu cần)
        List<MauSac> filteredMauSacs;
        if ("Mã Màu".equals(maMau) && "Tên Màu".equals(tenMau)) {
            // Nếu người dùng chọn "Tất cả", hiển thị tất cả dữ liệu
            filteredMauSacs = mauSacService.getALlMauSac();
        } else {
            // Thực hiện lọc dữ liệu dựa trên selectedSize
            filteredMauSacs = mauSacService.filterMauSac(maMau, tenMau);
        }
        model.addAttribute("mauSac", filteredMauSacs);
        model.addAttribute("mauSacAll", mauSacService.getALlMauSac());

        return "manage/mau-sac"; // Trả về mẫu HTML chứa bảng dữ liệu sau khi lọc
    }

    @PostMapping("/mauSac/import")
    public String importData(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                InputStream excelFile = file.getInputStream();
                mauSacService.importDataFromExcel(excelFile); // Gọi phương thức nhập liệu từ Excel
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi
            }
        }
        return "redirect:/manage/mau-sac"; // Chuyển hướng sau khi nhập liệu thành công hoặc không thành công
    }
}
