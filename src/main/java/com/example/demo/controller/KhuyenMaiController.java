package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import com.example.demo.viewModel.CTGViewModel;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RequestMapping("/manage/")
@Controller
public class KhuyenMaiController {


    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @Autowired
    private LoaiKhuyenMaiService loaiKhuyenMaiService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CTGViewModelService ctgViewModelService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private GiayService giayService;

    @Autowired
    private GiayChiTietService chiTietService;

    @Autowired
    private KhuyenMaiChiTietProductService khuyenMaiChiTietProductService;

    @Autowired
    private HttpSession session;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private SendMailService sendMailService;

    @GetMapping("voucher")
    private String getPageVoucher(Model model){
        return showDataVoucher(model, "",  "",false);
    }

    @PostMapping("voucher/update/{idVoucher}")
    private String updateVoucher(Model model, @PathVariable UUID idVoucher){
        KhuyenMai khuyenMai = khuyenMaiService.findByID(idVoucher);
        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM02");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String tenKhuyenMai = request.getParameter("tenKM");
        Boolean loaiGiam = Boolean.valueOf(request.getParameter("loaiGiam"));
        String ngayBatDau = request.getParameter("ngayBatDau");
        String ngayKetThuc = request.getParameter("ngayKetThuc");
        Double soTienGiamGia = Double.valueOf(request.getParameter("soTienGiamGia"));
        Integer trangThai = Integer.parseInt(request.getParameter("trangThai"));
        Double phanTramGiam = Double.valueOf(request.getParameter("phanTramGiam"));
        Double soTienGiamGiaTD = Double.valueOf(request.getParameter("soTienGiamToiDa"));
        String dieuKienKMHD = request.getParameter("dieuKienKMHD");
        String soLuong = request.getParameter("soLuong");
        String moTa = request.getParameter("moTa");
        String tenLKM = request.getParameter("tenLoaiKM");

        Date dateStart = null;
        Date dateEnd = null;

        try {
            Date date = dateFormat.parse(ngayBatDau);
            Date date2 = dateFormat.parse(ngayKetThuc);
            dateStart = date;
            dateEnd = date2;
        } catch (ParseException e) {
        }

        if (dieuKienKMHD == "" || dieuKienKMHD == null){

        }else{
            khuyenMai.setDieuKienKMBill(Double.valueOf(dieuKienKMHD));
            khuyenMai.setSoLuong(Integer.parseInt(soLuong));
        }

        khuyenMai.setLoaiGiam(loaiGiam);
        khuyenMai.setGiaTienGiam(soTienGiamGia);
        khuyenMai.setMoTa(moTa);
        khuyenMai.setPhanTramGiam(phanTramGiam/100);
        khuyenMai.setTgBatDau(dateStart);
        khuyenMai.setTgKetThuc(dateEnd);
        khuyenMai.setTgSua(new Date());
        khuyenMai.setTenKM(tenKhuyenMai);
        khuyenMai.setTrangThai(trangThai);
        khuyenMai.setGiaTienGiamToiDaPT(soTienGiamGiaTD);
        khuyenMaiService.createKhuyenMais(khuyenMai);

        if (khuyenMai.getLoaiKhuyenMai() == loaiKhuyenMai || trangThai == 0){
            List<KhuyenMaiChiTietCTG> khuyenMaiChiTietCTGList = khuyenMai.getKhuyenMaiChiTietCTGList();
            for (KhuyenMaiChiTietCTG x: khuyenMaiChiTietCTGList ) {
                khuyenMaiChiTietProductService.removeKMCTSP(x);
            }
        }

        return showDataVoucher(model, "Thay đổi", tenLKM, true);
    }

//    Voucher Bill Start

    @GetMapping("voucher-bill")
    private String getPageVoucherBill(Model model){
        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM01");
        List<KhuyenMai> khuyenMaiList = khuyenMaiService.findByLoaiKM(loaiKhuyenMai);

        List<KhuyenMai> listKhuyenMaiActive = khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMai);

        List<KhachHang> khachHangList = khachHangService.getAllKhachHang();

        LocalDate currentDate = LocalDate.now();
        int soLuongKhuyenMaiHetHan = 0;

