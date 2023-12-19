package com.example.demo.controller;

import com.example.demo.config.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.service.impls.GiayChiTietServiceImpl;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.util.StringUtils;

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
public class GiayChiTietController {
    @Autowired
    private GiayChiTietService giayChiTietService;
    @Autowired
    private GiayService giayService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private HinhAnhService hinhAnhService;
    @Autowired
    private CreateBarCode createBarCode;
    @Autowired
    private HangService hangService;
    @Autowired
    private ChatLieuService chatLieuService;
    @Autowired
    private HttpSession session;
    @Autowired
    private GiayRepository giayRepository;
    @Autowired
    private MauSacRepository mauSacRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private HangRepository hangRepository;
    @Autowired
    private GiayChiTietServiceImpl giayChiTietServiceImpl;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @GetMapping("/giay-chi-tiet")
    public String dsGiayChiTiet(Model model, @ModelAttribute("message") String message, @ModelAttribute("importError") String importError) {
        List<ChiTietGiay> items = giayChiTietService.getAllChiTietGiay();

        List<ChiTietGiay> chiTietGiayList = new ArrayList<>();

        for (ChiTietGiay x : items) {
            if (x.getIdCTGOld() == null) {
                chiTietGiayList.add(x);
            }
        }

        List<Giay> giayList = giayService.getAllGiay();
        List<Size> sizeList = sizeService.getAllSize();
        List<MauSac> mauSacList = mauSacService.getALlMauSac();
        model.addAttribute("items", chiTietGiayList);
        model.addAttribute("giayList", giayList);
        model.addAttribute("sizeList", sizeList);
        model.addAttribute("mauSacList", mauSacList);
        //
        if (message == null || !"true".equals(message)) {
            model.addAttribute("message", false);
        }
        if (importError == null || !"true".equals(importError)) {
            model.addAttribute("importError", false);
        }
        return "manage/giay-chi-tiet";
    }

