package com.example.demo.controller;

import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("ban-hang")
public class ThongKeController {
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private GiayChiTietRepository giayChiTietRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Autowired
    private GiayRepository giayRepository;
    @GetMapping("/thongke")
    private String getTong(Model model){
        model.addAttribute("tong",khachHangRepository.getTongKH());
        model.addAttribute("tonggiay",giayChiTietRepository.getTongGiay());

        //làm tròn doanh thu

        Double Resulttdt = Math.ceil(hoaDonChiTietRepository.getTongTien()*100.0)/100.0;
        Double Resultdtt = Math.ceil(hoaDonChiTietRepository.getDoanhThuThang()*100.0)/100.0;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattdt = currencyFormat.format(Resulttdt);
        String formatdtt = currencyFormat.format(Resultdtt);
        model.addAttribute("tdt",formattdt);
        model.addAttribute("dtt",formatdtt);
//
//        // top 5 sp bán chạy
            
        System.out.println(hoaDonChiTietRepository.getTop5().get(1)+"Đã in tc");
//        //chỉ số ở hóa đơn
        model.addAttribute("hdht",hoaDonRepository.getAllHoaDonDaThanhToan());
        model.addAttribute("hdc",hoaDonRepository.getAllHoaDonCho());
//  hoa don
//        model.addAttribute("hoaDonCT2",hoaDonChiTietRepository.getAllHoaDonChoThanhToan());
//        model.addAttribute("hoaDonCT1",hoaDonChiTietRepository.getAllHoaDonDaThanhToan());
        // biểu đồ thống kê
        List<String> listThem= Arrays.asList("Tháng 1","Tháng 2","Tháng 3","Tháng 4","Tháng 5",
                "Tháng 6","Tháng 7","Tháng 8","Tháng 9","Tháng 10","Tháng 11","Tháng 12");
        List<String> listThang = new ArrayList<>(listThem);

        List<Integer> list = Arrays.asList(hoaDonChiTietRepository.getThang1(),
                hoaDonChiTietRepository.getThang2(),
                hoaDonChiTietRepository.getThang3(),
                hoaDonChiTietRepository.getThang4(),
                hoaDonChiTietRepository.getThang5(),
                hoaDonChiTietRepository.getThang6(),
                hoaDonChiTietRepository.getThang7(),
                hoaDonChiTietRepository.getThang8(),
                hoaDonChiTietRepository.getThang9(),
                hoaDonChiTietRepository.getThang10(),
                hoaDonChiTietRepository.getThang11(),
                hoaDonChiTietRepository.getThang12()

                );
        List<Integer> listDoanhSo = new ArrayList<>(list);
        model.addAttribute("listThang", listThang);
        model.addAttribute("listDoanhSo", listDoanhSo);

        return "manage/ThongKe/index";
    }








//    @GetMapping("/hoadoncho/filter")
//    public String searchHoaDonCho(Model model, @RequestParam(name = "searchTerm") String searchTerm) {
//        List<HoaDon> filteredHoaDonCho;
//        if ("Giày".equals(searchTerm) && "Size".equals(searchTerm) && "Màu Sắc".equals(searchTerm)) {
//
//            filteredHoaDonCho = hoaDonCho.getAll();
//        } else {
//
//            filteredHoaDonCho = hoaDonCho.fillterHoaDonCho(searchTerm);
//        }
//        model.addAttribute("HoaDon", filteredHoaDonCho);
//        model.addAttribute("HoaDonAll", hoaDonCho.getAll());
//        return "manage/giay";
//    }
}
