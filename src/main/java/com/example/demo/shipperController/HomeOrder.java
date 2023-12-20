package com.example.demo.shipperController;

import com.example.demo.model.*;
import com.example.demo.service.*;
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

@Controller
@RequestMapping("/order")
public class HomeOrder {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private GiaoHangService giaoHangService;

    @Autowired
    private ViTriDonHangServices viTriDonHangServices;

    @Autowired
    private PhieuTraHangServices phieuTraHangServices;

    @Autowired
    private GiayChiTietService giayChiTietService;

    @Autowired
    private LSThanhToanService lsThanhToanService;

    @RequestMapping(value = {"", "/", "/home"})
    private String getHomeShipping(Model model){

        showData(model);

        showDataTab1(model);
        return "transportation/index";
    }

    @GetMapping("/delivery/update/{idHD}")
    private String viewUpdateGiaoHang(Model model, @PathVariable UUID idHD){

        showData(model);

        HoaDon hoaDon = hoaDonService.getOne(idHD);
        GiaoHang giaoHang = hoaDon.getGiaoHang();
        List<ViTriDonHang> giaoHangList = viTriDonHangServices.findByGiaoHang(giaoHang);
        int soLanHuy = 0;
        for (ViTriDonHang xx : giaoHangList) {
            if (xx.getTrangThai() == 2){
                soLanHuy ++;
            }
        }

        showDataGH(model, hoaDon);
        showDataTab2(model);
        model.addAttribute("soLanHuy", soLanHuy);
        model.addAttribute("giaoHangList", giaoHangList);
        return "transportation/index";

    }

