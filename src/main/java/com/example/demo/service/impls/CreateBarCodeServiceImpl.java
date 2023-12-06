package com.example.demo.service.impls;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.service.CreateBarCode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Logger;

@Service
public class CreateBarCodeServiceImpl implements CreateBarCode {
    @Override
    public void saveBarcodeImage(ChiTietGiay chiTietGiay, int width, int height) {
        try {
            String projectPath = System.getProperty("user.dir");
            String qrCodePath = projectPath + "/src/main/resources/static/images/imgsBarcode/";
//            String qrCodePath = getClass().getResource("src/main/resources/static/images/imgsBarcode/").getPath();
            String qrCodeName = qrCodePath + chiTietGiay.getIdCTG() + ".png";
            Color backgroundColor = Color.white;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(
                    String.valueOf(chiTietGiay.getIdCTG()),
                    com.google.zxing.BarcodeFormat.QR_CODE,
                    width,
                    height,
                    null);
            BufferedImage qrCodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    qrCodeImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : backgroundColor.getRGB());
                }
            }
            ImageIO.write(qrCodeImage, "png", new File(qrCodeName));
        } catch (Exception e) {
            System.out.printf("Lỗi " + e.getMessage());
        }
    }
    @Override
    public void deleteQRCode() {
        String projectPath = System.getProperty("user.dir");
        String qrCodePath = projectPath + "/src/main/resources/static/images/imgsBarcode/";
//        String qrCodePath = getClass().getResource("src/main/resources/static/images/imgsBarcode/").getPath();
        File directory = new File(qrCodePath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        file.delete();
                    }
                }
            }
        }
    }
}
