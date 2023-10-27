package com.example.demo.service;

import com.example.demo.model.ChiTietGiay;



public interface CreateBarCode {

    void saveBarcodeImage(ChiTietGiay chiTietGiay, int width, int height);

    void deleteQRCode();

}
