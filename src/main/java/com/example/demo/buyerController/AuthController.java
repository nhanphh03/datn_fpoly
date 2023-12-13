package com.example.demo.buyerController;

import com.example.demo.model.*;
import com.example.demo.repository.CTGViewModelRepository;
import com.example.demo.repository.GiayChiTietRepository;
import com.example.demo.service.*;
import com.example.demo.viewModel.CTGViewModel;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/buyer")
public class AuthController {

    private Random random = new Random();

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CTGViewModelRepository giayChiTietRepository;

    @Autowired
    private LoaiKhachHangService loaiKHService;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HanhViKHService hanhViKHService;

    @GetMapping("/login")
    public String getFormBuyerLogin(){

       return "online/login";
    }


    @PostMapping("/login")
    private String buyerLogin(Model model, HttpSession session) throws MessagingException {

//        sendMailService.sendMimeMessage("nhanphh2003@gmail.com", "", "");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        Date date = new Date();

        Boolean b = username.matches(EMAIL_REGEX);

        if (b){
            KhachHang kh  = khachHangService.checkLoginEmail(username, password);
            if (kh !=null){

                GioHang gh = gioHangService.findByKhachHang(kh);
                HanhViKhachHang hanhViKhachHang = hanhViKHService.checkByKH(kh);

                if (gh != null){
                    String fullName = kh.getHoTenKH();
                    model.addAttribute("fullNameLogin", fullName);
                    session.setAttribute("KhachHangLogin", kh);
                    session.setAttribute("GHLogged", gh);
                    session.setAttribute("HVKHLogged", hanhViKhachHang);
                    return "redirect:/buyer/";
                }

//Add new Hanh vi Khach Hang
                String maHVKH = kh.getHoTenKH() + kh.getMaKH() + kh.getCCCDKH() ;
                HanhViKhachHang hvkh = new HanhViKhachHang();
                hvkh.setKhachHang(kh);
                hvkh.setTgThem(new Date());
                hvkh.setTrangThai(1);
                hvkh.setMa_HVKH(maHVKH);
                hanhViKHService.addNewHanhViKH(hvkh);
//End

//Add new Gio Hang Khach Hang
                GioHang gioHang = new GioHang();
                gioHang.setKhachHang(kh);
                gioHang.setTrangThai(1);
                gioHang.setTgThem(date);
                gioHangService.saveGH(gioHang);
//End

                session.setAttribute("KhachHangLogin", kh);
                session.setAttribute("GHLogged", gioHang);
                session.setAttribute("HVKHLogged", hvkh);

                return "redirect:/buyer/";

            }else{
                model.addAttribute("messageLogin", "Username or Password incorrect");
                return "online/login";
            }
        }else{
            KhachHang kh  = khachHangService.checkLoginSDT(username, password);
            if (kh != null){
                GioHang gh = gioHangService.findByKhachHang(kh);
                HanhViKhachHang hanhViKhachHang = hanhViKHService.checkByKH(kh);

                if (gh != null){
                    String fullName = kh.getHoTenKH();

                    model.addAttribute("fullNameLogin", fullName);
                    session.setAttribute("KhachHangLogin", kh);
                    session.setAttribute("GHLogged", gh);
                    session.setAttribute("HVKHLogged", hanhViKhachHang);
                    return "redirect:/buyer/";
                }

                String maHVKH = kh.getHoTenKH() + kh.getMaKH() + kh.getCCCDKH() ;
                HanhViKhachHang hvkh = new HanhViKhachHang();
                hvkh.setKhachHang(kh);
                hvkh.setTgThem(new Date());
                hvkh.setTrangThai(1);
                hvkh.setMa_HVKH(maHVKH);
                hanhViKHService.addNewHanhViKH(hvkh);

                GioHang gioHang = new GioHang();
                gioHang.setKhachHang(kh);
                gioHang.setTrangThai(1);
                gioHang.setTgThem(date);
                gioHangService.saveGH(gioHang);
                session.setAttribute("GHLogged", gioHang);
                session.setAttribute("HVKHLogged", hvkh);

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
    public String getFormBuyerRegister(Model model){
        KhachHang khachHang = new KhachHang();
        model.addAttribute("formRegister", true);
        model.addAttribute("userRegister",khachHang);
        return "online/register";
    }


    @PostMapping("/register")
    public String buyerRegister(Model model, RedirectAttributes redirectAttributes){
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
            model.addAttribute("formRegister", true);
            return "/online/register";
        }

        KhachHang kh = khachHangService.checkEmail(email);
        if (kh != null){
            model.addAttribute("messEmail", "Email already exists ! ");
            model.addAttribute("messageFullName", fullName);
            model.addAttribute("formRegister", true);
            return "/online/register";
        }
        KhachHang khachHang = new KhachHang();
        khachHang.setEmailKH(email);
        khachHang.setHoTenKH(fullName);
        khachHang.setMatKhau(password);
        khachHang.setTrangThai(2);
        Date date = new Date();
        khachHang.setTgThem(date);
        LoaiKhachHang loaiKhachHang = loaiKHService.findByMaLKH("H1");
        khachHang.setLoaiKhachHang(loaiKhachHang);
        khachHangService.save(khachHang);

        String numberOTP = generateRandomNumbers();

        session.setAttribute("userRegister", khachHang);
        session.setAttribute("OTPRegister", numberOTP);

        String subject="Dear" + " " + fullName + " " + "This is OTP to register a your account : ";

        sendMailService.sendSimpleMail(email, "Verify your email address"  ,subject + " "+  numberOTP);

        model.addAttribute("formRegister", false);
        model.addAttribute("formOTPRegister", true);

        return "online/register";
    }

    @PostMapping("/register/verify-otp")
    public String buyerRegisterVerifyOTP(Model model){

        String OTPNumber = (String) session.getAttribute("OTPRegister");
        KhachHang KHRegiter = (KhachHang) session.getAttribute("userRegister");

        String OTPUser = request.getParameter("OTPRegister");

        if (OTPNumber.equals(OTPUser)){
            KHRegiter.setTrangThai(1);
            khachHangService.save(KHRegiter);
            session.invalidate();
            return"online/login";
        }else {
            model.addAttribute("formRegister", false);
            model.addAttribute("formOTPRegister", true);
            model.addAttribute("messageOTP", "Invalid OTP. Please try again.");
            return "online/register";
        }

    }

    @GetMapping("/register/resened-otp")
    public String resendOTPEmailForm(Model model){
        model.addAttribute("formRegister", false);
        model.addAttribute("formOTPRegister", true);

        KhachHang KHRegiter = (KhachHang) session.getAttribute("userRegister");

        String numberOTPResend = generateRandomNumbers();
        String fullName = KHRegiter.getHoTenKH();
        String email = KHRegiter.getEmailKH();
        session.removeAttribute("OTPRegister");
        session.setAttribute("OTPRegisterResend", numberOTPResend);

        String subject="Dear" + " " + fullName + " " + "This is OTP to register a your account : ";

        sendMailService.sendSimpleMail(email, "Verify your email address"  ,subject + " "+  numberOTPResend);

        return "online/register";
    }

    @GetMapping("/reset")
    public String getFormBuyerResetPass(){

        return "online/reset";
    }

    @GetMapping("/logout")
    public String getFormBuyerLogout(){
        session.invalidate();
        return "redirect:/buyer/";
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
