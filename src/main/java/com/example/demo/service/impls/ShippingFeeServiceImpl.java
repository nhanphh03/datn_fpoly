package com.example.demo.service.impls;

import com.example.demo.model.*;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.ShippingFeeService;
import com.example.demo.service.ThanhPhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingFeeServiceImpl implements ShippingFeeService {

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private ThanhPhoService thanhPhoService;


    @Override
    public Double calculatorShippingFee(HoaDon hoaDon, Double giaTriMacDinh) {

        Double shippingFee = 0.0;
        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietService.findByHoaDon(hoaDon);

        String diaChiChiTiet = hoaDon.getDiaChiNguoiNhan();

        String[] parts = diaChiChiTiet.split(",");
        String thanhPho = parts[parts.length - 1].trim();

        int tongSP = 0;
        int trongLuong = 0;

        Province province = thanhPhoService.findByNameProvince(thanhPho);

        if(province == null){
            List<Province> provinceList =  thanhPhoService.getAll();
            for (Province xxxx: provinceList) {
                if(thanhPho.contains(xxxx.getNameProvince())){
                    province = xxxx;
                }
            }
        }

        List<ChiTietGiay> chiTietGiayList = new ArrayList<>();

        for (HoaDonChiTiet hoaDonChiTiet: hoaDonChiTietList) {
            chiTietGiayList.add(hoaDonChiTiet.getChiTietGiay());
            ChiTietGiay chiTietGiay = hoaDonChiTiet.getChiTietGiay();
            trongLuong = trongLuong + chiTietGiay.getTrongLuong()*hoaDonChiTiet.getSoLuong();
            tongSP = tongSP + hoaDonChiTiet.getSoLuong();
        }

        if(tongSP <= 2){
            shippingFee = giaTriMacDinh*province.getTransportCoefficient();
        }else if(tongSP <= 5){
            shippingFee = giaTriMacDinh*province.getTransportCoefficient() - giaTriMacDinh;
        }else if(tongSP <= 7){
            shippingFee = giaTriMacDinh*province.getTransportCoefficient() - 2*giaTriMacDinh;
        }else{
            shippingFee =  giaTriMacDinh*province.getTransportCoefficient() - 3*giaTriMacDinh;
        }
        if(shippingFee<=0){
            shippingFee = 0.0;
        }
        return shippingFee;
    }

    @Override
    public Double calculatorVoucherBill(HoaDon hoaDon, KhuyenMai khuyenMai) {

        Double billFee = 0.0;

        Double sumPrice = hoaDon.getTongTien();
        boolean loaiGiam = khuyenMai.isLoaiGiam();

        if (loaiGiam){
            billFee = khuyenMai.getGiaTienGiam();
        }else{
            Double soTienGiam = sumPrice*khuyenMai.getPhanTramGiam();
            if (soTienGiam > khuyenMai.getGiaTienGiamToiDaPT()){
                billFee=khuyenMai.getGiaTienGiamToiDaPT();
            }else {
                billFee = soTienGiam;
            }
        }
        return billFee;
    }

    @Override
    public Double calculatorVoucherShipping(HoaDon hoaDon, KhuyenMai khuyenMai, Double tienShip) {

        Double voucherShipping = 0.0;
        boolean loaiGiam = khuyenMai.isLoaiGiam();

        if(loaiGiam){
            voucherShipping = khuyenMai.getGiaTienGiam();
            if (tienShip <= voucherShipping){
                voucherShipping = tienShip ;
            }
        }else{
            Double soTienGiamShip = khuyenMai.getPhanTramGiam()*tienShip;

            if (soTienGiamShip < khuyenMai.getGiaTienGiamToiDaPT()){
                voucherShipping = khuyenMai.getPhanTramGiam()*tienShip;
            }else{
                voucherShipping = khuyenMai.getGiaTienGiamToiDaPT();
            }

        }

        return voucherShipping;
    }
}
