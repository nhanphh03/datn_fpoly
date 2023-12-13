package com.example.demo.buyerController;

import com.example.demo.model.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private DiaChiKHService diaChiKHService;

    @Autowired
    private GHCTService ghctService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoaiKhuyenMaiService loaiKhuyenMaiService;

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @Autowired
    private ShippingFeeService shippingFeeService;


    @PostMapping("/checkout")
    private String checkOutCart(Model model, @RequestParam("selectedProducts") List<UUID> selectedProductIds){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;

        DiaChiKH diaChiKHDefault = diaChiKHService.findDCKHDefaulByKhachHang(khachHang);
        LoaiKhuyenMai loaiKhuyenMaiBill = loaiKhuyenMaiService.findByMaLKM("LKM01");
        LoaiKhuyenMai loaiKhuyenMaiShip = loaiKhuyenMaiService.findByMaLKM("LKM03");

        List<DiaChiKH> diaChiKHList = diaChiKHService.findbyKhachHangAndLoaiAndTrangThai(khachHang, false, 1);
        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        List<KhuyenMai> khuyenMaiListShipping = khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMaiShip);
        List<KhuyenMai> khuyenMaiListBill =  khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMaiBill);

        List<HoaDonChiTiet> listHDCTCheckOut = new ArrayList<>();

        Integer sumProductInCart = listGHCTActive.size();

        Date date = new Date();
        HoaDon hoaDon = new HoaDon();

        String maHD = "HD_" + khachHang.getMaKH() + "_" + date.getDate() + generateRandomNumbers();

        hoaDon.setKhachHang(khachHang);
        hoaDon.setMaHD(maHD);
        hoaDon.setLoaiHD(0);
        hoaDon.setTgTao(date);
        hoaDon.setTrangThai(6);

        if (diaChiKHDefault != null){
            hoaDon.setDiaChiNguoiNhan(diaChiKHDefault.getDiaChiChiTiet());
            hoaDon.setSdtNguoiNhan(diaChiKHDefault.getSdtNguoiNhan());
            hoaDon.setTenNguoiNhan(diaChiKHDefault.getTenNguoiNhan());
            session.removeAttribute("diaChiGiaoHang");
            session.setAttribute("diaChiGiaoHang", diaChiKHDefault);
        }

        hoaDonService.add(hoaDon);

        for (UUID x: selectedProductIds) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            GioHangChiTiet gioHangChiTiet = ghctService.findByCTGActiveAndKhachHangAndTrangThai(giayChiTietService.getByIdChiTietGiay(x), gioHang);

            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setChiTietGiay(giayChiTietService.getByIdChiTietGiay(x));
            hoaDonChiTiet.setDonGia(gioHangChiTiet.getDonGia());
            hoaDonChiTiet.setSoLuong(gioHangChiTiet.getSoLuong());
            hoaDonChiTiet.setDonGiaTruocGiam(gioHangChiTiet.getSoLuong() * gioHangChiTiet.getChiTietGiay().getSoTienTruocKhiGiam());
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

        if(diaChiKHDefault == null){
            model.addAttribute("addNewAddressNulll", true);
            model.addAttribute("addNewAddressNull", true);
        }else{
            model.addAttribute("diaChiKHDefault", diaChiKHDefault);
            model.addAttribute("addNewAddressNotNull", true);
            model.addAttribute("listAddressKH", diaChiKHList);
        }

        hoaDon.setTongTien(total);
        hoaDon.setTongSP(sumQuantity);
        hoaDon.setTrangThaiHoan(8);

        hoaDonService.add(hoaDon);

        model.addAttribute("sumQuantity", sumQuantity);
        model.addAttribute("total", total);
        model.addAttribute("listProductCheckOut", listHDCTCheckOut);
        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());
        model.addAttribute("sumProductInCart", sumProductInCart);
        model.addAttribute("listVoucherBill", khuyenMaiListBill);
        model.addAttribute("listVoucherShipping", khuyenMaiListShipping);
        model.addAttribute("toTalOder", total);

        model.addAttribute("tienGiamVoucherShip", 0);
        model.addAttribute("tienGiamVoucherBill", 0);

        if (diaChiKHDefault != null){
            Double shippingFee = shippingFeeService.calculatorShippingFee(hoaDon, 25000.0);
            model.addAttribute("shippingFee", shippingFee);
            model.addAttribute("toTalOder", total  + shippingFee );
            model.addAttribute("tongTienDaGiamVoucherShip", total + shippingFee);

        }else{
            model.addAttribute("tongTienDaGiamVoucherShip", total);
        }

        session.removeAttribute("hoaDonTaoMoi");

        session.setAttribute("hoaDonTaoMoi", hoaDon);

        return "online/checkout";
    }

    @PostMapping("/checkout/add/address")
    public String addNewAddressPlaceOrder(Model model,@RequestParam(name = "defaultSelected", defaultValue = "false") boolean defaultSelected){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        HoaDon hoaDon = (HoaDon) session.getAttribute("hoaDonTaoMoi") ;
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;
        KhuyenMai khuyenMaiGiaoHang = (KhuyenMai) session.getAttribute("khuyenMaiGiaoHang");
        KhuyenMai khuyenMaiHoaDon = (KhuyenMai) session.getAttribute("khuyenMaiHoaDon");

        LoaiKhuyenMai loaiKhuyenMaiBill = loaiKhuyenMaiService.findByMaLKM("LKM01");
        LoaiKhuyenMai loaiKhuyenMaiShip = loaiKhuyenMaiService.findByMaLKM("LKM03");

        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietService.findByHoaDon(hoaDon);
        List<DiaChiKH> diaChiKHList = diaChiKHService.findbyKhachHangAndLoaiAndTrangThai(khachHang, false, 1);
        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        List<KhuyenMai> khuyenMaiListShipping = khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMaiShip);
        List<KhuyenMai> khuyenMaiListBill =  khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMaiBill);

        Integer sumProductInCart = listGHCTActive.size();
        Date date = new Date();

        if (defaultSelected){
            for (DiaChiKH xxx: diaChiKHService.getAllDiaChiKH()) {
                xxx.setLoai(false);
                diaChiKHService.save(xxx);
            }
        }

        String nameAddress = request.getParameter("nameAddress");
        String fullName = request.getParameter("fullName");
        String phoneAddress = request.getParameter("phoneAddress");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String description = request.getParameter("description");
        String diaChiChiTiet = description + ", " + ward + ", " + district + ", " + city;

        DiaChiKH diaChiKH = new DiaChiKH();

        diaChiKH.setDiaChiChiTiet(diaChiChiTiet);
        diaChiKH.setMoTa(description);
        diaChiKH.setKhachHang(khachHang);
        diaChiKH.setTrangThai(1);
        diaChiKH.setMaDC( "DC_" + khachHang.getMaKH() + date.getDay() + generateRandomNumbers());
        diaChiKH.setSdtNguoiNhan(phoneAddress);
        diaChiKH.setQuanHuyen(district);
        diaChiKH.setTenDC(nameAddress);
        diaChiKH.setTinhTP(city);
        diaChiKH.setTenNguoiNhan(fullName);
        diaChiKH.setXaPhuong(ward);
        diaChiKH.setTgThem(new Date());
        diaChiKH.setLoai(defaultSelected);

        diaChiKHService.save(diaChiKH);

        hoaDon.setDiaChiNguoiNhan(diaChiChiTiet);
        hoaDon.setTenNguoiNhan(fullName);
        hoaDon.setSdtNguoiNhan(phoneAddress);

        hoaDonService.add(hoaDon);

        int sumQuantity = hoaDonChiTietList.stream()
                .mapToInt(HoaDonChiTiet::getSoLuong)
                .sum();

        double total = hoaDonChiTietList.stream()
                .mapToDouble(HoaDonChiTiet::getDonGia)
                .sum();

        Double shippingFee = shippingFeeService.calculatorShippingFee(hoaDon, 25000.0);
        Double shippingVoucher = 0.0;
        if (khuyenMaiGiaoHang !=null){
            shippingVoucher = shippingFeeService.calculatorVoucherShipping(hoaDon, khuyenMaiGiaoHang, shippingFee);

        }
        Double voucherBill = 0.0;

        if(khuyenMaiHoaDon != null){
            voucherBill =  shippingFeeService.calculatorVoucherBill(hoaDon, khuyenMaiHoaDon);
            model.addAttribute("nameVoucher", khuyenMaiHoaDon.getTenKM());
        }

        model.addAttribute("sumQuantity", sumQuantity);
        model.addAttribute("total", total);
        model.addAttribute("diaChiKHDefault", diaChiKH);
        model.addAttribute("listProductCheckOut", hoaDonChiTietList);
        model.addAttribute("listAddressKH", diaChiKHList);
        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());
        model.addAttribute("sumProductInCart", sumProductInCart);
        model.addAttribute("addNewAddressNotNull", true);
        model.addAttribute("listVoucherBill", khuyenMaiListBill);
        model.addAttribute("listVoucherShipping", khuyenMaiListShipping);

        model.addAttribute("shippingFee", shippingFee);

        model.addAttribute("tienGiamVoucherShip", shippingVoucher);
        model.addAttribute("tienGiamVoucherBill", voucherBill);
        model.addAttribute("tongTienDaGiamVoucherShip", total + shippingFee - shippingVoucher);
        model.addAttribute("toTalOder", total - voucherBill + shippingFee - shippingVoucher);

        session.removeAttribute("diaChiGiaoHang");
        session.setAttribute("diaChiGiaoHang", diaChiKH);

        return "online/checkout";
    }

    @PostMapping("/checkout/change/address")
    private String changeAddressCheckOut(Model model){

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        HoaDon hoaDon = (HoaDon) session.getAttribute("hoaDonTaoMoi") ;
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;
        KhuyenMai khuyenMaiGiaoHang = (KhuyenMai) session.getAttribute("khuyenMaiGiaoHang");
        KhuyenMai khuyenMaiHoaDon = (KhuyenMai) session.getAttribute("khuyenMaiHoaDon");

        LoaiKhuyenMai loaiKhuyenMaiBill = loaiKhuyenMaiService.findByMaLKM("LKM01");
        LoaiKhuyenMai loaiKhuyenMaiShip = loaiKhuyenMaiService.findByMaLKM("LKM03");

        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietService.findByHoaDon(hoaDon);
        List<DiaChiKH> diaChiKHList = diaChiKHService.findbyKhachHangAndLoaiAndTrangThai(khachHang, false, 1);
        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        List<KhuyenMai> khuyenMaiListShipping = khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMaiShip);
        List<KhuyenMai> khuyenMaiListBill =  khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMaiBill);

        UUID idDCKH = UUID.fromString(request.getParameter("idDCKH"));
        DiaChiKH diaChiKHChange = diaChiKHService.findByIdDiaChiKH(idDCKH);

        int sumQuantity = hoaDonChiTietList.stream()
                .mapToInt(HoaDonChiTiet::getSoLuong)
                .sum();
        double total = hoaDonChiTietList.stream()
                .mapToDouble(HoaDonChiTiet::getDonGia)
                .sum();

