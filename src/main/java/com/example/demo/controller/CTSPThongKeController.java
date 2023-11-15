package com.example.demo.controller;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.repository.GiayChiTietRepository;
import com.example.demo.repository.HoaDonChiTietRepository;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.utils.BaoHanhDTO;
import com.example.demo.utils.SanPhamBanChayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/manage")
@Controller
public class CTSPThongKeController {
    @Autowired
    private GiayChiTietRepository giayChiTietRepository;
    @Autowired
    private GiayChiTietService giayChiTietService;
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @GetMapping("/chi-tiet-san-pham-thong-ke")
    public String ctspThongKe(Model model) {
        //Top 4 sản phẩm mới
        List<ChiTietGiay> items = giayChiTietService.getTop4ChiTietGiay();
        //ds hết hạn bảo hành
        List<Object[]> dsHetBaoHanh = giayChiTietRepository.dsHetBaoHanh();
        List<BaoHanhDTO> chiTietGiayDTOs = new ArrayList<>();
        for (Object[] row : dsHetBaoHanh) {
            BaoHanhDTO dto = new BaoHanhDTO();
            //
            UUID idCTGiay = UUID.fromString((String) row[0]);
            //
            dto.setIdCTG(idCTGiay);
            dto.setMaGiay((String) row[1]);
            dto.setTenGiay((String) row[2]);
            dto.setTenHang((String) row[3]);
            dto.setNamBaoHanh((Integer) row[4]);
            chiTietGiayDTOs.add(dto);
        }
        // ds sản phẩm bán chạy
        List<Object[]> spBanChay = hoaDonChiTietRepository.spBanChay();
        List<SanPhamBanChayDTO> banChayDTOs = new ArrayList<>();
        for (Object[] row : spBanChay) {
            SanPhamBanChayDTO dto = new SanPhamBanChayDTO();
            //
            UUID idCTGiay = UUID.fromString((String) row[0]);
            //
            dto.setIdCTG(idCTGiay);
            dto.setTenGiay((String) row[1]);
            dto.setSoSize((Integer) row[2]);
            dto.setTenHang((String) row[3]);
            dto.setTenChatLieu((String) row[4]);
            dto.setTenMau((String) row[5]);
            dto.setSoLuongBan((Integer) row[6]);
            banChayDTOs.add(dto);
        }
        //
        model.addAttribute("dsHetBaoHanh", chiTietGiayDTOs);
        model.addAttribute("spBanChay", banChayDTOs);
        model.addAttribute("items", items);
        return "manage/CTSPThongKe";
    }
}
