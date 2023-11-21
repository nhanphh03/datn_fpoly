package com.example.demo.controller;

import com.example.demo.config.*;
import com.example.demo.model.*;
import com.example.demo.repository.GiayChiTietRepository;
import com.example.demo.repository.HangRepository;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.GiayService;
import com.example.demo.service.HangService;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/manage")
@Controller
public class HangController {
    @Autowired
    private HangService hangService;
    @Autowired
    private GiayService giayService;
    @Autowired
    private GiayChiTietService giayChiTietService;
    @Autowired
    private GiayController giayController;
    @Autowired
    private HttpSession session;
    @Autowired
    private HangRepository repository;


    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/hang")
    public String dsHang(Model model, @ModelAttribute("message") String message
            , @ModelAttribute("maHangError") String maHangError
            , @ModelAttribute("tenHangError") String tenHangError
            , @ModelAttribute("error") String error, @ModelAttribute("userInput") Hang userInput, @ModelAttribute("Errormessage") String Errormessage) {

        List<Hang> hang = hangService.getALlHang();
        model.addAttribute("hang", hang);
        //
        model.addAttribute("hangAdd", new Hang());
        //
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        if (maHangError == null || !"maHangError".equals(error)) {
            model.addAttribute("maHangError", false);
        }
        if (tenHangError == null || !"tenHangError".equals(error)) {
            model.addAttribute("tenHangError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("hangAdd", userInput);
        }
        //
        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/hang";
    }

//    @GetMapping("/hang/viewAdd")
//    public String viewAddHang(Model model) {
//        model.addAttribute("hang", new Hang());
//        return "manage/add-hang";
//    }

    @PostMapping("/hang/viewAdd/add")
    public String addHang(@RequestParam("maHang") String maHang,
                          @RequestParam("tenHang") String tenHang,
                          @RequestParam("logoHang") MultipartFile logoHang,
                          @Valid @ModelAttribute("hang") Hang hang, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        Hang existingHang = repository.findByMaHang(hang.getMaHang());
        if (existingHang != null) {
            redirectAttributes.addFlashAttribute("userInput", hang);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return "redirect:/manage/hang";
        }
        //
        Path path = Paths.get("src/main/resources/static/images/logoBrands/");
        //
        Hang hang1 = new Hang();
        hang1.setMaHang(maHang);
        hang1.setTenHang(tenHang);
        if (logoHang.isEmpty()) {
            return "redirect:/manage/hang";
        }
        try {
            InputStream inputStream = logoHang.getInputStream();
            Files.copy(inputStream, path.resolve(logoHang.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            hang1.setLogoHang(logoHang.getOriginalFilename().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        hang1.setTgThem(new Date());
        hang1.setTrangThai(1);
        hangService.save(hang1);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/hang";
    }

    @GetMapping("/hang/delete/{id}")
    public String deleteHang(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        Hang hang = hangService.getByIdHang(id);
        hang.setTrangThai(0);
        hang.setTgSua(new Date());
        hangService.save(hang);
        // Cập nhật trạng thái của tất cả sản phẩm chi tiết của hãng thành 0
//        List<Giay> giays = giayService.findByHang(hang);
//        for (Giay giay : giays) {
//            giay.setTrangThai(0);
//            giayService.save(giay);
//            giayController.deleteGiayById(giay.getIdGiay());
//        }
        //
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/hang";
    }

    @GetMapping("/hang/viewUpdate/{id}")
    public String viewUpdateHang(@PathVariable UUID id, Model model
            , @ModelAttribute("message") String message
            , @ModelAttribute("maHangError") String maHangError
            , @ModelAttribute("tenHangError") String tenHangError
            , @ModelAttribute("error") String error, @ModelAttribute("userInput") Hang userInput
            , @ModelAttribute("Errormessage") String Errormessage) {
        Hang hang = hangService.getByIdHang(id);
        model.addAttribute("hang", hang);
        //
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        if (maHangError == null || !"maHangError".equals(error)) {
            model.addAttribute("maHangError", false);
        }
        if (tenHangError == null || !"tenHangError".equals(error)) {
            model.addAttribute("tenHangError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("hangAdd", userInput);
        }
        //
        session.setAttribute("id", id);
        //
        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/update-hang";
    }

    @PostMapping("/hang/viewUpdate/{id}")
    public String updateHang(@RequestParam("maHang") String maHang,
                             @RequestParam("tenHang") String tenHang,
                             @RequestParam("logoHang") MultipartFile logoHang,
                             @PathVariable UUID id, @Valid @ModelAttribute("hang") Hang hang, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Hang hangDb = hangService.getByIdHang(id);
        UUID idHang = (UUID) session.getAttribute("id");
        String link = "redirect:/manage/hang/viewUpdate/" + idHang;
        //
        Hang existingHang = repository.findByMaHang(hang.getMaHang());
        if (existingHang != null && !existingHang.getIdHang().equals(id)) {
            redirectAttributes.addFlashAttribute("userInput", hang);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return link;
        }
        //
        Path path = Paths.get("src/main/resources/static/images/logoBrands/");
        //
        if (hangDb != null) {
            hangDb.setMaHang(maHang);
            hangDb.setTenHang(tenHang);
            try {
                InputStream inputStream = logoHang.getInputStream();
                Files.copy(inputStream, path.resolve(logoHang.getOriginalFilename()),
                        StandardCopyOption.REPLACE_EXISTING);
                hangDb.setLogoHang(logoHang.getOriginalFilename().toLowerCase());
            } catch (Exception e) {
                e.printStackTrace();
            }
            hangDb.setTgSua(new Date());
            hangDb.setTrangThai(hang.getTrangThai());
            hangService.save(hangDb);
            redirectAttributes.addFlashAttribute("message", true);
        }
        // Nếu trạng thái của hãng là 1, hãy cập nhật trạng thái của tất cả sản phẩm chi tiết của hãng thành 1.
        if (hangDb.getTrangThai() == 1) {
            List<Giay> giays = giayService.findByHang(hangDb);
            for (Giay giay : giays) {
                giay.setTrangThai(1);
                giayService.save(giay);
                giayController.updateGiayById(giay.getIdGiay());
            }
        }
        return "redirect:/manage/hang";
    }

    @GetMapping("/hang/export/pdf")
    public void exportToPDFHang(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=hang_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Hang> listHang = hangService.getALlHang();

        PDFExporterHang exporter = new PDFExporterHang(listHang);
        exporter.export(response);
    }

    @GetMapping("/hang/export/excel")
    public void exportToExcelSize(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=hang_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Hang> listHang = hangService.getALlHang();

        ExcelExporterHang excelExporter = new ExcelExporterHang(listHang);

        excelExporter.export(response);
    }

    @GetMapping("/hang/filter")
    public String filterData(Model model,
                             @RequestParam(value = "maHang", required = false) String maHang,
                             @RequestParam(value = "tenHang", required = false) String tenHang) {
        // Thực hiện lọc dữ liệu dựa trên selectedSize (và trạng thái nếu cần)
        List<Hang> filteredHangs;
        if ("Mã Hãng".equals(maHang) && "Tên Hãng".equals(tenHang)) {
            // Nếu người dùng chọn "Tất cả", hiển thị tất cả dữ liệu
            filteredHangs = hangService.getALlHang();
        } else {
            // Thực hiện lọc dữ liệu dựa trên selectedSize
            filteredHangs = hangService.fillterHang(maHang, tenHang);
        }
        model.addAttribute("hang", filteredHangs);
        model.addAttribute("hangAll", hangService.getALlHang());

        return "manage/hang"; // Trả về mẫu HTML chứa bảng dữ liệu sau khi lọc
    }

    @PostMapping("/hang/import")
    public String importData(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                InputStream excelFile = file.getInputStream();
                hangService.importDataFromExcel(excelFile); // Gọi phương thức nhập liệu từ Excel
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi
            }
        }
        return "redirect:/manage/hang"; // Chuyển hướng sau khi nhập liệu thành công hoặc không thành công
    }
}