//        TODO update HoaDon BEGIN
        hoaDon.setTenNguoiNhan(diaChiKHChange.getTenNguoiNhan());
        hoaDon.setSdtNguoiNhan(diaChiKHChange.getTenNguoiNhan());
        hoaDon.setDiaChiNguoiNhan(diaChiKHChange.getDiaChiChiTiet());
        hoaDonService.add(hoaDon);
//        TODO update HoaDon END

        Double shippingFee = shippingFeeService.calculatorShippingFee(hoaDon, 25000.0);
        Double shippingVoucher = 0.0;

        if (khuyenMaiGiaoHang !=null){
            shippingVoucher = shippingFeeService.calculatorVoucherShipping(hoaDon, khuyenMaiGiaoHang, shippingFee);

        }
        Double voucherBill = 0.0;

        if(khuyenMaiHoaDon != null){
            voucherBill =  shippingFeeService.calculatorVoucherBill(hoaDon, khuyenMaiHoaDon);
            model.addAttribute("nameVoucher", khuyenMaiHoaDon.getTenKM());
        }



//      TODO PASSING DATA BEGIN
        model.addAttribute("sumQuantity", sumQuantity);
        model.addAttribute("total", total);
        model.addAttribute("diaChiKHDefault", diaChiKHChange);
        model.addAttribute("listProductCheckOut", hoaDonChiTietList);
        model.addAttribute("listAddressKH", diaChiKHList);
        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());
        model.addAttribute("sumProductInCart", listGHCTActive.size());
        model.addAttribute("addNewAddressNotNull", true);
        model.addAttribute("listVoucherBill", khuyenMaiListBill);
        model.addAttribute("listVoucherShipping", khuyenMaiListShipping);
        model.addAttribute("shippingFee", shippingFee);

        model.addAttribute("tienGiamVoucherShip", shippingVoucher);
        model.addAttribute("tienGiamVoucherBill", voucherBill);
        model.addAttribute("tongTienDaGiamVoucherShip", total + shippingFee - shippingVoucher);
        model.addAttribute("toTalOder", total - voucherBill + shippingFee - shippingVoucher);
