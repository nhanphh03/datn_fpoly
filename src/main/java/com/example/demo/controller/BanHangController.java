package com.example.demo.controller;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.Giay;
import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.model.KhachHang;
import com.example.demo.model.NhanVien;
import com.example.demo.model.*;
import com.example.demo.repository.SizeRepository;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.GiayService;
import com.example.demo.service.GiayViewModelService;
import com.example.demo.service.HangService;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.KhuyenMaiService;
import com.example.demo.service.LoaiKhachHangService;
import com.example.demo.service.LoaiKhuyenMaiService;
import com.example.demo.service.NhanVienService;
import com.example.demo.viewModel.GiayViewModel;
import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private LoaiKhuyenMaiService loaiKhuyenMaiService;

    @Autowired
    private KhuyenMaiService khuyenMaiService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoaiKhachHangService loaiKhachHangService;

    private double tongTien = 0;
    private double tienKhuyenMai = 0;
    private double tongTienSauKM = tongTien - tienKhuyenMai;
    private UUID idHoaDon = null;
    private int tongSanPham = 0;


    @RequestMapping(value = {"", "/", "/home", "/hien-thi"})
    public String hienThi(Model model
            , @ModelAttribute("messageSuccess") String messageSuccess
            , @ModelAttribute("messageError") String messageError) {
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        model.addAttribute("tongTien", this.tongTien);
        model.addAttribute("tongSanPham", this.tongSanPham);
        model.addAttribute("khuyenMai", this.tienKhuyenMai);
        model.addAttribute("tongTienSauKM", tongTien - tienKhuyenMai);
        model.addAttribute("listKhachHang", khachHangService.findKhachHangByTrangThai());
        if (!"true".equals(messageSuccess)) {
            model.addAttribute("messageSuccess", false);
        }
        if (!"true".equals(messageError)) {
            model.addAttribute("messageError", false);
        }
        return "offline/index";
    }

    @GetMapping("/add-cart")
    public String taoHoaDon(Model model, RedirectAttributes redirectAttributes) {
        NhanVien nhanVien = (NhanVien) httpSession.getAttribute("staffLogged");
        List<HoaDon> listHD = hoaDonService.getListHoaDonChuaThanhToan();
        if (listHD.size() < 3) {
            String ma = String.valueOf(Math.floor(((Math.random() * 899999) + 100000)));
            HoaDon hd = new HoaDon();
            Date date = new Date();
            hd.setMaHD("HD" + date.getDate() + ma);
            hd.setTgTao(new Date());
            hd.setTrangThai(0);
            hd.setLoaiHD(1);
            hd.setNhanVien(nhanVien);
            hoaDonService.add(hd);
            model.addAttribute("message", "Tạo hóa đơn thành công");
            redirectAttributes.addFlashAttribute("messageSuccess", true);
            redirectAttributes.addFlashAttribute("tb", "Tạo thành công hóa đơn");
        } else {
            redirectAttributes.addFlashAttribute("messageError", true);
            redirectAttributes.addFlashAttribute("tbaoError", "Quá số lượng hóa đơn");
        }
        model.addAttribute("listHoaDon", listHD);
        return "redirect:/ban-hang/hien-thi";
    }

    @GetMapping("/cart/hoadon/{idHoaDon}")
    public String chonHoaDon(@PathVariable("idHoaDon") UUID idHoaDon, Model model
            , @ModelAttribute("messageSuccess") String messageSuccess
            , @ModelAttribute("messageError") String messageError
    ) {
        httpSession.removeAttribute("idHoaDon");
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
        model.addAttribute("khachHang", httpSession.getAttribute("khachHang"));
        model.addAttribute("tongSanPham", findByIdHoaDon.size());
        httpSession.setAttribute("tongSP", findByIdHoaDon.size());
        httpSession.setAttribute("tongTien", hoaDonChiTietService.tongTien(findByIdHoaDon));

        //list KH
        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiService.findByMaLKM("LKM01");
        List<KhuyenMai> khuyenMaiList = khuyenMaiService.findByLoaiKMAndTrangThai(loaiKhuyenMai);
        model.addAttribute("listKhuyenMai", khuyenMaiList);

        //
        model.addAttribute("khuyenMai", this.tienKhuyenMai);
        model.addAttribute("tongTienSauKM", hoaDonChiTietService.tongTien(findByIdHoaDon) - tienKhuyenMai);
        // add tong tièn
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
        hoaDon.setTongTien(hoaDonChiTietService.tongTien(findByIdHoaDon));
        hoaDonService.add(hoaDon);

        if (messageSuccess == null || !"true".equals(messageSuccess)) {
            System.out.println(messageSuccess);
            model.addAttribute("messageSuccess", false);
        }
        if (messageError == null || !"true".equals(messageError)) {
            model.addAttribute("messageError", false);
        }
        return "offline/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model,
                         RedirectAttributes redirectAttributes) {
        UUID idHoaDon = (UUID) httpSession.getAttribute("idHoaDon");
        if (idHoaDon == null) {
            redirectAttributes.addFlashAttribute("messageError", true);
            redirectAttributes.addFlashAttribute("tbaoError", "Bạn chưa chọn hóa đơn");
            model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
            return "redirect:/ban-hang/";
        }

        if(keyword.substring(0,3).equals("CTG")){
            ChiTietGiay chiTietGiay = giayChiTietService.findByMa(keyword);
            System.out.println(keyword);
            if (chiTietGiay == null) {
                redirectAttributes.addFlashAttribute("messageError", true);
                redirectAttributes.addFlashAttribute("tbaoError", "Không tìm thấy sản mã phẩm ");
                return "redirect:/ban-hang/cart/hoadon/" + this.idHoaDon;
            }
            model.addAttribute("quetQR", chiTietGiay);
            model.addAttribute("idHoaDon", idHoaDon);
            model.addAttribute("showModalQuetQR", true);
        }else {
            List<GiayViewModel> list = giayViewModelService.getAll(keyword);
            if (list.isEmpty()) {
                redirectAttributes.addFlashAttribute("messageError", true);
                redirectAttributes.addFlashAttribute("tbaoError", "Không tìm thấy sản phẩm");
                return "redirect:/ban-hang/cart/hoadon/" + this.idHoaDon;
            }
            model.addAttribute("listSanPham", list);
        }


        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());

        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        if (findByIdHoaDon.isEmpty()) {
            model.addAttribute("messageGioHang", "Trong giỏ hàng chưa có sản phẩm");
        }
        model.addAttribute("tongTien", hoaDonChiTietService.tongTien(findByIdHoaDon));
        model.addAttribute("gioHang", findByIdHoaDon);
        model.addAttribute("khuyenMai", this.tienKhuyenMai);
        model.addAttribute("tongTienSauKM", hoaDonChiTietService.tongTien(findByIdHoaDon) - tienKhuyenMai);
        return "offline/index";
    }

    @GetMapping("/chon-size/{idGiay}/{mauSac}")
    public String chonSize(@PathVariable(value = "idGiay") UUID idGiay,
                           @PathVariable(value = "mauSac") String mauSac, Model model) {
        Giay giay = giayService.getByIdGiay(idGiay);
        List<ChiTietGiay> sizeList = sizeRepository.findByIdGiayAndMauSac2(idGiay, mauSac);
        model.addAttribute("giay", giay);
        model.addAttribute("listChiTietGiay", sizeList);
        model.addAttribute("idHoaDon", idHoaDon);
        model.addAttribute("showModal", true);

        model.addAttribute("tongTien", tongTien);
        model.addAttribute("khuyenMai", this.tienKhuyenMai);
        model.addAttribute("tongTienSauKM", tongTien - tienKhuyenMai);
        return "offline/index";
    }

    @GetMapping("/add-to-cart")
    public String addToCart(@RequestParam("idChiTietGiay") UUID idChiTietGiay,
                            @RequestParam("soLuong") int soLuong, Model model,
                            RedirectAttributes redirectAttributes) {
        UUID idHoaDon = (UUID) httpSession.getAttribute("idHoaDon");
        if (idHoaDon == null) {
            redirectAttributes.addFlashAttribute("messageError", true);
            redirectAttributes.addFlashAttribute("tbaoError", "Bạn chưa chọn hóa đơn");
            model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
            return "redirect:/ban-hang/";
        }
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(idChiTietGiay);
        if (soLuong > chiTietGiay.getSoLuong()) {
            redirectAttributes.addFlashAttribute("messageError", true);
            redirectAttributes.addFlashAttribute("tbaoError", "Số lượng trong kho không đủ");
            model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
            return "redirect:/ban-hang/cart/hoadon/" + this.idHoaDon;
        }
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idHoaDon, idChiTietGiay);
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
            hdct.setDonGiaKhiGiam(chiTietGiay.getGiaBan());
            hdct.setSoLuong(soLuong);
            hdct.setTrangThai(1);
            hdct.setTgThem(new Date());
            tongSanPham++;
            httpSession.setAttribute("tongSP", tongSanPham);
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
        redirectAttributes.addFlashAttribute("messageSuccess", true);
        redirectAttributes.addFlashAttribute("tb", "Thêm vào giỏ hàng thành công");
        return "redirect:/ban-hang/cart/hoadon/" + this.idHoaDon;
    }

    @GetMapping("/quet-qr/{idChiTietGiay}")
    public String quetQr(@PathVariable(value = "idChiTietGiay") UUID idChiTietGiay,
                         Model model, RedirectAttributes redirectAttributes) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(idChiTietGiay);
        if (chiTietGiay == null) {
            redirectAttributes.addFlashAttribute("messageError", true);
            redirectAttributes.addFlashAttribute("tbaoError", "Ảnh QR không đúng");
            return "redirect:/ban-hang/cart/hoadon/" + this.idHoaDon;
        }
        model.addAttribute("quetQR", chiTietGiay);
        model.addAttribute("idHoaDon", idHoaDon);
        model.addAttribute("khuyenMai", this.tienKhuyenMai);
        model.addAttribute("tongTienSauKM", tongTien - tienKhuyenMai);
        model.addAttribute("tongTien", tongTien);

        model.addAttribute("showModalQuetQR", true);
        return "offline/index";
    }

    @GetMapping("/xoa-gio-hang/{idChiTietGiay}")
    public String xoaSanPham(@PathVariable("idChiTietGiay") UUID idChiTietGiay, RedirectAttributes redirectAttributes) {
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(idChiTietGiay);
        UUID idHoaDon = (UUID) httpSession.getAttribute("idHoaDon");
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idHoaDon, idChiTietGiay);

        chiTietGiay.setSoLuong(chiTietGiay.getSoLuong() + hoaDonChiTiet.getSoLuong());
        giayChiTietService.save(chiTietGiay);

        hoaDonChiTiet.setTrangThai(0);
        hoaDonChiTiet.setSoLuong(0);
        hoaDonChiTietService.add(hoaDonChiTiet);
        tongSanPham--;
        httpSession.setAttribute("tongSP", tongSanPham);
        redirectAttributes.addFlashAttribute("messageSuccess", true);
        redirectAttributes.addFlashAttribute("tb", "Xóa thành công");
        return "redirect:/ban-hang/cart/hoadon/" + idHoaDon;
    }

    @GetMapping("/list-khach-hang")
    public String listKH(Model model) {
        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        model.addAttribute("gioHang", findByIdHoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        model.addAttribute("listKhachHang", khachHangService.findKhachHangByTrangThai());
        model.addAttribute("idHoaDon", idHoaDon);
        model.addAttribute("showModalKhachHang", true);

        model.addAttribute("tongTien", hoaDonChiTietService.tongTien(findByIdHoaDon));
        model.addAttribute("khuyenMai", this.tienKhuyenMai);
        model.addAttribute("tongTienSauKM", hoaDonChiTietService.tongTien(findByIdHoaDon) - tienKhuyenMai);
        return "offline/index";
    }

    @GetMapping("/chon-khach-hang/{idKhachHang}")
    public String chonKhachHang(@PathVariable("idKhachHang") UUID idKhachHang, Model model) {
        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        model.addAttribute("gioHang", findByIdHoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        KhachHang khachHang = khachHangService.getByIdKhachHang(idKhachHang);
        httpSession.setAttribute("khachHang", khachHang);
        model.addAttribute("khachHang", khachHang);
        //update khách hàng
        UUID idHoaDon = (UUID) httpSession.getAttribute("idHoaDon");
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
        hoaDon.setKhachHang(khachHang);
        hoaDonService.add(hoaDon);
        return "redirect:/ban-hang/cart/hoadon/" + idHoaDon;
    }

    @GetMapping("/search-khach-hang")
    public String searchKhachHang(@RequestParam(value = "keyword", required = false) String keyword,
                                  Model model, RedirectAttributes redirectAttributes) {
        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        model.addAttribute("gioHang", findByIdHoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        model.addAttribute("showModalKhachHang", true);
        List<KhachHang> search = khachHangService.findKhachHangByKeyword(keyword);
        if (search.isEmpty()) {
            redirectAttributes.addFlashAttribute("messageError", true);
            redirectAttributes.addFlashAttribute("tbaoError", "Không tìm thấy khách hàng");
            return "redirect:/ban-hang/cart/hoadon/" + idHoaDon;
        }
        model.addAttribute("listKhachHang", search);
        model.addAttribute("idHoaDon", idHoaDon);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("khuyenMai", this.tienKhuyenMai);
        model.addAttribute("tongTienSauKM", tongTien - tienKhuyenMai);
        model.addAttribute("khuyenMai", this.tienKhuyenMai);
        model.addAttribute("tongTienSauKM", hoaDonChiTietService.tongTien(findByIdHoaDon) - tienKhuyenMai);
        return "offline/index";
    }

    @GetMapping("/khach-hang/viewAdd")
    public String viewAdd(Model model) {
        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        model.addAttribute("gioHang", findByIdHoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        model.addAttribute("idHoaDon", idHoaDon);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("khuyenMai", this.tienKhuyenMai);
        model.addAttribute("tongTienSauKM", tongTien - tienKhuyenMai);
        model.addAttribute("khuyenMai", this.tienKhuyenMai);
        model.addAttribute("tongTienSauKM", hoaDonChiTietService.tongTien(findByIdHoaDon) - tienKhuyenMai);
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
            LoaiKhachHang loaiKhachHang = loaiKhachHangService.getByIdLoaiKhachHang(
                    UUID.fromString("22BE1F84-AE1D-442D-AAAF-4504F3552F70"));
            khachHang.setLoaiKhachHang(loaiKhachHang);
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
    public String thanhToan(RedirectAttributes redirectAttributes) {
        this.tongSanPham = (int) httpSession.getAttribute("tongSP");
        this.tongTien = (double) httpSession.getAttribute("tongTien");
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
        hoaDon.setTrangThai(1);
        hoaDon.setTgThanhToan(new Date());
        hoaDon.setTongTien(tongTien);
        hoaDon.setTongSP(tongSanPham);

        hoaDon.setTongTienDG(tongTien - tienKhuyenMai);
        hoaDon.setGiamGiaHoaDon(this.tienKhuyenMai);
        hoaDon.setHinhThucThanhToan(0);
        hoaDonService.add(hoaDon);


        KhuyenMai khuyenMai = (KhuyenMai) httpSession.getAttribute("khuyenMai");
        if (khuyenMai != null) {
            hoaDonService.saveKhuyenMai(khuyenMai, hoaDon, tienKhuyenMai);
        }
        this.tongTien = 0;
        this.tongSanPham = 0;
        this.tienKhuyenMai = 0;
        this.tongTienSauKM = 0;
        httpSession.removeAttribute("idHoaDon");
        httpSession.removeAttribute("khachHang");
        redirectAttributes.addFlashAttribute("messageSuccess", true);
        redirectAttributes.addFlashAttribute("tb", "Thanh toán thành công");
        return "redirect:/ban-hang/hien-thi";
    }

    @PostMapping("/khuyen-mai")
    public String chonKhuyenMai(Model model, RedirectAttributes redirectAttributes) {
        this.tongTien = (double) httpSession.getAttribute("tongTien");
        UUID idVoucherBill = UUID.fromString(request.getParameter("selectVoucherShipping"));
        UUID idHoaDon = (UUID) httpSession.getAttribute("idHoaDon");
        KhuyenMai khuyenMai = khuyenMaiService.findByID(idVoucherBill);
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
        Boolean checkDK = hoaDonService.checkHoaDonGiamGia(hoaDon, khuyenMai);
        if (checkDK) {
            redirectAttributes.addFlashAttribute("messageSuccess", true);
            redirectAttributes.addFlashAttribute("tb", "Áp dụng khuyến mãi thành công");
            Double soTienGiamGia = hoaDonService.tinhTienGiamGiaTaiQuay(hoaDon, khuyenMai);
            this.tienKhuyenMai = soTienGiamGia;
            httpSession.setAttribute("khuyenMai", khuyenMai);
        } else {
            redirectAttributes.addFlashAttribute("messageError", true);
            redirectAttributes.addFlashAttribute("tbaoError", "Chưa đủ điều kiện");
        }

        return "redirect:/ban-hang/cart/hoadon/" + idHoaDon;
    }

    @GetMapping("/searchByCode")
    public String searchByCode(@RequestParam(value = "code", required = false) String code, Model model,
                               RedirectAttributes redirectAttributes) {
        UUID idHoaDon = (UUID) httpSession.getAttribute("idHoaDon");
        if (idHoaDon == null) {
            redirectAttributes.addFlashAttribute("messageError", true);
            redirectAttributes.addFlashAttribute("tbaoError", "Bạn chưa chọn hóa đơn");
            model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
            return "redirect:/ban-hang/";
        }

        ChiTietGiay chiTietGiay = giayChiTietService.findByMa(code);
        if (chiTietGiay == null) {
            redirectAttributes.addFlashAttribute("messageError", true);
            redirectAttributes.addFlashAttribute("tbaoError", "Không tìm thấy sản phẩm");
            return "redirect:/ban-hang/cart/hoadon/" + this.idHoaDon;
        }
        model.addAttribute("quetQR", chiTietGiay);
        model.addAttribute("idHoaDon", idHoaDon);


        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());

        List<HoaDonChiTiet> findByIdHoaDon = hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        if (findByIdHoaDon.isEmpty()) {
            model.addAttribute("messageGioHang", "Trong giỏ hàng chưa có sản phẩm");
        }
        model.addAttribute("tongTien", hoaDonChiTietService.tongTien(findByIdHoaDon));
        model.addAttribute("gioHang", findByIdHoaDon);
        model.addAttribute("khuyenMai", this.tienKhuyenMai);
        model.addAttribute("tongTienSauKM", hoaDonChiTietService.tongTien(findByIdHoaDon) - tienKhuyenMai);

        model.addAttribute("showModalQuetQR", true);
        return "offline/index";
    }
    @PostMapping("/updateQuantity")
    public String updateQuantity(@RequestParam UUID idCTG, @RequestParam int quantity) {
        UUID idHoaDon = (UUID) httpSession.getAttribute("idHoaDon");
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idHoaDon, idCTG);
        hoaDonChiTiet.setSoLuong(quantity);
        hoaDonChiTietService.add(hoaDonChiTiet);
        return "redirect:/ban-hang/cart/hoadon/" + idHoaDon;
    }
}
