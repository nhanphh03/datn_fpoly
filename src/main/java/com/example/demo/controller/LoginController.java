package com.example.demo.controller;


import com.example.demo.model.ChucVu;
import com.example.demo.model.NhanVien;
import com.example.demo.service.ChucVuService;
import com.example.demo.service.NhanVienService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpSession session) {
        session.invalidate();
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping("/login")
    private String getLoginAll(Model model) {
        return "/login";
    }

    @PostMapping("/manager/login")
    private String managerLogin(Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        Boolean b = username.matches(EMAIL_REGEX);

        ChucVu chucVu = chucVuService.findByMaCV("CV01");
        if (b) {
            NhanVien nhanVien = nhanVienService.checkByEmailAndChucVuAndPass(username, password, chucVu);
            if (nhanVien != null) {
                model.addAttribute("fullNameManage", nhanVien.getHoTenNV());
                session.setAttribute("managerLogged", nhanVien);
                return "redirect:/admin/";
            } else {
                model.addAttribute("messageLogin", "Username or Password incorrect");
                return "/login";
            }
        } else {
            NhanVien nhanVien = nhanVienService.checkBySDTAndChucVuAndPass(username, password, chucVu);
            if (nhanVien != null) {
                model.addAttribute("fullNameManage", nhanVien.getHoTenNV());
                session.setAttribute("managerLogged", nhanVien);
                return "redirect:/admin/";
            } else {
                model.addAttribute("messageLogin", "Username or Password incorrect");
                return "/login";
            }
        }
    }

    @PostMapping("/staff/login")
    private String staffLogin(Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        Boolean b = username.matches(EMAIL_REGEX);

        ChucVu chucVu = chucVuService.findByMaCV("CV02");
        if (b) {
            NhanVien nhanVien = nhanVienService.checkByEmailAndChucVuAndPass(username, password, chucVu);
            if (nhanVien != null) {
                model.addAttribute("fullNameStaff", nhanVien.getHoTenNV());
                session.setAttribute("staffLogged", nhanVien);
                return "redirect:/ban-hang/hien-thi";
            } else {
                model.addAttribute("messageLogin", "Username or Password incorrect");
                return "/login";
            }
        } else {
            NhanVien nhanVien = nhanVienService.checkBySDTAndChucVuAndPass(username, password, chucVu);
            if (nhanVien != null) {
                model.addAttribute("fullNameStaff", nhanVien.getHoTenNV());
                session.setAttribute("staffLogged", nhanVien);
                return "redirect:/ban-hang/hien-thi";
            } else {
                model.addAttribute("messageLogin", "Username or Password incorrect");
                return "/login";
            }
        }
    }

    @PostMapping("/shipper/login")
    private String shipperLogin(Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        Boolean b = username.matches(EMAIL_REGEX);

        ChucVu chucVu = chucVuService.findByMaCV("CV03");
        if (b) {
            NhanVien nhanVien = nhanVienService.checkByEmailAndChucVuAndPass(username, password, chucVu);
            if (nhanVien != null) {
                model.addAttribute("fullNameShipper", nhanVien.getHoTenNV());
                session.setAttribute("shipperLogged", nhanVien);
                return "redirect:/order/";
            } else {
                model.addAttribute("messageLogin", "Username or Password incorrect");
                return "/login";
            }
        } else {
            NhanVien nhanVien = nhanVienService.checkBySDTAndChucVuAndPass(username, password, chucVu);
            if (nhanVien != null) {
                model.addAttribute("fullNameShipper", nhanVien.getHoTenNV());
                session.setAttribute("shipperLogged", nhanVien);
                return "redirect:/order/";
            } else {
                model.addAttribute("messageLogin", "Username or Password incorrect");
                return "/login";
            }
        }
    }


}