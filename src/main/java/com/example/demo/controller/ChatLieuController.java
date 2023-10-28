package com.example.demo.controller;

import com.example.demo.config.ExcelExporterChatLieu;
import com.example.demo.config.ExcelExporterSize;
import com.example.demo.config.PDFExporterChatLieu;
import com.example.demo.config.PDFExporterSizes;
import com.example.demo.model.ChatLieu;
import com.example.demo.model.Giay;
import com.example.demo.model.NhanVien;
import com.example.demo.model.Size;
import com.example.demo.service.ChatLieuService;
import com.example.demo.service.GiayService;
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
public class ChatLieuController {
    @Autowired
    private ChatLieuService chatLieuService;
    @Autowired
    private GiayService giayService;
    @Autowired
    private GiayController giayController;
    @Autowired
    private HttpSession session;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/chat-lieu")
    public String dsChatLieu(Model model, @ModelAttribute("message") String message
            , @ModelAttribute("maChatLieuError") String maChatLieuError
            , @ModelAttribute("tenChatLieuError") String tenChatLieuError
            , @ModelAttribute("error") String error, @ModelAttribute("userInput") ChatLieu userInput) {

        List<ChatLieu> chatLieu = chatLieuService.getAllChatLieu();
        Collections.sort(chatLieu, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("chatLieu", chatLieu);
        //
        model.addAttribute("chatLieuAdd", new ChatLieu());
        //
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        if (maChatLieuError == null || !"maChatLieuError".equals(error)) {
            model.addAttribute("maChatLieuError", false);
        }
        if (tenChatLieuError == null || !"tenChatLieuError".equals(error)) {
            model.addAttribute("tenChatLieuError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("chatLieuAdd", userInput);
        }
        return "manage/chat-lieu";
    }

//    @GetMapping("/chat-lieu/viewAdd")
//    public String viewAddChatLieu(Model model) {
//        model.addAttribute("chatLieu", new ChatLieu());
//        return "manage/add-chat-lieu";
//    }

    @PostMapping("/chat-lieu/viewAdd/add")
    public String addChatLieu(@Valid @ModelAttribute("chatLieu") ChatLieu chatLieu, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("maChatLieu")) {
                redirectAttributes.addFlashAttribute("userInput", chatLieu);
                redirectAttributes.addFlashAttribute("error", "maChatLieuError");
            }
            if (bindingResult.hasFieldErrors("tenChatLieu")) {
                redirectAttributes.addFlashAttribute("userInput", chatLieu);
                redirectAttributes.addFlashAttribute("error", "tenChatLieuError");
            }
            return "redirect:/manage/chat-lieu";
        }
        ChatLieu chatLieu1 = new ChatLieu();
        chatLieu1.setMaChatLieu(chatLieu.getMaChatLieu());
        chatLieu1.setTenChatLieu(chatLieu.getTenChatLieu());
        chatLieu1.setTgThem(new Date());
        chatLieu1.setTrangThai(1);
        chatLieuService.save(chatLieu1);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/chat-lieu";
    }

    @GetMapping("/chat-lieu/delete/{id}")
    public String deleteChatLieu(@PathVariable UUID id) {
        ChatLieu chatLieu = chatLieuService.getByIdChatLieu(id);
        chatLieu.setTrangThai(0);
        chatLieu.setTgSua(new Date());
        chatLieuService.save(chatLieu);
        // Cập nhật trạng thái của tất cả sản phẩm chi tiết của hãng thành 0
        List<Giay> giays = giayService.findByChatLieu(chatLieu);
        for (Giay giay : giays) {
            giay.setTrangThai(0);
            giayService.save(giay);
            giayController.deleteGiayById(giay.getIdGiay());
        }
        return "redirect:/manage/chat-lieu";
    }

    @GetMapping("/chat-lieu/viewUpdate/{id}")
    public String viewUpdateChatLieu(@PathVariable UUID id, Model model) {
        ChatLieu chatLieu = chatLieuService.getByIdChatLieu(id);
        model.addAttribute("chatLieu", chatLieu);
        return "manage/update-chat-lieu";
    }

    @PostMapping("/chat-lieu/viewUpdate/{id}")
    public String updateChatLieu(@PathVariable UUID id, @ModelAttribute("chatLieu") ChatLieu chatLieu) {
        ChatLieu chatLieuDb = chatLieuService.getByIdChatLieu(id);
        if (chatLieuDb != null) {
            chatLieuDb.setMaChatLieu(chatLieu.getMaChatLieu());
            chatLieuDb.setTenChatLieu(chatLieu.getTenChatLieu());
            chatLieuDb.setTgSua(new Date());
            chatLieuDb.setTrangThai(chatLieu.getTrangThai());
            chatLieuService.save(chatLieuDb);
        }
        // Nếu trạng thái của chatLieu là 1, hãy cập nhật trạng thái của tất cả sản phẩm chi tiết của chatLieu thành 1.
        if (chatLieuDb.getTrangThai() == 1) {
            List<Giay> giays = giayService.findByChatLieu(chatLieuDb);
            for (Giay giay : giays) {
                giay.setTrangThai(1);
                giayService.save(giay);
                giayController.updateGiayById(giay.getIdGiay());
            }
        }
        return "redirect:/manage/chat-lieu";
    }

    @GetMapping("/chatLieu/export/pdf")
    public void exportToPDFChatLieu(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=chatLieu_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<ChatLieu> listChatLieu = chatLieuService.getAllChatLieu();

        PDFExporterChatLieu exporter = new PDFExporterChatLieu(listChatLieu);
        exporter.export(response);
    }

    @GetMapping("/chatLieu/export/excel")
    public void exportToExcelSize(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=chatLieu_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<ChatLieu> listChatLieu = chatLieuService.getAllChatLieu();

        ExcelExporterChatLieu excelExporter = new ExcelExporterChatLieu(listChatLieu);

        excelExporter.export(response);
    }

    @GetMapping("/chatLieu/filter")
    public String filterData(Model model,
                             @RequestParam(value = "maCL", required = false) String maCL,
                             @RequestParam(value = "tenCL", required = false) String tenCL) {
        // Thực hiện lọc dữ liệu dựa trên selectedSize (và trạng thái nếu cần)
        List<ChatLieu> filteredChatLieus;
        if ("Mã Chất Liệu".equals(maCL) && "Tên Chất Liệu".equals(tenCL)) {
            // Nếu người dùng chọn "Tất cả", hiển thị tất cả dữ liệu
            filteredChatLieus = chatLieuService.getAllChatLieu();
        } else {
            // Thực hiện lọc dữ liệu dựa trên selectedSize
            filteredChatLieus = chatLieuService.fillterChatLieu(maCL, tenCL);
        }
        model.addAttribute("chatLieu", filteredChatLieus);
        model.addAttribute("chatLieuAll", chatLieuService.getAllChatLieu());

        return "manage/chat-lieu"; // Trả về mẫu HTML chứa bảng dữ liệu sau khi lọc
    }

    @PostMapping("/chatLieu/import")
    public String importData(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                InputStream excelFile = file.getInputStream();
                chatLieuService.importDataFromExcel(excelFile); // Gọi phương thức nhập liệu từ Excel
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi
            }
        }
        return "redirect:/manage/chat-lieu"; // Chuyển hướng sau khi nhập liệu thành công hoặc không thành công
    }
}
