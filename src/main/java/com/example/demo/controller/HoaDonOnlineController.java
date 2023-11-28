package com.example.demo.controller;

import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.model.NhanVien;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.NhanVienService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/manage/bill/")
@Controller
public class HoaDonOnlineController {

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("online")
    private String manageBillOnline(Model model){
        model.addAttribute("reLoadPage", true);
        showData(model);
        return "manage/manage-bill-online";
    }

    @GetMapping("online/delivery/{idHD}")
    private String editBillOnline(Model model, @PathVariable UUID idHD){

        HoaDon billDelivery = hoaDonService.getOne(idHD);
        List<NhanVien> listNhanVienGiao = nhanVienService.findByTrangThai(1);

        model.addAttribute("listNhanVienGiao", listNhanVienGiao);
        model.addAttribute("billDelivery", billDelivery);
        model.addAttribute("showEditBillDelivery", true);


        showData(model);

        return "manage/manage-bill-online";
    }

    @PostMapping("online/delivery/confirm/{idHD}")
    private String confirmNVGH(Model model,  @PathVariable UUID idHD){
        UUID idNV = UUID.fromString(request.getParameter("idNhanVien"));

        NhanVien nhanVien = nhanVienService.getByIdNhanVien(idNV);
        HoaDon hoaDon = hoaDonService.getOne(idHD);

        hoaDon.setNhanVien(nhanVien);
        hoaDon.setTrangThai(2);
        hoaDon.setTgShip(new Date());

        hoaDonService.add(hoaDon);

        showData(model);

        model.addAttribute("showMessThanhCong", true);
        model.addAttribute("reLoadPage", true);

        return "manage/manage-bill-online";
    }



    private void showData(Model model){
        List<HoaDon> listHoaDonOnline = hoaDonService.listHoaDonOnline();
        List<HoaDon> listHoaDonOnlineQRCode = hoaDonService.listHoaDonOnlineAndHTTT(1);
        List<HoaDon> listHoaDonOnlineThanhToanNhanHang = hoaDonService.listHoaDonOnlineAndHTTT(0);
        List<HoaDon> listHoaDonOnlineChuaThanhToan = hoaDonService.listHoaDonOnlineAndTrangThai(0);
        List<HoaDon> listHoaDonOnlineDaThanhToan = hoaDonService.listHoaDonOnlineAndTrangThai(1);
        List<HoaDon> listHoaDonOnlineDangGiao = hoaDonService.listHoaDonOnlineAndTrangThai(2);
        List<HoaDon> listHoaDonOnlineDaNhan = hoaDonService.listHoaDonOnlineAndTrangThai(3);
        List<HoaDon> listHoaDonOnlineHuy = hoaDonService.listHoaDonOnlineAndTrangThai(4);
        List<HoaDon> listHoaDonOnlineDaHoan = hoaDonService.listHoaDonOnlineAndTrangThai(5);


        double totalAmount = listHoaDonOnline.stream()
                .mapToDouble(HoaDon::getTongTienDG)
                .sum();

        int sumBillOnline = listHoaDonOnline.size();
        int sumBillQRCode = listHoaDonOnlineQRCode.size();
        int sumQuantityDelivery = listHoaDonOnlineThanhToanNhanHang.size();
        int hoaDonChuaThanhToan = listHoaDonOnlineChuaThanhToan.size();
        int hoaDonDaThanhToan =  listHoaDonOnlineDaThanhToan.size();
        int hoaDonDangGiao = listHoaDonOnlineDangGiao.size();
        int hoaDonHuy = listHoaDonOnlineHuy.size();
        int hoaDonDaNhan = listHoaDonOnlineDaNhan.size();
        int hoaDonDaHoan = listHoaDonOnlineDaHoan.size();

        List<HoaDon> listHoaDonOnlineGiaoHang = new ArrayList<>();

        for (HoaDon bill : listHoaDonOnlineDaThanhToan) {
            listHoaDonOnlineGiaoHang.add(bill);
        }

        for (HoaDon bill: listHoaDonOnlineDangGiao) {
            listHoaDonOnlineGiaoHang.add(bill);
        }

        model.addAttribute("sumBillOnline", sumBillOnline);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("sumQuantityQRCode", sumBillQRCode);
        model.addAttribute("sumQuantityDelivery", sumQuantityDelivery);
        model.addAttribute("hoaDonChuaThanhToan", hoaDonChuaThanhToan);
        model.addAttribute("hoaDonDaThanhToan", hoaDonDaThanhToan);
        model.addAttribute("hoaDonDangGiao", hoaDonDangGiao);
        model.addAttribute("hoaDonHuy", hoaDonHuy);
        model.addAttribute("hoaDonDaNhan", hoaDonDaNhan);
        model.addAttribute("hoaDonDaHoan", hoaDonDaHoan);

        model.addAttribute("listHoaDonOnline", listHoaDonOnline);
        model.addAttribute("listHoaDonOnlineChuaThanhToan", listHoaDonOnlineChuaThanhToan);
        model.addAttribute("listHoaDonOnlineGiaoHang", listHoaDonOnlineGiaoHang);
    }
}
