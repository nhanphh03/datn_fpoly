package com.example.demo.buyerController;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.Giay;
import com.example.demo.model.KhachHang;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.GiayService;
import com.example.demo.service.GioHangService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/buyer")
public class DetailProductController {

    @Autowired
    private HttpSession session;

    @Autowired
    private GiayChiTietService giayChiTietService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private GiayService giayService;

//    @Autowired
//    private GioHang;




    @GetMapping("/shop-details/{idGiay}")
    private String getFormDetail(Model model,@PathVariable UUID idGiay){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");

        if (khachHang != null){
            String fullName = khachHang.getHoTenKH();
            model.addAttribute("fullNameLogin", fullName);
            return "online/detail-product";
        }

        System.out.println(idGiay);

        Giay giay = giayService.getByIdGiay(idGiay);

        List<ChiTietGiay> listCTGByGiay = giayChiTietService.getCTGByGiayActive(giay);

        Optional<Double> maxPriceByGiay = listCTGByGiay.stream()
                .map(ChiTietGiay :: getGiaBan)
                .max(Double :: compare);
        Double maxPrice = maxPriceByGiay.get();





        return "online/detail-product";
    }

}
