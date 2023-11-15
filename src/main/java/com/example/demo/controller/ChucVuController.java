package com.example.demo.controller;

import com.example.demo.config.*;
import com.example.demo.model.ChucVu;
import com.example.demo.model.NhanVien;
import com.example.demo.repository.ChucVuRepsitory;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private ChucVuRepsitory chucVuRepsitory;

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
    public String dsChucVu(Model model, @ModelAttribute("message") String message
            , @ModelAttribute("error") String error
            , @ModelAttribute("maCVError") String maCVError
            , @ModelAttribute("tenCVError") String tenCVError
            , @ModelAttribute("userInput") ChucVu userInput, @ModelAttribute("Errormessage") String Errormessage) {
        List<ChucVu> chucVu = chucVuService.getAllChucVu();
        model.addAttribute("chucVu", chucVu);
        //
        model.addAttribute("chucVuAdd", new ChucVu());
        //
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        //
        if (maCVError == null || !"maCVError".equals(error)) {
            model.addAttribute("maCVError", false);
        }
        if (tenCVError == null || !"tenCVError".equals(error)) {
            model.addAttribute("tenCVError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("chucVuAdd", userInput);
        }
        //
        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/chuc-vu";
    }

    @PostMapping("/chuc-vu/viewAdd/add")
    public String addchucVu(@Valid  @ModelAttribute("chucVu") ChucVu chucVu, BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            if (result.hasFieldErrors("tenCV")) {
                redirectAttributes.addFlashAttribute("userInput", chucVu);
                redirectAttributes.addFlashAttribute("error", "tenCVError");
            }
            if (result.hasFieldErrors("maCV")) {
                redirectAttributes.addFlashAttribute("userInput", chucVu);
                redirectAttributes.addFlashAttribute("error", "maCVError");
            }
            return "redirect:/manage/chuc-vu";
        }
        //
        ChucVu existingChucVu = chucVuRepsitory.findByMaCV(chucVu.getMaCV());
        if (existingChucVu != null) {
            redirectAttributes.addFlashAttribute("userInput", chucVu);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return "redirect:/manage/chuc-vu";
        }
        ChucVu chucvu1 = new ChucVu();
        chucvu1.setMaCV(chucVu.getMaCV());
        chucvu1.setTenCV(chucVu.getTenCV());
        chucvu1.setTgThem(new Date());
        chucvu1.setTrangThai(1);
        chucVuService.save(chucvu1);
        redirectAttributes.addFlashAttribute("message",true);
        return "redirect:/manage/chuc-vu";
    }

    @GetMapping("/chuc-vu/delete/{id}")
    public String deletechucVu(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
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
        //
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/chuc-vu";
    }

    @GetMapping("/chuc-vu/viewUpdate/{id}")
    public String viewUpdatechucVu(@PathVariable UUID id, Model model
            , @ModelAttribute("message") String message
            , @ModelAttribute("maCVError") String maCVError
            , @ModelAttribute("tenCVError") String tenCVError
            , @ModelAttribute("error") String error
            , @ModelAttribute("userInput") ChucVu userInput
            , @ModelAttribute("Errormessage") String Errormessage) {
        ChucVu chucVu = chucVuService.getByIdChucVu(id);
        model.addAttribute("chucVu", chucVu);
        //
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        //
        if (maCVError == null || !"maCVError".equals(error)) {
            model.addAttribute("maCVError", false);
        }
        if (tenCVError == null || !"tenCVError".equals(error)) {
            model.addAttribute("tenCVError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("chucVuAdd", userInput);
        }
        //
        session.setAttribute("id", id);
        //
        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/update-chuc-vu";
    }

    @PostMapping("/chuc-vu/viewUpdate/{id}")
    public String updateChucVu(@PathVariable UUID id,@Valid @ModelAttribute("chucVu") ChucVu chucVu,BindingResult result,RedirectAttributes redirectAttributes) {
        ChucVu chucVuDb = chucVuService.getByIdChucVu(id);
        UUID idCV = (UUID) session.getAttribute("id");
        String link = "redirect:/manage/chuc-vu/viewUpdate/" + idCV;
        if (result.hasErrors()) {
            if (result.hasFieldErrors("tenCV")) {
                redirectAttributes.addFlashAttribute("userInput", chucVu);
                redirectAttributes.addFlashAttribute("error", "tenCVError");
            }
            if (result.hasFieldErrors("maCV")) {
                redirectAttributes.addFlashAttribute("userInput", chucVu);
                redirectAttributes.addFlashAttribute("error", "maCVError");
            }
            return link;
        }
        //
        ChucVu existingChucVu = chucVuRepsitory.findByMaCV(chucVu.getMaCV());
        if (existingChucVu != null && !existingChucVu.getIdCV().equals(id)) {
            redirectAttributes.addFlashAttribute("userInput", chucVu);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return link;
        }
        //
        if (chucVuDb != null) {
            chucVuDb.setMaCV(chucVu.getMaCV());
            chucVuDb.setTenCV(chucVu.getTenCV());
            chucVuDb.setTgSua(new Date());
            chucVuDb.setTrangThai(chucVu.getTrangThai());
            chucVuService.save(chucVuDb);
            redirectAttributes.addFlashAttribute("message", true);
        }
//         Nếu trạng thái của chatLieu là 1, hãy cập nhật trạng thái của tất cả sản phẩm chi tiết của chatLieu thành 1.
        if (chucVuDb.getTrangThai() == 1) {
            List<NhanVien> nhanViens = nhanVienService.findByChucVu(chucVuDb);
            for (NhanVien nhanVien : nhanViens) {
                nhanVien.setTrangThai(1);
                nhanVienService.save(nhanVien);
            }
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