        for (KhuyenMai xx : khuyenMaiList) {
            Instant tgKetThucInstant = xx.getTgKetThuc().toInstant();

            LocalDateTime tgKetThucLocalDateTime = tgKetThucInstant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            if (tgKetThucLocalDateTime.isBefore(currentDate.atStartOfDay())) {
                soLuongKhuyenMaiHetHan ++;
                xx.setTrangThai(0);
                khuyenMaiService.createKhuyenMais(xx);
            }
            List<KhuyenMaiChiTietCTG> khuyenMaiChiTietCTGList = xx.getKhuyenMaiChiTietCTGList();

            for (KhuyenMaiChiTietCTG x: khuyenMaiChiTietCTGList ) {
                ChiTietGiay chiTietGiay = x.getChiTietGiay();
                chiTietGiay.setGiaBan(chiTietGiay.getSoTienTruocKhiGiam());
                chiTietService.save(chiTietGiay);
                khuyenMaiChiTietProductService.removeKMCTSP(x);
            }
        }
        if (soLuongKhuyenMaiHetHan != 0){
            model.addAttribute("showSoLuongKhuyenMaiHetHan", true);
            model.addAttribute("soLuongKhuyenMaiHetHan", soLuongKhuyenMaiHetHan);
        }

        model.addAttribute("listKH", khachHangList);
        model.addAttribute("listKhuyenMaiActive", listKhuyenMaiActive);
        model.addAttribute("listKhuyenMai", khuyenMaiList);
        return "manage/manageVoucherBill";
    }

    @PostMapping("/voucher-bill/send-mail")
    private String sendMailKMHD(Model model, @RequestParam("idKH") List<String> listKH) throws MessagingException {
        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM01");
        List<KhuyenMai> khuyenMaiList = khuyenMaiService.findByLoaiKM(loaiKhuyenMai);

        List<KhuyenMai> listKhuyenMaiActive = khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMai);

        List<KhachHang> khachHangList = khachHangService.getAllKhachHang();

        UUID khuyenMaiSelected =UUID.fromString(request.getParameter("khuyenMaiSelected"));

        for (String xx: listKH) {
            KhachHang khachHang = khachHangService.getByIdKhachHang(UUID.fromString(xx));
            if (khachHang != null){
                sendMailService.sendMimeMessageKMHD(khachHang.getEmailKH(), "", "'");
            }
        }

        model.addAttribute("listKH", khachHangList);
        model.addAttribute("listKhuyenMaiActive", listKhuyenMaiActive);
        model.addAttribute("listKhuyenMai", khuyenMaiList);
        model.addAttribute("thongBaoGuiMai", true);
        return "manage/manageVoucherBill";
    }

    @PostMapping("/voucher/voucher-bill/add")
    private String addVoucherBill(Model model){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM01");

        String tenKM = request.getParameter("tenKM");
        Boolean loaiGiamGia = Boolean.valueOf(request.getParameter("loaiGiamAdd"));
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        Integer soLuongKM = Integer.parseInt(request.getParameter("soLuongKM"));
        Double soTienGiamGia = Double.valueOf(request.getParameter("soTienGiamGia"));
        Integer trangThaiVoucher = Integer.parseInt(request.getParameter("trangThaiAdd"));
        Double phanTramGiam = Double.valueOf(request.getParameter("phanTramGiam"));
        Double soTienToiDa = Double.valueOf(request.getParameter("soTienToiDa"));
        Double dieuKienKMHD = Double.valueOf(request.getParameter("dieuKienKMHD"));
        String moTa = request.getParameter("moTa");


        Date dateStartFommat = null;
        Date dateEndFommat = null;

        Date dateToday = new Date();
        String maKM = "KM_HD0" + dateToday.getDate() + generateRandomNumbers();

        try {
            Date date = dateFormat.parse(dateStart);
            Date date2 = dateFormat.parse(dateEnd);
            dateStartFommat = date;
            dateEndFommat = date2;
        } catch (ParseException e) {
        }

        KhuyenMai khuyenMai = new KhuyenMai();

        khuyenMai.setLoaiKhuyenMai(loaiKhuyenMai);
        khuyenMai.setMaKM(maKM);
        khuyenMai.setGiaTienGiamToiDaPT(soTienToiDa);
        khuyenMai.setTgThem(dateToday);
        khuyenMai.setGiaTienGiam(soTienGiamGia);
        khuyenMai.setLoaiGiam(loaiGiamGia);
        khuyenMai.setMoTa(moTa);
        khuyenMai.setTrangThai(trangThaiVoucher);
        khuyenMai.setPhanTramGiam(phanTramGiam/100);
        khuyenMai.setTgBatDau(dateStartFommat);
        khuyenMai.setTgKetThuc(dateEndFommat);
        khuyenMai.setSoLuong(soLuongKM);
        khuyenMai.setDieuKienKMBill(dieuKienKMHD);
        khuyenMai.setTenKM(tenKM);

        khuyenMaiService.createKhuyenMais(khuyenMai);

        return showPageVoucherBill(model, "Tạo mới" , loaiKhuyenMai, true);
    }

    @PostMapping("voucher/voucher-bill/update/{idVoucher}")
    private String updateVoucherBill(Model model, @PathVariable UUID idVoucher){
        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM01");
        KhuyenMai khuyenMai = khuyenMaiService.findByID(idVoucher);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String tenKhuyenMai = request.getParameter("tenKM");
        Boolean loaiGiam = Boolean.valueOf(request.getParameter("loaiGiam"));
        String ngayBatDau = request.getParameter("ngayBatDau");
        String ngayKetThuc = request.getParameter("ngayKetThuc");
        Integer soLuong = Integer.parseInt(request.getParameter("soLuong"));
        Double soTienGiamGia = Double.parseDouble(request.getParameter("soTienGiamGia"));
        Integer trangThai = Integer.parseInt(request.getParameter("trangThai"));
        Double phanTramGiam = Double.parseDouble(request.getParameter("phanTramGiam"));
        Double soTienGiamToiDa = Double.parseDouble(request.getParameter("soTienGiamGia"));
        Double dieuKienKMHD = Double.parseDouble(request.getParameter("dieuKienKMHD"));
        String moTa = request.getParameter("moTa");

        Date dateStart = null;
        Date dateEnd = null;

        try {
            Date date = dateFormat.parse(ngayBatDau);
            Date date2 = dateFormat.parse(ngayKetThuc);
            dateStart = date;
            dateEnd = date2;
        } catch (ParseException e) {
        }

        khuyenMai.setLoaiGiam(loaiGiam);
        khuyenMai.setGiaTienGiam(soTienGiamGia);
        khuyenMai.setMoTa(moTa);
        khuyenMai.setGiaTienGiamToiDaPT(soTienGiamToiDa);
        khuyenMai.setPhanTramGiam(phanTramGiam/100);
        khuyenMai.setSoLuong(soLuong);
        khuyenMai.setTgBatDau(dateStart);
        khuyenMai.setTgKetThuc(dateEnd);
        khuyenMai.setTgSua(new Date());
        khuyenMai.setTenKM(tenKhuyenMai);
        khuyenMai.setTrangThai(trangThai);
        khuyenMai.setDieuKienKMBill(dieuKienKMHD);

        khuyenMaiService.createKhuyenMais(khuyenMai);

        return showPageVoucherBill(model, "Thay đổi", loaiKhuyenMai, true);
    }

