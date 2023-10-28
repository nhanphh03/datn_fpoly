package com.example.demo.controller;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.Giay;
import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.model.KhachHang;
import com.example.demo.repository.SizeRepository;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.GiayService;
import com.example.demo.service.GiayViewModelService;
import com.example.demo.service.HangService;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.KhachHangService;
import com.example.demo.viewModel.GiayViewModel;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ban-hang")
public class BanHangController {


    @Autowired
    private HangService hangService;

    @Autowired
    private GiayChiTietService giayChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private GiayService giayService;

    @Autowired
    private GiayViewModelService giayViewModelService;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    private double tongTien = 0.0;
    private UUID idHoaDon = null;

    @GetMapping("/offline")
    public String offline() {
        return "manage/activities";
    }

    @RequestMapping(value = {"", "/", "/home", "/hien-thi"})
    public String hienThi(Model model) {
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        model.addAttribute("tongtien", this.tongTien);
        model.addAttribute("listKhachHang", khachHangService.findKhachHangByTrangThai());
        return "offline/index";
    }

    @GetMapping("/add-cart")
    public String taoHoaDon(Model model) {
        List<HoaDon> listHD = hoaDonService.getListHoaDonChuaThanhToan();
        if (listHD.size() < 3) {
            String ma = String.valueOf(Math.floor(((Math.random() * 899999) + 100000)));
            HoaDon hd = new HoaDon();
            hd.setMaHD("hd" + ma);
            hd.setTgTao(new Date());
            hd.setTrangThai(3);
            hoaDonService.add(hd);
            model.addAttribute("message", "Tạo hóa đơn thành công");
        } else {
            model.addAttribute("message", "Quá số lượng");
        }
        model.addAttribute("listHoaDon", listHD);
        return "redirect:/ban-hang/hien-thi";
    }

    @GetMapping("/cart/hoadon/{idHoaDon}")
    public String chonHoaDon(@PathVariable("idHoaDon") UUID idHoaDon, Model model) {
        httpSession.setAttribute("idHoaDon", idHoaDon);
        this.idHoaDon = idHoaDon;
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        if (findByIdHoaDon.isEmpty()) {
            model.addAttribute("messageGioHang", "Trong giỏ hàng chưa có sản phẩm");
        } else {
            model.addAttribute("gioHang", findByIdHoaDon);
        }
        model.addAttribute("tongTien", hoaDonChiTietService.tongTien(findByIdHoaDon));
        model.addAttribute("listKhachHang", khachHangService.findKhachHangByTrangThai());
        model.addAttribute("khachHang",httpSession.getAttribute("khachHang"));
        return "offline/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        UUID idHoaDon = (UUID) httpSession.getAttribute("idHoaDon");
        if (idHoaDon == null) {
            model.addAttribute("nullHoaDon", "Bạn chưa chọn hóa đơn");
            return "offline/index";
        }
        List<GiayViewModel> list = giayViewModelService.getAll(keyword);
        model.addAttribute("listSanPham", list);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());

        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        if (findByIdHoaDon.isEmpty()) {
            model.addAttribute("messageGioHang", "Trong giỏ hàng chưa có sản phẩm");
        } else {
            model.addAttribute("tongTien", hoaDonChiTietService.tongTien(findByIdHoaDon));
            model.addAttribute("gioHang", findByIdHoaDon);
        }
        return "offline/index";
    }

    @GetMapping("/chon-size/{idGiay}/{mauSac}")
    public String chonSize(@PathVariable(value = "idGiay") UUID idGiay,
                           @PathVariable(value = "mauSac") String mauSac, Model model) {
        Giay giay = giayService.getByIdGiay(idGiay);
        List<ChiTietGiay> sizeList = sizeRepository.findByIdGiayAndMauSac2(idGiay, mauSac);
        model.addAttribute("giay", giay);
        model.addAttribute("listChiTietGiay", sizeList);
        model.addAttribute("idHoaDon",idHoaDon);
        model.addAttribute("showModal", true);
        return "offline/index";
    }

    @GetMapping("/add-to-cart")
    public String addToCart(@RequestParam("idChiTietGiay") UUID idChiTietGiay,
                            @RequestParam("soLuong") int soLuong, Model model) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(idChiTietGiay);
        UUID idHoaDon = (UUID) httpSession.getAttribute("idHoaDon");
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idHoaDon, idChiTietGiay);
        System.out.println(idHoaDon);
        System.out.println(chiTietGiay.toString());
        if (hoaDonChiTiet != null) {
            hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong() + soLuong);
            hoaDonChiTiet.setTgSua(new Date());
            hoaDonChiTiet.setTrangThai(1);
            hoaDonChiTietService.add(hoaDonChiTiet);
        } else {
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setChiTietGiay(chiTietGiay);
            hdct.setHoaDon(hoaDon);
            hdct.setDonGia(chiTietGiay.getGiaBan());
            hdct.setSoLuong(soLuong);
            hdct.setTrangThai(1);
            hdct.setTgThem(new Date());
            hoaDonChiTietService.add(hdct);
        }
//        List<HoaDonChiTiet> findByIdHoaDon= hoaDonChiTietService.findByIdHoaDon(idHoaDon);
//        model.addAttribute("gioHang",findByIdHoaDon);
//        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());

        // tong tien
