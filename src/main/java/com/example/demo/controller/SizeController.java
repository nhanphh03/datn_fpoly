package com.example.demo.controller;

import com.example.demo.config.ExcelExporterSize;
import com.example.demo.config.PDFExporterSizes;
import com.example.demo.model.*;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.SizeService;
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

@RequestMapping("/manage")
@Controller
public class SizeController {
    @Autowired
    private SizeService sizeService;
    @Autowired
    private GiayChiTietService giayChiTietService;
    @Autowired
    private HttpSession session;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(0, "Không Hoạt Động");
        dsTrangThai.put(1, "Hoạt Động");
        return dsTrangThai;
    }

    @ModelAttribute("currentTime")
    public Date getCurrentTime() {
        return new Date();
    }

    @GetMapping("/size")
    public String dsSize(Model model, @ModelAttribute("message") String message
            , @ModelAttribute("maSizeError") String maSizeError
            , @ModelAttribute("soSizeError") String soSizeError
            , @ModelAttribute("error") String error
            , @ModelAttribute("userInput") Size userInput) {
        List<Size> size = sizeService.getAllSize();
        Collections.sort(size, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("size", size);
        //
        model.addAttribute("sizeAll", sizeService.getAllSize());
        //
        model.addAttribute("sizeAdd", new Size());
        //
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        if (maSizeError == null || !"maSizeError".equals(error)) {
            model.addAttribute("maSizeError", false);
        }
        if (soSizeError == null || !"soSizeError".equals(error)) {
            model.addAttribute("soSizeError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("sizeAdd", userInput);
        }
        return "manage/size-giay";
    }

//    @GetMapping("/size/viewAdd")
//    public String viewAddSize(Model model) {
//        model.addAttribute("size", new Size());
//        return "manage/add-size";
//    }

    @PostMapping("/size/viewAdd/add")
    public String addSize(@Valid @ModelAttribute("size") Size size, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("maSize")) {
                redirectAttributes.addFlashAttribute("userInput", size);
                redirectAttributes.addFlashAttribute("error", "maSizeError");
            }
            if (bindingResult.hasFieldErrors("soSize")) {
                redirectAttributes.addFlashAttribute("userInput", size);
                redirectAttributes.addFlashAttribute("error", "soSizeError");
            }
            return "redirect:/manage/size";
        }
        if (size != null) {
            Size sizeAdd = new Size();
            sizeAdd.setMaSize(size.getMaSize());
            sizeAdd.setSoSize(size.getSoSize());
            sizeAdd.setTgThem(new Date());
            sizeAdd.setTrangThai(1);
            sizeService.save(sizeAdd);
        } else {
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return "redirect:/manage/size";
        }
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/size";
    }

    @GetMapping("/size/delete/{id}")
    public String deleteSize(@PathVariable UUID id) {
        Size size = sizeService.getByIdSize(id);
        List<ChiTietGiay> chiTietGiayList = giayChiTietService.findBySize(size);
        //
        size.setTrangThai(0);
        size.setTgSua(new Date());
        sizeService.save(size);
        // Cập nhật trạng thái của tất cả sản phẩm chi tiết của hãng thành 0
        for (ChiTietGiay chiTietGiay : chiTietGiayList) {
            chiTietGiay.setTrangThai(0);
            giayChiTietService.save(chiTietGiay);
        }
        return "redirect:/manage/size";
    }

    @GetMapping("/size/viewUpdate/{id}")
    public String viewUpdateSize(@PathVariable UUID id, Model model) {
        Size size = sizeService.getByIdSize(id);
        model.addAttribute("size", size);
        return "manage/update-size";
    }

    @PostMapping("/size/viewUpdate/{id}")
    public String updateSize(@PathVariable UUID id, @ModelAttribute("size") Size size) {
        Size sizeDb = sizeService.getByIdSize(id);
        if (sizeDb != null) {
            sizeDb.setMaSize(size.getMaSize());
            sizeDb.setSoSize(size.getSoSize());
            sizeDb.setTgSua(new Date());
            sizeDb.setTrangThai(size.getTrangThai());
            sizeService.save(sizeDb);
        }
        if (sizeDb.getTrangThai() == 1) {
            List<ChiTietGiay> chiTietGiays = giayChiTietService.findBySize(sizeDb);
            for (ChiTietGiay chiTietGiay : chiTietGiays) {
                chiTietGiay.setTrangThai(1);
                giayChiTietService.save(chiTietGiay);
            }
        }
        return "redirect:/manage/size";
    }

    @GetMapping("/size/export/pdf")
    public void exportToPDFSize(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=sizes_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Size> listSizes = sizeService.getAllSize();

        PDFExporterSizes exporter = new PDFExporterSizes(listSizes);
        exporter.export(response);
    }

    @GetMapping("/size/export/excel")
    public void exportToExcelSize(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=sizes_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Size> lisSize = sizeService.getAllSize();

        ExcelExporterSize excelExporter = new ExcelExporterSize(lisSize);

        excelExporter.export(response);
    }

    @GetMapping("/size/filter")
    public String filterData(Model model,
                             @RequestParam(value = "selectedSize", required = false) Integer selectedSize,
                             @RequestParam(value = "maSize", required = false) String maSize) {
        // Thực hiện lọc dữ liệu dựa trên selectedSize (và trạng thái nếu cần)
        List<Size> filteredSizes;
        if (selectedSize == null && "Mã Size".equals(maSize)) {
            // Nếu người dùng chọn "Tất cả", hiển thị tất cả dữ liệu
            filteredSizes = sizeService.getAllSize();
        } else {
            // Thực hiện lọc dữ liệu dựa trên selectedSize
            filteredSizes = sizeService.filterSizes(selectedSize, maSize);
        }
        model.addAttribute("size", filteredSizes);
        model.addAttribute("sizeAll", sizeService.getAllSize());

        return "manage/size-giay"; // Trả về mẫu HTML chứa bảng dữ liệu sau khi lọc
    }

    @PostMapping("/size/import")
    public String importData(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                InputStream excelFile = file.getInputStream();
                sizeService.importDataFromExcel(excelFile); // Gọi phương thức nhập liệu từ Excel
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi
            }
        }
        return "redirect:/manage/size"; // Chuyển hướng sau khi nhập liệu thành công hoặc không thành công
    }

}
