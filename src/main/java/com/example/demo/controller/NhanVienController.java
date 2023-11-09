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
        return dsGioiTinh;
    }

    @GetMapping("/nhan-vien")
    public String dsNhanVien(Model model,@ModelAttribute("message") String message) {
        List<NhanVien> nhanViens = nhanVienService.getAllNhanVien();
        List<ChucVu> chucVus = chucVuService.getAllChucVu();
        // Kiểm tra và cập nhật trạng thái của giày nếu trạng thái của hãng hoặc chất liệu không hoạt động (0)
        for (NhanVien nhanVienItem : nhanViens) {
            if (nhanVienItem.getChucVu().getTrangThai() == 0) {
                nhanVienItem.setTrangThai(0);
                nhanVienService.save(nhanVienItem);
            }
        }
        model.addAttribute("nhanVien", nhanViens);
        model.addAttribute("chucVu", chucVus);
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        return "manage/nhan-vien";
    }

    @GetMapping("/nhan-vien/viewAdd")
    public String viewAddNhanVien(Model model) {
        List<ChucVu> chucVuList = chucVuService.getAllChucVu();
        Collections.sort(chucVuList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("chucVu", chucVuList);

        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("chucVuAdd", new ChucVu());
        return "manage/add-nhan-vien";
    }

    @PostMapping("/nhan-vien/viewAdd/add")
    public String addNhanVien(@Valid @ModelAttribute("nhanVien") NhanVien nhanVien,Model model, BindingResult result) {
       if(result.hasErrors()){
           List<ChucVu> chucVuList = chucVuService.getAllChucVu();
           Collections.sort(chucVuList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
           model.addAttribute("chucVu", chucVuList);

           model.addAttribute("nhanVien", new NhanVien());
           model.addAttribute("chucVuAdd", new ChucVu());
           return "manage/add-nhan-vien";
       }
        nhanVien.setTgThem(new Date());
        nhanVienService.save(nhanVien);
        return "redirect:/manage/nhan-vien";
    }
    @PostMapping("/nhan-vien/chuc-vu/viewAdd/add")
    public String addChucVu(@ModelAttribute("chucVuAdd") ChucVu chucVu) {
        chucVu.setTgThem(new Date());
        chucVuService.save(chucVu);
        return "redirect:/manage/nhan-vien/viewAdd";
    }

    @GetMapping("/nhan-vien/delete/{id}")
    public String deleteNhanVien(@PathVariable UUID id) {
        NhanVien nhanVien = nhanVienService.getByIdNhanVien(id);
        nhanVien.setTrangThai(0);
        nhanVien.setTgSua(new Date());
        nhanVienService.save(nhanVien);
        return "redirect:/manage/nhan-vien";
    }
    public void deleteNVById(UUID idNV) {
        NhanVien nhanVien = nhanVienService.getByIdNhanVien(idNV);
        nhanVien.setTrangThai(0);
        nhanVien.setTgSua(new Date());
        nhanVienService.save(nhanVien);
    }

    @GetMapping("/nhan-vien/viewUpdate/{id}")
    public String viewUpdatenhanVien(@PathVariable UUID id, Model model) {
        NhanVien nhanVien = nhanVienService.getByIdNhanVien(id);
        List<ChucVu> chucVus = chucVuService.getAllChucVu();
        model.addAttribute("nhanVien", nhanVien);
        model.addAttribute("chucVu", chucVus);
        return "manage/update-nhan-vien";
    }
    @PostMapping("/nhan-vien/viewUpdate/{id}")
    public String updatenhanVien(@PathVariable UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        NhanVien nhanViendb = nhanVienService.getByIdNhanVien(id);
        if (nhanViendb != null) {
            nhanViendb.setMaNV(nhanVien.getMaNV());
            nhanViendb.setHoTenNV(nhanVien.getHoTenNV());
            nhanViendb.setAnhNV(nhanVien.getAnhNV());
            nhanViendb.setCCCDNV(nhanVien.getCCCDNV());
            nhanViendb.setDiaChi(nhanVien.getDiaChi());
            nhanViendb.setEmailNV(nhanVien.getEmailNV());
            nhanViendb.setMatKhau(nhanVien.getMatKhau());
            nhanViendb.setSdtNV(nhanVien.getSdtNV());
            nhanViendb.setGioiTinh(nhanVien.getGioiTinh());
            nhanViendb.setNgaySinh(nhanVien.getNgaySinh());
            nhanViendb.setTgSua(new Date());
            nhanViendb.setTrangThai(nhanVien.getTrangThai());
            nhanViendb.setChucVu(nhanVien.getChucVu());
            nhanVienService.save(nhanViendb);
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
