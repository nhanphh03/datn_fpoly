package com.example.demo.buyerController;

import com.example.demo.model.GioHang;
import com.example.demo.model.KhachHang;
import com.example.demo.service.GioHangService;
import com.example.demo.service.KhachHangService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Random;


@Controller
@RequestMapping("/buyer")
public class LoginController {

    private Random random = new Random();

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private HttpServletRequest request;


    @GetMapping("/login")
    public String getFormBuyerLogin(){
       return "online/login";
    }


    @PostMapping("/login")
    private String buyerLogin(Model model, RedirectAttributes redirectAttributes,
                              HttpSession session){

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        Date date = new Date();

        Boolean b = username.matches(EMAIL_REGEX);

        if (b){
            KhachHang kh  = khachHangService.checkLoginEmail(username, password);
            if (kh !=null){
                return "redirect:/buyer/";
            }else{
                GioHang gh = gioHangService.findByKhachHang(kh);
                if (gh != null){
                    GioHang gioHang = new GioHang();
                    gioHang.setKhachHang(kh);
                    gioHang.setTrangThai(1);
                    gioHang.setTgThem(date);
                    gioHangService.saveGH(gh);
                    model.addAttribute("messageLogin", "Username or Password incorrect");
                    return "online/login";
                }
                model.addAttribute("messageLogin", "Username or Password incorrect");
                return "online/login";
            }
        }else{
            KhachHang kh  = khachHangService.checkLoginSDT(username, password);
            if (kh !=null){
                return "redirect:/buyer/";
            }else{
                GioHang gh = gioHangService.findByKhachHang(kh);
                if (gh != null){
                    GioHang gioHang = new GioHang();
                    gioHang.setKhachHang(kh);
                    gioHang.setTrangThai(1);
                    gioHang.setTgThem(date);
                    gioHangService.saveGH(gh);
                    model.addAttribute("messageLogin", "Username or Password incorrect");
                    return "online/login";
                }
                model.addAttribute("messageLogin", "Username or Password incorrect");
                return "online/login";
            }
        }
    }


    @GetMapping("/register")
    public String getFormBuyerRegister(){
        return "online/register";
    }


    @PostMapping("/register")
    public String buyerRegister(Model model, RedirectAttributes redirectAttributes,
                                HttpSession session){
        String fullName = request.getParameter("fullname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        boolean hasErr = false;

        if (fullName == null || "".equals(fullName)){
            model.addAttribute("messFullname", "Not Blank  !");
            hasErr=true;
        }
        if (email == null || "".equals(email)){
            model.addAttribute("messEmail", "Not Blank  !");
            hasErr=true;
        }
        if (hasErr){
            model.addAttribute("messageFullName", fullName);
            model.addAttribute("messageEmail", email);
            return "/online/register";
        }

        KhachHang kh = khachHangService.checkEmail(email);
        if (kh != null){
            
        }

        return "online/login";
    }

    @GetMapping("/reset")
    public String getFormBuyerResetPass(){
        return "online/reset";
    }

    public String generateRandomNumbers() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomNumber = random.nextInt(10); // Tạo số ngẫu nhiên từ 0 đến 9
            sb.append(randomNumber);
        }
        return sb.toString();
    }
}
