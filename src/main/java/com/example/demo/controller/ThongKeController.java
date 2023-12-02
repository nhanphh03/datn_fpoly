package com.example.demo.controller;

import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.repository.*;

import com.example.demo.service.CTGViewModelService;
import com.example.demo.viewModel.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("manage")
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
    @Autowired
    private CTGViewModelRepository ctgViewModelRepository;

    public NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    @GetMapping("/thongke")
    private String getTong(Model model, HttpServletRequest request){
        model.addAttribute("tong",khachHangRepository.getTongKH());
        model.addAttribute("tonggiay",giayChiTietRepository.getTongGiay());

        //làm tròn doanh thu


        Optional<Double> ltn = hoaDonChiTietRepository.getLaiThangNay();

        Optional<Double> tlbr = hoaDonChiTietRepository.getTongTienLaiCuaHang();
        Optional<Integer> tongSPBanTrongNgay = hoaDonChiTietRepository.getTongSPBanTrongNgay();
        Optional<Integer> tongSPBanTrongThang = hoaDonChiTietRepository.getTongSPBanTrongThang();


        if(tongSPBanTrongNgay.isPresent() ){
            Integer a = tongSPBanTrongNgay.get();

            model.addAttribute("sptn",a);
        }else{
            model.addAttribute("sptn","0");
        }

        if(ltn.isPresent()){
            Double b = ltn.get();
            String formatdtt = currencyFormat.format(b);
            model.addAttribute("ltn",formatdtt);
        }else{
            model.addAttribute("ltn","0đ");
        }
        //Tổng tiền của cửa hàng
        if(tlbr.isPresent()){
            Double c = tlbr.get();
            String formatdtt2 = currencyFormat.format(c);
            model.addAttribute("tlbr",formatdtt2);
        }else{
            model.addAttribute("tlbr","0đ");
        }

        if(tongSPBanTrongThang.isPresent()){
            Integer d = tongSPBanTrongThang.get();

            model.addAttribute("sptt",d);
        }else{
            model.addAttribute("sptt","0");
        }

//
//        // top 5 sp bán chạy
        List<Object[]> listCTGModelBestSeller = ctgViewModelRepository.getTop5SPBanChayTrongThang();
        List<Top5SPBanChayTrongThang> dataTop5 = listCTGModelBestSeller.stream()
                .map(result -> new Top5SPBanChayTrongThang(
                        (String) result[0],
                        (String) result[1],
                        (Integer) result[2]
                )).collect(Collectors.toList());
        model.addAttribute("spBanChay",dataTop5);

//        //chỉ số ở hóa đơn
        model.addAttribute("hdht",hoaDonRepository.getAllHoaDonDaThanhToan());
        model.addAttribute("hdc",hoaDonRepository.getAllHoaDonCho());
//  hoa don
//        model.addAttribute("hoaDonCT2",hoaDonChiTietRepository.getAllHoaDonChoThanhToan());
//        model.addAttribute("hoaDonCT1",hoaDonChiTietRepository.getAllHoaDonDaThanhToan());
        // biểu đồ thống kê
        //theo tháng
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
// theo ngày
    List<String> listThemNgay = new ArrayList<>();
    listThemNgay.add("Ngày Hôm Nay");
    List<Integer> doanhSoNgay = new ArrayList<>();
    doanhSoNgay.add(hoaDonChiTietRepository.getNgaythu1());
        model.addAttribute("Ngay", listThemNgay);
        model.addAttribute("listSL", doanhSoNgay);
        //theo năm
        List<String> listThemNam = new ArrayList<>();
        listThemNam.add("2021");
        listThemNam.add("2022");
        listThemNam.add("2023");

        List<Integer> doanhSoNam = new ArrayList<>();
        doanhSoNam.add(hoaDonChiTietRepository.Nam2021());
        doanhSoNam.add(hoaDonChiTietRepository.Nam2022());
        doanhSoNam.add(hoaDonChiTietRepository.Nam2023());
        model.addAttribute("Nam", listThemNam);
        model.addAttribute("listNam", doanhSoNam);
        // chi tiết sp đã bán trong ngày
        List<Object[]> k = hoaDonChiTietRepository.findHoaDonChiTietByDate();
        List<CTHDViewModel> yourDTOList = k.stream()
                .map(result -> new CTHDViewModel(
                        (Double) result[0],
                        (String) result[1],
                        (Double) result[2],
                        (Integer) result[3],
                        (String) result[4],
                        (String) result[5],
                        (String) result[6],
                        (String) result[7]
                )).collect(Collectors.toList());
        // chi tiết sp đã bán trong tháng
        List<Object[]> x = hoaDonChiTietRepository.findHoaDonChiTietByMonth();
        List<CTHDViewModel> yourDTOList2 = x.stream()
                .map(result -> new CTHDViewModel(
                        (Double) result[0],
                        (String) result[1],
                        (Double) result[2],
                        (Integer) result[3],
                        (String) result[4],
                        (String) result[5],
                        (String) result[6],
                        (String) result[7]
                )).collect(Collectors.toList());
            model.addAttribute("getN",yourDTOList);
            model.addAttribute("getT",yourDTOList2);

            //hiệu suất nhân viên
        List<Object[]> objects = hoaDonChiTietRepository.getHieuSuatNhanVienBanHang();
        List<HieuSuatBanHang> hs = objects.stream()
                .map(result -> new HieuSuatBanHang(
                        (String) result[0],
                        (String) result[1],
                        (String) result[2],
                        (Integer) result[3]

                )).collect(Collectors.toList());
        model.addAttribute("hieuSuat",hs);
        //sp sắp hết hàng
        List<Object[]> v = ctgViewModelRepository.spSapHet();
        List<SanPhamSapHet> sh = v.stream()
                .map(result -> new SanPhamSapHet(
                        (String) result[0],
                        (String) result[1],
                        (Integer) result[2]

                )).collect(Collectors.toList());
        model.addAttribute("hieuSuat",hs);
        model.addAttribute("spSapHet",sh);
        // biểu đồ thống kê doanh thu
        //*lọc theo tháng
        List<Double> l = Arrays.asList(
                hoaDonChiTietRepository.getTongTienDaGiamThang1(),
                hoaDonChiTietRepository.getTongTienDaGiamThang2(),
                hoaDonChiTietRepository.getTongTienDaGiamThang3(),
                hoaDonChiTietRepository.getTongTienDaGiamThang4(),
                hoaDonChiTietRepository.getTongTienDaGiamThang5(),
                hoaDonChiTietRepository.getTongTienDaGiamThang6(),
                hoaDonChiTietRepository.getTongTienDaGiamThang7(),
                hoaDonChiTietRepository.getTongTienDaGiamThang8(),
                hoaDonChiTietRepository.getTongTienDaGiamThang9(),
                hoaDonChiTietRepository.getTongTienDaGiamThang10(),
                hoaDonChiTietRepository.getTongTienDaGiamThang11(),
                hoaDonChiTietRepository.getTongTienDaGiamThang12()
        );
        List<Double> listTongtienDaGiamTheoThang = new ArrayList<>(l);
        for(int i =0;i<listTongtienDaGiamTheoThang.size();i++) {
            if (listTongtienDaGiamTheoThang.get(i)==null) {
                listTongtienDaGiamTheoThang.set(i,0.0);
            }
        }
        List<Double> q = Arrays.asList(
                hoaDonChiTietRepository.getTongTienThang1(),
                hoaDonChiTietRepository.getTongTienThang2(),
                hoaDonChiTietRepository.getTongTienThang3(),
                hoaDonChiTietRepository.getTongTienThang4(),
                hoaDonChiTietRepository.getTongTienThang5(),
                hoaDonChiTietRepository.getTongTienThang6(),
                hoaDonChiTietRepository.getTongTienThang7(),
                hoaDonChiTietRepository.getTongTienThang8(),
                hoaDonChiTietRepository.getTongTienThang9(),
                hoaDonChiTietRepository.getTongTienThang10(),
                hoaDonChiTietRepository.getTongTienThang11(),
                hoaDonChiTietRepository.getTongTienThang12()
        );
        List<Double> listTongtienTheoThang = new ArrayList<>(q);
        for(int i =0;i<listTongtienTheoThang.size();i++) {
            if (listTongtienTheoThang.get(i)==null) {
                listTongtienTheoThang.set(i,0.0);
            }
        }
        model.addAttribute("ttdgtt",listTongtienDaGiamTheoThang);
        model.addAttribute("tttt",listTongtienTheoThang);
        //*lọc theo năm
        List<String> nam = new ArrayList<>();
        nam.add(0,"Năm 2021");
        nam.add(1,"Năm 2022");
        nam.add(2,"Năm 2023");
        nam.add(3,"Năm 2024");
        nam.add(4,"Năm 2025");
        List<Double> s = Arrays.asList(
                hoaDonChiTietRepository.getTongTienDaGiamNam2021(),
                hoaDonChiTietRepository.getTongTienDaGiamNam2022(),
                hoaDonChiTietRepository.getTongTienDaGiamNam2023(),
                hoaDonChiTietRepository.getTongTienDaGiamNam2024(),
                hoaDonChiTietRepository.getTongTienDaGiamNam2025()
        );
        List<Double> listTTDGTN=new ArrayList<>(s);
        List<Double> m = Arrays.asList(
                hoaDonChiTietRepository.getTongTienNam2021(),
                hoaDonChiTietRepository.getTongTienNam2022(),
                hoaDonChiTietRepository.getTongTienNam2023(),
                hoaDonChiTietRepository.getTongTienNam2024(),
                hoaDonChiTietRepository.getTongTienNam2025()
        );
        List<Double> listTTTN =new ArrayList<>(m);
        model.addAttribute("ttdgtn",listTTDGTN);
        model.addAttribute("tttn",listTTTN);
        model.addAttribute("listNam",nam);
        return "manage/ThongKe/test";
    }
    @GetMapping("/thongke/{maNV}")
    public String getEmployeeDetail(@PathVariable("maNV") String maNV, Model model) {
        // Xử lý dữ liệu chi tiết nhân viên dựa trên maNv
        System.out.println("Đã sang tc");
        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.getChiTietSPNhanVienBan(maNV);
        model.addAttribute("giay",giayRepository.findAll());
        model.addAttribute("chiTietGiay",giayChiTietRepository.findAll());
        model.addAttribute("hinhAnh",hinhAnhRepository.findAll());
        model.addAttribute("hoaDon",hoaDonRepository.findAll());
        model.addAttribute("ctspNV",hoaDonChiTiets);



        return "manage/ThongKe/detailCTSPNV";

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
