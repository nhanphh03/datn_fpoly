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
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
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
            String qrCodePath = "H:\\TaiLieuDuAnTotNghiep\\anhBarCode\\";
            String qrCodeName = qrCodePath + chiTietGiay.getGiay().getTenGiay()+chiTietGiay.getIdCTG() +  ".png";

            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            Writer writer = new Code128Writer();
            BitMatrix bitMatrix = writer.encode(String.valueOf(chiTietGiay.getIdCTG()), BarcodeFormat.CODE_128, width, height);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);

            // Lưu ảnh vào đường dẫn qrCodeName
            File file = new File(qrCodeName);
            ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "png", file);
        } catch (Exception e) {
            System.out.printf("Lỗi " + e.getMessage());
        }
    }
}
