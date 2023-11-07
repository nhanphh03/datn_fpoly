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

    private Random random = new Random();

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

    @PostMapping("/cart/checkout")
    private String checkoutCart(Model model, @RequestParam("selectedProducts") List<UUID> selectedProductIds){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;
        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());

        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        Integer sumProductInCart = listGHCTActive.size();
        model.addAttribute("sumProductInCart", sumProductInCart);

        DiaChiKH diaChiKHDefault = diaChiKHService.findDCKHDefaulByKhachHang(khachHang);

        String maHD = "HD_" + khachHang.getMaKH() + khachHang.getCCCDKH() + generateRandomNumbers();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setKhachHang(khachHang);
        hoaDon.setMaHD(maHD);
        hoaDon.setLoaiHD(0);
        hoaDon.setTgTao(new Date());
        hoaDon.setTrangThai(0);
        hoaDonService.add(hoaDon);

        List<HoaDonChiTiet> listHDCTCheckOut = new ArrayList<>();

        for (UUID x: selectedProductIds) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setChiTietGiay(gctService.getByIdChiTietGiay(x));

            GioHangChiTiet gioHangChiTiet = ghctService.findByCTGActiveAndKhachHangAndTrangThai(gctService.getByIdChiTietGiay(x), gioHang);

            hoaDonChiTiet.setDonGia(gioHangChiTiet.getDonGia());
            hoaDonChiTiet.setSoLuong(gioHangChiTiet.getSoLuong());

            hoaDonChiTiet.setTgThem(new Date());

            hoaDonChiTiet.setTrangThai(1);

            hoaDonChiTietService.add(hoaDonChiTiet);
            listHDCTCheckOut.add(hoaDonChiTiet);

        }

        int sumQuantity = listHDCTCheckOut.stream()
                .mapToInt(HoaDonChiTiet::getSoLuong)
                .sum();

        double total = listHDCTCheckOut.stream()
                .mapToDouble(HoaDonChiTiet::getDonGia)
                .sum();

        model.addAttribute("sumQuantity", sumQuantity);
        model.addAttribute("total", total);
        model.addAttribute("diaChiKHDefault", diaChiKHDefault);
        model.addAttribute("listProductCheckOut", listHDCTCheckOut);




        return "online/checkout";
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randomNumber = random.nextInt(10); // Tạo số ngẫu nhiên từ 0 đến 9
            sb.append(randomNumber);
        }
        return sb.toString();
    }
}