    @GetMapping("/giay-chi-tiet/detail/{id}")
    public String detailGiayChiTiet(@PathVariable UUID id, Model model) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        model.addAttribute("giayChiTietDetail", chiTietGiay);
        return "manage/giay-chi-tiet-detail";
    }

    @GetMapping("/chi-tiet-giay/detail/{id}")
    public String detailChiTietGiay(@PathVariable UUID id, Model model) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        model.addAttribute("giayChiTietDetail", chiTietGiay);
        return "manage/giay-chi-tiet-detail";
    }

    @GetMapping("/giay-chi-tiet/viewAdd")
    public String viewAddGiayChiTiet(Model model
            , @ModelAttribute("namSXError") String namSXError
            , @ModelAttribute("namBHError") String namBHError
            , @ModelAttribute("trongLuongError") String trongLuongError
            , @ModelAttribute("giaBanError") String giaBanError
            , @ModelAttribute("soLuongError") String soLuongError
            , @ModelAttribute("mauSacError") String mauSacError
            , @ModelAttribute("hinhAnhError") String hinhAnhError
            , @ModelAttribute("giayError") String giayError
            , @ModelAttribute("sizeError") String sizeError
            , @ModelAttribute("daTonTai") String daTonTai
            , @ModelAttribute("error") String error
            , @ModelAttribute("userInput") ChiTietGiay userInput

            , @ModelAttribute("messageSize") String messageSize
            , @ModelAttribute("maSizeError") String maSizeError
            , @ModelAttribute("soSizeError") String soSizeError
            , @ModelAttribute("userInputSize") Size userInputSize

            , @ModelAttribute("messageMau") String messageMau
            , @ModelAttribute("maError") String maError
            , @ModelAttribute("maMauError") String maMauError
            , @ModelAttribute("tenMauError") String tenMauError
            , @ModelAttribute("userInputMau") MauSac userInputMau

            , @ModelAttribute("messageGiay") String messageGiay
            , @ModelAttribute("maGiayError") String maGiayError
            , @ModelAttribute("tenGiayError") String tenGiayError
            , @ModelAttribute("hangError") String hangError
            , @ModelAttribute("chatLieuError") String chatLieuError
            , @ModelAttribute("errorGiay") String errorGiay
            , @ModelAttribute("userInputGiay") Giay userInputGiay

            , @ModelAttribute("messageAnh") String messageAnh
            , @ModelAttribute("maHinhAnhError") String maHinhAnhError
            , @ModelAttribute("userInputAnh") HinhAnh userInputAnh

            , @ModelAttribute("messageHang") String messageHang
            , @ModelAttribute("maHangError") String maHangError
            , @ModelAttribute("tenHangError") String tenHangError
            , @ModelAttribute("errorHang") String errorHang
            , @ModelAttribute("userInput") Hang userInputHang

            , @ModelAttribute("messageChatLieu") String messageChatLieu
            , @ModelAttribute("maChatLieuError") String maChatLieuError
            , @ModelAttribute("tenChatLieuError") String tenChatLieuError
            , @ModelAttribute("errorChatLieu") String errorChatLieu
            , @ModelAttribute("userInput") ChatLieu userInputChatLieu

            , @ModelAttribute("ErrormessageGiay") String ErrormessageGiay
            , @ModelAttribute("ErrormessageMau") String ErrormessageMau
            , @ModelAttribute("ErrormessageSize") String ErrormessageSize
            , @ModelAttribute("ErrormessageAnh") String ErrormessageAnh) {
        List<HinhAnh> hinhAnhList = hinhAnhService.getAllHinhAnh();
        Collections.sort(hinhAnhList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("hinhAnh", hinhAnhList);
        //
        List<Giay> giayList = giayService.getAllGiay();
        Collections.sort(giayList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("giay", giayList);
        //
        List<MauSac> mauSacList = mauSacService.getALlMauSac();
        Collections.sort(mauSacList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("mauSac", mauSacList);
        //
        List<Size> sizeList = sizeService.getAllSize();
        Collections.sort(sizeList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("size", sizeList);
        //
        List<Hang> hangList = hangService.getALlHang();
        Collections.sort(hangList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("hang", hangList);
        //
        List<ChatLieu> chatLieuList = chatLieuService.getAllChatLieu();
        Collections.sort(chatLieuList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("chatLieu", chatLieuList);
        //
        model.addAttribute("giayChiTiet", new ChiTietGiay());
        model.addAttribute("giayAdd", new Giay());
        model.addAttribute("mauSacAdd", new MauSac());
        model.addAttribute("sizeAdd", new Size());
        model.addAttribute("hinhAnhAdd", new HinhAnh());
        model.addAttribute("hangAdd", new Hang());
        model.addAttribute("chatLieuAdd", new ChatLieu());
        //
        if (namSXError == null || !"namSXError".equals(error)) {
            model.addAttribute("namSXError", false);
        }
        if (namBHError == null || !"namBHError".equals(error)) {
            model.addAttribute("namBHError", false);
        }
        if (trongLuongError == null || !"trongLuongError".equals(error)) {
            model.addAttribute("trongLuongError", false);
        }
        if (giaBanError == null || !"giaBanError".equals(error)) {
            model.addAttribute("giaBanError", false);
        }
        if (soLuongError == null || !"soLuongError".equals(error)) {
            model.addAttribute("soLuongError", false);
        }
        if (mauSacError == null || !"mauSacError".equals(error)) {
            model.addAttribute("mauSacError", false);
        }
        if (hinhAnhError == null || !"hinhAnhError".equals(error)) {
            model.addAttribute("hinhAnhError", false);
        }
        if (giayError == null || !"giayError".equals(error)) {
            model.addAttribute("giayError", false);
        }
        if (sizeError == null || !"sizeError".equals(error)) {
            model.addAttribute("sizeError", false);
        }
        if (daTonTai == null || !"daTonTai".equals(error)) {
            model.addAttribute("daTonTai", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("giayChiTiet", userInput);
        }
        //Size
        if (messageSize == null || !"true".equals(messageSize)) {
            model.addAttribute("messageSize", false);
        }
        if (maSizeError == null || !"maSizeError".equals(error)) {
            model.addAttribute("maSizeError", false);
        }
        if (soSizeError == null || !"soSizeError".equals(error)) {
            model.addAttribute("soSizeError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputSize != null) {
            model.addAttribute("sizeAdd", userInputSize);
        }
        //Màu sắc
        if (messageMau == null || !"true".equals(messageMau)) {
            model.addAttribute("messageMau", false);
        }
        if (maError == null || !"maError".equals(error)) {
            model.addAttribute("maError", false);
        }
        if (maMauError == null || !"maMauError".equals(error)) {
            model.addAttribute("maMauError", false);
        }
        if (tenMauError == null || !"tenMauError".equals(error)) {
            model.addAttribute("tenMauError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputMau != null) {
            model.addAttribute("mauSacAdd", userInputMau);
        }
        //Giay
        if (messageGiay == null || !"true".equals(messageGiay)) {
            model.addAttribute("messageGiay", false);
        }
        if (maGiayError == null || !"maGiayError".equals(error)) {
            model.addAttribute("maGiayError", false);
        }
        if (tenGiayError == null || !"tenGiayError".equals(error)) {
            model.addAttribute("tenGiayError", false);
        }
        if (hangError == null || !"hangError".equals(error)) {
            model.addAttribute("hangError", false);
        }
        if (chatLieuError == null || !"chatLieuError".equals(error)) {
            model.addAttribute("chatLieuError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputGiay != null) {
            model.addAttribute("giayAdd", userInputGiay);
        }
        //Anh
        if (messageAnh == null || !"true".equals(messageAnh)) {
            model.addAttribute("messageAnh", false);
        }
        if (maHinhAnhError == null || !"maHinhAnhError".equals(error)) {
            model.addAttribute("maHinhAnhError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputAnh != null) {
            model.addAttribute("hinhAnhAdd", userInputAnh);
        }
        //add Hang
        if (messageHang == null || !"true".equals(messageHang)) {
            model.addAttribute("messageHang", false);
        }
        if (maHangError == null || !"maHangError".equals(errorHang)) {
            model.addAttribute("maHangError", false);
        }
        if (tenHangError == null || !"tenHangError".equals(errorHang)) {
            model.addAttribute("tenHangError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputHang != null) {
            model.addAttribute("hangAdd", userInputHang);
        }
        //add chat-lieu
        if (messageChatLieu == null || !"true".equals(messageChatLieu)) {
            model.addAttribute("messageChatLieu", false);
        }
        if (maChatLieuError == null || !"maChatLieuError".equals(errorChatLieu)) {
            model.addAttribute("maChatLieuError", false);
        }
        if (tenChatLieuError == null || !"tenChatLieuError".equals(errorChatLieu)) {
            model.addAttribute("tenChatLieuError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputChatLieu != null) {
            model.addAttribute("chatLieuAdd", userInputChatLieu);
        }
        //check ma
        if (ErrormessageGiay == null || !"true".equals(ErrormessageGiay)) {
            model.addAttribute("ErrormessageGiay", false);
        }
        if (ErrormessageMau == null || !"true".equals(ErrormessageMau)) {
            model.addAttribute("ErrormessageMau", false);
        }
        if (ErrormessageSize == null || !"true".equals(ErrormessageSize)) {
            model.addAttribute("ErrormessageSize", false);
        }
        if (ErrormessageAnh == null || !"true".equals(ErrormessageAnh)) {
            model.addAttribute("ErrormessageAnh", false);
        }
        return "manage/add-giay-chi-tiet";
    }

    @GetMapping("/chi-tiet-giay/viewAdd/{id}")
    public String viewAddChiTietGiay(@PathVariable UUID id, Model model
            , @ModelAttribute("namSXError") String namSXError
            , @ModelAttribute("namBHError") String namBHError
            , @ModelAttribute("trongLuongError") String trongLuongError
            , @ModelAttribute("giaBanError") String giaBanError
            , @ModelAttribute("soLuongError") String soLuongError
            , @ModelAttribute("mauSacError") String mauSacError
            , @ModelAttribute("hinhAnhError") String hinhAnhError
            , @ModelAttribute("giayError") String giayError
            , @ModelAttribute("sizeError") String sizeError
            , @ModelAttribute("daTonTai") String daTonTai
            , @ModelAttribute("error") String error
            , @ModelAttribute("userInput") ChiTietGiay userInput

            , @ModelAttribute("messageSize") String messageSize
            , @ModelAttribute("maSizeError") String maSizeError
            , @ModelAttribute("soSizeError") String soSizeError
            , @ModelAttribute("userInputSize") Size userInputSize

            , @ModelAttribute("messageMau") String messageMau
            , @ModelAttribute("maError") String maError
            , @ModelAttribute("maMauError") String maMauError
            , @ModelAttribute("tenMauError") String tenMauError
            , @ModelAttribute("userInputMau") MauSac userInputMau

            , @ModelAttribute("messageAnh") String messageAnh
            , @ModelAttribute("maHinhAnhError") String maHinhAnhError
            , @ModelAttribute("userInputAnh") HinhAnh userInputAnh

            , @ModelAttribute("ErrormessageMau") String ErrormessageMau
            , @ModelAttribute("ErrormessageSize") String ErrormessageSize
            , @ModelAttribute("ErrormessageAnh") String ErrormessageAnh) {
        List<HinhAnh> hinhAnhList = hinhAnhService.getAllHinhAnh();
        Collections.sort(hinhAnhList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("hinhAnh", hinhAnhList);
        //
        List<MauSac> mauSacList = mauSacService.getALlMauSac();
        Collections.sort(mauSacList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("mauSac", mauSacList);
        //
        List<Size> sizeList = sizeService.getAllSize();
        Collections.sort(sizeList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("size", sizeList);
        //
        session.removeAttribute("idViewAddCTG");
        session.setAttribute("idViewAddCTG", id);
        //
        Giay giay = giayService.getByIdGiay(id);
        model.addAttribute("chiTietGiay", new ChiTietGiay());
        model.addAttribute("giay", giay);
        model.addAttribute("giayAdd", new Giay());
        model.addAttribute("mauSacAdd", new MauSac());
        model.addAttribute("sizeAdd", new Size());
        model.addAttribute("hinhAnhAdd", new HinhAnh());
        model.addAttribute("hangAdd", new Hang());
        model.addAttribute("chatLieuAdd", new ChatLieu());
        model.addAttribute("hang", hangService.getALlHang());
        model.addAttribute("chatLieu", chatLieuService.getAllChatLieu());
        //
        if (namSXError == null || !"namSXError".equals(error)) {
            model.addAttribute("namSXError", false);
        }
        if (namBHError == null || !"namBHError".equals(error)) {
            model.addAttribute("namBHError", false);
        }
        if (trongLuongError == null || !"trongLuongError".equals(error)) {
            model.addAttribute("trongLuongError", false);
        }
        if (giaBanError == null || !"giaBanError".equals(error)) {
            model.addAttribute("giaBanError", false);
        }
        if (soLuongError == null || !"soLuongError".equals(error)) {
            model.addAttribute("soLuongError", false);
        }
        if (mauSacError == null || !"mauSacError".equals(error)) {
            model.addAttribute("mauSacError", false);
        }
        if (hinhAnhError == null || !"hinhAnhError".equals(error)) {
            model.addAttribute("hinhAnhError", false);
        }
        if (giayError == null || !"giayError".equals(error)) {
            model.addAttribute("giayError", false);
        }
        if (sizeError == null || !"sizeError".equals(error)) {
            model.addAttribute("sizeError", false);
        }
        if (daTonTai == null || !"daTonTai".equals(error)) {
            model.addAttribute("daTonTai", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("chiTietGiay", userInput);
        }
        //Size
        if (messageSize == null || !"true".equals(messageSize)) {
            model.addAttribute("messageSize", false);
        }
        if (maSizeError == null || !"maSizeError".equals(error)) {
            model.addAttribute("maSizeError", false);
        }
        if (soSizeError == null || !"soSizeError".equals(error)) {
            model.addAttribute("soSizeError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputSize != null) {
            model.addAttribute("sizeAdd", userInputSize);
        }
        //Màu sắc
        if (messageMau == null || !"true".equals(messageMau)) {
            model.addAttribute("messageMau", false);
        }
        if (maError == null || !"maError".equals(error)) {
            model.addAttribute("maError", false);
        }
        if (maMauError == null || !"maMauError".equals(error)) {
            model.addAttribute("maMauError", false);
        }
        if (tenMauError == null || !"tenMauError".equals(error)) {
            model.addAttribute("tenMauError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputMau != null) {
            model.addAttribute("mauSacAdd", userInputMau);
        }
        //Anh
        if (messageAnh == null || !"true".equals(messageAnh)) {
            model.addAttribute("messageAnh", false);
        }
        if (maHinhAnhError == null || !"maHinhAnhError".equals(error)) {
            model.addAttribute("maHinhAnhError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInputAnh != null) {
            model.addAttribute("hinhAnhAdd", userInputAnh);
        }
        //check ma
        if (ErrormessageMau == null || !"true".equals(ErrormessageMau)) {
            model.addAttribute("ErrormessageMau", false);
        }
        if (ErrormessageSize == null || !"true".equals(ErrormessageSize)) {
            model.addAttribute("ErrormessageSize", false);
        }
        if (ErrormessageAnh == null || !"true".equals(ErrormessageAnh)) {
            model.addAttribute("ErrormessageAnh", false);
        }
        return "manage/add-chi-tiet-giay";
    }


    @PostMapping("/giay-chi-tiet/viewAdd/add")
    public String addGiayChiTiet(@Valid @ModelAttribute("giayChiTiet") ChiTietGiay chiTietGiay,
                                 @RequestParam("listSize") List<UUID> listSize,
                                 BindingResult bindingResult, Model model,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<HinhAnh> hinhAnhList = hinhAnhService.getAllHinhAnh();
            Collections.sort(hinhAnhList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
            model.addAttribute("hinhAnh", hinhAnhList);
            //
            List<Giay> giayList = giayService.getAllGiay();
            Collections.sort(giayList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
            model.addAttribute("giay", giayList);
            //
            List<MauSac> mauSacList = mauSacService.getALlMauSac();
            Collections.sort(mauSacList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
            model.addAttribute("mauSac", mauSacList);
            //
            List<Size> sizeList = sizeService.getAllSize();
            Collections.sort(sizeList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
            model.addAttribute("size", sizeList);
            //
            model.addAttribute("giayChiTiet", new ChiTietGiay());
            model.addAttribute("giayAdd", new Giay());
            model.addAttribute("mauSacAdd", new MauSac());
            model.addAttribute("sizeAdd", new Size());
            model.addAttribute("hinhAnhAdd", new HinhAnh());
            model.addAttribute("hangAdd", new Hang());
            model.addAttribute("chatLieuAdd", new ChatLieu());
            model.addAttribute("hang", hangService.getALlHang());
            model.addAttribute("chatLieu", chatLieuService.getAllChatLieu());
            //
            if (bindingResult.hasFieldErrors("namSX")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "namSXError");
            }
            if (bindingResult.hasFieldErrors("namBH")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "namBHError");
            }
            if (bindingResult.hasFieldErrors("trongLuong")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "trongLuongError");
            }
            if (bindingResult.hasFieldErrors("giaBan")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "giaBanError");
            }
            if (bindingResult.hasFieldErrors("soLuong")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "soLuongError");
            }
            if (bindingResult.hasFieldErrors("mauSac")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "mauSacError");
            }
            if (bindingResult.hasFieldErrors("hinhAnh")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "hinhAnhError");
            }
            if (bindingResult.hasFieldErrors("giay")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "giayError");
            }
            if (bindingResult.hasFieldErrors("size")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "sizeError");
            }
            return "redirect:/manage/giay-chi-tiet/viewAdd";
        }

        for (UUID x : listSize) {
            //
            List<ChiTietGiay> isDuplicate = giayChiTietService.isDuplicateChiTietGiay(
                    chiTietGiay.getGiay().getIdGiay(),
                    x,
                    chiTietGiay.getMauSac().getIdMau(),
                    chiTietGiay.getHinhAnh().getIdHinhAnh()
            );
            if (!isDuplicate.isEmpty()) {
                for (ChiTietGiay duplicateChiTietGiay : isDuplicate) {
                    System.out.println("ChiTietGiay đã tồn tại với ID: " + duplicateChiTietGiay.getIdCTG());
                    redirectAttributes.addFlashAttribute("userInput", duplicateChiTietGiay.getIdCTG());
                }
                // Xử lý sự trùng lặp, ví dụ: hiển thị thông báo và không thêm mới
                redirectAttributes.addFlashAttribute("error", "daTonTai");
                redirectAttributes.addFlashAttribute("updateQuantity", true);
                return "redirect:/manage/giay-chi-tiet/viewAdd";
            }
            //
            List<ChiTietGiay> list = giayChiTietService.getAllChiTietGiay();
            Date date = new Date();
            String maCtg = "CTG00" + date.getDate();
            int index = list.size();
            //
            ChiTietGiay chiTietGiay1 = new ChiTietGiay();
            chiTietGiay1.setMaCTG(maCtg + index);
            chiTietGiay1.setGiay(chiTietGiay.getGiay());
            chiTietGiay1.setNamSX(chiTietGiay.getNamSX());
            chiTietGiay1.setNamBH(chiTietGiay.getNamBH());
            chiTietGiay1.setTrongLuong(chiTietGiay.getTrongLuong());
            chiTietGiay1.setGiaBan(chiTietGiay.getGiaBan());
            chiTietGiay1.setSoTienTruocKhiGiam(chiTietGiay.getGiaBan());
            chiTietGiay1.setSoLuong(chiTietGiay.getSoLuong());
            chiTietGiay1.setTrangThai(1);
            chiTietGiay1.setMauSac(chiTietGiay.getMauSac());
            chiTietGiay1.setHinhAnh(chiTietGiay.getHinhAnh());
            chiTietGiay1.setSize(sizeService.getByIdSize(x));
            chiTietGiay1.setTgThem(new Date());
            giayChiTietService.save(chiTietGiay1);
            // Lấy id đã được tạo sau khi thêm sản phẩm mới
            UUID idNew = chiTietGiay1.getIdCTG();
            String barcodeNew = idNew.toString();
            chiTietGiay1.setBarcode(barcodeNew);
            // Cập nhật thông tin sản phẩm giày
            ZxingHelperBarCode.saveBarcodeImage(idNew, 200, 100); // Tạo và lưu mã vạch
            giayChiTietService.update(chiTietGiay1);
        }
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/giay-chi-tiet";

    }

    @PostMapping("/chi-tiet-giay/viewAdd/add")
    public String addChiTietGiay(@Valid @ModelAttribute("chiTietGiay") ChiTietGiay chiTietGiay, BindingResult bindingResult,
                                 @RequestParam("listSize") List<UUID> listSize,
                                 RedirectAttributes redirectAttributes) {
        //
        UUID idCTGViewAdd = (UUID) session.getAttribute("idViewAddCTG");
        String link1 = "redirect:/manage/chi-tiet-giay/viewAdd/" + idCTGViewAdd;
        //
        UUID idGiay = chiTietGiay.getGiay().getIdGiay();
        //
        Giay giay = giayService.getByIdGiay(idGiay);
        //
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("namSX")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "namSXError");
            }
            if (bindingResult.hasFieldErrors("namBH")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "namBHError");
            }
            if (bindingResult.hasFieldErrors("trongLuong")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "trongLuongError");
            }
            if (bindingResult.hasFieldErrors("giaBan")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "giaBanError");
            }
            if (bindingResult.hasFieldErrors("soLuong")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "soLuongError");
            }
            if (bindingResult.hasFieldErrors("mauSac")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "mauSacError");
            }
            if (bindingResult.hasFieldErrors("hinhAnh")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "hinhAnhError");
            }
            if (bindingResult.hasFieldErrors("giay")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "giayError");
            }
            if (bindingResult.hasFieldErrors("size")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "sizeError");
            }
            return link1;
        }
        ///
        for (UUID x : listSize) {
            //
            List<ChiTietGiay> isDuplicate = giayChiTietService.isDuplicateChiTietGiay(
                    chiTietGiay.getGiay().getIdGiay(),
                    x,
                    chiTietGiay.getMauSac().getIdMau(),
                    chiTietGiay.getHinhAnh().getIdHinhAnh()
            );
            if (!isDuplicate.isEmpty()) {
                for (ChiTietGiay duplicateChiTietGiay : isDuplicate) {
                    System.out.println("ChiTietGiay đã tồn tại với ID: " + duplicateChiTietGiay.getIdCTG());
                    redirectAttributes.addFlashAttribute("userInput", duplicateChiTietGiay.getIdCTG());
                }
                // Xử lý sự trùng lặp, ví dụ: hiển thị thông báo và không thêm mới
                redirectAttributes.addFlashAttribute("error", "daTonTai");
                redirectAttributes.addFlashAttribute("updateQuantity", true);
                return link1;
            }
            //
            List<ChiTietGiay> list = giayChiTietService.getAllChiTietGiay();
            Date date = new Date();
            String maCtg = "CTG00" + date.getDate();
            int index = list.size();
            //
            ChiTietGiay chiTietGiay2 = new ChiTietGiay();
            chiTietGiay2.setGiay(giay);
            chiTietGiay2.setMaCTG(maCtg + index);
            chiTietGiay2.setNamSX(chiTietGiay.getNamSX());
            chiTietGiay2.setNamBH(chiTietGiay.getNamBH());
            chiTietGiay2.setTrongLuong(chiTietGiay.getTrongLuong());
            chiTietGiay2.setGiaBan(chiTietGiay.getGiaBan());
            chiTietGiay2.setSoTienTruocKhiGiam(chiTietGiay.getGiaBan());
            chiTietGiay2.setSoLuong(chiTietGiay.getSoLuong());
            chiTietGiay2.setTrangThai(1);
            chiTietGiay2.setMauSac(chiTietGiay.getMauSac());
            chiTietGiay2.setHinhAnh(chiTietGiay.getHinhAnh());
            chiTietGiay2.setSize(sizeService.getByIdSize(x));
            chiTietGiay2.setTgThem(new Date());
            giayChiTietService.save(chiTietGiay2);
            // Lấy id đã được tạo sau khi thêm sản phẩm mới
            UUID idNew = chiTietGiay2.getIdCTG();
            String barcodeNew = idNew.toString();
            chiTietGiay2.setBarcode(barcodeNew);
            // Cập nhật thông tin sản phẩm giày
            ZxingHelperBarCode.saveBarcodeImage(idNew, 200, 100); // Tạo và lưu mã vạch
            giayChiTietService.update(chiTietGiay2);
        }
        //
        redirectAttributes.addFlashAttribute("message", true);
        //
        String link2 = "redirect:/manage/giay/detail/" + idCTGViewAdd;
        return link2;
    }

    @RequestMapping("/giayCT/detail/{id}")
    public String detailCTG(@PathVariable UUID id, Model model) {
        Giay giay = giayService.getByIdGiay(id);
        List<ChiTietGiay> listCTGByGiay = giayChiTietService.getCTGByGiay(giay);
        model.addAttribute("chiTietGiayList", listCTGByGiay);
        return "manage/giay-detail";
    }

    @PostMapping("/chi-tiet-giay/giay/viewAdd/add")
    public String addGiay(@ModelAttribute("giayAdd") Giay giay, Model model) {
        Giay giay1 = new Giay();
        giay1.setMaGiay(giay.getMaGiay());
        giay1.setMoTa(giay.getMoTa());
        giay1.setTenGiay(giay.getTenGiay());
        giay1.setTgThem(new Date());
        giay1.setHang(giay.getHang());
        giay1.setChatLieu(giay.getChatLieu());
        giay1.setTrangThai(1);
        giayService.save(giay1);
        return "manage/message";
    }

    @PostMapping("/giay-chi-tiet/giay/viewAdd/add")
    public String addGiayCT(@Valid @ModelAttribute("giayAdd") Giay giay, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("maGiay")) {
                redirectAttributes.addFlashAttribute("userInputGiay", giay);
                redirectAttributes.addFlashAttribute("error", "maGiayError");
            }
            if (bindingResult.hasFieldErrors("tenGiay")) {
                redirectAttributes.addFlashAttribute("userInputGiay", giay);
                redirectAttributes.addFlashAttribute("error", "tenGiayError");
            }
            if (bindingResult.hasFieldErrors("hang")) {
                redirectAttributes.addFlashAttribute("userInputGiay", giay);
                redirectAttributes.addFlashAttribute("error", "hangError");
            }
            if (bindingResult.hasFieldErrors("chatLieu")) {
                redirectAttributes.addFlashAttribute("userInputGiay", giay);
                redirectAttributes.addFlashAttribute("error", "chatLieuError");
            }
            return "redirect:/manage/giay-chi-tiet/viewAdd";
        }
        //
        Giay existingGiay = giayRepository.findByMaGiay(giay.getMaGiay());
        if (existingGiay != null) {
            redirectAttributes.addFlashAttribute("userInputGiay", giay);
            redirectAttributes.addFlashAttribute("ErrormessageGiay", true);
            return "redirect:/manage/giay-chi-tiet/viewAdd";
        }
        //
        Giay giay1 = new Giay();
        giay1.setMaGiay(giay.getMaGiay());
        giay1.setMoTa(giay.getMoTa());
        giay1.setTenGiay(giay.getTenGiay());
        giay1.setTgThem(new Date());
        giay1.setHang(giay.getHang());
        giay1.setChatLieu(giay.getChatLieu());
        giay1.setTrangThai(1);
        giayService.save(giay1);
        redirectAttributes.addFlashAttribute("messageGiay", true);
        return "redirect:/manage/giay-chi-tiet/viewAdd";
    }

    @PostMapping("/chi-tiet-giay/giay/hang/viewAdd/add")
    public String addHang(@ModelAttribute("hangAdd") Hang hang) {
        Hang hang1 = new Hang();
        hang1.setLogoHang(hang.getLogoHang());
        hang1.setMaHang(hang.getMaHang());
        hang1.setTenHang(hang.getTenHang());
        hang1.setTgThem(new Date());
        hang1.setTrangThai(1);
        hangService.save(hang1);
        return "manage/message";
    }

    @PostMapping("/giay-chi-tiet/giay/hang/viewAdd/add")
    public String addHangCT(@RequestParam("maHang") String maHang,
                            @RequestParam("tenHang") String tenHang,
                            @RequestParam("logoHang") MultipartFile logoHang,
                            @Valid @ModelAttribute("hangAdd") Hang hang, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Hang existingHang = hangRepository.findByMaHang(hang.getMaHang());
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
        redirectAttributes.addFlashAttribute("messageHang", true);
        return "redirect:/manage/giay-chi-tiet/viewAdd";
    }

    @PostMapping("/chi-tiet-giay/giay/chat-lieu/viewAdd/add")
    public String addChatLieu(@ModelAttribute("chatLieuAdd") ChatLieu chatLieu) {
        ChatLieu chatLieu1 = new ChatLieu();
        chatLieu1.setMaChatLieu(chatLieu.getMaChatLieu());
        chatLieu1.setTenChatLieu(chatLieu.getTenChatLieu());
        chatLieu1.setTgThem(new Date());
        chatLieu1.setTrangThai(1);
        chatLieuService.save(chatLieu1);
        return "manage/message";
    }

    @PostMapping("/giay-chi-tiet/giay/chat-lieu/viewAdd/add")
    public String addChatLieuCT(@Valid @ModelAttribute("chatLieuAdd") ChatLieu chatLieu, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("maChatLieu")) {
                redirectAttributes.addFlashAttribute("userInput", chatLieu);
                redirectAttributes.addFlashAttribute("errorChatLieu", "maChatLieuError");
            }
            if (bindingResult.hasFieldErrors("tenChatLieu")) {
                redirectAttributes.addFlashAttribute("userInput", chatLieu);
                redirectAttributes.addFlashAttribute("errorChatLieu", "tenChatLieuError");
            }
            return "redirect:/manage/giay-chi-tiet/viewAdd";
        }
        ChatLieu chatLieu1 = new ChatLieu();
        chatLieu1.setMaChatLieu(chatLieu.getMaChatLieu());
        chatLieu1.setTenChatLieu(chatLieu.getTenChatLieu());
        chatLieu1.setTgThem(new Date());
        chatLieu1.setTrangThai(1);
        chatLieuService.save(chatLieu1);
        redirectAttributes.addFlashAttribute("messageChatLieu", true);
        return "redirect:/manage/giay-chi-tiet/viewAdd";
    }

    @PostMapping("/chi-tiet-giay/mau-sac/viewAdd/add")
    public String addMauSac(@Valid @ModelAttribute("mauSacAdd") MauSac mauSac, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        UUID idCTGViewAdd = (UUID) session.getAttribute("idViewAddCTG");
        String link = "redirect:/manage/chi-tiet-giay/viewAdd/" + idCTGViewAdd;
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("ma")) {
                redirectAttributes.addFlashAttribute("userInputMau", mauSac);
                redirectAttributes.addFlashAttribute("error", "maError");
            }
            if (bindingResult.hasFieldErrors("maMau")) {
                redirectAttributes.addFlashAttribute("userInputMau", mauSac);
                redirectAttributes.addFlashAttribute("error", "maMauError");
            }
            if (bindingResult.hasFieldErrors("tenMau")) {
                redirectAttributes.addFlashAttribute("userInputMau", mauSac);
                redirectAttributes.addFlashAttribute("error", "tenMauError");
            }
            return link;
        }
        //
        MauSac existingMau = mauSacRepository.findByMa(mauSac.getMa());
        if (existingMau != null) {
            redirectAttributes.addFlashAttribute("userInputMau", mauSac);
            redirectAttributes.addFlashAttribute("ErrormessageMau", true);
            return link;
        }
        //
        MauSac mauSac1 = new MauSac();
        mauSac1.setMa(mauSac.getMa());
        mauSac1.setMaMau(mauSac.getMaMau());
        mauSac1.setTenMau(mauSac.getTenMau());
        mauSac1.setTgThem(new Date());
        mauSac1.setTrangThai(1);
        mauSacService.save(mauSac1);
        redirectAttributes.addFlashAttribute("messageMau", true);
        return link;
    }

    @PostMapping("/giay-chi-tiet/mau-sac/viewAdd/add")
    public String addMauSacCT(@Valid @ModelAttribute("mauSacAdd") MauSac mauSac, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("ma")) {
                redirectAttributes.addFlashAttribute("userInputMau", mauSac);
                redirectAttributes.addFlashAttribute("error", "maError");
            }
            if (bindingResult.hasFieldErrors("maMau")) {
                redirectAttributes.addFlashAttribute("userInputMau", mauSac);
                redirectAttributes.addFlashAttribute("error", "maMauError");
            }
            if (bindingResult.hasFieldErrors("tenMau")) {
                redirectAttributes.addFlashAttribute("userInputMau", mauSac);
                redirectAttributes.addFlashAttribute("error", "tenMauError");
            }
            return "redirect:/manage/giay-chi-tiet/viewAdd";
        }
        //
        MauSac existingMau = mauSacRepository.findByMa(mauSac.getMa());
        if (existingMau != null) {
            redirectAttributes.addFlashAttribute("userInputMau", mauSac);
            redirectAttributes.addFlashAttribute("ErrormessageMau", true);
            return "redirect:/manage/giay-chi-tiet/viewAdd";
        }
        //
        MauSac mauSac1 = new MauSac();
        mauSac1.setMa(mauSac.getMa());
        mauSac1.setMaMau(mauSac.getMaMau());
        mauSac1.setTenMau(mauSac.getTenMau());
        mauSac1.setTgThem(new Date());
        mauSac1.setTrangThai(1);
        mauSacService.save(mauSac1);
        redirectAttributes.addFlashAttribute("messageMau", true);
        return "redirect:/manage/giay-chi-tiet/viewAdd";
    }

    @PostMapping("/chi-tiet-giay/size/viewAdd/add")
    public String addSize(@Valid @ModelAttribute("sizeAdd") Size size, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        UUID idCTGViewAdd = (UUID) session.getAttribute("idViewAddCTG");
        String link = "redirect:/manage/chi-tiet-giay/viewAdd/" + idCTGViewAdd;
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("maSize")) {
                redirectAttributes.addFlashAttribute("userInputSize", size);
                redirectAttributes.addFlashAttribute("error", "maSizeError");
            }
            if (bindingResult.hasFieldErrors("soSize")) {
                redirectAttributes.addFlashAttribute("userInputSize", size);
                redirectAttributes.addFlashAttribute("error", "soSizeError");
            }
            return link;
        }
        //
        Size existingSize = sizeRepository.findByMaSize(size.getMaSize());
        if (existingSize != null) {
            redirectAttributes.addFlashAttribute("userInputSize", size);
            redirectAttributes.addFlashAttribute("ErrormessageSize", true);
            return link;
        }
        //
        Size sizeAdd = new Size();
        sizeAdd.setMaSize(size.getMaSize());
        sizeAdd.setSoSize(size.getSoSize());
        sizeAdd.setTgThem(new Date());
        sizeAdd.setTrangThai(1);
        sizeService.save(sizeAdd);
        redirectAttributes.addFlashAttribute("messageSize", true);
        return link;
    }

    @PostMapping("/giay-chi-tiet/size/viewAdd/add")
    public String addSizeCT(@Valid @ModelAttribute("sizeAdd") Size size, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("maSize")) {
                redirectAttributes.addFlashAttribute("userInputSize", size);
                redirectAttributes.addFlashAttribute("error", "maSizeError");
            }
            if (bindingResult.hasFieldErrors("soSize")) {
                redirectAttributes.addFlashAttribute("userInputSize", size);
                redirectAttributes.addFlashAttribute("error", "soSizeError");
            }
            return "redirect:/manage/giay-chi-tiet/viewAdd";
        }
        //
        Size existingSize = sizeRepository.findByMaSize(size.getMaSize());
        if (existingSize != null) {
            redirectAttributes.addFlashAttribute("userInputSize", size);
            redirectAttributes.addFlashAttribute("ErrormessageSize", true);
            return "redirect:/manage/giay-chi-tiet/viewAdd";
        }
        //
        Size sizeAdd = new Size();
        sizeAdd.setMaSize(size.getMaSize());
        sizeAdd.setSoSize(size.getSoSize());
        sizeAdd.setTgThem(new Date());
        sizeAdd.setTrangThai(1);
        sizeService.save(sizeAdd);
        redirectAttributes.addFlashAttribute("messageSize", true);
        return "redirect:/manage/giay-chi-tiet/viewAdd";
    }

    @PostMapping("/chi-tiet-giay/hinh-anh/viewAdd/add")
    public String addHinhAnhCTG(@RequestParam("maAnh") String maAnh,
                                @RequestParam("anh1") MultipartFile anh1,
                                @RequestParam("anh2") MultipartFile anh2,
                                @RequestParam("anh3") MultipartFile anh3,
                                @RequestParam("anh4") MultipartFile anh4,
                                @Valid @ModelAttribute("hinhAnhAdd") HinhAnh hinhAnh, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        UUID idCTGViewAdd = (UUID) session.getAttribute("idViewAddCTG");
        String link = "redirect:/manage/chi-tiet-giay/viewAdd/" + idCTGViewAdd;
        HinhAnh existingAnh = hinhAnhRepository.findByMaAnh(hinhAnh.getMaAnh());
        if (existingAnh != null) {
            redirectAttributes.addFlashAttribute("userInputAnh", hinhAnh);
            redirectAttributes.addFlashAttribute("ErrormessageAnh", true);
            return link;
        }
        //
        HinhAnh hinhAnh1 = new HinhAnh();
        hinhAnh1.setMaAnh(maAnh);
        if (anh1.isEmpty() || anh2.isEmpty() || anh3.isEmpty() || anh4.isEmpty()) {
            return "redirect:/manage/hinh-anh";
        }
        Path path = Paths.get("src/main/resources/static/images/imgsProducts/");
        try {
            InputStream inputStream = anh1.getInputStream();
            Files.copy(inputStream, path.resolve(anh1.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            hinhAnh1.setUrl1(anh1.getOriginalFilename().toLowerCase());
            //
            inputStream = anh2.getInputStream();
            Files.copy(inputStream, path.resolve(anh2.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            hinhAnh1.setUrl2(anh2.getOriginalFilename().toLowerCase());
            //
            inputStream = anh3.getInputStream();
            Files.copy(inputStream, path.resolve(anh3.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            hinhAnh1.setUrl3(anh3.getOriginalFilename().toLowerCase());
            //
            inputStream = anh4.getInputStream();
            Files.copy(inputStream, path.resolve(anh4.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            hinhAnh1.setUrl4(anh4.getOriginalFilename().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        hinhAnh1.setTgThem(new Date());
        hinhAnh1.setTrangThai(1);
        hinhAnhService.save(hinhAnh1);
        redirectAttributes.addFlashAttribute("messageAnh", true);
        return link;
    }

    @PostMapping("/giay-chi-tiet/hinh-anh/viewAdd/add")
    public String addHinhAnh(@RequestParam("maAnh") String maAnh,
                             @RequestParam("anh1") MultipartFile anh1,
                             @RequestParam("anh2") MultipartFile anh2,
                             @RequestParam("anh3") MultipartFile anh3,
                             @RequestParam("anh4") MultipartFile anh4,
                             @Valid @ModelAttribute("hinhAnhAdd") HinhAnh hinhAnh, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        HinhAnh existingAnh = hinhAnhRepository.findByMaAnh(hinhAnh.getMaAnh());
        if (existingAnh != null) {
            redirectAttributes.addFlashAttribute("userInputAnh", hinhAnh);
            redirectAttributes.addFlashAttribute("ErrormessageAnh", true);
            return "redirect:/manage/giay-chi-tiet/viewAdd";
        }
        //
        HinhAnh hinhAnh1 = new HinhAnh();
        hinhAnh1.setMaAnh(maAnh);
        if (anh1.isEmpty() || anh2.isEmpty() || anh3.isEmpty() || anh4.isEmpty()) {
            return "redirect:/manage/hinh-anh";
        }
        Path path = Paths.get("src/main/resources/static/images/imgsProducts/");
        try {
            InputStream inputStream = anh1.getInputStream();
            Files.copy(inputStream, path.resolve(anh1.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            hinhAnh1.setUrl1(anh1.getOriginalFilename().toLowerCase());
            //
            inputStream = anh2.getInputStream();
            Files.copy(inputStream, path.resolve(anh2.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            hinhAnh1.setUrl2(anh2.getOriginalFilename().toLowerCase());
            //
            inputStream = anh3.getInputStream();
            Files.copy(inputStream, path.resolve(anh3.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            hinhAnh1.setUrl3(anh3.getOriginalFilename().toLowerCase());
            //
            inputStream = anh4.getInputStream();
            Files.copy(inputStream, path.resolve(anh4.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            hinhAnh1.setUrl4(anh4.getOriginalFilename().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        hinhAnh1.setTgThem(new Date());
        hinhAnh1.setTrangThai(1);
        hinhAnhService.save(hinhAnh1);
        redirectAttributes.addFlashAttribute("messageAnh", true);
        return "redirect:/manage/giay-chi-tiet/viewAdd";
    }

    @GetMapping("/giay-chi-tiet/delete/{id}")
    public String deleteGiayChiTiet(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        chiTietGiay.setTrangThai(0);
        chiTietGiay.setTgSua(new Date());
        giayChiTietService.save(chiTietGiay);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/giay-chi-tiet";
    }

    @GetMapping("/chi-tiet-giay/delete/{id}")
    public String deleteChiTietGiay(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        UUID idCTG = (UUID) session.getAttribute("idCTG");
        String link = "redirect:/manage/giay/detail/" + idCTG;
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        chiTietGiay.setTrangThai(0);
        chiTietGiay.setTgSua(new Date());
        giayChiTietService.save(chiTietGiay);
        redirectAttributes.addFlashAttribute("message", true);
        return link;
    }

    @GetMapping("/giay-chi-tiet/viewUpdate/{id}")
    public String viewUpdateGiayChiTiet(@PathVariable UUID id, Model model
            , @ModelAttribute("namSXError") String namSXError
            , @ModelAttribute("namBHError") String namBHError
            , @ModelAttribute("trongLuongError") String trongLuongError
            , @ModelAttribute("giaBanError") String giaBanError
            , @ModelAttribute("soLuongError") String soLuongError
            , @ModelAttribute("mauSacError") String mauSacError
            , @ModelAttribute("hinhAnhError") String hinhAnhError
            , @ModelAttribute("giayError") String giayError
            , @ModelAttribute("sizeError") String sizeError
            , @ModelAttribute("error") String error
            , @ModelAttribute("userInput") ChiTietGiay userInput) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        model.addAttribute("giayChiTiet", chiTietGiay);
        List<HinhAnh> hinhAnhList = hinhAnhService.getAllHinhAnh();
        Collections.sort(hinhAnhList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("hinhAnh", hinhAnhList);
        //
        List<Giay> giayList = giayService.getAllGiay();
        Collections.sort(giayList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("giay", giayList);
        //
        List<MauSac> mauSacList = mauSacService.getALlMauSac();
        Collections.sort(mauSacList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("mauSac", mauSacList);
        //
        List<Size> sizeList = sizeService.getAllSize();
        Collections.sort(sizeList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("size", sizeList);
        //
        model.addAttribute("giayAdd", new Giay());
        model.addAttribute("mauSacAdd", new MauSac());
        model.addAttribute("sizeAdd", new Size());
        model.addAttribute("hinhAnhAdd", new HinhAnh());
        model.addAttribute("hangAdd", new Hang());
        model.addAttribute("chatLieuAdd", new ChatLieu());
        model.addAttribute("hang", hangService.getALlHang());
        model.addAttribute("chatLieu", chatLieuService.getAllChatLieu());
        //
        if (namSXError == null || !"namSXError".equals(error)) {
            model.addAttribute("namSXError", false);
        }
        if (namBHError == null || !"namBHError".equals(error)) {
            model.addAttribute("namBHError", false);
        }
        if (trongLuongError == null || !"trongLuongError".equals(error)) {
            model.addAttribute("trongLuongError", false);
        }
        if (giaBanError == null || !"giaBanError".equals(error)) {
            model.addAttribute("giaBanError", false);
        }
        if (soLuongError == null || !"soLuongError".equals(error)) {
            model.addAttribute("soLuongError", false);
        }
        if (mauSacError == null || !"mauSacError".equals(error)) {
            model.addAttribute("mauSacError", false);
        }
        if (hinhAnhError == null || !"hinhAnhError".equals(error)) {
            model.addAttribute("hinhAnhError", false);
        }
        if (giayError == null || !"giayError".equals(error)) {
            model.addAttribute("giayError", false);
        }
        if (sizeError == null || !"sizeError".equals(error)) {
            model.addAttribute("sizeError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("giayChiTietUpdate", userInput);
        }
        //
        session.setAttribute("id", id);
        return "manage/update-giay-chi-tiet";
    }

    @GetMapping("/chi-tiet-giay/viewUpdate/{id}")
    public String viewUpdateChiTietGiay(@PathVariable UUID id, Model model
            , @ModelAttribute("namSXError") String namSXError
            , @ModelAttribute("namBHError") String namBHError
            , @ModelAttribute("trongLuongError") String trongLuongError
            , @ModelAttribute("giaBanError") String giaBanError
            , @ModelAttribute("soLuongError") String soLuongError
            , @ModelAttribute("mauSacError") String mauSacError
            , @ModelAttribute("hinhAnhError") String hinhAnhError
            , @ModelAttribute("giayError") String giayError
            , @ModelAttribute("sizeError") String sizeError
            , @ModelAttribute("error") String error
            , @ModelAttribute("userInput") ChiTietGiay userInput) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(id);
        model.addAttribute("giayChiTiet", chiTietGiay);
        List<HinhAnh> hinhAnhList = hinhAnhService.getAllHinhAnh();
        Collections.sort(hinhAnhList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("hinhAnh", hinhAnhList);
        //
        List<Giay> giayList = giayService.getAllGiay();
        Collections.sort(giayList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("giay", giayList);
        //
        List<MauSac> mauSacList = mauSacService.getALlMauSac();
        Collections.sort(mauSacList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("mauSac", mauSacList);
        //
        List<Size> sizeList = sizeService.getAllSize();
        Collections.sort(sizeList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("size", sizeList);
        //
        model.addAttribute("giayAdd", new Giay());
        model.addAttribute("mauSacAdd", new MauSac());
        model.addAttribute("sizeAdd", new Size());
        model.addAttribute("hinhAnhAdd", new HinhAnh());
        model.addAttribute("hangAdd", new Hang());
        model.addAttribute("chatLieuAdd", new ChatLieu());
        model.addAttribute("hang", hangService.getALlHang());
        model.addAttribute("chatLieu", chatLieuService.getAllChatLieu());
        //
        if (namSXError == null || !"namSXError".equals(error)) {
            model.addAttribute("namSXError", false);
        }
        if (namBHError == null || !"namBHError".equals(error)) {
            model.addAttribute("namBHError", false);
        }
        if (trongLuongError == null || !"trongLuongError".equals(error)) {
            model.addAttribute("trongLuongError", false);
        }
        if (giaBanError == null || !"giaBanError".equals(error)) {
            model.addAttribute("giaBanError", false);
        }
        if (soLuongError == null || !"soLuongError".equals(error)) {
            model.addAttribute("soLuongError", false);
        }
        if (mauSacError == null || !"mauSacError".equals(error)) {
            model.addAttribute("mauSacError", false);
        }
        if (hinhAnhError == null || !"hinhAnhError".equals(error)) {
            model.addAttribute("hinhAnhError", false);
        }
        if (giayError == null || !"giayError".equals(error)) {
            model.addAttribute("giayError", false);
        }
        if (sizeError == null || !"sizeError".equals(error)) {
            model.addAttribute("sizeError", false);
        }
        // Kiểm tra xem có dữ liệu người dùng đã nhập không và điền lại vào trường nhập liệu
        if (userInput != null) {
            model.addAttribute("giayChiTietUpdate", userInput);
        }
        //
        session.setAttribute("id", id);
        return "manage/update-chi-tiet-giay";
    }

    @PostMapping("/giay-chi-tiet/viewUpdate/{id}")
    public String updateGiayChiTiet(@PathVariable UUID id, @Valid @ModelAttribute("giayChiTiet") ChiTietGiay chiTietGiay,
                                    BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        ChiTietGiay chiTietGiayDb = giayChiTietService.getByIdChiTietGiay(id);
        UUID idGiayCT = (UUID) session.getAttribute("id");
        String link = "redirect:/manage/giay-chi-tiet/viewUpdate/" + idGiayCT;
        if (bindingResult.hasErrors()) {
            //
            if (bindingResult.hasFieldErrors("namSX")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "namSXError");
            }
            if (bindingResult.hasFieldErrors("namBH")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "namBHError");
            }
            if (bindingResult.hasFieldErrors("trongLuong")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "trongLuongError");
            }
            if (bindingResult.hasFieldErrors("giaBan")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "giaBanError");
            }
            if (bindingResult.hasFieldErrors("soLuong")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "soLuongError");
            }
            if (bindingResult.hasFieldErrors("mauSac")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "mauSacError");
            }
            if (bindingResult.hasFieldErrors("hinhAnh")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "hinhAnhError");
            }
            if (bindingResult.hasFieldErrors("giay")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "giayError");
            }
            if (bindingResult.hasFieldErrors("size")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "sizeError");
            }
            return link;
        }
        if (chiTietGiayDb != null) {
            chiTietGiayDb.setGiay(chiTietGiay.getGiay());
            chiTietGiayDb.setHinhAnh(chiTietGiay.getHinhAnh());
            chiTietGiayDb.setMauSac(chiTietGiay.getMauSac());
            chiTietGiayDb.setSize(chiTietGiay.getSize());
            chiTietGiayDb.setGiaBan(chiTietGiay.getGiaBan());
            chiTietGiayDb.setMaNVSua(chiTietGiay.getMaNVSua());
            chiTietGiayDb.setNamBH(chiTietGiay.getNamBH());
            chiTietGiayDb.setNamSX(chiTietGiay.getNamSX());
            chiTietGiayDb.setSoLuong(chiTietGiay.getSoLuong());
            chiTietGiayDb.setTgSua(new Date());
            chiTietGiayDb.setTrangThai(chiTietGiay.getTrangThai());
            chiTietGiayDb.setTrongLuong(chiTietGiay.getTrongLuong());
            giayChiTietService.save(chiTietGiayDb);


            redirectAttributes.addFlashAttribute("message", true);
        }
        return "redirect:/manage/giay-chi-tiet";
    }

    @PostMapping("/chi-tiet-giay/viewUpdate/{id}")
    public String updateChiTietGiay(@PathVariable UUID id, @Valid @ModelAttribute("giayChiTiet") ChiTietGiay chiTietGiay,
                                    BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        ChiTietGiay chiTietGiayDb = giayChiTietService.getByIdChiTietGiay(id);
        UUID idCTG = (UUID) session.getAttribute("id");
        String link = "redirect:/manage/chi-tiet-giay/viewUpdate/" + idCTG;
        UUID idCTG1 = (UUID) session.getAttribute("idCTG");
        String link1 = "redirect:/manage/giay/detail/" + idCTG1;
        if (bindingResult.hasErrors()) {
            //
            if (bindingResult.hasFieldErrors("namSX")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "namSXError");
            }
            if (bindingResult.hasFieldErrors("namBH")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "namBHError");
            }
            if (bindingResult.hasFieldErrors("trongLuong")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "trongLuongError");
            }
            if (bindingResult.hasFieldErrors("giaBan")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "giaBanError");
            }
            if (bindingResult.hasFieldErrors("soLuong")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "soLuongError");
            }
            if (bindingResult.hasFieldErrors("mauSac")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "mauSacError");
            }
            if (bindingResult.hasFieldErrors("hinhAnh")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "hinhAnhError");
            }
            if (bindingResult.hasFieldErrors("giay")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "giayError");
            }
            if (bindingResult.hasFieldErrors("size")) {
                redirectAttributes.addFlashAttribute("userInput", chiTietGiay);
                redirectAttributes.addFlashAttribute("error", "sizeError");
            }
            return link;
        }
        if (chiTietGiayDb != null) {
            chiTietGiayDb.setGiay(chiTietGiay.getGiay());
            chiTietGiayDb.setHinhAnh(chiTietGiay.getHinhAnh());
            chiTietGiayDb.setMauSac(chiTietGiay.getMauSac());
            chiTietGiayDb.setSize(chiTietGiay.getSize());
            chiTietGiayDb.setGiaBan(chiTietGiay.getGiaBan());
            chiTietGiayDb.setMaNVSua(chiTietGiay.getMaNVSua());
            chiTietGiayDb.setNamBH(chiTietGiay.getNamBH());
            chiTietGiayDb.setNamSX(chiTietGiay.getNamSX());
            chiTietGiayDb.setSoLuong(chiTietGiay.getSoLuong());
            chiTietGiayDb.setTgSua(new Date());
            chiTietGiayDb.setTrangThai(chiTietGiay.getTrangThai());
            chiTietGiayDb.setTrongLuong(chiTietGiay.getTrongLuong());
            giayChiTietService.save(chiTietGiayDb);

            redirectAttributes.addFlashAttribute("message", true);
        }
        return link1;
    }

    @GetMapping("/barCodeTest")
    public String createBarCode(Model model, RedirectAttributes redirectAttributes) {
        //Xóa dữ liệu cũ
        createBarCode.deleteQRCode();
        //
        List<ChiTietGiay> chiTietGiays = giayChiTietService.getAllChiTietGiay();
        if (chiTietGiays != null && !chiTietGiays.isEmpty()) {
            for (ChiTietGiay chiTietGiay : chiTietGiays) {
                createBarCode.saveBarcodeImage(chiTietGiay, 300, 200);
            }
        } else {
            return "redirect:/manage/giay-chi-tiet";
        }
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/giay-chi-tiet";
    }

    @GetMapping("/giayCT/export/pdf")
    public void exportToPDFCTGiay(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ctGiay_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<ChiTietGiay> listCTGiay = giayChiTietService.getAllChiTietGiay();

        PDFExporterCTGiay exporter = new PDFExporterCTGiay(listCTGiay);
        exporter.export(response);
    }

    @GetMapping("/giayCT/export/excel")
    public void exportToExcelSize(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=giayCT_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<ChiTietGiay> listCTGiay = giayChiTietService.getAllChiTietGiay();

        ExcelExporterCTGiay excelExporter = new ExcelExporterCTGiay(listCTGiay);

        excelExporter.export(response);

    }

    @GetMapping("/giayct/filter")
    public String searchGiay(Model model, @RequestParam(name = "searchTerm") String searchTerm) {
        List<ChiTietGiay> filteredGiayCTs;
        if ("Giày".equals(searchTerm) && "Size".equals(searchTerm) && "Màu Sắc".equals(searchTerm)) {
            // Nếu người dùng chọn "Tất cả", hiển thị tất cả dữ liệu
            filteredGiayCTs = giayChiTietService.getAllChiTietGiay();
        } else {
            // Thực hiện lọc dữ liệu dựa trên selectedSize
            filteredGiayCTs = giayChiTietService.fillterGCT(searchTerm);
        }
        model.addAttribute("giayCT", filteredGiayCTs);
        model.addAttribute("giayCTAll", giayChiTietService.getAllChiTietGiay());
        return "manage/giay";
    }

    @PostMapping("/giayCT/importExcel")
    public String importData(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                InputStream excelFile = file.getInputStream();
                giayChiTietServiceImpl.importDataFromExcel(excelFile); // Gọi phương thức nhập liệu từ Excel
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi
            }
        }
        return "redirect:/manage/giay-chi-tiet"; // Chuyển hướng sau khi nhập liệu thành công hoặc không thành công
    }
}
