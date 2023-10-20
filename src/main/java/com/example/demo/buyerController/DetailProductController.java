package com.example.demo.buyerController;

import com.example.demo.model.*;
import com.example.demo.service.*;
import com.example.demo.viewModel.CTGViewModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
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

    @Autowired
    private LuotXemFAService luotXemFAService;

    @Autowired
    private CTGViewModelService ctgViewModelService;



    @GetMapping("/shop-details/sold/{idGiay}")
    private String getFormDetailSold(Model model,@PathVariable UUID idGiay){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");

        Giay giay = giayService.getByIdGiay(idGiay);

        checkKHLogged(model, khachHang, giay);



        List<Size> listSizeByGiay = giayChiTietService.findDistinctSizeByGiay(giay);

        List<MauSac> listMauSacByGiay = giayChiTietService.findDistinctMauSacByGiay(giay);



        List<ChiTietGiay> listCTGByGiay = giayChiTietService.getCTGByGiaySoldOut(giay);

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
        List<HinhAnh> listHinhAnh = giayChiTietService.listHinhAnhByGiay(giay);

        String brand = giay.getHang().getTenHang();

        model.addAttribute("material", material);
        model.addAttribute("nameBrand", brand);
        model.addAttribute("product", giay);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("sunProductAvaible", sumCTGByGiay);
        model.addAttribute("listProducts", listCTGByGiay);
        model.addAttribute("listHA", listHinhAnh);
        model.addAttribute("listSizeByGiay", listSizeByGiay);
        model.addAttribute("listMauSacByGiay", listMauSacByGiay);
        model.addAttribute("buyReceiveMail", true);
        model.addAttribute("buyNowAddCartLogged", false);

        return "online/detail-product";
    }

    @GetMapping("/shop-details/{idGiay}")
    private String getFormDetail(Model model,@PathVariable UUID idGiay){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");

        Giay giay = giayService.getByIdGiay(idGiay);
        List<Size> listSizeByGiay = giayChiTietService.findDistinctSizeByGiay(giay);
        List<MauSac> listMauSacByGiay = giayChiTietService.findDistinctMauSacByGiay(giay);
        List<ChiTietGiay> listCTGByGiay = giayChiTietService.getCTGByGiayActive(giay);

        checkKHLogged(model, khachHang, giay);

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
        List<HinhAnh> listHinhAnh = giayChiTietService.listHinhAnhByGiay(giay);
        String material = giay.getChatLieu().getTenChatLieu();
        String brand = giay.getHang().getTenHang();

        model.addAttribute("nameBrand", brand);
        model.addAttribute("material", material);
        model.addAttribute("product", giay);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("sunProductAvaible", sumCTGByGiay);
        model.addAttribute("listProducts", listCTGByGiay);
        model.addAttribute("listSizeByGiay", listSizeByGiay);
        model.addAttribute("listMauSacByGiay", listMauSacByGiay);
        model.addAttribute("listHA", listHinhAnh);



        addToLuotXemFA(khachHang, idGiay, giay, minPrice, sumCTGByGiay, 1);
        return "online/detail-product";
    }

    private void checkKHLogged(Model model, KhachHang khachHang, Giay giay){
        if (khachHang != null){
            String fullName = khachHang.getHoTenKH();
            model.addAttribute("fullNameLogin", fullName);
            GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

            List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
            model.addAttribute("heartLogged", true);

            Integer sumProductInCart = listGHCTActive.size();
            model.addAttribute("sumProductInCart", sumProductInCart);
            model.addAttribute("buyNowAddCartLogged", true);

        }else {
            model.addAttribute("messageLoginOrSignin", true);
        }

        session.removeAttribute("idGiayDetail");

        session.setAttribute("idGiayDetail", giay.getIdGiay());
    }

    @GetMapping("/shop/addProductCart")
    public String handleAddToCart(@RequestParam("idDProduct") UUID idDProduct,
                                  @RequestParam("quantity") int quantity,
                                  Model model) {

        UUID idGiay = (UUID) session.getAttribute("idGiayDetail");

        ChiTietGiay ctg = giayChiTietService.getByIdChiTietGiay(idDProduct);
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();

        gioHangChiTiet.setChiTietGiay(ctg);
        gioHangChiTiet.setGioHang(gioHang);
        gioHangChiTiet.setSoLuong(quantity);
        gioHangChiTiet.setTgThem(new Date());
        gioHangChiTiet.setDonGia(quantity*ctg.getGiaBan());
        gioHangChiTiet.setTrangThai(1);

        ghctService.addNewGHCT(gioHangChiTiet);

        return "redirect:/buyer/shop-details/" + idGiay;
    }


    @GetMapping("/detail/heart/{idGiay}")
    private String addToHeart(Model model,@PathVariable UUID idGiay){


        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        Giay giay = giayService.getByIdGiay(idGiay);
        List<ChiTietGiay> listCTGByGiay = giayChiTietService.getCTGByGiayActive(giay);

        int sumCTGByGiay = listCTGByGiay.stream()
                .mapToInt(ChiTietGiay::getSoLuong)
                .sum();

        Optional<Double> minPriceByGiay = listCTGByGiay.stream()
                .map(ChiTietGiay :: getGiaBan)
                .min(Double :: compare);

        Double minPrice = minPriceByGiay.get();

        addToLuotXemFA(khachHang, idGiay, giay, minPrice, sumCTGByGiay, 0);

        return "redirect:/buyer/shop-details/" + idGiay;
    }


    @GetMapping("/heart/{idGiay}")
    private String addToHeartShop(Model model,@PathVariable UUID idGiay){


        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        Giay giay = giayService.getByIdGiay(idGiay);
        List<ChiTietGiay> listCTGByGiay = giayChiTietService.getCTGByGiayActive(giay);

        int sumCTGByGiay = listCTGByGiay.stream()
                .mapToInt(ChiTietGiay::getSoLuong)
                .sum();

        Optional<Double> minPriceByGiay = listCTGByGiay.stream()
                .map(ChiTietGiay :: getGiaBan)
                .min(Double :: compare);

        Double minPrice = minPriceByGiay.get();

        addToLuotXemFA(khachHang, idGiay, giay, minPrice, sumCTGByGiay, 0);

        return "redirect:/buyer/shop";
    }

    private void addToLuotXemFA(KhachHang khachHang, UUID idGiay, Giay giay, Double minPrice, Integer sumCTGByGiay , Integer loai){
        if(khachHang != null){
            CTGViewModel ctgViewModel =  ctgViewModelService.findByIDGiay(idGiay);
            LuotXemFA checkLuotXemFA = luotXemFAService.checkLuotXemOrFA(khachHang, giay, loai);

            if (checkLuotXemFA != null){
                checkLuotXemFA.setTgThem(new Date());
                luotXemFAService.addNewLuotXem(checkLuotXemFA);
            }else {
                LuotXemFA luotXemFA = new LuotXemFA();

                luotXemFA.setGiay(giay);
                luotXemFA.setTrangThai(1);
                luotXemFA.setKhachHang(khachHang);
                luotXemFA.setTgThem(new Date());
                luotXemFA.setLoai(loai);
                luotXemFA.setMinPrice(minPrice);
                luotXemFA.setSlTon(sumCTGByGiay);
                luotXemFA.setSoLuongDaBan(ctgViewModel.getSoLuongDaBan());
                luotXemFA.setHinhAnh(ctgViewModel.getHinhAnh());

                luotXemFAService.addNewLuotXem(luotXemFA);
            }
        }
    }
}