    @PostMapping("/delivery/update/{idHD}")
    private String updateGiaoHang(Model model, @PathVariable UUID idHD) {

        HoaDon hoaDon = hoaDonService.getOne(idHD);

        showData(model);

        String trangThaiGiaoHang = request.getParameter("trangThaiGiaoHang");

        String thanhPho = request.getParameter("city");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String moTa = request.getParameter("moTa");

        GiaoHang giaoHang = hoaDon.getGiaoHang();
        List<ViTriDonHang> giaoHangList = viTriDonHangServices.findByGiaoHang(giaoHang);

        int soLanHuy = 0;

        for (ViTriDonHang xx : giaoHangList) {
            if (xx.getTrangThai() == 2){
                soLanHuy ++;
            }
        }

        if (trangThaiGiaoHang.equals("daGuiHang")){

            String donViVanChuyen = request.getParameter("donViVanChuyen");

            giaoHang.setTenDVVC("Human Express");
            giaoHangService.saveGiaoHang(giaoHang);

            if(!donViVanChuyen.equals("humanExpress")){
                String maVanDonGH = request.getParameter("maVanDonGH");
                Double phiGiaoHangGH = Double.parseDouble(request.getParameter("phiGiaoHangGH"));
                if(donViVanChuyen.equals("ghn")){
                    giaoHang.setTenDVVC("Giao hàng nhanh");
                    giaoHang.setPhiGiaoHang(phiGiaoHangGH);
                    giaoHang.setMaVanDon(maVanDonGH);
                }
                if(donViVanChuyen.equals("ghtk")){
                    giaoHang.setTenDVVC("Giao Hàng Tiết Kiệm");
                    giaoHang.setPhiGiaoHang(phiGiaoHangGH);
                    giaoHang.setMaVanDon(maVanDonGH);
                }
                if(donViVanChuyen.equals("viettelPost")){
                    giaoHang.setTenDVVC("Viettel Post");
                    giaoHang.setPhiGiaoHang(phiGiaoHangGH);
                    giaoHang.setMaVanDon(maVanDonGH);
                }
                if(donViVanChuyen.equals("ahamove")){
                    giaoHang.setTenDVVC("Ahamove");
                    giaoHang.setPhiGiaoHang(phiGiaoHangGH);
                    giaoHang.setMaVanDon(maVanDonGH);
                }
                if(donViVanChuyen.equals("grab")){
                    giaoHang.setTenDVVC("Grab");
                    giaoHang.setPhiGiaoHang(phiGiaoHangGH);
                    giaoHang.setMaVanDon(maVanDonGH);
                }
                if(donViVanChuyen.equals("be")){
                    giaoHang.setTenDVVC("Be");
                    giaoHang.setPhiGiaoHang(phiGiaoHangGH);
                    giaoHang.setMaVanDon(maVanDonGH);
                }
                giaoHangService.saveGiaoHang(giaoHang);
            }
            ViTriDonHang viTriDonHang = new ViTriDonHang();

            viTriDonHang.setViTri("Đã lấy/gửi hành thành công ");
            viTriDonHang.setTrangThai(1);
            viTriDonHang.setGiaoHang(hoaDon.getGiaoHang());
            viTriDonHang.setThoiGian(new Date());
            viTriDonHang.setNoiDung(moTa);

            viTriDonHangServices.addViTriDonHang(viTriDonHang);

            showData(model);
            showDataTab2(model);
            return "transportation/index";
        }else if (trangThaiGiaoHang.equals("thanhCong")){
            String viTri = "Đơn hàng đã giao hàng thành công";

            Date date = new Date();
            ViTriDonHang viTriDonHang = new ViTriDonHang();

            viTriDonHang.setViTri(viTri);
            viTriDonHang.setThoiGian(date);
            viTriDonHang.setTrangThai(1);
            viTriDonHang.setNoiDung(moTa);
            viTriDonHang.setGiaoHang(hoaDon.getGiaoHang());
            viTriDonHangServices.addViTriDonHang(viTriDonHang);

            hoaDon.setTrangThai(3);
            hoaDon.setTgThanhToan(date);
            hoaDon.setTgNhan(date);
            hoaDonService.add(hoaDon);

            LichSuThanhToan lichSuThanhToan = new LichSuThanhToan();
            lichSuThanhToan.setHoaDon(hoaDon);
            lichSuThanhToan.setTgThanhToan(date);
            lichSuThanhToan.setSoTienThanhToan(0.0);
            lichSuThanhToan.setKhachHang(hoaDon.getKhachHang());
            lichSuThanhToan.setMaLSTT("LSST0" + date.getTime());
            lichSuThanhToan.setNoiDungThanhToan("Khách hàng đã thanh toán cho đơn hàng");
            lichSuThanhToan.setTrangThai(1);
            lsThanhToanService.addLSTT(lichSuThanhToan);


            showData(model);
            showDataTab2(model);
            return "transportation/index";
        } else if (trangThaiGiaoHang.equals("thatBai")){

            String viTri = "Đơn hàng giao thất bại ( " +moTa + " )";
            ViTriDonHang viTriDonHang = new ViTriDonHang();

            viTriDonHang.setViTri(viTri);
            viTriDonHang.setThoiGian(new Date());
            viTriDonHang.setTrangThai(2);
            viTriDonHang.setNoiDung(moTa);
            viTriDonHang.setGiaoHang(giaoHang);
            viTriDonHangServices.addViTriDonHang(viTriDonHang);

            if (soLanHuy == 2){
                hoaDon.setTrangThai(4);
                hoaDon.setTgHuy(new Date());
                hoaDon.setLyDoHuy(moTa);
                hoaDonService.add(hoaDon);

                Date date = new Date();

                ViTriDonHang viTriDonHang2 = new ViTriDonHang();

                viTriDonHang.setViTri("Đơn hàng đã bị hủy");
                viTriDonHang.setThoiGian(new Date());
                viTriDonHang.setTrangThai(2);
                viTriDonHang.setNoiDung(moTa);
                viTriDonHang.setGiaoHang(giaoHang);
                viTriDonHangServices.addViTriDonHang(viTriDonHang2);

                LichSuThanhToan lichSuThanhToan = new LichSuThanhToan();
                lichSuThanhToan.setHoaDon(hoaDon);
                lichSuThanhToan.setTgThanhToan(date);
                lichSuThanhToan.setSoTienThanhToan(hoaDon.getTongTienDG());
                lichSuThanhToan.setKhachHang(hoaDon.getKhachHang());
                lichSuThanhToan.setMaLSTT("LSST0" + date.getTime());
                lichSuThanhToan.setNoiDungThanhToan("Khách hàng không nhận hàng");
                lichSuThanhToan.setTrangThai(5);
                lsThanhToanService.addLSTT(lichSuThanhToan);

                showDataTab2(model);
                showData(model);
                return "transportation/index";
            }
            showData(model);
            showDataTab2(model);
            return "transportation/index";
        } else {
            String viTri = "Đơn hàng đã đến"  + ", " + ward + ", " + district + " , " + thanhPho;
            ViTriDonHang viTriDonHang = new ViTriDonHang();

            viTriDonHang.setViTri(viTri);
            viTriDonHang.setThoiGian(new Date());
            viTriDonHang.setTrangThai(1);
            viTriDonHang.setNoiDung(moTa);
            viTriDonHang.setGiaoHang(giaoHang);
            viTriDonHangServices.addViTriDonHang(viTriDonHang);

            for (ViTriDonHang x: giaoHangList) {
                if(x.getTrangThai() == 1){
                    x.setTrangThai(0);
                    viTriDonHangServices.addViTriDonHang(x);
                }
            }
            showData(model);
            showDataTab2(model);
            return "transportation/index";

        }
    }

