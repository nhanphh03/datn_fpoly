package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/manage/bill/")
@Controller
public class HoaDonOnlineController {

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private GiaoHangService giaoHangService;

    @Autowired
    private GiayChiTietService giayChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LSThanhToanService lsThanhToanService;

    @Autowired
    private PhieuTraHangServices  phieuTraHangServices;

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

        Date date = new Date();
        NhanVien nhanVien = nhanVienService.getByIdNhanVien(idNV);
        HoaDon hoaDon = hoaDonService.getOne(idHD);

        hoaDon.setNhanVien(nhanVien);
        hoaDon.setTrangThai(2);
        hoaDon.setTgShip(date);

        hoaDonService.add(hoaDon);

        GiaoHang giaoHang = new GiaoHang();
        giaoHang.setHoaDon(hoaDon);
        giaoHang.setTrangThai(1);
        giaoHang.setThoiGian(new Date());
        giaoHang.setNoiDung("Xác nhận nhân viên giao hàng");
        giaoHang.setViTri("Xác nhận nhân viên giao hàng");

        giaoHangService.saveGiaoHang(giaoHang);

        showData(model);

        model.addAttribute("showMessThanhCong", true);
        model.addAttribute("reLoadPage", true);

        return "manage/manage-bill-online";
    }

    @PostMapping("online/confirm/pay/{idHD}")
    private String confirmPayBill(Model model, @PathVariable UUID idHD){

        Date date = new Date();

        String noiDungThanhToan = request.getParameter("noiDungThanhToan");

        HoaDon hoaDonThanhToan = hoaDonService.getOne(idHD);

        String thoiGianThanhToan = request.getParameter("thoiGianThanhToan");

        Date thoiGianThanhToanFormat = new Date();

        String pattern = "HH:mm yyyy-MM-dd";


        // Sử dụng SimpleDateFormat để chuyển đổi
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            thoiGianThanhToanFormat = inputFormat.parse(thoiGianThanhToan);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String maLSTT = "HTT0" + date.getDay() + generateRandomNumbers();

        LichSuThanhToan lichSuThanhToan = new LichSuThanhToan();

        lichSuThanhToan.setHoaDon(hoaDonThanhToan);
        lichSuThanhToan.setTgThanhToan(new Date());
        lichSuThanhToan.setSoTienThanhToan(hoaDonThanhToan.getTongTienDG());
        lichSuThanhToan.setTrangThai(1);
        lichSuThanhToan.setKhachHang(hoaDonThanhToan.getKhachHang());
        lichSuThanhToan.setMaLSTT(maLSTT);
        lichSuThanhToan.setLoaiTT(0); //Thanh toán online
        lichSuThanhToan.setNoiDungThanhToan(noiDungThanhToan);

        lsThanhToanService.addLSTT(lichSuThanhToan);

        hoaDonThanhToan.setTrangThai(1);
        hoaDonThanhToan.setTgThanhToan(thoiGianThanhToanFormat);
        hoaDonService.add(hoaDonThanhToan);

        return "redirect:/manage/bill/online";
    }

    @GetMapping("pay")
    private String payHistory(Model model){

        List<LichSuThanhToan> lichSuThanhToanList = lsThanhToanService.getAllLSTT();

        model.addAttribute("lichSuThanhToanList", lichSuThanhToanList);

        return "manage/manage-pay";
    }

    @GetMapping("/online/refund/detail/{idHD}")
    private String pageDetailBillRefund(Model model, @PathVariable UUID idHD){

        HoaDon hoaDon = hoaDonService.getOne(idHD);

        UUID idHDOld = hoaDon.getIdHDOld();

        HoaDon hoaDonOld = hoaDonService.getOne(idHDOld);

        PhieuTraHang phieuTraHang = phieuTraHangServices.findByHoaDon(hoaDon);
        System.out.println(phieuTraHang.getLyDoHoanHang());

        model.addAttribute("hoaDon", hoaDon);

        model.addAttribute("phieuTraHang", phieuTraHang);

        model.addAttribute("hoaDonOld", hoaDonOld);

        return "manage/detailBillRefund";
    }

    @PostMapping("/online/refund/detail/dismist/accept/{idHD}")
    private String dismistOrAcceptBillRefund(Model model,
                                     @PathVariable UUID idHD,
                                     @RequestParam("idCTGHoanHang") List<String> listIdCTGHoanHang,
                                     @RequestParam("lyDoTuChoi") String lyDoTuChoi,
                                     @RequestParam(name = "accept", required = false) String accept,
                                     @RequestParam(name = "dismist", required = false) String dismist){

        if (accept != null) {
            List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietService.findByHoaDon(hoaDonService.getOne(idHD));

            HoaDon hoaDon = hoaDonService.getOne(idHD);
            HoaDon hoaDonOld = hoaDonService.getOne(hoaDon.getIdHDOld());

            for (String x: listIdCTGHoanHang) {
                HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idHD, UUID.fromString(x));

                hoaDonChiTiet.setTrangThai(2);

                hoaDonChiTietService.add(hoaDonChiTiet);
                hoaDonChiTietList.remove(hoaDonChiTiet);

            }
            for ( HoaDonChiTiet x: hoaDonChiTietList) {
                x.setMoTa(lyDoTuChoi);
                x.setTrangThai(1);
                hoaDonChiTietService.add(x);
            }

            PhieuTraHang phieuTraHang = phieuTraHangServices.findByHoaDon(hoaDonService.getOne(idHD));
            phieuTraHang.setTrangThai(1);

            hoaDonOld.setMaHDOld("1");
            hoaDonService.add(hoaDonOld);

            phieuTraHang.setTgXacNhan(new Date());
            phieuTraHangServices.savePTH(phieuTraHang);

        } else if (dismist != null) {
            List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietService.findByHoaDon(hoaDonService.getOne(idHD));

            HoaDon hoaDon = hoaDonService.getOne(idHD);
            HoaDon hoaDonOld = hoaDonService.getOne(hoaDon.getIdHDOld());

            for (String x: listIdCTGHoanHang) {
                HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idHD, UUID.fromString(x));
                hoaDonChiTiet.setTrangThai(1);
                hoaDonChiTiet.setMoTa(lyDoTuChoi);
                hoaDonChiTietList.remove(hoaDonChiTiet);
            }

            for ( HoaDonChiTiet x: hoaDonChiTietList) {
                x.setTrangThai(2);
                hoaDonChiTietService.add(x);
            }

            PhieuTraHang phieuTraHang = phieuTraHangServices.findByHoaDon(hoaDonService.getOne(idHD));
            if (hoaDonChiTietList.size() == 0){
                phieuTraHang.setTrangThai(2);

                hoaDonOld.setMaHDOld("2");
                hoaDonService.add(hoaDonOld);
            }

            hoaDonOld.setMaHDOld("1");
            hoaDonService.add(hoaDonOld);

            phieuTraHang.setTgXacNhan(new Date());
            phieuTraHangServices.savePTH(phieuTraHang);

        }
        return"redirect:/manage/bill/online";
    }

    private void showData(Model model){

        List<HoaDon> listHoaDonOnline = hoaDonService.listAllHoaDonOnline();
        List<HoaDon> listHoaDonOnlineQRCode = hoaDonService.listHoaDonOnlineAndHTTT(1);
        List<HoaDon> listHoaDonOnlineThanhToanNhanHang = hoaDonService.listHoaDonOnlineAndHTTT(0);


        List<PhieuTraHang> phieuTraHangList = phieuTraHangServices.getAll();




        List<HoaDon> listHoaDonOnlineDaThanhToan = hoaDonService.listHoaDonOnlineAndTrangThai(1);

        int soLuongDaThanhToan = hoaDonService.listHoaDonOnlineAndHTTTAndTrangThai(0 , 3).size() +
                hoaDonService.listHoaDonOnlineAndHTTTAndTrangThai(1, 1).size() ;

        int soLuongChuaThanhToan = hoaDonService.listHoaDonOnlineAndHTTTAndTrangThai(0 , 1).size() +
                hoaDonService.listHoaDonOnlineAndHTTTAndTrangThai(0, 2).size() +
                hoaDonService.listHoaDonOnlineAndHTTTAndTrangThai(1, 0).size();

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
        model.addAttribute("hoaDonChuaThanhToan", soLuongChuaThanhToan);
        model.addAttribute("hoaDonDaThanhToan", soLuongDaThanhToan);
        model.addAttribute("hoaDonDangGiao", hoaDonDangGiao);
        model.addAttribute("hoaDonHuy", hoaDonHuy);
        model.addAttribute("hoaDonDaNhan", hoaDonDaNhan);
        model.addAttribute("hoaDonDaHoan", hoaDonDaHoan);

        model.addAttribute("listBillRefund", phieuTraHangList);
        model.addAttribute("listHoaDonOnline", listHoaDonOnline);
        model.addAttribute("listHoaDonOnlineQRCode", listHoaDonOnlineQRCode);
        model.addAttribute("listHoaDonOnlineGiaoHang", listHoaDonOnlineGiaoHang);
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
