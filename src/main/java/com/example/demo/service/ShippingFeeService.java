package com.example.demo.service;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhuyenMai;

public interface ShippingFeeService {

    Double calculatorShippingFee(HoaDon hoaDon, Double giaTriMacDinh);

    Double calculatorVoucherBill(HoaDon hoaDon, KhuyenMai khuyenMai);

    Double calculatorVoucherShipping(HoaDon hoaDon, KhuyenMai khuyenMai, Double tienShip);

}