    @GetMapping("/bill/print/{idHD}")
    private String printBill(Model model, @PathVariable UUID idHD){

        HoaDon hoaDon = hoaDonService.getOne(idHD);

        model.addAttribute("billPrint", hoaDon);

        return "transportation/printBill";
    }

    @GetMapping("/delivery/refund/update/{idHD}")
    private String viewUpdateRefundGiaoHang(Model model, @PathVariable UUID idHD){


        showData(model);
        HoaDon hoaDon = hoaDonService.getOne(idHD);
        showDataGHHH(model, hoaDon);
        showDataTab2(model);

        return "transportation/index";

    }

    @PostMapping("/delivery/refund/update/{idHD}")
    private String updateRefundGiaoHang(Model model, @PathVariable UUID idHD){

        showData(model);
        String trangThaiGiaoHang = request.getParameter("trangThaiGiaoHang");
        HoaDon hoaDon = hoaDonService.getOne(idHD);
        HoaDon hoaDonOld = hoaDonService.getOne(hoaDon.getIdHDOld());

        String thanhPho = request.getParameter("city");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String moTa = request.getParameter("moTa");
        ViTriDonHang viTriDonHang = new ViTriDonHang();

        if (trangThaiGiaoHang.equals("daLayHang")){

            String donViVanChuyen = request.getParameter("donViVanChuyen");
            GiaoHang giaoHang= hoaDon.getGiaoHang();

            giaoHang.setTenDVVC("Human Express");
            giaoHangService.saveGiaoHang(giaoHang);

            if(!donViVanChuyen.equals("humanExpress")){
                String maVanDonGH = request.getParameter("maVanDonGH");
                Double phiGiaoHangGH = Double.parseDouble(request.getParameter("phiGiaoHangGH"));
                if(donViVanChuyen.equals("ghn")){
                    giaoHang.setTenDVVC("Giao hàng nhanh");
                    giaoHang.setPhiGiaoHang(phiGiaoHangGH);
                    giaoHang.setMaVanDon(maVanDonGH);
                }
                if(donViVanChuyen.equals("ghtk")){
                    giaoHang.setTenDVVC("Giao Hàng Tiết Kiệm");
                    giaoHang.setPhiGiaoHang(phiGiaoHangGH);
                    giaoHang.setMaVanDon(maVanDonGH);
                }
                if(donViVanChuyen.equals("viettelPost")){
                    giaoHang.setTenDVVC("Viettel Post");
                    giaoHang.setPhiGiaoHang(phiGiaoHangGH);
                    giaoHang.setMaVanDon(maVanDonGH);
                }
                if(donViVanChuyen.equals("ahamove")){
                    giaoHang.setTenDVVC("Ahamove");
                    giaoHang.setPhiGiaoHang(phiGiaoHangGH);
                    giaoHang.setMaVanDon(maVanDonGH);
                }
                if(donViVanChuyen.equals("grab")){
                    giaoHang.setTenDVVC("Grab");
                    giaoHang.setPhiGiaoHang(phiGiaoHangGH);
                    giaoHang.setMaVanDon(maVanDonGH);
                }
                if(donViVanChuyen.equals("be")){
                    giaoHang.setTenDVVC("Be");
                    giaoHang.setPhiGiaoHang(phiGiaoHangGH);
                    giaoHang.setMaVanDon(maVanDonGH);
                }
                giaoHangService.saveGiaoHang(giaoHang);
            }
            viTriDonHang.setViTri("Đã lấy/gửi hàng hoàn thành công ");
            viTriDonHang.setTrangThai(1);
            viTriDonHang.setGiaoHang(hoaDon.getGiaoHang());
            viTriDonHang.setThoiGian(new Date());
            viTriDonHang.setNoiDung(moTa);

            viTriDonHangServices.addViTriDonHang(viTriDonHang);

            hoaDon.setTrangThaiHoan(5);
            hoaDonService.add(hoaDon);
            hoaDonOld.setTrangThaiHoan(5);
            hoaDonService.add(hoaDonOld);

            PhieuTraHang phieuTraHang = hoaDon.getPhieuTraHang();
            phieuTraHang.setTrangThai(5);
            phieuTraHangServices.savePTH(phieuTraHang);

            showData(model);
            showDataTab2(model);

            return "transportation/index";

        }
        else if(trangThaiGiaoHang.equals("thatBai")){
            String viTri = "Đơn hàng hoàn thất bại ( " +moTa + " )";

            GiaoHang giaoHang = hoaDon.getGiaoHang();

            viTriDonHang.setViTri(viTri);
            viTriDonHang.setThoiGian(new Date());
            viTriDonHang.setTrangThai(2);
            viTriDonHang.setNoiDung(moTa);
            viTriDonHang.setGiaoHang(giaoHang);
            viTriDonHangServices.addViTriDonHang(viTriDonHang);

            List<ViTriDonHang> giaoHangList = giaoHang.getViTriDonHangs();

            for (ViTriDonHang  xx:giaoHangList) {
                if (xx.getTrangThai() == 2){
                    giaoHangList.remove(xx);
                }
            }

            if (giaoHangList.size() == 3){
                PhieuTraHang phieuTraHang = hoaDon.getPhieuTraHang();
                phieuTraHang.setTgKhachHuy(new Date());
                phieuTraHang.setTrangThai(3);
                phieuTraHangServices.savePTH(phieuTraHang);

                hoaDon.setTrangThaiHoan(3);
                hoaDon.setTrangThai(4);
                hoaDon.setTgHuy(new Date());
                hoaDon.setLyDoHuy(moTa);
                hoaDonService.add(hoaDon);

                viTriDonHang.setViTri("Lấy hàng hoàn không thành công, hủy đơn hàng");
                viTriDonHang.setThoiGian(new Date());
                viTriDonHang.setTrangThai(2);
                viTriDonHang.setNoiDung(moTa);
                viTriDonHang.setGiaoHang(giaoHang);
                viTriDonHangServices.addViTriDonHang(viTriDonHang);

                hoaDonOld.setTrangThaiHoan(3);
                hoaDonService.add(hoaDonOld);

                showDataTab2(model);
                showData(model);

                return "transportation/index";
            }

            showDataGH(model, hoaDon);
            showDataTab2(model);
            return "transportation/index";

        }
        else if(trangThaiGiaoHang.equals("dangGiao")){
            String viTri = "Đơn hàng đã đến"  + ", " + ward + ", " + district + " , " + thanhPho;

            GiaoHang giaoHang = hoaDon.getGiaoHang();

            viTriDonHang.setViTri(viTri);
            viTriDonHang.setThoiGian(new Date());
            viTriDonHang.setTrangThai(1);
            viTriDonHang.setNoiDung(moTa);
            viTriDonHang.setGiaoHang(giaoHang);
            viTriDonHangServices.addViTriDonHang(viTriDonHang);

            List<ViTriDonHang> giaoHangList = giaoHang.getViTriDonHangs();

            for (ViTriDonHang x: giaoHangList) {
                if(x.getTrangThai() == 1){
                    x.setTrangThai(0);
                    viTriDonHangServices.addViTriDonHang(x);
                }
            }

            showData(model);
            showDataTab2(model);

            return "transportation/index";

        }
        else if(trangThaiGiaoHang.equals("thanhCong")){
            String viTri = "Đơn hàng đã hoàn hàng thành công";

            viTriDonHang.setViTri(viTri);
            viTriDonHang.setThoiGian(new Date());
            viTriDonHang.setTrangThai(1);
            viTriDonHang.setNoiDung(moTa);
            viTriDonHang.setGiaoHang(hoaDon.getGiaoHang());
            viTriDonHangServices.addViTriDonHang(viTriDonHang);

            PhieuTraHang phieuTraHang = hoaDon.getPhieuTraHang();

            phieuTraHang.setTrangThai(6);
            phieuTraHang.setTgHoanThanhCong(new Date());
            phieuTraHangServices.savePTH(phieuTraHang);

            hoaDon.setTrangThaiHoan(6);
            hoaDon.setTrangThai(3);
            hoaDonService.add(hoaDon);

            for (HoaDonChiTiet xx: hoaDon.getHoaDonChiTiets()) {
                if (xx.getTrangThai() == 2){
                    Date date = new Date();
                    ChiTietGiay chiTietGiay = xx.getChiTietGiay();
                    ChiTietGiay chiTietGiayHoan = new ChiTietGiay();
                    chiTietGiayHoan.setIdCTGOld(chiTietGiay.getIdCTG());
                    chiTietGiayHoan.setMaCTG("CTGHOAN_" + date.getDate() + phieuTraHang.getMaMPTH());
                    chiTietGiayHoan.setGiay(chiTietGiay.getGiay());
                    chiTietGiayHoan.setHinhAnh(chiTietGiay.getHinhAnh());
                    chiTietGiayHoan.setMauSac(chiTietGiay.getMauSac());
                    chiTietGiayHoan.setNamSX(chiTietGiay.getNamSX());
                    chiTietGiayHoan.setNamBH(chiTietGiay.getNamBH());
                    chiTietGiayHoan.setSize(chiTietGiay.getSize());
                    chiTietGiayHoan.setTrongLuong(chiTietGiay.getTrongLuong());
                    chiTietGiayHoan.setGiaBan(chiTietGiay.getGiaBan());
                    chiTietGiayHoan.setTgThem(date);
                    chiTietGiayHoan.setSoLuong(1);
                    chiTietGiayHoan.setTrangThai(3);
                    chiTietGiayHoan.setTgThem(date);
                    giayChiTietService.save(chiTietGiayHoan);
                }
            }
            hoaDonOld.setTrangThaiHoan(6);
            hoaDonService.add(hoaDonOld);

            showData(model);
            showDataTab2(model);
            return "transportation/index";

        }
            showData(model);
            showDataTab2(model);
            return "transportation/index";
    }

