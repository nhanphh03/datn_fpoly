package com.example.demo.controller;

import com.example.demo.config.ExcelExporterHinhAnh;
import com.example.demo.config.ExcelExporterSize;
import com.example.demo.config.PDFExporterHinhAnh;
import com.example.demo.config.PDFExporterSizes;
import com.example.demo.model.HinhAnh;
import com.example.demo.model.NhanVien;
import com.example.demo.model.Size;
import com.example.demo.repository.HinhAnhRepository;
import com.example.demo.service.HinhAnhService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/manage")
@Controller
public class HinhAnhController {
    @Autowired
    private HinhAnhService hinhAnhService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HinhAnhRepository repository;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/hinh-anh")
    public String dsHinhAnh(Model model, @ModelAttribute("message") String message
            , @ModelAttribute("maHinhAnhError") String maHinhAnhError
            , @ModelAttribute("error") String error, @ModelAttribute("userInput") HinhAnh userInput, @ModelAttribute("Errormessage") String Errormessage) {

        List<HinhAnh> hinhAnh = hinhAnhService.getAllHinhAnh();
        model.addAttribute("hinhAnh", hinhAnh);
        //
        model.addAttribute("hinhAnhAdd", new HinhAnh());
        //
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        if (maHinhAnhError == null || !"maHinhAnhError".equals(error)) {
            model.addAttribute("maHinhAnhError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("hinhAnhAdd", userInput);
        }
        //
        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/hinh-anh";
    }

//    @GetMapping("/hinh-anh/viewAdd")
//    public String viewAddHinhAnh(Model model) {
//        model.addAttribute("hinhAnh", new HinhAnh());
//        return "manage/add-hinh-anh";
//    }

    @PostMapping("/hinh-anh/viewAdd/add")
    public String addHinhAnh(@Valid @ModelAttribute("hinhAnh") HinhAnh hinhAnh, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("maAnh")) {
                redirectAttributes.addFlashAttribute("userInput", hinhAnh);
                redirectAttributes.addFlashAttribute("error", "maHinhAnhError");
            }
            return "redirect:/manage/hinh-anh";
        }
        //
        HinhAnh existingAnh = repository.findByMaAnh(hinhAnh.getMaAnh());
        if (existingAnh != null) {
            redirectAttributes.addFlashAttribute("userInput", hinhAnh);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return "redirect:/manage/hinh-anh";
        }
        //
        String uploadDir = "src/main/resources/static/images/imgsProducts/";
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        try {
            String fileName = UUID.randomUUID().toString() + ".jpg"; // Tạo tên file duy nhất
            Path filePath = Paths.get(uploadPath.getAbsolutePath(), fileName);
            Files.write(filePath, hinhAnh.getUrl1().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        HinhAnh hinhAnh1 = new HinhAnh();
        hinhAnh1.setMaAnh(hinhAnh.getMaAnh());
        hinhAnh1.setUrl1(hinhAnh.getUrl1());
        hinhAnh1.setUrl2(hinhAnh.getUrl2());
        hinhAnh1.setUrl3(hinhAnh.getUrl3());
        hinhAnh1.setUrl4(hinhAnh.getUrl4());
        hinhAnh1.setTgThem(new Date());
        hinhAnh1.setTrangThai(1);
        hinhAnhService.save(hinhAnh1);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/hinh-anh";
    }

    @GetMapping("/hinh-anh/delete/{id}")
    public String deleteHinhAnh(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        HinhAnh hinhAnh = hinhAnhService.getByIdHinhAnh(id);
        hinhAnh.setTrangThai(0);
        hinhAnh.setTgSua(new Date());
        hinhAnhService.save(hinhAnh);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/hinh-anh";
    }

    @GetMapping("/hinh-anh/viewUpdate/{id}")
    public String viewUpdateHinhAnh(@PathVariable UUID id, Model model
            , @ModelAttribute("message") String message
            , @ModelAttribute("maHinhAnhError") String maHinhAnhError
            , @ModelAttribute("error") String error, @ModelAttribute("userInput") HinhAnh userInput, @ModelAttribute("Errormessage") String Errormessage) {
        HinhAnh hinhAnh = hinhAnhService.getByIdHinhAnh(id);
        model.addAttribute("hinhAnh", hinhAnh);
        //
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        if (maHinhAnhError == null || !"maHinhAnhError".equals(error)) {
            model.addAttribute("maHinhAnhError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("hinhAnhAdd", userInput);
        }
        //
        session.setAttribute("id", id);
        //
        //
        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/update-hinh-anh";
    }

    @PostMapping("/hinh-anh/viewUpdate/{id}")
    public String updateHinhAnh(@PathVariable UUID id, @Valid @ModelAttribute("hinhAnh") HinhAnh hinhAnh, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        HinhAnh hinhAnhDb = hinhAnhService.getByIdHinhAnh(id);
        UUID idAnh = (UUID) session.getAttribute("id");
        String link = "redirect:/manage/hinh-anh/viewUpdate/" + idAnh;
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("maAnh")) {
                redirectAttributes.addFlashAttribute("userInput", hinhAnh);
                redirectAttributes.addFlashAttribute("error", "maHinhAnhError");
            }
            return link;
        }
        //
        HinhAnh existingAnh = repository.findByMaAnh(hinhAnh.getMaAnh());
        if (existingAnh != null && !existingAnh.getIdHinhAnh().equals(id)) {
            redirectAttributes.addFlashAttribute("userInput", hinhAnh);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return link;
        }
        //
        if (hinhAnhDb != null) {
            hinhAnhDb.setTgSua(new Date());
            hinhAnhDb.setTrangThai(hinhAnh.getTrangThai());
//            hinhAnhDb.setUrl1(hinhAnh.getUrl1());
//            hinhAnhDb.setUrl2(hinhAnh.getUrl2());
//            hinhAnhDb.setUrl3(hinhAnh.getUrl3());
//            hinhAnhDb.setUrl4(hinhAnh.getUrl4());
            hinhAnhService.save(hinhAnhDb);
            redirectAttributes.addFlashAttribute("message", true);
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