//      TODO PASSING DATA END

        session.removeAttribute("diaChiGiaoHang");
        session.setAttribute("diaChiGiaoHang", diaChiKHChange);

        return "online/checkout";
    }

    @PostMapping("/checkout/change/voucher/bill")
    private String changeVoucherBillCheckOut(Model model){

        UUID idVoucherBill = UUID.fromString(request.getParameter("selectVoucherBill"));

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        HoaDon hoaDon = (HoaDon) session.getAttribute("hoaDonTaoMoi") ;
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;
        DiaChiKH diaChiKH = (DiaChiKH) session.getAttribute("diaChiGiaoHang");
        KhuyenMai khuyenMaiGiaoHang = (KhuyenMai) session.getAttribute("khuyenMaiGiaoHang");

        KhuyenMai khuyenMai = khuyenMaiService.findByID(idVoucherBill);
        LoaiKhuyenMai loaiKhuyenMaiBill = loaiKhuyenMaiService.findByMaLKM("LKM01");
        LoaiKhuyenMai loaiKhuyenMaiShip = loaiKhuyenMaiService.findByMaLKM("LKM03");

        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietService.findByHoaDon(hoaDon);
        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        List<DiaChiKH> diaChiKHList = diaChiKHService.findbyKhachHangAndLoaiAndTrangThai(khachHang, false, 1);
        List<KhuyenMai> khuyenMaiListShipping = khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMaiShip);
        List<KhuyenMai> khuyenMaiListBill =  khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMaiBill);

        Double billVoucher = 0.0;

        if ( hoaDon.getTongTien() < khuyenMai.getDieuKienKMBill() ){
            model.addAttribute("noticaAddVoucher", true);
            model.addAttribute("addErr", true);
            billVoucher = 0.0;
        }else{
            Double voucherBillFee = shippingFeeService.calculatorVoucherBill(hoaDon, khuyenMai);
            model.addAttribute("tienGiamVoucherBill", voucherBillFee);
            model.addAttribute("noticaAddVoucher", true);
            model.addAttribute("nameVoucher", khuyenMai.getTenKM());
            model.addAttribute("addSuccess", true);

            billVoucher = shippingFeeService.calculatorVoucherBill(hoaDon, khuyenMai);
            session.removeAttribute("khuyenMaiHoaDon");
            session.setAttribute("khuyenMaiHoaDon", khuyenMai);
        }

        int sumQuantity = hoaDonChiTietList.stream()
                .mapToInt(HoaDonChiTiet::getSoLuong)
                .sum();
        double total = hoaDonChiTietList.stream()
                .mapToDouble(HoaDonChiTiet::getDonGia)
                .sum();

