package com.example.demo.controller;


import com.example.demo.model.ChiTietGiay;
import com.example.demo.model.ChucVu;
import com.example.demo.model.NhanVien;
import com.example.demo.service.ChucVuService;
import com.example.demo.service.GiayChiTietService;
import com.example.demo.service.NhanVienService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class LoginController {


    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private ChucVuService chucVuService;

    @Autowired
    private GiayChiTietService giayChiTietService;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpSession session) {
        session.invalidate();
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping("/login")
    private String getLoginAll(Model model) {
//        List<ChiTietGiay> list = giayChiTietService.getAllChiTietGiay();
//        Date date = new Date();
//        String maCtg = "CTG00"+ date.getDate();
//        int index = 0;
//        for (ChiTietGiay chiTietGiay : list){
//            chiTietGiay.setMaCTG(maCtg + index++);
//            giayChiTietService.save(chiTietGiay);
//        }
        return "/login";
    }

    @PostMapping("/login")
    private String nhanVienLogin(Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        Boolean b = username.matches(EMAIL_REGEX);


        if (b) {
            NhanVien nhanVien = nhanVienService.checkByEmailAndPass(username, password);
            if (nhanVien != null) {
                if (nhanVien.getChucVu().getMaCV().equalsIgnoreCase("CV01")) {
                    session.setAttribute("managerLogged", nhanVien);
                    System.out.println(nhanVien.getChucVu().getMaCV());
                    return "redirect:/manage/";
                } else if (nhanVien.getChucVu().getMaCV().equalsIgnoreCase("CV02")) {
                    session.setAttribute("staffLogged", nhanVien);
                    System.out.println(nhanVien.getChucVu().getMaCV());
                    return "redirect:/ban-hang/";
                } else if  (nhanVien.getChucVu().getMaCV().equalsIgnoreCase("CV03")){
                    session.setAttribute("shipperLogged", nhanVien);
                    return "redirect:/order/";
                }else{
                    model.addAttribute("messageLogin", "Not Access");
                    return "/login";
                }
            } else {
                model.addAttribute("messageLogin", "Username or Password incorrect");
                return "/login";
            }
        } else {
            NhanVien nhanVien = nhanVienService.checkBySDTAndPass(username, password);
            if (nhanVien != null) {
                if (nhanVien.getChucVu().getMaCV().equalsIgnoreCase("CV01")) {
                    session.setAttribute("managerLogged", nhanVien);
                    return "redirect:/manage/";
                } else if (nhanVien.getChucVu().getMaCV().equalsIgnoreCase("CV02")) {
                    session.setAttribute("staffLogged", nhanVien);
                    return "redirect:/ban-hang/";
                } else if  (nhanVien.getChucVu().getMaCV().equalsIgnoreCase("CV03")){
                    session.setAttribute("shipperLogged", nhanVien);
                    return "redirect:/order/";
                }else{
                    model.addAttribute("messageLogin", "Not Access");
                    return "/login";
                }
            } else {
                model.addAttribute("messageLogin", "Username or Password incorrect");
                return "/login";
            }
        }
    }

}