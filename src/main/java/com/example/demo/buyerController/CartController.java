package com.example.demo.buyerController;

import com.example.demo.model.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/buyer")
public class CartController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private GHCTService ghctService;

    @Autowired
    private GiayChiTietService gctService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private DiaChiKHService diaChiKHService;

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @Autowired
    private LoaiKhuyenMaiService loaiKhuyenMaiService;

    @GetMapping("/cart")
    private String getShoppingCart(Model model){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        Integer sumProductInCart = listGHCTActive.size();

        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());
        model.addAttribute("sumProductInCart", sumProductInCart);
        model.addAttribute("listCartDetail", listGHCTActive);

        return "/online/shopping-cart";
    }

    @PostMapping("/cart/updateQuantity")
    @ResponseBody
    public void updateQuantity(@RequestParam UUID idCTG, @RequestParam int quantity) {

        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;
        ChiTietGiay chiTietGiay = gctService.getByIdChiTietGiay(idCTG);

        GioHangChiTiet gioHangChiTiet = ghctService.findByCTGActiveAndKhachHangAndTrangThai(chiTietGiay, gioHang);
        gioHangChiTiet.setSoLuong(quantity);
        gioHangChiTiet.setDonGia(quantity*chiTietGiay.getGiaBan());
        ghctService.addNewGHCT(gioHangChiTiet);

    }

    @GetMapping("/cart/options/{idProduct}")
    private String getOptionProduct(Model model, @PathVariable UUID idProuduct){

//        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(idProuduct);
//        Integer sumProductInCart = listGHCTActive.size();

        return "/online/shopping-cart";
    }

    @GetMapping("/cart/delete/{idCTG}")
    private String deleteInCard(Model model, @PathVariable UUID idCTG){

        ChiTietGiay chiTietGiay = gctService.getByIdChiTietGiay(idCTG);
        GioHangChiTiet gioHangChiTiet = ghctService.findByCTSPActive(chiTietGiay);
        gioHangChiTiet.setTrangThai(0);
        ghctService.addNewGHCT(gioHangChiTiet);

        return "redirect:/buyer/cart";
    }

    public String generateRandomNumbers() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randomNumber = random.nextInt(10); // Tạo số ngẫu nhiên từ 0 đến 9
            sb.append(randomNumber);
        }
        return sb.toString();
    }
}