//TODO update HoaDon BEGIN
        hoaDonService.add(hoaDon);
//TODO update HoaDon END

        Double shippingFee = shippingFeeService.calculatorShippingFee(hoaDon, 25000.0);

        Double shippingVoucher = 0.0;
        if (khuyenMaiGiaoHang !=null){
            shippingVoucher = shippingFeeService.calculatorVoucherShipping(hoaDon, khuyenMaiGiaoHang, shippingFee);

        }

//      TODO PASSING DATA BEGIN
        model.addAttribute("sumQuantity", sumQuantity);
        model.addAttribute("total", total);
        model.addAttribute("diaChiKHDefault", diaChiKH);
        model.addAttribute("listProductCheckOut", hoaDonChiTietList);
        model.addAttribute("listAddressKH", diaChiKHList);
        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());
        model.addAttribute("sumProductInCart", listGHCTActive.size());
        model.addAttribute("addNewAddressNotNull", true);
        model.addAttribute("shippingFee", shippingFee);
        model.addAttribute("tienGiamVoucherShip", shippingVoucher);
        model.addAttribute("tienGiamVoucherBill", billVoucher);

        model.addAttribute("listVoucherBill", khuyenMaiListBill);
        model.addAttribute("listVoucherShipping", khuyenMaiListShipping);

        model.addAttribute("tongTienDaGiamVoucherShip", total + shippingFee - shippingVoucher);
        model.addAttribute("toTalOder", total - billVoucher + shippingFee - shippingVoucher);

