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

import java.util.*;

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

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private CTHVService cthvService;

    @Autowired
    private LoaiHVService loaiHVService;

    @Autowired
    private DanhGiaServices danhGiaServices;


    @GetMapping("/shop-details/{idGiay}/{idMau}")
    private String getFormDetail(Model model,@PathVariable UUID idGiay, @PathVariable UUID idMau){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");

        Giay giay = giayService.getByIdGiay(idGiay);
        MauSac mau = mauSacService.getByIdMauSac(idMau);

        if (giay == null){
            giay = giayService.getByIdGiay(idMau);
            mau = mauSacService.getByIdMauSac(idGiay);
        }

        checkKHLogged(model, khachHang, giay, mau);

        List<ChiTietGiay> listCTGByGiay = giayChiTietService.findByMauSacAndGiay(mau, giay,1);
        List<ChiTietGiay> listCTGByGiaySold = giayChiTietService.findByMauSacAndGiay(mau, giay,0);

        List<Object[]> allSizeByGiay = new ArrayList<>();
        List<Object[]> allSizeByGiaySold = new ArrayList<>();

        String showReceiveMail = "true";

        for (ChiTietGiay x : listCTGByGiay) {
            if (x.getTrangThai()==1){
                showReceiveMail = "false";
            }
            allSizeByGiay.add(new Object[] { x.getSize().getSoSize(), x.getIdCTG(), showReceiveMail});
        }
        for (ChiTietGiay x : listCTGByGiaySold) {
            if (x.getTrangThai()==0){
                showReceiveMail = "true";
            }
            allSizeByGiaySold.add(new Object[] { x.getSize().getSoSize(), x.getIdCTG(), showReceiveMail});
        }
        allSizeByGiay.addAll(allSizeByGiaySold);

        allSizeByGiay.sort(Comparator.comparingInt(obj -> ((Integer) obj[0])));

        List<MauSac> listMauSacByGiay = giayChiTietService.findDistinctMauSacByGiay(giay);

        if (listMauSacByGiay.size() == 1){
            model.addAttribute("CTGBy1Mau", true);
            model.addAttribute("tenMau", mau.getTenMau());
        }else {
            model.addAttribute("CTGByMoreMau", true);
            model.addAttribute("listMauSacByGiay", listMauSacByGiay);
        }

        //Infor begin
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

        //Infor end

        HinhAnh hinhAnhByGiayAndMau = giayChiTietService.hinhAnhByGiayAndMau(giay, mau);

        String maMau = mau.getMaMau();

        List<DanhGiaKhachHang> danhGiaKhachHangs = danhGiaServices.findByGiay(giay);


        model.addAttribute("listDanhGiaKhachHang", danhGiaKhachHangs);
        model.addAttribute("product", giay);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("sunProductAvaible", sumCTGByGiay);
        model.addAttribute("hinhAnh", hinhAnhByGiayAndMau);
        model.addAttribute("idHeartMau", mau.getIdMau());
        model.addAttribute("listMauSacByGiay", listMauSacByGiay);
        model.addAttribute("listSizeCTG", allSizeByGiay);
        model.addAttribute("listGiavaSize", listCTGByGiay);

        addToLuotXemFA(khachHang, mau, giay, minPrice, sumCTGByGiay, 1);
        model.addAttribute(maMau, "true");
        return "online/detail-product";
    }

    @GetMapping("/shop-details/sold/{idGiay}/{idMau}")
    private String getFormDetailSold(Model model,@PathVariable UUID idGiay, @PathVariable UUID idMau){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");

        Giay giay = giayService.getByIdGiay(idMau);
        MauSac mau = mauSacService.getByIdMauSac(idGiay);

        if (giay ==null){
            giay=giayService.getByIdGiay(idGiay);;
            mau = mauSacService.getByIdMauSac(idMau);
        }


        checkKHLogged(model, khachHang, giay, mau);

        model.addAttribute("buyReceiveMail", true);

        List<ChiTietGiay> listCTGByGiay = giayChiTietService.getCTGByGiaySoldOut(giay);

        List<MauSac> listMauSacByGiay = giayChiTietService.findDistinctMauSacByGiay(giay);

        if (listMauSacByGiay.size() == 1){
            model.addAttribute("CTGBy1Mau", true);
            model.addAttribute("tenMau", mau.getTenMau());
        }else {
            model.addAttribute("CTGByMoreMau", true);
            model.addAttribute("listMauSacByGiay", listMauSacByGiay);
        }


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

        String brand = giay.getHang().getTenHang();

        HinhAnh hinhAnhByGiayAndMau = giayChiTietService.hinhAnhByGiayAndMau(giay, mau);

        List<DanhGiaKhachHang> danhGiaKhachHangs = danhGiaServices.findByGiay(giay);

        model.addAttribute("listDanhGiaKhachHang", danhGiaKhachHangs);
        model.addAttribute("hinhAnh", hinhAnhByGiayAndMau);
        model.addAttribute("material", material);
        model.addAttribute("nameBrand", brand);
        model.addAttribute("product", giay);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("listSizeCTG", listCTGByGiay);
        model.addAttribute("sunProductAvaible", sumCTGByGiay);
        model.addAttribute("listProducts", listCTGByGiay);
        model.addAttribute("listMauSacByGiay", listMauSacByGiay);

        addToLuotXemFA(khachHang, mau, giay, minPrice, sumCTGByGiay, 1);

        return "online/detail-product";
    }

    @GetMapping("/shop/addProductCart")
    public String handleAddToCart(@RequestParam("idDetailProduct") UUID idDProduct, @RequestParam("quantity") int quantity, Model model) {

        ChiTietGiay ctg = giayChiTietService.getByIdChiTietGiay(idDProduct);

        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

        GioHangChiTiet gioHangChiTiet = ghctService.findByCTSPActive(ctg);

        if (gioHangChiTiet != null){
            gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong() + quantity);
            gioHangChiTiet.setTgThem(new Date());
            gioHangChiTiet.setDonGia(quantity*ctg.getGiaBan() + gioHangChiTiet.getDonGia());
            gioHangChiTiet.setDonGiaTruocKhiGiam(quantity*ctg.getSoTienTruocKhiGiam() + gioHangChiTiet.getDonGiaTruocKhiGiam());
            ghctService.addNewGHCT(gioHangChiTiet);
        }else {
            GioHangChiTiet gioHangChiTietNew = new GioHangChiTiet();

            gioHangChiTietNew.setChiTietGiay(ctg);
            gioHangChiTietNew.setGioHang(gioHang);
            gioHangChiTietNew.setSoLuong(quantity);
            gioHangChiTietNew.setTgThem(new Date());
            gioHangChiTietNew.setDonGia(quantity * ctg.getGiaBan());
            System.out.println(quantity*ctg.getSoTienTruocKhiGiam());
//            gioHangChiTiet.setDonGiaTruocKhiGiam(quantity*ctg.getSoTienTruocKhiGiam());
            gioHangChiTietNew.setTrangThai(1);

            ghctService.addNewGHCT(gioHangChiTietNew);
        }

        String idGiay = String.valueOf(ctg.getGiay().getIdGiay());

        String idMau = String.valueOf(ctg.getMauSac().getIdMau());

        String link = idGiay +"/" +idMau;
        return "redirect:/buyer/shop-details/" + link;
    }

    @GetMapping("/detail/heart/{idGiay}/{idMau}")
    private String addToHeart(@PathVariable UUID idGiay,@PathVariable UUID idMau){
        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");

        Giay giay = giayService.getByIdGiay(idGiay);
        MauSac mau = mauSacService.getByIdMauSac(idMau);

        List<ChiTietGiay> listCTGByGiay = giayChiTietService.findByMauSacAndGiay(mau, giay,1);

        int sumCTGByGiay = listCTGByGiay.stream()
                .mapToInt(ChiTietGiay::getSoLuong)
                .sum();

        Optional<Double> minPriceByGiay = listCTGByGiay.stream()
                .map(ChiTietGiay :: getGiaBan)
                .min(Double :: compare);

        Double minPrice = minPriceByGiay.get();

        addToLuotXemFA(khachHang, mau, giay, minPrice, sumCTGByGiay, 0);

        return "redirect:/buyer/shop-details/" + idGiay +"/" +idMau;
    }

    @GetMapping("/shop/buyNowButton")
    private String buyNow(@RequestParam("idDetailProduct") UUID idDProduct, @RequestParam("quantity") int quantity, Model model){

        ChiTietGiay ctg = giayChiTietService.getByIdChiTietGiay(idDProduct);

        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

        GioHangChiTiet gioHangChiTiet = ghctService.findByCTSPActive(ctg);

        if (gioHangChiTiet != null){
            gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong() + quantity);
            gioHangChiTiet.setTgThem(new Date());
            gioHangChiTiet.setDonGia(quantity*ctg.getGiaBan() + gioHangChiTiet.getDonGia());
            ghctService.addNewGHCT(gioHangChiTiet);
        }else {
            GioHangChiTiet gioHangChiTietNew = new GioHangChiTiet();
            gioHangChiTietNew.setChiTietGiay(ctg);
            gioHangChiTietNew.setGioHang(gioHang);
            gioHangChiTietNew.setSoLuong(quantity);
            gioHangChiTietNew.setTgThem(new Date());
            gioHangChiTietNew.setDonGia(quantity * ctg.getGiaBan());
            gioHangChiTietNew.setTrangThai(1);
            ghctService.addNewGHCT(gioHangChiTietNew);
        }

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");

        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);

        Integer sumProductInCart = listGHCTActive.size();
        String idBuyNow = String.valueOf(ctg.getIdCTG());

        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());
        model.addAttribute("sumProductInCart", sumProductInCart);
        model.addAttribute("listCartDetail", listGHCTActive);
        model.addAttribute(idBuyNow, true);
        model.addAttribute("chiTietGiay", ctg);
        

        return "/online/shopping-cart";

    }

    @GetMapping("/heart/{idGiay}/{idMau}")
    private String addToHeartShop(Model model,@PathVariable UUID idGiay, @PathVariable UUID idMau){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");

        Giay giay = giayService.getByIdGiay(idMau);
        MauSac mau = mauSacService.getByIdMauSac(idGiay);

        List<ChiTietGiay> listCTGByGiay = giayChiTietService.getCTGByGiayActive(giay);

        int sumCTGByGiay = listCTGByGiay.stream()
                .mapToInt(ChiTietGiay::getSoLuong)
                .sum();

        Optional<Double> minPriceByGiay = listCTGByGiay.stream()
                .map(ChiTietGiay :: getGiaBan)
                .min(Double :: compare);

        Double minPrice = minPriceByGiay.get();

        addToLuotXemFA(khachHang, mau, giay, minPrice, sumCTGByGiay, 0);

        return "redirect:/buyer/shop";
    }

    private void addToLuotXemFA(KhachHang khachHang, MauSac mauSac, Giay giay, Double minPrice, Integer sumCTGByGiay , Integer loai){

        if(khachHang != null){

            CTGViewModel ctgViewModel =  ctgViewModelService.findByIDGiayAndMau(giay.getIdGiay(), mauSac.getIdMau());

            LuotXemFA checkLuotXemFA = luotXemFAService.checkLuotXemOrFA(khachHang, giay, loai, mauSac.getIdMau());

            if (checkLuotXemFA != null){

                checkLuotXemFA.setTgThem(new Date());
                int sl=0;
                String sldb = String.valueOf(ctgViewModel.getSoLuongDaBan());
                if (sldb == null){
                    sl = 0;
                }else{
                    sl = Integer.valueOf(sldb);
                }
                checkLuotXemFA.setSlTon(sumCTGByGiay);
                checkLuotXemFA.setSoLuongDaBan(sl);
                checkLuotXemFA.setTgSua(new Date());

                luotXemFAService.addNewLuotXem(checkLuotXemFA);

            }else {

                LuotXemFA luotXemFA = new LuotXemFA();

                int sl=0;
                String sldb = String.valueOf(ctgViewModel.getSoLuongDaBan());
                if (sldb == null){
                    sl = 0;
                }else{
                    sl = Integer.valueOf(sldb);
                }

                luotXemFA.setGiay(giay);
                luotXemFA.setTrangThai(1);
                luotXemFA.setKhachHang(khachHang);
                luotXemFA.setTgThem(new Date());
                luotXemFA.setLoai(loai);
                luotXemFA.setMinPrice(minPrice);
                luotXemFA.setSlTon(sumCTGByGiay);
                luotXemFA.setSoLuongDaBan(sl);
                luotXemFA.setMaMau(mauSac.getIdMau());
                luotXemFA.setHinhAnh(ctgViewModel.getHinhAnh());

                luotXemFAService.addNewLuotXem(luotXemFA);
            }
        }
    }

    private void checkKHLogged(Model model, KhachHang khachHang, Giay giay, MauSac mauSac){
        HanhViKhachHang hanhViKhachHang = (HanhViKhachHang) session.getAttribute("HVKHLogged");

        ChiTietHVKH chiTietHVKH = new ChiTietHVKH();

        chiTietHVKH.setId_Giay(giay.getIdGiay());
        chiTietHVKH.setId_Mau(mauSac.getIdMau());

        if (khachHang != null){

            LoaiHanhVi loaiHanhVi = loaiHVService.findByMaLHVDDN("DDN.HV01");
            chiTietHVKH.setLoaiHanhVi(loaiHanhVi);
            chiTietHVKH.setHanhViKhachHang(hanhViKhachHang);
            chiTietHVKH.setTgThem(new Date());

            cthvService.addnewcthv(chiTietHVKH);

            String fullName = khachHang.getHoTenKH();
            model.addAttribute("fullNameLogin", fullName);
            GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

            List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
            model.addAttribute("heartLogged", true);

            Integer sumProductInCart = listGHCTActive.size();
            model.addAttribute("sumProductInCart", sumProductInCart);
            model.addAttribute("buyNowAddCartLogged", true);

        }else {
            LoaiHanhVi loaiHanhVi = loaiHVService.findByMaLHVCDN("CDN.HV01");

            chiTietHVKH.setLoaiHanhVi(loaiHanhVi);
            cthvService.addnewcthv(chiTietHVKH);
            chiTietHVKH.setTgThem(new Date());

            model.addAttribute("messageLoginOrSignin", true);
        }
    }

}