//    Voucher Bill End

//Voucher San Pham Start

    @GetMapping("voucher-product")
    private String getPageVoucherProduct(Model model){

        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM02");
        List<KhuyenMai> khuyenMaiList = khuyenMaiService.findByLoaiKM(loaiKhuyenMai);
        List<CTGViewModel> ctgViewModelList = ctgViewModelService.getAll();

        model.addAttribute("listCTGViewModel",ctgViewModelList);
        model.addAttribute("listKhuyenMai", khuyenMaiList);
        model.addAttribute("listKhuyenMaiActive", khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMai));
        LocalDate currentDate = LocalDate.now();
        int soLuongKhuyenMaiHetHan = 0;

        for (KhuyenMai xx : khuyenMaiList) {
            Instant tgKetThucInstant = xx.getTgKetThuc().toInstant();

            LocalDateTime tgKetThucLocalDateTime = tgKetThucInstant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            if (tgKetThucLocalDateTime.isBefore(currentDate.atStartOfDay())) {
                soLuongKhuyenMaiHetHan ++;
                xx.setTrangThai(0);
                khuyenMaiService.createKhuyenMais(xx);
            }
            List<KhuyenMaiChiTietCTG> khuyenMaiChiTietCTGList = xx.getKhuyenMaiChiTietCTGList();

            for (KhuyenMaiChiTietCTG x: khuyenMaiChiTietCTGList ) {
                ChiTietGiay chiTietGiay = x.getChiTietGiay();
                chiTietGiay.setGiaBan(chiTietGiay.getSoTienTruocKhiGiam());
                chiTietService.save(chiTietGiay);
                khuyenMaiChiTietProductService.removeKMCTSP(x);
            }
        }
        if (soLuongKhuyenMaiHetHan != 0){
            model.addAttribute("showSoLuongKhuyenMaiHetHan", true);
            model.addAttribute("soLuongKhuyenMaiHetHan", soLuongKhuyenMaiHetHan);
        }

        return "manage/manageVoucherSP";
    }

    @PostMapping("voucher-product/add")
    private String addKhuyenMaiProduct(Model model){
        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM02");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String tenKM = request.getParameter("tenKM");
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateStart");
        String phanTramGiam = request.getParameter("phanTramGiam");
        String soTienToiDa = request.getParameter("soTienToiDa");
        String soTienGiamGia = request.getParameter("soTienGiamGia");
        String moTa = request.getParameter("moTa");
        String loaiGiamAdd = request.getParameter("loaiGiamAdd");
        String trangThaiAdd = request.getParameter("trangThaiAdd");

        Date dateStartFommat = new Date();
        Date dateEndFommat = new Date();

        Date dateToday = new Date();
        String maKM = "KM_SP0" + dateToday.getDate() + generateRandomNumbers();

        try {
            Date date = dateFormat.parse(dateStart);
            Date date2 = dateFormat.parse(dateEnd);
            dateStartFommat = date;
            dateEndFommat = date2;
        } catch (ParseException e) {
        }

        KhuyenMai khuyenMai = new KhuyenMai();
        khuyenMai.setLoaiKhuyenMai(loaiKhuyenMai);
        khuyenMai.setTenKM(tenKM);
        khuyenMai.setTrangThai(Integer.parseInt(trangThaiAdd));
        khuyenMai.setTgKetThuc(dateEndFommat);
        khuyenMai.setTgBatDau(dateStartFommat);
        khuyenMai.setPhanTramGiam(Double.valueOf(phanTramGiam)/100);
        khuyenMai.setMoTa(moTa);
        khuyenMai.setGiaTienGiam(Double.valueOf(soTienGiamGia));
        khuyenMai.setGiaTienGiamToiDaPT(Double.valueOf(soTienToiDa));
        khuyenMai.setLoaiGiam(Boolean.valueOf(loaiGiamAdd));
        khuyenMai.setTgThem(dateToday);
        khuyenMai.setMaKM(maKM);

        khuyenMaiService.createKhuyenMais(khuyenMai);

        return showData(model, "Thêm");
    }

    @PostMapping("voucher/voucher-product/update")
    private String updateVoucherVoucherProduct(Model model, @RequestParam("idGiayIdMauVoucher") List<String> listIdGiayIdMauVoucher){
        UUID idKM = UUID.fromString(request.getParameter("khuyenMaiSelected"));
        KhuyenMai khuyenMai = khuyenMaiService.findByID(idKM);

        session.removeAttribute("listTrung");
        session.removeAttribute("khuyenMaiConfirm");

        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM02");
        List<String> listMauTrung = new ArrayList<>();

        List<CTGViewModel> listTenGiayTrung = new ArrayList<>();

        for (String x: listIdGiayIdMauVoucher ) {

            String[] uuidStrings = x.split("/");
            UUID idMau = UUID.fromString(uuidStrings[0]);
            UUID idGiay = UUID.fromString(uuidStrings[1]);

            MauSac mauSac = mauSacService.getByIdMauSac(idMau);
            Giay giay = giayService.getByIdGiay(idGiay);

            if (mauSac == null){
                mauSac = mauSacService.getByIdMauSac(idGiay);
                giay = giayService.getByIdGiay(idMau);
            }
//            Xử lý sự kiện khuyến mãi bị trùng
            CTGViewModel ctgViewModel = ctgViewModelService.findByIDGiayAndMau(giay.getIdGiay(), mauSac.getIdMau());
            if (ctgViewModel.getTenKM() == null){
                List<ChiTietGiay> chiTietGiayList = chiTietService.findByMauSacAndGiay(mauSac, giay, 1);
                for (ChiTietGiay z: chiTietGiayList) {
                    KhuyenMaiChiTietCTG khuyenMaiChiTietCTG = new KhuyenMaiChiTietCTG();
                    khuyenMaiChiTietCTG.setKhuyenMai(khuyenMai);
                    khuyenMaiChiTietCTG.setChiTietGiay(z);
                    khuyenMaiChiTietCTG.setTenKM(khuyenMai.getTenKM());
                    khuyenMaiChiTietProductService.addKMCTSP(khuyenMaiChiTietCTG);
                    hamGiamTien(z, khuyenMai);
                }
            }else{
                listTenGiayTrung.add(ctgViewModel);
                String mauGiayTrung = mauSac.getIdMau() + "/" + giay.getIdGiay();
                listMauTrung.add(mauGiayTrung);
            }
//            Kết thúc
        }

        if (listTenGiayTrung.size() == 0){

            return showData(model, "Thay đổi");

        }else{
            model.addAttribute("sumKMTrung", listTenGiayTrung.size());
            session.setAttribute("listTrung", listMauTrung);
            session.setAttribute("khuyenMaiConfirm", khuyenMai);

            List<KhuyenMai> khuyenMaiList = khuyenMaiService.findByLoaiKM(loaiKhuyenMai);
            List<CTGViewModel> ctgViewModelList = ctgViewModelService.getAll();

            model.addAttribute("listCTGViewModel",ctgViewModelList);
            model.addAttribute("listKhuyenMai", khuyenMaiList);
            model.addAttribute("showMessKMTrung", true);

            return "manage/manageVoucherSP";
        }

    }

    @GetMapping("voucher/voucher-product/confirm")
    private String confirmUpdateVoucher(Model model){

        List<String> listTrungConfirm =(List) session.getAttribute("listTrung");
        KhuyenMai khuyenMai = (KhuyenMai) session.getAttribute("khuyenMaiConfirm");


        for (String x: listTrungConfirm) {
            String[] uuidStrings = x.split("/");
            UUID idMau = UUID.fromString(uuidStrings[0]);
            UUID idGiay = UUID.fromString(uuidStrings[1]);

            MauSac mauSac = mauSacService.getByIdMauSac(idMau);
            Giay giay = giayService.getByIdGiay(idGiay);

            List<ChiTietGiay> listCTGConfirm = chiTietService.findByMauSacAndGiay(mauSac, giay, 1);

            for (ChiTietGiay xx: listCTGConfirm) {

                KhuyenMaiChiTietCTG khuyenMaiChiTietCTG = khuyenMaiChiTietProductService.findByIDCTSP(xx);

                khuyenMaiChiTietProductService.removeKMCTSP(khuyenMaiChiTietCTG);

                KhuyenMaiChiTietCTG khuyenMaiChiTietCTGNew = new KhuyenMaiChiTietCTG();
                khuyenMaiChiTietCTGNew.setKhuyenMai(khuyenMai);
                khuyenMaiChiTietCTGNew.setChiTietGiay(xx);
                khuyenMaiChiTietCTGNew.setTgSua(new Date());
                khuyenMaiChiTietCTGNew.setTrangThai(1);
                khuyenMaiChiTietCTGNew.setTenKM(khuyenMai.getTenKM());

                khuyenMaiChiTietProductService.addKMCTSP(khuyenMaiChiTietCTGNew);
            }

        }
        return showData(model, "Thay đổi");
    }

    @PostMapping("voucher/voucher-product/remove")
    private String removeVoucherVoucherProduct(Model model, @RequestParam("idGiayIdMauVoucher") List<String> listIdGiayIdMauVoucher){
        for (String x: listIdGiayIdMauVoucher ) {
            String[] uuidStrings = x.split("/");
            UUID idMau = UUID.fromString(uuidStrings[0]);
            UUID idGiay = UUID.fromString(uuidStrings[1]);

            MauSac mauSac = mauSacService.getByIdMauSac(idMau);
            Giay giay = giayService.getByIdGiay(idGiay);

            if (mauSac == null){
                mauSac = mauSacService.getByIdMauSac(idGiay);
                giay = giayService.getByIdGiay(idMau);
            }

            List<ChiTietGiay> chiTietGiayList = chiTietService.findByMauSacAndGiay(mauSac, giay, 1);

            for (ChiTietGiay z: chiTietGiayList) {
                KhuyenMaiChiTietCTG khuyenMaiChiTietCTG = khuyenMaiChiTietProductService.findByIDCTSP(z);
                khuyenMaiChiTietProductService.removeKMCTSP(khuyenMaiChiTietCTG);
                z.setGiaBan(z.getSoTienTruocKhiGiam());
                chiTietService.save(z);
            }
        }
        return showData(model, "Thay đổi");
    }

    @PostMapping("voucher/update/voucher-product/{idVoucher}")
    private String updateVoucherProduct(Model model, @PathVariable UUID idVoucher){
        KhuyenMai khuyenMai = khuyenMaiService.findByID(idVoucher);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String tenKhuyenMai = request.getParameter("tenKhuyenMai");
        Boolean loaiGiam  =Boolean.valueOf(request.getParameter("loaiGiam"));
        String ngayBatDau = request.getParameter("ngayBatDau");
        String ngayKetThuc = request.getParameter("ngayKetThuc");
        String soTienGiam = request.getParameter("soTienGiamGia");
        Integer trangThai = Integer.parseInt(request.getParameter("trangThai"));
        String phanTramGiam = request.getParameter("phanTramGiam");
        String soTienGiamTD = request.getParameter("soTienToiDa");
        String moTa = request.getParameter("moTa");

        Date dateStart = new Date();
        Date dateEnd = new Date();

        try {
            Date date = dateFormat.parse(ngayBatDau);
            Date date2 = dateFormat.parse(ngayKetThuc);
            dateStart = date;
            dateEnd = date2;
        } catch (ParseException e) {
        }

        Double soTienGiamNew = Double.valueOf(soTienGiam);
        Double phanTramGiamNew = Double.valueOf(phanTramGiam);
        Double soTienTheoPhanTramGiam = Double.valueOf(soTienGiamTD);

        khuyenMai.setLoaiGiam(loaiGiam);
        khuyenMai.setGiaTienGiam(soTienGiamNew);
        khuyenMai.setMoTa(moTa);
        khuyenMai.setGiaTienGiamToiDaPT(soTienTheoPhanTramGiam);
        khuyenMai.setPhanTramGiam(phanTramGiamNew/100);
        khuyenMai.setTgBatDau(dateStart);
        khuyenMai.setTgKetThuc(dateEnd);
        khuyenMai.setTgSua(new Date());
        khuyenMai.setTenKM(tenKhuyenMai);
        khuyenMai.setTrangThai(trangThai);

        khuyenMaiService.createKhuyenMais(khuyenMai);

        List<KhuyenMaiChiTietCTG> khuyenMaiChiTietCTGList = khuyenMai.getKhuyenMaiChiTietCTGList();
        for (KhuyenMaiChiTietCTG x: khuyenMaiChiTietCTGList ) {
            ChiTietGiay chiTietGiay = x.getChiTietGiay();
            hamGiamTien(chiTietGiay, khuyenMai);
        }
        if (trangThai == 0){
            for (KhuyenMaiChiTietCTG x: khuyenMaiChiTietCTGList ) {
                ChiTietGiay chiTietGiay = x.getChiTietGiay();
                chiTietGiay.setGiaBan(chiTietGiay.getSoTienTruocKhiGiam());
                chiTietService.save(chiTietGiay);
                khuyenMaiChiTietProductService.removeKMCTSP(x);
            }
        }

        return showData(model, "Thay đổi");
    }

    @GetMapping("voucher/voucher-product/product-promotion")
    private String showProductHasPromotion(Model model){
        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM02");
        List<KhuyenMai> khuyenMaiList = khuyenMaiService.findByLoaiKM(loaiKhuyenMai);
        List<CTGViewModel> ctgViewModelList = ctgViewModelService.getAllProductPromotion();

        model.addAttribute("listCTGViewModel",ctgViewModelList);
        model.addAttribute("listKhuyenMai", khuyenMaiList);
        model.addAttribute("listKhuyenMaiActive", khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMai));
        return "manage/manageVoucherSP";
    }

    @GetMapping("voucher/voucher-product/product-nonpromotion")
    private String showProductNonPromotion(Model model){
        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM02");
        List<KhuyenMai> khuyenMaiList = khuyenMaiService.findByLoaiKM(loaiKhuyenMai);
        List<CTGViewModel> ctgViewModelList = ctgViewModelService.getAllProductNonPromotion();

        model.addAttribute("listCTGViewModel",ctgViewModelList);
        model.addAttribute("listKhuyenMai", khuyenMaiList);
        model.addAttribute("listKhuyenMaiActive", khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMai));
        return "manage/manageVoucherSP";
    }

