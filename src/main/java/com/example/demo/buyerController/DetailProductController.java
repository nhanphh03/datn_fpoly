package com.example.demo.buyerController;

import com.example.demo.model.*;
import com.example.demo.service.GHCTService;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.GiayService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private GHCTService ghctService;

//    @Autowired
//    private GioHang;




    @GetMapping("/shop-details/{idGiay}")
    private String getFormDetail(Model model,@PathVariable UUID idGiay){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");

        if (khachHang != null){
            String fullName = khachHang.getHoTenKH();
            model.addAttribute("fullNameLogin", fullName);
            GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

            List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);

            Integer sumProductInCart = listGHCTActive.size();
            model.addAttribute("sumProductInCart", sumProductInCart);

        }else {
            model.addAttribute("messageLoginOrSignin", true);
        }

        Giay giay = giayService.getByIdGiay(idGiay);
        session.removeAttribute("idGiayDetail");

        session.setAttribute("idGiayDetail", giay.getIdGiay());


        List<ChiTietGiay> listCTGByGiay = giayChiTietService.getCTGByGiayActive(giay);

        Optional<Double> maxPriceByGiay = listCTGByGiay.stream()
                .map(ChiTietGiay :: getGiaBan)
                .max(Double :: compare);

        Double maxPrice = maxPriceByGiay.get();

        int sumCTGByGiay = listCTGByGiay.stream()
                .mapToInt(ChiTietGiay::getSoLuong)
                .sum();


        Optional<Double> minPriceByGiay = listCTGByGiay.stream()
                .map(ChiTietGiay :: getGiaBan)
                .min(Double :: compare);

        Double minPrice = minPriceByGiay.get();

        String material = giay.getChatLieu().getTenChatLieu();
        model.addAttribute("material", material);

        String brand = giay.getHang().getTenHang();
        model.addAttribute("nameBrand", brand);

        model.addAttribute("product", giay);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("sunProductAvaible", sumCTGByGiay);
        model.addAttribute("listProducts", listCTGByGiay);

        List<HinhAnh> listHinhAnh = giayChiTietService.listHinhAnhByGiay(giay);
        model.addAttribute("listHA", listHinhAnh);


        return "online/detail-product";
    }


    @GetMapping("/shop/addProductCart")
    public String handleAddToCart(@RequestParam("idDProduct") String idDProduct, Model model) {

        UUID idGiay = (UUID) session.getAttribute("idGiayDetail");

        return getFormDetail(model,idGiay);
    }

}
