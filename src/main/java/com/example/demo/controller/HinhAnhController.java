package com.example.demo.controller;

import com.example.demo.config.ExcelExporterHinhAnh;
import com.example.demo.config.ExcelExporterSize;
import com.example.demo.config.PDFExporterHinhAnh;
import com.example.demo.config.PDFExporterSizes;
import com.example.demo.model.HinhAnh;
import com.example.demo.model.Size;
import com.example.demo.service.HinhAnhService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/manage")
@Controller
public class HinhAnhController {
    @Autowired
    private HinhAnhService hinhAnhService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/hinh-anh")
    public String dsHinhAnh(Model model) {
        List<HinhAnh> hinhAnh = hinhAnhService.getAllHinhAnh();
        model.addAttribute("hinhAnh", hinhAnh);
        return "manage/hinh-anh";
    }

    @GetMapping("/hinh-anh/viewAdd")
    public String viewAddHinhAnh(Model model) {
        model.addAttribute("hinhAnh", new HinhAnh());
        return "manage/add-hinh-anh";
    }

    @PostMapping("/hinh-anh/viewAdd/add")
    public String addHinhAnh(@ModelAttribute("hinhAnh") HinhAnh hinhAnh) {
        HinhAnh hinhAnh1 = new HinhAnh();
        hinhAnh1.setUrl1(hinhAnh.getUrl1());
        hinhAnh1.setUrl2(hinhAnh.getUrl2());
        hinhAnh1.setUrl3(hinhAnh.getUrl3());
        hinhAnh1.setUrl4(hinhAnh.getUrl4());
        hinhAnh1.setTgThem(new Date());
        hinhAnh1.setTrangThai(hinhAnh.getTrangThai());
        hinhAnhService.save(hinhAnh1);
        return "redirect:/manage/hinh-anh";
    }

    @GetMapping("/hinh-anh/delete/{id}")
    public String deleteHinhAnh(@PathVariable UUID id) {
        HinhAnh hinhAnh = hinhAnhService.getByIdHinhAnh(id);
        hinhAnh.setTrangThai(0);
        hinhAnh.setTgSua(new Date());
        hinhAnhService.save(hinhAnh);
        return "redirect:/manage/hinh-anh";
    }

    @GetMapping("/hinh-anh/viewUpdate/{id}")
    public String viewUpdateHinhAnh(@PathVariable UUID id, Model model) {
        HinhAnh hinhAnh = hinhAnhService.getByIdHinhAnh(id);
        model.addAttribute("hinhAnh", hinhAnh);
        return "manage/update-hinh-anh";
    }

    @PostMapping("/hinh-anh/viewUpdate/{id}")
    public String updateHinhAnh(@PathVariable UUID id, @ModelAttribute("hinhAnh") HinhAnh hinhAnh) {
        HinhAnh hinhAnhDb = hinhAnhService.getByIdHinhAnh(id);
        if (hinhAnhDb != null) {
            hinhAnhDb.setTgSua(new Date());
            hinhAnhDb.setTrangThai(hinhAnh.getTrangThai());
            hinhAnhDb.setUrl1(hinhAnh.getUrl1());
            hinhAnhDb.setUrl2(hinhAnh.getUrl2());
            hinhAnhDb.setUrl3(hinhAnh.getUrl3());
            hinhAnhDb.setUrl4(hinhAnh.getUrl4());
            hinhAnhService.save(hinhAnhDb);
        }
        return "redirect:/manage/hinh-anh";
    }

    @GetMapping("/hinhAnh/export/pdf")
    public void exportToPDFHinhAnh(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=hinhAnh_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<HinhAnh> listHinhAnh = hinhAnhService.getAllHinhAnh();

        PDFExporterHinhAnh exporter = new PDFExporterHinhAnh(listHinhAnh);
        exporter.export(response);
    }

    @GetMapping("/hinhAnh/export/excel")
    public void exportToExcelSize(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=hinhAnh_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<HinhAnh> lisHinhAnh = hinhAnhService.getAllHinhAnh();

        ExcelExporterHinhAnh excelExporter = new ExcelExporterHinhAnh(lisHinhAnh);

        excelExporter.export(response);
    }
}
