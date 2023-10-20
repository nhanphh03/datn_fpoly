package com.example.demo.controller;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.Giay;
import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.repository.SizeRepository;
import com.example.demo.service.CTGViewModelService;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.GiayService;
import com.example.demo.service.GiayViewModelService;
import com.example.demo.service.HangService;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import com.example.demo.viewModel.GiayViewModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    private HttpSession session;

    @Autowired
    private HangService hangService;

    @Autowired
    private GiayChiTietService giayChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private CTGViewModelService ctgViewModelService;

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

    @GetMapping("/offline")
    public String offline(){
        return "manage/activities";
    }


    @GetMapping("/hien-thi")
    public String hienThi(Model model){
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
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
        httpSession.setAttribute("idHoaDon",idHoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        List<HoaDonChiTiet> findByIdHoaDon= hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        if (findByIdHoaDon.isEmpty()){
            model.addAttribute("messageGioHang","Trong giỏ hàng chưa có sản phẩm");
        }else {
            model.addAttribute("gioHang",findByIdHoaDon);
        }
        return "offline/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword,Model model){
        List<GiayViewModel> list = giayViewModelService.getAll(keyword);
        model.addAttribute("listSanPham",list);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        UUID idHoaDon = (UUID) session.getAttribute("idHoaDon");
        List<HoaDonChiTiet> findByIdHoaDon= hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        if (findByIdHoaDon.isEmpty()){
            model.addAttribute("messageGioHang","Trong giỏ hàng chưa có sản phẩm");
        }else {
            model.addAttribute("gioHang",findByIdHoaDon);
        }
        return "offline/index";
    }

    @GetMapping("/chon-size/{idGiay}/{mauSac}")
    public String chonSize(@PathVariable(value = "idGiay") UUID idGiay,
                           @PathVariable(value = "mauSac") String mauSac, Model model){
        Giay giay = giayService.getByIdGiay(idGiay);
        List<ChiTietGiay> sizeList = sizeRepository.findByIdGiayAndMauSac2(idGiay,mauSac);
        model.addAttribute("giay",giay);
        model.addAttribute("listChiTietGiay",sizeList);
        model.addAttribute("showModal",true);
        return "offline/index";
    }

    @GetMapping("/add-to-cart")
    public String addToCart(@RequestParam("idChiTietGiay") UUID idChiTietGiay,
                            @RequestParam("soLuong") int soLuong, Model model){
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(idChiTietGiay);
        UUID idHoaDon = (UUID) session.getAttribute("idHoaDon");
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idHoaDon,idChiTietGiay);
        System.out.println(idHoaDon);
        System.out.println(chiTietGiay.toString());
        if (hoaDonChiTiet != null){
            hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong()+soLuong);
            hoaDonChiTiet.setTgSua(new Date());
            hoaDonChiTiet.setTrangThai(1);
            hoaDonChiTietService.add(hoaDonChiTiet);
        }else {
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setChiTietGiay(chiTietGiay);
            hdct.setHoaDon(hoaDon);
            hdct.setDonGia(chiTietGiay.getGiaBan());
            hdct.setSoLuong(soLuong);
            hdct.setTrangThai(1);
            hdct.setTgThem(new Date());
            hoaDonChiTietService.add(hdct);
        }
        List<HoaDonChiTiet> findByIdHoaDon= hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        model.addAttribute("gioHang",findByIdHoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());

        // cập nhật sl ctg
        chiTietGiay.setSoLuong(chiTietGiay.getSoLuong()- soLuong);
        giayChiTietService.save(chiTietGiay);
        return "offline/index";
    }
    @GetMapping("/quet-qr/{idChiTietGiay}")
    public String quetQr(@PathVariable(value = "idChiTietGiay") UUID idChiTietGiay,Model model){
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(idChiTietGiay);
        model.addAttribute("quetQR",chiTietGiay);
        model.addAttribute("showModalQuetQR",true);
        return "offline/index";
    }

    @GetMapping("/xoa-gio-hang/{idChiTietGiay}")
    public String xoaSanPham(@PathVariable("idChiTietGiay")UUID idChiTietGiay,Model model){
        ChiTietGiay chiTietGiay = giayChiTietService.getByIdChiTietGiay(idChiTietGiay);
        UUID idHoaDon = (UUID) session.getAttribute("idHoaDon");
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idHoaDon,idChiTietGiay);

        chiTietGiay.setSoLuong(chiTietGiay.getSoLuong()+hoaDonChiTiet.getSoLuong());
        giayChiTietService.save(chiTietGiay);

        hoaDonChiTiet.setTrangThai(0);
        hoaDonChiTiet.setSoLuong(0);
        hoaDonChiTietService.add(hoaDonChiTiet);
        List<HoaDonChiTiet> findByIdHoaDon= hoaDonChiTietService.findByIdHoaDon(idHoaDon);
        model.addAttribute("gioHang",findByIdHoaDon);
        model.addAttribute("listHoaDon", hoaDonService.getListHoaDonChuaThanhToan());
        return "offline/index";
    }
}
