package com.example.demo.shipperController;

import com.example.demo.model.*;
import com.example.demo.service.GiaoHangService;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.PhieuTraHangServices;
import com.example.demo.service.ViTriDonHangServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        showDataGH(model, hoaDon);
        showDataTab2(model);

        return "transportation/index";

    }

    @PostMapping("/delivery/update/{idHD}")
    private String updateGiaoHang(Model model, @PathVariable UUID idHD) {

        HoaDon hoaDon = hoaDonService.getOne(idHD);

        showData(model);

        ViTriDonHang viTriDonHang = new ViTriDonHang();

        String trangThaiGiaoHang = request.getParameter("trangThaiGiaoHang");

        String thanhPho = request.getParameter("city");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String moTa = request.getParameter("moTa");

        if (trangThaiGiaoHang.equals("daGuiHang")){

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

            viTriDonHang.setViTri(viTri);
            viTriDonHang.setThoiGian(new Date());
            viTriDonHang.setTrangThai(1);
            viTriDonHang.setNoiDung(moTa);
            viTriDonHang.setGiaoHang(hoaDon.getGiaoHang());
            viTriDonHangServices.addViTriDonHang(viTriDonHang);

            hoaDon.setTrangThai(3);
            hoaDon.setTgThanhToan(new Date());
            hoaDon.setTgNhan(new Date());
            hoaDonService.add(hoaDon);

            showData(model);
            showDataTab2(model);
            return "transportation/index";
        } else if (trangThaiGiaoHang.equals("thatBai")){

            String viTri = "Đơn hàng giao thất bại ( " +moTa + " )";

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
                hoaDon.setTrangThai(4);
                hoaDon.setTgHuy(new Date());
                hoaDon.setLyDoHuy(moTa);
                hoaDonService.add(hoaDon);

                showDataTab2(model);

                showData(model);

                return "transportation/index";
            }

            showDataGH(model, hoaDon);
            showDataTab2(model);
            return "transportation/index";
        } else {
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

            showDataGH(model, hoaDon);
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

        HoaDon hoaDonOld = hoaDonService.findByIdHoaDonOld(hoaDon.getIdHDOld());

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

        }else if(trangThaiGiaoHang.equals("thatBai")){
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
                hoaDon.setTgHuy(new Date());
                hoaDon.setLyDoHuy(moTa);
                hoaDonService.add(hoaDon);

                hoaDonOld.setTrangThaiHoan(3);
                hoaDonService.add(hoaDonOld);

                showDataTab2(model);
                showData(model);

                return "transportation/index";
            }

            showDataGH(model, hoaDon);
            showDataTab2(model);
            return "transportation/index";

        }else if(trangThaiGiaoHang.equals("dangGiao")){
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

            showDataGH(model, hoaDon);
            showDataTab2(model);

            return "transportation/index";

        }else if(trangThaiGiaoHang.equals("thanhCong")){
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
            hoaDonService.add(hoaDon);

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

        model.addAttribute("allHoaDonList",allHoaDonList);
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