    @GetMapping("/return/{idHD}")
    private String viewReturnGiaoHang(Model model, @PathVariable UUID idHD){
        HoaDon hoaDon = hoaDonService.getOne(idHD);
        GiaoHang giaoHang = hoaDon.getGiaoHang();

        if (giaoHang.getThoiGianHoan() == null){
            model.addAttribute("chuaBatDau", true);
        }else{
            model.addAttribute("daBatDau", true);
        }
        showData(model);
        showDataTab3(model);
        model.addAttribute("showHuyHoanHang", true);
        model.addAttribute("HoaDonVanChuyen", hoaDon);
        List<ViTriDonHang> giaoHangList = viTriDonHangServices.findByGiaoHang(giaoHang);
        model.addAttribute("giaoHangList", giaoHangList);
        return "transportation/index";

    }

    @PostMapping("/return/update/{idHD}")
    private String updateBillReturn(Model model,  @PathVariable UUID idHD){

        HoaDon hoaDon = hoaDonService.getOne(idHD);
        GiaoHang giaoHang = hoaDon.getGiaoHang();
        String trangThaiHoan = request.getParameter("trangThaiGiaoHang");

        if (trangThaiHoan.equals("batDauHoan")){
            if(giaoHang.getMaVanDonHoan() == null){
                giaoHang.setThoiGianHoan(new Date());
                giaoHangService.saveGiaoHang(giaoHang);

                ViTriDonHang viTriDonHang = new ViTriDonHang();
                viTriDonHang.setViTri("Bắt đầu hoàn hàng về kho");
                viTriDonHang.setGiaoHang(giaoHang);
                viTriDonHang.setTrangThai(3);
                viTriDonHang.setThoiGian(new Date());
                viTriDonHangServices.addViTriDonHang(viTriDonHang);
            }else{
                String maVanDonHoan = request.getParameter("maVanDonGH");
                Double phiHoanHang = Double.parseDouble(request.getParameter("phiGiaoHangGH"));

                giaoHang.setPhiHoanHang(phiHoanHang);
                giaoHang.setMaVanDonHoan(maVanDonHoan);
                giaoHang.setThoiGianHoan(new Date());
                giaoHangService.saveGiaoHang(giaoHang);

                ViTriDonHang viTriDonHang = new ViTriDonHang();
                viTriDonHang.setViTri("Bắt đầu hoàn hàng về kho");
                viTriDonHang.setGiaoHang(giaoHang);
                viTriDonHang.setTrangThai(3);
                viTriDonHang.setThoiGian(new Date());
                viTriDonHangServices.addViTriDonHang(viTriDonHang);
            }
        }else if(trangThaiHoan.equals("dangGiao")){
            String thanhPho = request.getParameter("city");
            String district = request.getParameter("district");
            String ward = request.getParameter("ward");
            String moTa = request.getParameter("moTa");

            String viTri = "Đơn hàng đã đến"  + ", " + ward + ", " + district + " , " + thanhPho + " || " + moTa;

            ViTriDonHang viTriDonHang = new ViTriDonHang();
            viTriDonHang.setThoiGian(new Date());
            viTriDonHang.setGiaoHang(giaoHang);
            viTriDonHang.setViTri(viTri);
            viTriDonHang.setTrangThai(4);
            viTriDonHang.setNoiDung(moTa);
            viTriDonHangServices.addViTriDonHang(viTriDonHang);


        }else{
            String thanhPho = request.getParameter("city");
            String district = request.getParameter("district");
            String ward = request.getParameter("ward");
            String moTa = request.getParameter("moTa");

            String viTri = "Đơn hàng đã về kho "  + ", " + ward + ", " + district + " , " + thanhPho + " || " + moTa;

            ViTriDonHang viTriDonHang = new ViTriDonHang();
            viTriDonHang.setThoiGian(new Date());
            viTriDonHang.setGiaoHang(giaoHang);
            viTriDonHang.setViTri(viTri);
            viTriDonHang.setTrangThai(5);
            viTriDonHang.setNoiDung(moTa);
            viTriDonHangServices.addViTriDonHang(viTriDonHang);
            giaoHang.setTrangThai(2);
            giaoHangService.saveGiaoHang(giaoHang);

            for (HoaDonChiTiet xx: hoaDon.getHoaDonChiTiets()) {
                ChiTietGiay chiTietGiay = xx.getChiTietGiay();
                chiTietGiay.setSoLuong(xx.getSoLuong() + xx.getSoLuong());
                giayChiTietService.save(chiTietGiay);
            }


        }

        showData(model);
        showDataTab3(model);
        return "transportation/index";
    }

