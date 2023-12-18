package com.example.demo.controller;

import com.beust.ah.A;
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

    @Autowired
    private ViTriDonHangServices viTriDonHangServices;

    @Autowired
    private ThongBaoServices thongBaoServices;

    @GetMapping("online")
    private String manageBillOnline(Model model){
        model.addAttribute("reLoadPage", true);
        showData(model);
        showTab1(model);
        showThongBao(model);

        return "manage/manage-bill-online";
    }

    @GetMapping("online/delivery/{idHD}")
    private String editBillOnline(Model model, @PathVariable UUID idHD){

        HoaDon billDelivery = hoaDonService.getOne(idHD);
        List<NhanVien> listNhanVienGiao = nhanVienService.findByTrangThai(1);

        model.addAttribute("listNhanVienGiao", listNhanVienGiao);
        model.addAttribute("billDelivery", billDelivery);
        model.addAttribute("showEditBillDelivery", true);

        showTab3(model);

        showData(model);
        showThongBao(model);

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

        String maGH = "GH_" + hoaDon.getMaHD() ;

        GiaoHang giaoHang = new GiaoHang();
        giaoHang.setHoaDon(hoaDon);
        giaoHang.setTrangThai(1);
        giaoHang.setMaGiaoHang(maGH);
        giaoHang.setThoiGian(new Date());
        giaoHang.setPhiGiaoHang(0.0);
        giaoHang.setNoiDung("Xác nhận nhân viên giao hàng");
        giaoHangService.saveGiaoHang(giaoHang);

        hoaDon.setGiaoHang(giaoHang);
        hoaDonService.add(hoaDon);

        ViTriDonHang viTriDonHang = new ViTriDonHang();
        viTriDonHang.setGiaoHang(giaoHang);
        viTriDonHang.setViTri("Xác nhận nhân viên giao hàng");
        viTriDonHang.setTrangThai(1);
        viTriDonHang.setThoiGian(new Date());
        viTriDonHangServices.addViTriDonHang(viTriDonHang);

        showData(model);

        model.addAttribute("showMessThanhCong", true);
        model.addAttribute("reLoadPage", true);
        showData(model);
        showTab3(model);
        showThongBao(model);

        return "manage/manage-bill-online";
    }

    @PostMapping("online/confirm/pay/{idHD}")
    private String confirmPayBill(Model model, @PathVariable UUID idHD){

        Date date = new Date();

        String noiDungThanhToan = request.getParameter("noiDungThanhToan");

        HoaDon hoaDonThanhToan = hoaDonService.getOne(idHD);

        String thoiGianThanhToan = request.getParameter("thoiGianThanhToan");

        Date thoiGianThanhToanFormat = new Date();

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

        showData(model);
        showTab2(model);
        showThongBao(model);

        model.addAttribute("reLoadPage", true);

        return "manage/manage-bill-online";
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
        showThongBao(model);


        return "manage/detailBillRefund";
    }

    @PostMapping("/online/refund/detail/dismist/accept/{idHD}")
    private String dismistOrAcceptBillRefund(Model model,
                                     @PathVariable UUID idHD,
                                     @RequestParam("idCTGHoanHang") List<String> listIdCTGHoanHang,
                                     @RequestParam("lyDoTuChoi") String lyDoTuChoi,
                                     @RequestParam(name = "accept", required = false) String accept,
                                     @RequestParam(name = "dismist", required = false) String dismist,
                                     @RequestParam(name = "inputCheckAll", defaultValue = "false") boolean inputCheckAll){

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


            hoaDonOld.setTrangThaiHoan(1);

            ThongBaoKhachHang thongBaoKhachHang = new ThongBaoKhachHang();
            thongBaoKhachHang.setKhachHang(hoaDon.getKhachHang());
            thongBaoKhachHang.setTgTB(new Date());
            thongBaoKhachHang.setHoaDon(hoaDon);
            thongBaoKhachHang.setTrangThai(0);
            thongBaoKhachHang.setNoiDungTB("Vui lòng xác nhận đơn hàng hoàn");


            if (inputCheckAll) {
                hoaDonOld.setTrangThaiHoan(4);
                phieuTraHang.setTrangThai(4);
                hoaDon.setTrangThaiHoan(4);
                thongBaoKhachHang.setNoiDungTB("Đơn hàng hoàn đã được xác nhận");
            }

            thongBaoServices.addThongBao(thongBaoKhachHang);
            hoaDonService.add(hoaDon);
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

                hoaDon.setTrangThaiHoan(4);
                hoaDonService.add(hoaDon);
            }

            hoaDonOld.setTrangThaiHoan(1);
            hoaDonService.add(hoaDonOld);

            phieuTraHang.setTgXacNhan(new Date());
            phieuTraHangServices.savePTH(phieuTraHang);

        }
        model.addAttribute("reLoadPage", true);
        showData(model);
        showTab4(model);
        showThongBao(model);

        return "manage/manage-bill-online";
    }


    @GetMapping("/online/print/{idHD}")
    private String printBillOnline(Model model, @PathVariable UUID idHD){

        HoaDon hoaDon = hoaDonService.getOne(idHD);

        model.addAttribute("billPrint", hoaDon);

        return "manage/managePrintBill";
    }

    @PostMapping("online/refund/delivery/confirm/{idHD}")
    private String confirmNVGHBillRefund(Model model,  @PathVariable UUID idHD){

        UUID idNV = UUID.fromString(request.getParameter("idNhanVien"));

        Date date = new Date();
        NhanVien nhanVien = nhanVienService.getByIdNhanVien(idNV);
        HoaDon hoaDon = hoaDonService.getOne(idHD);

        hoaDon.setNhanVien(nhanVien);
        hoaDon.setTrangThai(2);
        hoaDon.setTgShip(date);

        hoaDonService.add(hoaDon);

        String maGH = "GH_" + hoaDon.getMaHD() ;

        GiaoHang giaoHang = new GiaoHang();
        giaoHang.setHoaDon(hoaDon);
        giaoHang.setTrangThai(1);
        giaoHang.setMaGiaoHang(maGH);
        giaoHang.setThoiGian(new Date());
        giaoHang.setPhiGiaoHang(0.0);
        giaoHang.setNoiDung("Xác nhận nhân viên hoàn hàng");
        giaoHangService.saveGiaoHang(giaoHang);

        hoaDon.setGiaoHang(giaoHang);
        hoaDonService.add(hoaDon);

        ViTriDonHang viTriDonHang = new ViTriDonHang();
        viTriDonHang.setGiaoHang(giaoHang);
        viTriDonHang.setViTri("Xác nhận nhân viên hoàn hàng");
        viTriDonHang.setTrangThai(1);
        viTriDonHang.setThoiGian(new Date());
        viTriDonHangServices.addViTriDonHang(viTriDonHang);

        showData(model);

        model.addAttribute("showMessThanhCong", true);
        model.addAttribute("reLoadPage", true);
        showData(model);
        showTab3(model);
        showThongBao(model);

        return "manage/manage-bill-online";
    }

    @GetMapping("online/bill/refund/delivery/{idHD}")
    private String editBillRefundOnline(Model model, @PathVariable UUID idHD){

        HoaDon billDelivery = hoaDonService.getOne(idHD);
        List<NhanVien> listNhanVienGiao = nhanVienService.findByTrangThai(1);

        model.addAttribute("listNhanVienGiao", listNhanVienGiao);
        model.addAttribute("billDelivery", billDelivery);
        model.addAttribute("showEditBillRefundDelivery", true);

        showTab4(model);

        showData(model);

        return "manage/manage-bill-online";
    }

    @GetMapping("/online/print/refund/bill/{idHD}")
    private String printBillRefundOnline(Model model, @PathVariable UUID idHD){

        HoaDon hoaDon = hoaDonService.getOne(idHD);

        model.addAttribute("billPrint", hoaDon);

        return "manage/managePrintBillRefund";
    }


    private void showThongBao(Model model){
        int soThongBao = 0;

        List<ThongBaoKhachHang> thongBaoKhachHangs =  thongBaoServices.getAll();
        for (ThongBaoKhachHang x: thongBaoKhachHangs) {
            if (x.getTrangThai() == 3){
                soThongBao++;
            }
        }

        model.addAttribute("soThongBao", soThongBao);
        model.addAttribute("listThongBao", thongBaoKhachHangs);
    }

    private void showData(Model model){

        List<HoaDon> listAllHoaDonOnline = hoaDonService.listHoaDonOnline();


        List<HoaDon> listHoaDonHoan = new ArrayList<>();

        List<HoaDon> listAllHoaDonOnline2 = new ArrayList<>();
        List<HoaDon> listAllHoaDonDangGiao = new ArrayList<>();
        List<HoaDon> listHoaDonOnlineQRCode = new ArrayList<>();


        int soLuongHoaDonHoan = 0;
        int soLuongHoaDonOnline = 0;
        int soLuongHoaDonHuy = 0;
        int soLuongHoaDonDaThanhToan = 0 ;
        int soLuongHoaDonChuaThanhToanQRCode = 0 ;
        int soLuongHoaDonChuaThanhToanNhanHang = 0 ;
        int soLuongHoaDonDangGiao = 0;
        int soLuongHoaDonBanking = 0;
        int soLuongHoaDonDaNhan = 0;

        int soLuongHoaDonThanhToanKhiNhanHang = 0;

        double tongTienHoaDon = 0.0;
        if (listAllHoaDonOnline != null) {
            for (HoaDon x : listAllHoaDonOnline) {
                if (x.getTrangThai() == 6 || x.getTrangThai() == 7) {
                    System.out.println("abc");
                } else {
                    tongTienHoaDon += x.getTongTienDG();
                }
            }
        }

        if (listAllHoaDonOnline != null){
            for (HoaDon x: listAllHoaDonOnline) {
                if (x.getTrangThai() == 6 || x.getTrangThai() == 7){
                    System.out.println("abc");
                }else{
                    if (x.getIdHDOld() != null){
                        soLuongHoaDonHoan ++;
                        listHoaDonHoan.add(x);
                    }
                    if (x.getIdHDOld() == null){
                        listAllHoaDonOnline2.add(x);
                        soLuongHoaDonOnline ++;
                    }
                    if(x.getTrangThai() == 4){
                        soLuongHoaDonHuy ++;
                    }
                    if (x.getHinhThucThanhToan() == 1 && x.getIdHDOld() == null){
                        soLuongHoaDonBanking ++;
                        listHoaDonOnlineQRCode.add(x);
                    }
                    if (x.getHinhThucThanhToan() == 0){
                        soLuongHoaDonThanhToanKhiNhanHang ++;
                    }
                    if (x.getHinhThucThanhToan() == 1 && x.getTrangThai() == 0){
                        soLuongHoaDonChuaThanhToanQRCode ++;
                    }
                    if ( x.getTrangThai() == 1 || x.getTrangThai() == 2){
                        if (x.getHinhThucThanhToan() == 0){
                            soLuongHoaDonChuaThanhToanQRCode ++;
                        }
                    }
                    if (x.getTrangThai() == 1 && x.getHinhThucThanhToan() == 1 && x.getIdHDOld() == null){
                        soLuongHoaDonDaThanhToan ++;
                    }
                    if (x.getTrangThai() == 3 && x.getHinhThucThanhToan() == 0 && x.getIdHDOld() == null){
                        soLuongHoaDonDaThanhToan ++;
                    }
                    if (x.getTrangThai() == 2 && x.getIdHDOld() == null){
                        soLuongHoaDonDangGiao ++;
                        listAllHoaDonDangGiao.add(x);
                    }
                    if (x.getTrangThai() == 1 && x.getIdHDOld() == null ){
                        soLuongHoaDonDangGiao ++;
                        listAllHoaDonDangGiao.add(x);
                    }
                    if (x.getTrangThai() == 4 && x.getIdHDOld() == null){
                        soLuongHoaDonHuy ++;
                    }
                    if (x.getTrangThai() == 3 && x.getIdHDOld() == null){
                        soLuongHoaDonDaNhan ++;
                    }
                }
                if(x.getIdHDOld() != null){

                }
            }
        }

        int soLuongHoaDonChuaThanhToan =  soLuongHoaDonChuaThanhToanNhanHang +  soLuongHoaDonChuaThanhToanQRCode;

        double tongTienHoan = 0.0;
        int soLuongSPHoan = 0;
        int soLuongHdHoanChoXacNhan = 0;
        int soLuongHdHoanKhachHuy = 0;
        int soLuongHdHoanDangHoan = 0;
        int soLuongHdHoanChuaHoanTien = 0;
        int soLuongHdHoanDaHoanTien = 0;
        int soLuongHdHoanTuChoi = 0;

        if (listHoaDonHoan.size() != 0){
            for (HoaDon x:listHoaDonHoan) {
                tongTienHoan += x.getTongTienDG();
                soLuongSPHoan += x.getTongSP();
                if(x.getTrangThaiHoan() == 0){
                    soLuongHdHoanChoXacNhan ++;
                }
                if(x.getTrangThaiHoan() == 3){
                    soLuongHdHoanKhachHuy ++;
                }
                if(x.getTrangThaiHoan() == 5){
                    soLuongHdHoanDangHoan ++;
                }
                if(x.getTrangThaiHoan() == 7){
                    soLuongHdHoanChuaHoanTien ++;
                }
                if(x.getTrangThaiHoan() == 8){
                    soLuongHdHoanDaHoanTien ++;
                }
                if(x.getTrangThaiHoan() == 2){
                    soLuongHdHoanTuChoi ++;
                }
            }
        }

        model.addAttribute("sumBillOnline", soLuongHoaDonOnline);
        model.addAttribute("totalAmount", tongTienHoaDon);
        model.addAttribute("sumQuantityQRCode", soLuongHoaDonBanking);
        model.addAttribute("sumQuantityDelivery", soLuongHoaDonThanhToanKhiNhanHang);
        model.addAttribute("hoaDonChuaThanhToan", soLuongHoaDonChuaThanhToan);
        model.addAttribute("hoaDonDaThanhToan", soLuongHoaDonDaThanhToan);
        model.addAttribute("hoaDonDangGiao", soLuongHoaDonDangGiao);
        model.addAttribute("hoaDonHuy", soLuongHoaDonHuy);
        model.addAttribute("hoaDonDaNhan", soLuongHoaDonDaNhan);
        model.addAttribute("hoaDonDaHoan", soLuongHoaDonHoan);

        model.addAttribute("tongTienHoan", tongTienHoan);
        model.addAttribute("soLuongSPHoan", soLuongSPHoan);
        model.addAttribute("soLuongHdHoanChoXacNhan", soLuongHdHoanChoXacNhan);
        model.addAttribute("soLuongHdHoanKhachHuy", soLuongHdHoanKhachHuy);
        model.addAttribute("soLuongHdHoanDangHoan", soLuongHdHoanDangHoan);
        model.addAttribute("soLuongHdHoanChuaHoanTien", soLuongHdHoanChuaHoanTien);
        model.addAttribute("soLuongHdHoanDaHoanTien", soLuongHdHoanDaHoanTien);
        model.addAttribute("soLuongHdHoanTuChoi", soLuongHdHoanTuChoi);

        model.addAttribute("listBillRefund", listHoaDonHoan);
        model.addAttribute("listHoaDonOnline", listAllHoaDonOnline);
        model.addAttribute("listHoaDonOnlineQRCode", listHoaDonOnlineQRCode);
        model.addAttribute("listHoaDonOnlineGiaoHang", listAllHoaDonDangGiao);
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

    private void showTab1(Model model){
        model.addAttribute("activeAll", "nav-link active");
        model.addAttribute("xac_nhan_tt", "nav-link");
        model.addAttribute("van_chuyen", "nav-link");
        model.addAttribute("hoan_hang", "nav-link");

        model.addAttribute("tabpane1", "tab-pane show active");
        model.addAttribute("tabpane2", "tab-pane");
        model.addAttribute("tabpane3", "tab-pane");
        model.addAttribute("tabpane4", "tab-pane");
    }

    private void showTab2(Model model){

        model.addAttribute("activeAll", "nav-link");
        model.addAttribute("xac_nhan_tt", "nav-link active");
        model.addAttribute("van_chuyen", "nav-link");
        model.addAttribute("hoan_hang", "nav-link");

        model.addAttribute("tabpane1", "tab-pane");
        model.addAttribute("tabpane2", "tab-pane show active");
        model.addAttribute("tabpane3", "tab-pane");
        model.addAttribute("tabpane4", "tab-pane");
    }

    private void showTab3(Model model){

        model.addAttribute("activeAll", "nav-link");
        model.addAttribute("xac_nhan_tt", "nav-link");
        model.addAttribute("van_chuyen", "nav-link active");
        model.addAttribute("hoan_hang", "nav-link");

        model.addAttribute("tabpane1", "tab-pane");
        model.addAttribute("tabpane2", "tab-pane");
        model.addAttribute("tabpane3", "tab-pane show active");
        model.addAttribute("tabpane4", "tab-pane");
    }

    private void showTab4(Model model){

        model.addAttribute("activeAll", "nav-link");
        model.addAttribute("xac_nhan_tt", "nav-link");
        model.addAttribute("van_chuyen", "nav-link");
        model.addAttribute("hoan_hang", "nav-link active");

        model.addAttribute("tabpane1", "tab-pane");
        model.addAttribute("tabpane2", "tab-pane");
        model.addAttribute("tabpane3", "tab-pane");
        model.addAttribute("tabpane4", "tab-pane show active");

        }
}
