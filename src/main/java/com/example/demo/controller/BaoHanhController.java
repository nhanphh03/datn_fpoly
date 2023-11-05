package com.example.demo.controller;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.repository.GiayChiTietRepository;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.utils.BaoHanhDTO;
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
public class BaoHanhController {
    @Autowired
    private GiayChiTietRepository repository;
    @Autowired
    private  GiayChiTietService giayChiTietService;

    @GetMapping("/danh-sach-het-bao-hanh")
    public String danhSachHetBaoHanh(Model model) {
        //
        List<ChiTietGiay> items = giayChiTietService.getTop4ChiTietGiay();
        //
        List<Object[]> dsHetBaoHanh = repository.dsHetBaoHanh();
        List<BaoHanhDTO> chiTietGiayDTOs = new ArrayList<>();
        for (Object[] row : dsHetBaoHanh) {
            BaoHanhDTO dto = new BaoHanhDTO();
            //
            UUID idGiay = UUID.fromString((String) row[0]);
            //
            dto.setIdCTG(idGiay);
            dto.setMaGiay((String) row[1]);
            dto.setTenGiay((String) row[2]);
            dto.setTenHang((String) row[3]);
            dto.setNamBaoHanh((Integer) row[4]);
            chiTietGiayDTOs.add(dto);
        }
        model.addAttribute("dsHetBaoHanh", chiTietGiayDTOs);
        model.addAttribute("items", items);
        return "manage/bao-hanh";
    }
}