    @GetMapping("/bill/refund/print/{idHD}")
    private String printBillRefund(Model model, @PathVariable UUID idHD){

        HoaDon hoaDon = hoaDonService.getOne(idHD);

        model.addAttribute("billPrint", hoaDon);

        return "transportation/printBillRefund";
    }

    private void showData(Model model){
        NhanVien nhanVien = (NhanVien) session.getAttribute("shipperLogged");

        List<HoaDon> allHoaDonList = hoaDonService.listAllHoaDonByNhanVien(nhanVien);
        List<HoaDon> hoaDonDGList = hoaDonService.listHoaDonByNhanVienAndTrangThai(nhanVien, 2);
        List<HoaDon> hoaDonDoneList = hoaDonService.listHoaDonHuyAndThanhCongByNhanVien(nhanVien);

        List<HoaDon> hoaDonListHoan= new ArrayList<>();

        if(allHoaDonList != null){
            for (HoaDon x: allHoaDonList) {
                if (x.getIdHDOld() != null){
                    hoaDonListHoan.add(x);
                }
            }
        }


        model.addAttribute("allHoaDonList",allHoaDonList);
        model.addAttribute("sumDH", allHoaDonList.size() + hoaDonListHoan.size());
        model.addAttribute("dangGiao", hoaDonDGList.size());
        model.addAttribute("sumDHMuaHang", hoaDonDGList.size());
        model.addAttribute("giaoThanhCong", hoaDonDoneList.size());
        model.addAttribute("hoaDonHoan", hoaDonListHoan.size());
        model.addAttribute("hoaDonDGList",hoaDonDGList);
        model.addAttribute("hoaDonDoneList",hoaDonDoneList);

        model.addAttribute("nameNhanVien",nhanVien.getHoTenNV());
    }