//      TODO PASSING DATA END

        return "online/checkout";
    }

    @PostMapping("/checkout/change/voucher/shipping")
    private String changeVoucherShippingCheckOut(Model model){
        UUID idVoucherBill = UUID.fromString(request.getParameter("selectVoucherShipping"));

        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        HoaDon hoaDon = (HoaDon) session.getAttribute("hoaDonTaoMoi") ;
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;
        DiaChiKH diaChiKH = (DiaChiKH) session.getAttribute("diaChiGiaoHang");
        KhuyenMai khuyenMaiHoaDon = (KhuyenMai) session.getAttribute("khuyenMaiHoaDon");

        KhuyenMai khuyenMai = khuyenMaiService.findByID(idVoucherBill);
        LoaiKhuyenMai loaiKhuyenMaiBill = loaiKhuyenMaiService.findByMaLKM("LKM01");
        LoaiKhuyenMai loaiKhuyenMaiShip = loaiKhuyenMaiService.findByMaLKM("LKM03");

        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietService.findByHoaDon(hoaDon);
        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        List<DiaChiKH> diaChiKHList = diaChiKHService.findbyKhachHangAndLoaiAndTrangThai(khachHang, false, 1);
        List<KhuyenMai> khuyenMaiListShipping = khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMaiShip);
        List<KhuyenMai> khuyenMaiListBill =  khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMaiBill);

        int sumQuantity = hoaDonChiTietList.stream()
                .mapToInt(HoaDonChiTiet::getSoLuong)
                .sum();
        double total = hoaDonChiTietList.stream()
                .mapToDouble(HoaDonChiTiet::getDonGia)
                .sum();

//        TODO update HoaDon BEGIN
        hoaDonService.add(hoaDon);
//        TODO update HoaDon END

        Double shippingFee = shippingFeeService.calculatorShippingFee(hoaDon, 25000.0);

        Double voucherShipFee = 0.0;

        if ( hoaDon.getTongTien() < khuyenMai.getDieuKienKMBill() ){
            model.addAttribute("noticaAddVoucher", true);
            model.addAttribute("addErr", true);
        }else{
            voucherShipFee = shippingFeeService.calculatorVoucherShipping(hoaDon, khuyenMai, shippingFee);
            model.addAttribute("noticaAddVoucher", true);
            model.addAttribute("addSuccess", true);

            session.removeAttribute("khuyenMaiGiaoHang");
            session.setAttribute("khuyenMaiGiaoHang", khuyenMai);
        }

        Double voucherBill = 0.0;

        if(khuyenMaiHoaDon != null){
            voucherBill =  shippingFeeService.calculatorVoucherBill(hoaDon, khuyenMaiHoaDon);
            model.addAttribute("nameVoucher", khuyenMaiHoaDon.getTenKM());
        }

