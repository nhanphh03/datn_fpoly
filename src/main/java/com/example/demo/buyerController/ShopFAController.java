package com.example.demo.buyerController;

import com.example.demo.model.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/buyer")
public class ShopFAController {

    @Autowired
    private HttpSession session;

    @Autowired
    private GHCTService ghctService;

    @Autowired
    private HangService hangService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private LuotXemFAService luotXemFAService;

    @Autowired
    private GiayService giayService;

    @GetMapping("/fa&recently")
    private String getShopFA(Model model){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());

        showDataBuyerShop(model);

        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;


        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        Integer sumProductInCart = listGHCTActive.size();
        model.addAttribute("sumProductInCart", sumProductInCart);

        List<LuotXemFA> listRencently = luotXemFAService.getAllActiveByFAOrRV(khachHang, 1);

        List<LuotXemFA> listFA = luotXemFAService.getAllActiveByFAOrRV(khachHang, 0);

        model.addAttribute("listRencently", listRencently);

        model.addAttribute("listFA", listFA);



        //model.addAttribute("listCartDetail", listGHCTActive);


        return "/online/shopFA";
    }

    @GetMapping("/unheart/{idGiay}/{idMau}")
    private String unToHeart(Model model,@PathVariable UUID idGiay, @PathVariable UUID idMau){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        Giay giay = giayService.getByIdGiay(idGiay);
        UUID idMau2= null;
        if (giay == null){
            giay = giayService.getByIdGiay(idMau);
            idMau2=idGiay;
        }else{
            idMau2=idMau;
        }

        LuotXemFA luotXemFA = luotXemFAService.checkLuotXemOrFA(khachHang, giay, 0, idMau2);

        luotXemFA.setTgSua(new Date());
        luotXemFA.setTrangThai(0);
        luotXemFAService.addNewLuotXem(luotXemFA);

        return "redirect:/buyer/fa&recently";
    }

    private void showDataBuyerShop(Model model){
        List<Hang> listHang = hangService.getAllActive();
        model.addAttribute("listBrand", listHang);

        List<Size> listSize = sizeService.getAllSizeActiveOrderByName();
        model.addAttribute("listSize", listSize);

        List<MauSac> listColor = mauSacService.getMauSacActive();
        model.addAttribute("listColor", listColor);
    }

    private void Sub (String xxx){
        String xxxx = "038203023900||Phạm Hữu Nhân|19092003|Nam|Thôn Phú Sơn, Các Sơn, Nghi Sơn, Thanh Hóa|11032021";

        int firstDelimiterIndex = xxxx.indexOf("||");

        if (firstDelimiterIndex != -1) {
            String id = xxxx.substring(0, firstDelimiterIndex);
            String remainingData = xxxx.substring(firstDelimiterIndex + 2);

            String[] parts = remainingData.split("\\|");

            if (parts.length == 5) {
                String name = parts[0];
                String birthdate = parts[1];
                String gender = parts[2];
                String address = parts[3];
                String dateCreated = parts[4];

                // Thực hiện xử lý với các phần được trích xuất từ chuỗi ở đây

                System.out.println("id: " + id + "name: " + name + "Birthdate: " + birthdate + "Gender: " + gender + "Address: " + address + "Date Created: " + dateCreated);
            } else {
                System.out.println("Chuỗi không hợp lệ");
            }
        } else {
            System.out.println("Chuỗi không hợp lệ");
        }
    }


}
