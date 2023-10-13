package com.example.demo.controller;

import com.example.demo.config.ExcelExporterChatLieu;
import com.example.demo.config.ExcelExporterSize;
import com.example.demo.config.PDFExporterChatLieu;
import com.example.demo.config.PDFExporterSizes;
import com.example.demo.model.ChatLieu;
import com.example.demo.model.Size;
import com.example.demo.service.ChatLieuService;
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
public class ChatLieuController {
    @Autowired
    private ChatLieuService chatLieuService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/chat-lieu")
    public String dsChatLieu(Model model) {
        List<ChatLieu> chatLieu = chatLieuService.getAllChatLieu();
        model.addAttribute("chatLieu", chatLieu);
        return "manage/chat-lieu";
    }

    @GetMapping("/chat-lieu/viewAdd")
    public String viewAddChatLieu(Model model) {
        model.addAttribute("chatLieu", new ChatLieu());
        return "manage/add-chat-lieu";
    }

    @PostMapping("/chat-lieu/viewAdd/add")
    public String addChatLieu(@ModelAttribute("chatLieu") ChatLieu chatLieu) {
        ChatLieu chatLieu1 = new ChatLieu();
        chatLieu1.setMaChatLieu(chatLieu.getMaChatLieu());
        chatLieu1.setTenChatLieu(chatLieu.getTenChatLieu());
        chatLieu1.setTgThem(new Date());
        chatLieu1.setTrangThai(chatLieu.getTrangThai());
        chatLieuService.save(chatLieu1);
        return "redirect:/manage/chat-lieu";
    }

    @GetMapping("/chat-lieu/delete/{id}")
    public String deleteChatLieu(@PathVariable UUID id) {
        ChatLieu chatLieu = chatLieuService.getByIdChatLieu(id);
        chatLieu.setTrangThai(0);
        chatLieu.setTgSua(new Date());
        chatLieuService.save(chatLieu);
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
}
