package com.example.demo.controller;

import com.example.demo.config.ExcelExporterChatLieu;
import com.example.demo.config.ExcelExporterNhanVien;
import com.example.demo.config.PDFExporterChatLieu;
import com.example.demo.config.PDFExporterNhanVien;
import com.example.demo.model.*;
import com.example.demo.repository.ChucVuRepsitory;
import com.example.demo.repository.NhanVienRepsitory;
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
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private ChucVuService chucVuService;

    @Autowired
    private HttpSession session;

    @Autowired
    private NhanVienRepsitory nhanVienRepsitory;

    @Autowired
    private ChucVuRepsitory chucVuRepsitory;

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
        dsGioiTinh.put(2, "Khác");
        return dsGioiTinh;
    }

    @GetMapping("/nhan-vien")
    public String dsNhanVien(Model model, @ModelAttribute("message") String message) {
        List<NhanVien> nhanViens = nhanVienRepsitory.getAllNhanVien();
        List<ChucVu> chucVus = chucVuService.getAllChucVu();
        model.addAttribute("nhanVien", nhanViens);
        model.addAttribute("chucVu", chucVus);
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        return "manage/nhan-vien";
    }

    @GetMapping("/nhan-vien/viewAdd")
    public String viewAddNhanVien(Model model
            , @ModelAttribute("errorNV") String errorNV
            , @ModelAttribute("userInput") NhanVien userInputNV
            , @ModelAttribute("messageCV") String messageCV
            , @ModelAttribute("maCVError") String maCVError
            , @ModelAttribute("tenCVError") String tenCVError
            , @ModelAttribute("errorCV") String errorCV, @ModelAttribute("userInput") ChucVu userInputCV
            , @ModelAttribute("Errormessage") String Errormessage
            , @ModelAttribute("ErrormessageCV") String ErrormessageCV) {
        List<ChucVu> chucVuList = chucVuService.getAllChucVu();
        Collections.sort(chucVuList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("chucVu", chucVuList);
        //
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("chucVuAdd", new ChucVu());
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputNV != null) {
            model.addAttribute("nhanVien", userInputNV);
        }
        //add CV
        if (messageCV == null || !"true".equals(messageCV)) {
            model.addAttribute("messageCV", false);
        }
        if (userInputCV != null) {
            model.addAttribute("chucVuAdd", userInputCV);
        }
        //
        if (ErrormessageCV == null || !"true".equals(ErrormessageCV)) {
            model.addAttribute("ErrormessageCV", false);
        }

        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/add-nhan-vien";
    }

    @PostMapping("/nhan-vien/viewAdd/add")
    public String addNhanVien(@ModelAttribute("nhanVien") NhanVien nhanVien, Model model
            , RedirectAttributes redirectAttributes) {
            List<ChucVu> chucVuList = chucVuService.getAllChucVu();
            Collections.sort(chucVuList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
            model.addAttribute("chucVu", chucVuList);
            model.addAttribute("nhanVien", new NhanVien());
            model.addAttribute("chucVuAdd", new ChucVu());

        NhanVien existingNV = nhanVienRepsitory.findByMaNV(nhanVien.getMaNV());
        if (existingNV != null) {
            redirectAttributes.addFlashAttribute("userInput", nhanVien);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return "redirect:/manage/nhan-vien/viewAdd";
        }
        NhanVien existingNV1 = nhanVienRepsitory.findByEmailNV(nhanVien.getEmailNV());
        if (existingNV1 != null) {
            redirectAttributes.addFlashAttribute("userInput", nhanVien);
            redirectAttributes.addFlashAttribute("ErrormessageeEmail", true);
            return "redirect:/manage/nhan-vien/viewAdd";
        }
        NhanVien existingNV2 = nhanVienRepsitory.findByCCCDNV(nhanVien.getCCCDNV());
        if (existingNV2 != null) {
            redirectAttributes.addFlashAttribute("userInput", nhanVien);
            redirectAttributes.addFlashAttribute("ErrormessageCCCD", true);
            return "redirect:/manage/nhan-vien/viewAdd";
        }
        NhanVien existingNV3 = nhanVienRepsitory.findBySdtNV(nhanVien.getSdtNV());
        if (existingNV3 != null) {
            redirectAttributes.addFlashAttribute("userInput", nhanVien);
            redirectAttributes.addFlashAttribute("ErrormessageSDT", true);
            return "redirect:/manage/nhan-vien/viewAdd";
        }

        nhanVien.setTgThem(new Date());
        nhanVien.setTrangThai(1);
        nhanVienService.save(nhanVien);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/nhan-vien";
    }

    @PostMapping("/nhan-vien/chuc-vu/viewAdd/add")
    public String addChucVu(@Valid @ModelAttribute("chucVuAdd") ChucVu chucVu
            , BindingResult result, RedirectAttributes redirectAttributes) {
        //
        ChucVu existingCV = chucVuRepsitory.findByMaCV(chucVu.getMaCV());
        if (existingCV != null) {
            redirectAttributes.addFlashAttribute("userInput", chucVu);
            redirectAttributes.addFlashAttribute("ErrormessageCV", true);
            return "redirect:/manage/nhan-vien/viewAdd";
        }

        //
        chucVu.setTgThem(new Date());
        chucVu.setTrangThai(1);
        chucVuService.save(chucVu);
        redirectAttributes.addFlashAttribute("messageCV", true);
        return "redirect:/manage/nhan-vien/viewAdd";
    }

    @GetMapping("/nhan-vien/delete/{id}")
    public String deleteNhanVien(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        NhanVien nhanVien = nhanVienService.getByIdNhanVien(id);
        nhanVien.setTrangThai(0);
        nhanVien.setTgSua(new Date());
        nhanVienService.save(nhanVien);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/nhan-vien";

    }

    public void deleteNVById(UUID idNV) {
        NhanVien nhanVien = nhanVienService.getByIdNhanVien(idNV);
        nhanVien.setTrangThai(0);
        nhanVien.setTgSua(new Date());
        nhanVienService.save(nhanVien);
    }

    @GetMapping("/nhan-vien/viewUpdate/{id}")
    public String viewUpdatenhanVien(@PathVariable UUID id, Model model
            , @ModelAttribute("errorNV") String errorNV, @ModelAttribute("userInput") NhanVien userInputNV
            , @ModelAttribute("messageCV") String messageCV
            , @ModelAttribute("errorCV") String errorCV, @ModelAttribute("userInput") ChucVu userInputCV
            , @ModelAttribute("Errormessage") String Errormessage
            , @ModelAttribute("ErrormessageCV") String ErrormessageCV) {
        NhanVien nhanVien = nhanVienService.getByIdNhanVien(id);
        model.addAttribute("nhanVien", nhanVien);
        //
        List<ChucVu> chucVus = chucVuService.getAllChucVu();
        Collections.sort(chucVus, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("chucVu", chucVus);
        //
        model.addAttribute("chucVuAdd", new ChucVu());
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputNV != null) {
            model.addAttribute("nhanVienUpdate", userInputNV);
        }
        session.setAttribute("id", id);
        //
        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }

        return "manage/update-nhan-vien";
    }

    @PostMapping("/nhan-vien/viewUpdate/{id}")
    public String updatenhanVien(@PathVariable UUID id
            , @Valid @ModelAttribute("nhanVien") NhanVien nhanVien
            , RedirectAttributes redirectAttributes) {
        NhanVien nhanViendb = nhanVienService.getByIdNhanVien(id);
        UUID idNV = (UUID) session.getAttribute("id");
        String link = "redirect:/manage/nhan-vien/viewUpdate/" + idNV;
//
        NhanVien existingNV = nhanVienRepsitory.findByMaNV(nhanVien.getMaNV());
        if (existingNV != null && !existingNV.getIdNV().equals(id)) {
            redirectAttributes.addFlashAttribute("userInput", nhanVien);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return link;
        }
        //
        NhanVien existingNV1 = nhanVienRepsitory.findByEmailNV(nhanVien.getEmailNV());
        if (existingNV1 != null  && !existingNV.getIdNV().equals(id) ) {
            redirectAttributes.addFlashAttribute("userInput", nhanVien);
            redirectAttributes.addFlashAttribute("ErrormessageeEmail", true);
            return link;
        }
        //
        NhanVien existingNV2 = nhanVienRepsitory.findByCCCDNV(nhanVien.getCCCDNV());
        if (existingNV2 != null  && !existingNV.getIdNV().equals(id)) {
            redirectAttributes.addFlashAttribute("userInput", nhanVien);
            redirectAttributes.addFlashAttribute("ErrormessageCCCD", true);
            return link;
        }
        NhanVien existingNV3 = nhanVienRepsitory.findBySdtNV(nhanVien.getSdtNV());
        if (existingNV3 != null  && !existingNV.getIdNV().equals(id)) {
            redirectAttributes.addFlashAttribute("userInput", nhanVien);
            redirectAttributes.addFlashAttribute("ErrormessageSDT", true);
            return link;
        }
        if (nhanViendb != null) {
            nhanViendb.setMaNV(nhanVien.getMaNV());
            nhanViendb.setHoTenNV(nhanVien.getHoTenNV());
            nhanViendb.setNgaySinh(nhanVien.getNgaySinh());
            nhanViendb.setSdtNV(nhanVien.getSdtNV());
            nhanViendb.setMatKhau(nhanVien.getMatKhau());
            nhanViendb.setEmailNV(nhanVien.getEmailNV());
            nhanViendb.setGioiTinh(nhanVien.getGioiTinh());
            nhanViendb.setCCCDNV(nhanVien.getCCCDNV());
            nhanViendb.setDiaChi(nhanVien.getDiaChi());
            nhanViendb.setTrangThai(nhanVien.getTrangThai());
            nhanViendb.setChucVu(nhanVien.getChucVu());
            nhanViendb.setTgSua(new Date());
            nhanVienService.save(nhanViendb);
            redirectAttributes.addFlashAttribute("message", true);
        }
        return "redirect:/manage/nhan-vien";
    }

    @GetMapping("/nhanVien/export/pdf")
    public void exportToPDFChatLieu(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=nhanVien_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<NhanVien> listNhanVien = nhanVienService.getAllNhanVien();

        PDFExporterNhanVien exporter = new PDFExporterNhanVien(listNhanVien);
        exporter.export(response);
    }

    @GetMapping("/nhanVien/export/excel")
    public void exportToExcelSize(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=nhanVien_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<NhanVien> listNhanVien = nhanVienService.getAllNhanVien();

        ExcelExporterNhanVien excelExporter = new ExcelExporterNhanVien(listNhanVien);

        excelExporter.export(response);
    }

    @GetMapping("/nhanVien/filter")
    public String filterData(Model model,
                             @RequestParam(value = "maNV", required = false) String maNV,
                             @RequestParam(value = "tenNV", required = false) String tenNV) {
        // Thực hiện lọc dữ liệu dựa trên selectedSize (và trạng thái nếu cần)
        List<NhanVien> filteredNhanViens;
        if ("Mã Nhân Viên".equals(maNV) && "Tên Nhân Viên".equals(tenNV)) {
            // Nếu người dùng chọn "Tất cả", hiển thị tất cả dữ liệu
            filteredNhanViens = nhanVienService.getAllNhanVien();
        } else {
            // Thực hiện lọc dữ liệu dựa trên selectedSize
            filteredNhanViens = nhanVienService.fillterNhanVien(maNV, tenNV);
        }
        model.addAttribute("nhanVien", filteredNhanViens);
        model.addAttribute("nhanVienAll", nhanVienService.getAllNhanVien());

        return "manage/nhan-vien"; // Trả về mẫu HTML chứa bảng dữ liệu sau khi lọc
    }

    @PostMapping("/nhanVien/import")
    public String importData(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                InputStream excelFile = file.getInputStream();
                nhanVienService.importDataFromExcel(excelFile); // Gọi phương thức nhập liệu từ Excel
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi
            }
        }
        return "redirect:/manage/nhan-vien"; // Chuyển hướng sau khi nhập liệu thành công hoặc không thành công
    }

}
