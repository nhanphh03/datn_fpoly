package com.example.demo.controller;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.HoaDon;
import com.example.demo.service.CTGViewModelService;
import com.example.demo.service.CreateBarCode;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.HangService;
import com.example.demo.service.HoaDonService;
import com.example.demo.viewModel.CTGViewModel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/ban-hang")
public class BanHangController {

    @Autowired
    private HttpSession session;

    @Autowired
    private HangService hangService;

    @Autowired
    private GiayChiTietService giayChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private CTGViewModelService ctgViewModelService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CreateBarCode createBarCode;

    @GetMapping("/offline")
    public String offline(){
        return "manage/activities";
    }


    @GetMapping("/hien-thi")
    public String hienThi(Model model){

        List<CTGViewModel> listCTGViewModel = ctgViewModelService.getAll();
        model.addAttribute("listSanPham",listCTGViewModel);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
//        model.addAttribute("listHang", hangService.getALlHang());
        return "offline/index";
    }

    @GetMapping("/add-cart")
    public String taoHoaDon(Model model) {
        List<HoaDon> listHD = hoaDonService.getListHoaDonChuaThanhToan();
        if (listHD.size() < 3) {
            String ma = String.valueOf(Math.floor(((Math.random() * 899999) + 100000)));
            Date ngayTao = new Date();
            int tinhTrang = 1;
            HoaDon hd = new HoaDon();
            hd.setMaHD("hd" + ma);
            hd.setTgTao(ngayTao);
            hd.setTrangThai(tinhTrang);
            hoaDonService.add(hd);
            session.setAttribute("message", "Tạo hóa đơn thành công");
        } else {
            session.setAttribute("message", "Quá số lượng");
        }
        List<CTGViewModel> listCTGViewModel = ctgViewModelService.getAll();
        model.addAttribute("listSanPham",listCTGViewModel);
        model.addAttribute("listHoaDon", listHD);
        return "redirect:/ban-hang/hien-thi";
    }

    @GetMapping("/cart/hoadon/{idHoaDon}")
    public String chonHoaDon(@PathVariable("idHoaDon") UUID idHoaDon, Model model) {
        httpSession.setAttribute("idHoaDon",idHoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());

        return "redirect:/ban-hang/hien-thi";
    }



    @GetMapping("/chon-size/{idChiTietGiay}")
    public String chonSize(@PathVariable(value = "idChiTietGiay") UUID idChiTietGiay, Model model){
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(idChiTietGiay);
        System.out.printf(chiTietGiay.toString());
        model.addAttribute("sizeList","1234");
        return "offline/index";
    }
    @PostMapping("/scanBarcode")
    public String scanBarcode(@RequestParam("file") MultipartFile file) {
        try {
            // Đọc ảnh từ MultipartFile
            BufferedImage image = ImageIO.read(file.getInputStream());
            String barcode = decodeBarcode(image);

            // Trả về chuỗi barcode đã quét được
            return "Mã barcode quét được: " + barcode;
        } catch (IOException | NotFoundException e) {
            return "Lỗi khi xử lý ảnh hoặc quét mã barcode: " + e.getMessage();
        }
    }

    private String decodeBarcode(BufferedImage image) throws NotFoundException {
        // Tạo đối tượng BinaryBitmap từ ảnh
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

        // Thiết lập cấu hình quét mã barcode
        Map<DecodeHintType, Object> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

        // Quét mã barcode từ ảnh
        Result result = new MultiFormatReader().decode(binaryBitmap, hints);

        return result.getText();
    }
//    @PostMapping("/uploadQrCode")
//    public String uploadQrCode(@RequestParam("qrCodeFile") MultipartFile qrCodeFile, RedirectAttributes redirectAttributes) {
//        if(qrCodeFile.isEmpty()) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Please choose file to upload.");
//            return "redirect:/";
//        }
//
//        try {
////            String qrContent = qrCodeService.decodeQR(qrCodeFile.getBytes());
////            String qrContent = createBarCode.saveBarcodeImage();
//            redirectAttributes.addFlashAttribute("successMessage", "File upload successfully.");
////            redirectAttributes.addFlashAttribute("qrContent", "Your QR Code Content:" + qrContent);
//            return "redirect:/";
//        } catch (IOException e) {
////            logger.error(e.getMessage(), e);
//            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
//        }
//
//        return "redirect:/";
//    }

}
