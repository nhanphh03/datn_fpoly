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

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private GiayService giayService;

    @GetMapping("/cart")
    private String getShoppingCart(Model model){
        model.addAttribute("reLoadPageCart", true);
        showDataBuyer(model);

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

    @GetMapping("/cart/option/{idProduct}/{idMau}/{idCTG}")
    private String getOptionProduct(Model model, @PathVariable UUID idProduct, @PathVariable UUID idMau, @PathVariable UUID idCTG){


        MauSac mauSac = mauSacService.getByIdMauSac(idMau);
        Giay giay = giayService.getByIdGiay(idProduct);

        if(mauSac == null){
            giay=giayService.getByIdGiay(idMau);
            mauSac=mauSacService.getByIdMauSac(idProduct);
        }

        ChiTietGiay chiTietGiay = gctService.getByIdChiTietGiay(idCTG);

        session.removeAttribute("ctgChangeSize");
        session.setAttribute("ctgChangeSize", chiTietGiay);

        List<ChiTietGiay> listCTGByGiay = gctService.findByGiayAndMau(giay, mauSac);


//        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(idProuduct);
//        Integer sumProductInCart = listGHCTActive.size();

        model.addAttribute("reLoadPage", true);

        showDataBuyer(model);
        model.addAttribute("showModalChooseSize", true);
        model.addAttribute("nameProduct", giay.getTenGiay());

        model.addAttribute("listCTGByGiay", listCTGByGiay);

        return "/online/shopping-cart";
    }

    @GetMapping("/cart/delete/{idCTG}")
    private String deleteInCard(Model model, @PathVariable UUID idCTG){

        ChiTietGiay chiTietGiay = gctService.getByIdChiTietGiay(idCTG);
        GioHangChiTiet gioHangChiTiet = ghctService.findByCTSPActive(chiTietGiay);
        gioHangChiTiet.setTrangThai(0);
        ghctService.addNewGHCT(gioHangChiTiet);

        showDataBuyer(model);

        return "redirect:/buyer/cart";
    }


    @PostMapping("/cart/receive/mail/{idCTG}")
    @ResponseBody
    private void receiveMail(@PathVariable UUID idCTG){
        System.out.println("Nhan ngu");
        System.out.println(idCTG);
    }

    @PostMapping("/cart/change/size")
    private String changeSize(Model model,@RequestParam("selectedValues") List<UUID> selectedValues){

        ChiTietGiay chiTietGiay = (ChiTietGiay) session.getAttribute("ctgChangeSize");

        UUID idCTGChangeSize = selectedValues.get(0);

        ChiTietGiay chiTietGiayChangeSize = gctService.getByIdChiTietGiay(idCTGChangeSize);

        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

        GioHangChiTiet gioHangChiTiet = ghctService.findByCTGActiveAndKhachHangAndTrangThai(chiTietGiay, gioHang);

        gioHangChiTiet.setTgThem(new Date());
        gioHangChiTiet.setChiTietGiay(chiTietGiayChangeSize);
        gioHangChiTiet.setDonGia(gioHangChiTiet.getSoLuong()*chiTietGiay.getGiaBan());
        ghctService.addNewGHCT(gioHangChiTiet);

        showDataBuyer(model);
        return "/online/shopping-cart";
    }

    private void showDataBuyer(Model model){
        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        Integer sumProductInCart = listGHCTActive.size();

        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());
        model.addAttribute("sumProductInCart", sumProductInCart);
        model.addAttribute("listCartDetail", listGHCTActive);
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
