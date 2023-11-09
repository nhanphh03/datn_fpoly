package com.example.demo.buyerController;

import com.example.demo.model.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/buyer")
public class CheckOutController {

    @Autowired
    private HttpSession session;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private GiayChiTietService giayChiTietService;

    private Random random;

    @Autowired
    private DiaChiKHService diaChiKHService;

    @Autowired
    private GHCTService ghctService;


    @PostMapping("/checkout/placeoder")
    public String PlaceOrder(Model model){

        HoaDon hoaDon = (HoaDon) session.getAttribute("hoaDonTaoMoi");

        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietService.findByHoaDonAndTrangThai(hoaDon, 1);

        List<ChiTietGiay>  chiTietGiayList = new ArrayList<>();

        for (HoaDonChiTiet x: hoaDonChiTietList) {
            chiTietGiayList.add(x.getChiTietGiay());
        }

        List<HoaDon> hoaDonList = hoaDonService.getAllHoaDon();

        for (HoaDon x: hoaDonList ) {
            System.out.println(x.getTgTao());
            for (HoaDonChiTiet xx: x.getHoaDonChiTiets()) {
                System.out.println(xx.getChiTietGiay().getGiay().getTenGiay());
                System.out.println(xx.getChiTietGiay().getMauSac().getTenMau());
                System.out.println(xx.getDonGia());
                System.out.println(xx.getSoLuong());


            }
            System.out.println("NHAN NGU @@@@@@@@@@@@@@@@@@");
        }

        for (ChiTietGiay x: chiTietGiayList) {
//            x.setSoLuong(x.getSoLuong()-);
        }



//        GiaoHang giaoHang = new GiaoHang();
//        giaoHang.setHoaDon(hoaDon);
//        giaoHang.setTrangThai(0);
//        giaoHang.setNoiDung("");
//        giaoHang.setNhanVien(null);



        return "redirect:/buyer/purchase";
    }

    private void UserForm(Model model){
        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;
        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());

        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        Integer sumProductInCart = listGHCTActive.size();
        model.addAttribute("sumProductInCart", sumProductInCart);
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