    private void showDataGH(Model model, HoaDon hoaDon){

        GiaoHang giaoHangListActive = hoaDon.getGiaoHang();
        model.addAttribute("showHTGH", "true");
        model.addAttribute("HoaDonVanChuyen", hoaDon);
        model.addAttribute("giaoHangListActive", giaoHangListActive);
    }

    private void showDataGHHH(Model model, HoaDon hoaDon){

        GiaoHang giaoHangListActive = hoaDon.getGiaoHang();
        model.addAttribute("showDonHangHoan", "true");
        model.addAttribute("HoaDonVanChuyen", hoaDon);
        model.addAttribute("giaoHangListActive", giaoHangListActive);

    }

    private void showDataTab1(Model model){

        model.addAttribute("activeAll", "nav-link active");
        model.addAttribute("activeVanChuyen", "nav-link");
        model.addAttribute("activeDone", "nav-link");

        model.addAttribute("tabpane1", "tab-pane show active");
        model.addAttribute("tabpane2", "tab-pane");
        model.addAttribute("tabpane3", "tab-pane");
    }

    private void showDataTab2(Model model){

        model.addAttribute("activeAll", "nav-link");
        model.addAttribute("activeVanChuyen", "nav-link active");
        model.addAttribute("activeDone", "nav-link");

        model.addAttribute("tabpane1", "tab-pane");
        model.addAttribute("tabpane2", "tab-pane show active");
        model.addAttribute("tabpane3", "tab-pane");
    }

    private void showDataTab3(Model model){

        model.addAttribute("activeAll", "nav-link");
        model.addAttribute("activeVanChuyen", "nav-link");
        model.addAttribute("activeDone", "nav-link active");

        model.addAttribute("tabpane1", "tab-pane");
        model.addAttribute("tabpane2", "tab-pane");
        model.addAttribute("tabpane3", "tab-pane show active");
    }
}