//        tongTien = hoaDonChiTietService.tongTien(findByIdHoaDon);
//        model.addAttribute("tongTien", this.tongTien);
        // cập nhật sl ctg
        chiTietGiay.setSoLuong(chiTietGiay.getSoLuong() - soLuong);
        giayChiTietService.save(chiTietGiay);
        return "redirect:/ban-hang/cart/hoadon/" + this.idHoaDon;
    }

    @GetMapping("/quet-qr/{idChiTietGiay}")
    public String quetQr(@PathVariable(value = "idChiTietGiay") UUID idChiTietGiay, Model model) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(idChiTietGiay);
        if (chiTietGiay == null) {
            model.addAttribute("message", "Ảnh QR không phù hợp");
            model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
            return "offline/index";
//            return "redirect:/ban-hang/cart/hoadon/" + this.idHoaDon;
        }
        model.addAttribute("quetQR", chiTietGiay);
        model.addAttribute("idHoaDon",idHoaDon);
        model.addAttribute("showModalQuetQR", true);
        return "offline/index";
    }

    @GetMapping("/xoa-gio-hang/{idChiTietGiay}")
    public String xoaSanPham(@PathVariable("idChiTietGiay") UUID idChiTietGiay, Model model) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(idChiTietGiay);
        UUID idHoaDon = (UUID) httpSession.getAttribute("idHoaDon");
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idHoaDon, idChiTietGiay);

        chiTietGiay.setSoLuong(chiTietGiay.getSoLuong() + hoaDonChiTiet.getSoLuong());
        giayChiTietService.save(chiTietGiay);

        hoaDonChiTiet.setTrangThai(0);
        hoaDonChiTiet.setSoLuong(0);
        hoaDonChiTietService.add(hoaDonChiTiet);
//        List<HoaDonChiTiet> findByIdHoaDon= hoaDonChiTietService.findByIdHoaDon(idHoaDon);
//        model.addAttribute("gioHang",findByIdHoaDon);
//        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
//        model.addAttribute("tongTien",hoaDonChiTietService.tongTien(findByIdHoaDon));
//        return "offline/index";
        return "redirect:/ban-hang/cart/hoadon/" + idHoaDon;
    }

    @GetMapping("/list-khach-hang")
    public String listKH(Model model) {
        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        model.addAttribute("gioHang", findByIdHoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        model.addAttribute("listKhachHang", khachHangService.findKhachHangByTrangThai());
        model.addAttribute("idHoaDon",idHoaDon);
        model.addAttribute("showModalKhachHang", true);
        return "offline/index";
    }

    @GetMapping("/chon-khach-hang/{idKhachHang}")
    public String chonKhachHang(@PathVariable("idKhachHang") UUID idKhachHang, Model model) {
        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        model.addAttribute("gioHang", findByIdHoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        KhachHang khachHang = khachHangService.getByIdKhachHang(idKhachHang);
        httpSession.setAttribute("khachHang", khachHang);
        model.addAttribute("khachHang",khachHang);
        //update khách hàng
        UUID idHoaDon = (UUID) httpSession.getAttribute("idHoaDon");
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
        hoaDon.setKhachHang(khachHang);
        hoaDonService.add(hoaDon);
        return "redirect:/ban-hang/cart/hoadon/" + idHoaDon;
    }

    @GetMapping("/search-khach-hang")
    public String searchKhachHang(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        model.addAttribute("gioHang", findByIdHoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        model.addAttribute("showModalKhachHang", true);
        List<KhachHang> search = khachHangService.findKhachHangByKeyword(keyword);
        model.addAttribute("listKhachHang", search);
        model.addAttribute("idHoaDon",idHoaDon);
        return "offline/index";
    }

    @GetMapping("/khach-hang/viewAdd")
    public String viewAdd(Model model) {
        model.addAttribute("addKH", new KhachHang());
        model.addAttribute("showModalAddKH", true);
        return "offline/index";
    }

    @PostMapping("/khach-hang/add")
    public String addKhachHang(@Valid @ModelAttribute("kh") KhachHang khachHang, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "offline/index";
        } else {
            String ma = String.valueOf(Math.floor(((Math.random() * 899999) + 100000)));
            khachHang.setMaKH("KH" + ma);
            khachHang.setTrangThai(1);
            khachHang.setTgThem(new Date());

            khachHangService.addKhachHang(khachHang);
//
//            // up date kh vào hóa đơn
            UUID idHoaDon = (UUID) httpSession.getAttribute("idHoaDon");
            HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
            hoaDon.setKhachHang(khachHang);
            hoaDonService.add(hoaDon);
            httpSession.setAttribute("khachHang", khachHang);
            return "redirect:/ban-hang/cart/hoadon/" + idHoaDon;
        }
    }

    //thanh toan
    @GetMapping("/thanh-toan")
    public String thanhToan(){
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
        hoaDon.setTrangThai(0);
        hoaDon.setTgThanhToan(new Date());
        hoaDonService.add(hoaDon);

        httpSession.invalidate();
        return "redirect:/ban-hang/hien-thi";
    }
}