// Voucher San Pham End

    @GetMapping("voucher-ship")
    private String getPageVoucherShip(Model model){

        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM03");

        return showPageVoucherShipping(model, "", loaiKhuyenMai, false);
    }

    @PostMapping("/voucher/voucher-shipping/add")
    private String addVoucherShip(Model model){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM03");

        String tenKM = request.getParameter("tenKM");
        Boolean loaiGiamGia = Boolean.valueOf(request.getParameter("loaiGiamAdd"));
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        Integer soLuongKM = Integer.parseInt(request.getParameter("soLuongKM"));
        Double soTienGiamGia = Double.valueOf(request.getParameter("soTienGiamGia"));
        Integer trangThaiVoucher = Integer.parseInt(request.getParameter("trangThaiAdd"));
        Double phanTramGiam = Double.valueOf(request.getParameter("phanTramGiam"));
        Double soTienToiDa = Double.valueOf(request.getParameter("soTienToiDa"));
        Double dieuKienKMHD = Double.valueOf(request.getParameter("dieuKienKMHD"));
        String moTa = request.getParameter("moTa");


        Date dateStartFommat = null;
        Date dateEndFommat = null;

        Date dateToday = new Date();
        String maKM = "KM_HD0" + dateToday.getDate() + generateRandomNumbers();

        try {
            Date date = dateFormat.parse(dateStart);
            Date date2 = dateFormat.parse(dateEnd);
            dateStartFommat = date;
            dateEndFommat = date2;
        } catch (ParseException e) {
        }

        KhuyenMai khuyenMai = new KhuyenMai();

        khuyenMai.setLoaiKhuyenMai(loaiKhuyenMai);
        khuyenMai.setMaKM(maKM);
        khuyenMai.setGiaTienGiamToiDaPT(soTienToiDa);
        khuyenMai.setTgThem(dateToday);
        khuyenMai.setGiaTienGiam(soTienGiamGia);
        khuyenMai.setLoaiGiam(loaiGiamGia);
        khuyenMai.setMoTa(moTa);
        khuyenMai.setTrangThai(trangThaiVoucher);
        khuyenMai.setPhanTramGiam(phanTramGiam/100);
        khuyenMai.setTgBatDau(dateStartFommat);
        khuyenMai.setTgKetThuc(dateEndFommat);
        khuyenMai.setSoLuong(soLuongKM);
        khuyenMai.setDieuKienKMBill(dieuKienKMHD);
        khuyenMai.setTenKM(tenKM);

        khuyenMaiService.createKhuyenMais(khuyenMai);

        return showPageVoucherShipping(model, "Tạo mới" , loaiKhuyenMai, true);
    }

    @PostMapping("voucher/voucher-shipping/update/{idVoucher}")
    private String updateVoucherShip(Model model, @PathVariable UUID idVoucher){
        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM03");
        KhuyenMai khuyenMai = khuyenMaiService.findByID(idVoucher);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String tenKhuyenMai = request.getParameter("tenKM");
        Boolean loaiGiam = Boolean.valueOf(request.getParameter("loaiGiam"));
        String ngayBatDau = request.getParameter("ngayBatDau");
        String ngayKetThuc = request.getParameter("ngayKetThuc");
        Integer soLuong = Integer.parseInt(request.getParameter("soLuong"));
        Double soTienGiamGia = Double.parseDouble(request.getParameter("soTienGiamGia"));
        Integer trangThai = Integer.parseInt(request.getParameter("trangThai"));
        Double phanTramGiam = Double.parseDouble(request.getParameter("phanTramGiam"));
        Double soTienGiamToiDa = Double.parseDouble(request.getParameter("soTienGiamGia"));
        Double dieuKienKMHD = Double.parseDouble(request.getParameter("dieuKienKMHD"));
        String moTa = request.getParameter("moTa");

        Date dateStart = null;
        Date dateEnd = null;

        try {
            Date date = dateFormat.parse(ngayBatDau);
            Date date2 = dateFormat.parse(ngayKetThuc);
            dateStart = date;
            dateEnd = date2;
        } catch (ParseException e) {
        }

        khuyenMai.setLoaiGiam(loaiGiam);
        khuyenMai.setGiaTienGiam(soTienGiamGia);
        khuyenMai.setMoTa(moTa);
        khuyenMai.setGiaTienGiamToiDaPT(soTienGiamToiDa);
        khuyenMai.setPhanTramGiam(phanTramGiam/100);
        khuyenMai.setSoLuong(soLuong);
        khuyenMai.setTgBatDau(dateStart);
        khuyenMai.setTgKetThuc(dateEnd);
        khuyenMai.setTgSua(new Date());
        khuyenMai.setTenKM(tenKhuyenMai);
        khuyenMai.setTrangThai(trangThai);
        khuyenMai.setDieuKienKMBill(dieuKienKMHD);

        khuyenMaiService.createKhuyenMais(khuyenMai);

        return showPageVoucherShipping(model, "Thay đổi", loaiKhuyenMai, true);
    }

    private String showDataVoucher(Model model, String mess, String loaiKM, boolean modalMess){

        List<KhuyenMai> khuyenMaiList = khuyenMaiService.getAllKhuyenMai();

        List<KhuyenMai> khuyenMaiListHD =khuyenMaiService.findByLoaiKM(loaiKhuyenMaiService.findByMaLKM("LKM01"));
        List<KhuyenMai> khuyenMaiListSP =khuyenMaiService.findByLoaiKM(loaiKhuyenMaiService.findByMaLKM("LKM02"));
        List<KhuyenMai> khuyenMaiListShip =khuyenMaiService.findByLoaiKM(loaiKhuyenMaiService.findByMaLKM("LKM03"));

        LocalDate currentDate = LocalDate.now();
        int soLuongKhuyenMaiHetHan = 0;

        for (KhuyenMai xx : khuyenMaiList) {
            Instant tgKetThucInstant = xx.getTgKetThuc().toInstant();

            LocalDateTime tgKetThucLocalDateTime = tgKetThucInstant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            if (tgKetThucLocalDateTime.isBefore(currentDate.atStartOfDay())) {
                soLuongKhuyenMaiHetHan ++;
                xx.setTrangThai(0);
                khuyenMaiService.createKhuyenMais(xx);
            }
        }

        model.addAttribute("listKhuyenMai", khuyenMaiList);
        model.addAttribute("listKhuyenMaiBill", khuyenMaiListHD);
        model.addAttribute("listKhuyenMaiSP", khuyenMaiListSP);
        model.addAttribute("listKhuyenMaiShip", khuyenMaiListShip);
        model.addAttribute("thongBaoABC", mess);
        model.addAttribute("loaiKM", loaiKM);
        model.addAttribute("showModalMess", modalMess);
        if (soLuongKhuyenMaiHetHan != 0){
            model.addAttribute("showSoLuongKhuyenMaiHetHan", true);
            model.addAttribute("soLuongKhuyenMaiHetHan", soLuongKhuyenMaiHetHan);
        }

        return "manage/manageVoucher";
    }

    private String showPageVoucherShipping(Model model, String mess, LoaiKhuyenMai loaiKhuyenMai, boolean thongBao){
        List<KhuyenMai> khuyenMaiList = khuyenMaiService.findByLoaiKM(loaiKhuyenMai);

        LocalDate currentDate = LocalDate.now();
        int soLuongKhuyenMaiHetHan = 0;

        for (KhuyenMai xx : khuyenMaiList) {
            Instant tgKetThucInstant = xx.getTgKetThuc().toInstant();

            LocalDateTime tgKetThucLocalDateTime = tgKetThucInstant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            if (tgKetThucLocalDateTime.isBefore(currentDate.atStartOfDay()) || xx.getSoLuong() <= xx.getSoLuongDaDung()) {
                soLuongKhuyenMaiHetHan ++;
                xx.setTrangThai(0);
                khuyenMaiService.createKhuyenMais(xx);
            }
        }
        if (soLuongKhuyenMaiHetHan != 0){
            model.addAttribute("showSoLuongKhuyenMaiHetHan", true);
            model.addAttribute("soLuongKhuyenMaiHetHan", soLuongKhuyenMaiHetHan);
        }

        model.addAttribute("thongBaoABC", mess);
        model.addAttribute("showModalMess", thongBao);
        model.addAttribute("listKhuyenMai", khuyenMaiList);

        return "manage/manageVoucherShipping";
    }

    private String showPageVoucherBill(Model model, String mess, LoaiKhuyenMai loaiKhuyenMai, boolean thongBao){
        List<KhuyenMai> khuyenMaiList = khuyenMaiService.findByLoaiKM(loaiKhuyenMai);

        LocalDate currentDate = LocalDate.now();
        int soLuongKhuyenMaiHetHan = 0;

        for (KhuyenMai xx : khuyenMaiList) {
            Instant tgKetThucInstant = xx.getTgKetThuc().toInstant();

            LocalDateTime tgKetThucLocalDateTime = tgKetThucInstant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            if (tgKetThucLocalDateTime.isBefore(currentDate.atStartOfDay())) {
                soLuongKhuyenMaiHetHan ++;
                xx.setTrangThai(0);
                khuyenMaiService.createKhuyenMais(xx);
            }
        }
        if (soLuongKhuyenMaiHetHan != 0){
            model.addAttribute("showSoLuongKhuyenMaiHetHan", true);
            model.addAttribute("soLuongKhuyenMaiHetHan", soLuongKhuyenMaiHetHan);
        }
        model.addAttribute("thongBaoABC", mess);
        model.addAttribute("showModalMess", thongBao);
        model.addAttribute("listKhuyenMai", khuyenMaiList);

        return "manage/manageVoucherBill";
    }

    private void hamGiamTien(ChiTietGiay chiTietGiay, KhuyenMai khuyenMai){
        if (khuyenMai.isLoaiGiam() == true){
            Double giaBan = chiTietGiay.getSoTienTruocKhiGiam() - khuyenMai.getGiaTienGiam();
            chiTietGiay.setGiaBan(giaBan);
            chiTietService.save(chiTietGiay);
        }else{
            Double giaTienGiamTheoPhanTram = chiTietGiay.getGiaBan()*khuyenMai.getPhanTramGiam();

            Double giaBan = 0.0;

            if (giaTienGiamTheoPhanTram > khuyenMai.getGiaTienGiamToiDaPT()){
                giaBan = chiTietGiay.getSoTienTruocKhiGiam() - khuyenMai.getGiaTienGiamToiDaPT();
            }else{
                giaBan = chiTietGiay.getSoTienTruocKhiGiam() - giaTienGiamTheoPhanTram;
            }
            chiTietGiay.setGiaBan(giaBan);
            chiTietService.save(chiTietGiay);
        }
    }

    private String generateRandomNumbers() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int randomNumber = random.nextInt(10); // Tạo số ngẫu nhiên từ 0 đến 9
            sb.append(randomNumber);
        }
        return sb.toString();
    }

    private String showData(Model model, String thongBao){
        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM02");
        List<KhuyenMai> khuyenMaiList = khuyenMaiService.findByLoaiKM(loaiKhuyenMai);
        List<CTGViewModel> ctgViewModelList = ctgViewModelService.getAll();

        model.addAttribute("listCTGViewModel",ctgViewModelList);
        model.addAttribute("listKhuyenMai", khuyenMaiList);
        model.addAttribute("showModalMess", true);
        model.addAttribute("thongBaoABC", thongBao);
        model.addAttribute("listKhuyenMaiActive", khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMai));
        return "manage/manageVoucherSP";
    }

}