//      TODO PASSING DATA BEGIN

        model.addAttribute("sumQuantity", sumQuantity);
        model.addAttribute("total", total);
        model.addAttribute("diaChiKHDefault", diaChiKH);
        model.addAttribute("listProductCheckOut", hoaDonChiTietList);
        model.addAttribute("listAddressKH", diaChiKHList);
        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());
        model.addAttribute("sumProductInCart", listGHCTActive.size());
        model.addAttribute("addNewAddressNotNull", true);
        model.addAttribute("listVoucherBill", khuyenMaiListBill);
        model.addAttribute("listVoucherShipping", khuyenMaiListShipping);
        model.addAttribute("shippingFee", shippingFee);
        model.addAttribute("tienGiamVoucherShip", voucherShipFee);
        model.addAttribute("tienGiamVoucherBill", voucherBill);
        model.addAttribute("tongTienDaGiamVoucherShip", total + shippingFee - voucherShipFee);
        model.addAttribute("toTalOder", total - voucherBill + shippingFee - voucherShipFee);

//      TODO PASSING DATA END

        return "online/checkout";
    }

    @PostMapping("/checkout/placeoder")
    public String placeOrder(Model model){

        HoaDon hoaDon = (HoaDon) session.getAttribute("hoaDonTaoMoi");

        String hinhThucThanhToan = request.getParameter("hinhThucThanhToan");
        String loiNhan = request.getParameter("loiNhan");


        KhuyenMai khuyenMaiGiaoHang = (KhuyenMai) session.getAttribute("khuyenMaiGiaoHang");
        KhuyenMai khuyenMaiHoaDon = (KhuyenMai) session.getAttribute("khuyenMaiHoaDon");

        Double shippingFee = shippingFeeService.calculatorShippingFee(hoaDon, 25000.0);

        Double tienGiamGiaShip = 0.0;
        Double tienGiamGiaHoaDon = 0.0;

        hoaDon.setTienShip(shippingFee);

        if (khuyenMaiGiaoHang !=null){
            tienGiamGiaShip = shippingFeeService.calculatorVoucherShipping(hoaDon, khuyenMaiGiaoHang, shippingFee);
        }

        if(khuyenMaiHoaDon != null){
            tienGiamGiaHoaDon =  shippingFeeService.calculatorVoucherBill(hoaDon, khuyenMaiHoaDon);
        }

        hoaDon.setGiamGiaShip(tienGiamGiaShip);
        hoaDon.setGiamGiaHoaDon(tienGiamGiaHoaDon);
        hoaDon.setTienShip(shippingFee);
        hoaDon.setLoiNhan(loiNhan);
        hoaDon.setTongTienDG(hoaDon.getTongTien() + shippingFee - tienGiamGiaHoaDon - tienGiamGiaShip);

        if (hinhThucThanhToan.equals("QRCodeBanking")){
            hoaDon.setHinhThucThanhToan(1);
            hoaDon.setTrangThai(0);
            hoaDonService.add(hoaDon);

            UserForm(model);

            model.addAttribute("maHD", hoaDon.getMaHD());
            model.addAttribute("toTalOder", hoaDon.getTongTienDG());
            model.addAttribute("thongTinThanhToan", true);

            model.addAttribute("addNewAddressNull", true);
            model.addAttribute("addNewAddressNulll", false);



            return "online/checkout";
        }else{
            hoaDon.setHinhThucThanhToan(0);
            hoaDon.setTrangThai(1);
            hoaDonService.add(hoaDon);

            UserForm(model);

            return "redirect:/buyer/purchase";
        }


    }

    private void UserForm(Model model){
        KhachHang khachHang = (KhachHang) session.getAttribute("KhachHangLogin");
        GioHang gioHang = (GioHang) session.getAttribute("GHLogged") ;
        model.addAttribute("fullNameLogin", khachHang.getHoTenKH());

        List<GioHangChiTiet> listGHCTActive = ghctService.findByGHActive(gioHang);
        Integer sumProductInCart = listGHCTActive.size();
        model.addAttribute("sumProductInCart", sumProductInCart);

        session.removeAttribute("khuyenMaiGiaoHang");
        session.removeAttribute("khuyenMaiHoaDon");
        session.removeAttribute("hoaDonTaoMoi");
        session.removeAttribute("diaChiGiaoHang");
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
