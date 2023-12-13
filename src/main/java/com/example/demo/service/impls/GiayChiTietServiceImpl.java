package com.example.demo.service.impls;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.repository.GiayChiTietRepository;
import com.example.demo.repository.GiayRepository;
import com.example.demo.service.GHCTService;
import com.example.demo.service.GiayChiTietService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class GiayChiTietServiceImpl implements GiayChiTietService {

    @Autowired
    private GiayChiTietRepository giayChiTietRepository;

    @Autowired
    private GiayRepository giayRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Autowired
    private HangRepository hangRepository;

    @Autowired
    private GHCTService ghctService;
    private KMCTProductRepository kmctProductRepository;

    @Override
    public List<ChiTietGiay> getAllChiTietGiay() {
        return giayChiTietRepository.findAllByOrderByTgThemDesc();
    }

    @Override
    public List<ChiTietGiay> getTop4ChiTietGiay() {
        List<ChiTietGiay> allChiTietGiay = giayChiTietRepository.findAllByOrderByTgThemDesc();
        int limit = 4;
        if (allChiTietGiay.size() <= limit) {
            return allChiTietGiay;
        } else {
            return allChiTietGiay.subList(0, limit);
        }
    }

    @Override
    public void save(ChiTietGiay chiTietGiay) {
        giayChiTietRepository.save(chiTietGiay);
    }

    @Override
    public void update(ChiTietGiay chiTietGiay) {
        giayChiTietRepository.save(chiTietGiay);
    }

    @Override
    public void deleteByIdChiTietGiay(UUID id) {
        giayChiTietRepository.deleteById(id);
    }

    @Override
    public ChiTietGiay getByIdChiTietGiay(UUID id) {
        return giayChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public List<ChiTietGiay> getCTGByGiay(Giay giay) {
        return giayChiTietRepository.findByGiay(giay);
    }

    @Override
    public List<ChiTietGiay> getCTGByGiayActive(Giay giay) {
        return giayChiTietRepository.findByTrangThaiAndGiay(1, giay);
    }

    @Override
    public List<ChiTietGiay> getCTGByGiaySoldOut(Giay giay) {
        return giayChiTietRepository.findByTrangThaiAndGiay(2, giay);
    }

    @Override
    public HinhAnh hinhAnhByGiayAndMau(Giay giay, MauSac mauSac) {
        return giayChiTietRepository.findDistinctByGiay(giay, mauSac);
    }

    @Override
    public List<ChiTietGiay> fillterCTG(String searchTerm) {
        if ("Size".equals(searchTerm) && "Màu Sắc".equals(searchTerm)) {
            return giayChiTietRepository.findAll();
        }
        return giayChiTietRepository.customSearchCTG(searchTerm);
    }

    @Override
    public List<ChiTietGiay> fillterGCT(String searchTerm) {
        if ("Size".equals(searchTerm) && "Màu Sắc".equals(searchTerm) && "Giày".equals(searchTerm)) {
            return giayChiTietRepository.findAll();
        }
        return giayChiTietRepository.customSearchGCT(searchTerm);
    }

    @Transactional
    public void importChiTietGiayFromExcel(InputStream excelFile) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ChiTietGiay chiTietGiay = createChiTietGiayFromRow(row);
                giayChiTietRepository.save(chiTietGiay);
            }
        }
    }

    private ChiTietGiay createChiTietGiayFromRow(Row row) {
        ChiTietGiay chiTietGiay = new ChiTietGiay();

        // Mã CTG (Giả sử là chuỗi)
        chiTietGiay.setMaCTG(row.getCell(0).getStringCellValue());

        // Size (Giả sử là số nguyên)
        int soSize = (int) row.getCell(1).getNumericCellValue();
        Size size = sizeRepository.findBySoSize(soSize);
        chiTietGiay.setSize(size);

        // Khuyến mãi chi tiết (Giả sử là chuỗi)
        String tenKM = row.getCell(2).getStringCellValue();
        KhuyenMaiChiTietCTG khuyenMaiChiTietCTG = kmctProductRepository.findByTenKM(tenKM);
        chiTietGiay.setKhuyenMaiChiTietCTG(khuyenMaiChiTietCTG);

        // Giày (Giả sử là chuỗi)
        String giayName = row.getCell(3).getStringCellValue();
        Giay giay = giayRepository.findByTenGiay(giayName);
        chiTietGiay.setGiay(giay);

        // Hình ảnh (Giả sử là chuỗi)
        String hinhAnhName = row.getCell(4).getStringCellValue();
        HinhAnh hinhAnh = hinhAnhRepository.findByMaAnh(hinhAnhName);
        chiTietGiay.setHinhAnh(hinhAnh);

        // Màu sắc (Giả sử là chuỗi)
        String mauSacName = row.getCell(5).getStringCellValue();
        MauSac mauSac = mauSacRepository.findByTenMau(mauSacName);
        chiTietGiay.setMauSac(mauSac);

        // Năm sản xuất (Giả sử là số nguyên)
        chiTietGiay.setNamSX((int) row.getCell(6).getNumericCellValue());

        // Năm bảo hành (Giả sử là số nguyên)
        chiTietGiay.setNamBH((int) row.getCell(7).getNumericCellValue());

        // Trọng lượng (Giả sử là số nguyên)
        chiTietGiay.setTrongLuong((int) row.getCell(8).getNumericCellValue());

        // Giá bán (Giả sử là số thập phân)
        chiTietGiay.setGiaBan(row.getCell(9).getNumericCellValue());

        // Số lượng (Giả sử là số nguyên)
        chiTietGiay.setSoLuong((int) row.getCell(10).getNumericCellValue());

        // Trạng thái (Giả sử là số nguyên)
        chiTietGiay.setTrangThai((int) row.getCell(11).getNumericCellValue());

        // Thời gian thêm (Giả sử là ngày tháng)
        chiTietGiay.setTgThem(row.getCell(12).getDateCellValue());

        // Thời gian sửa (Giả sử là ngày tháng)
        chiTietGiay.setTgSua(row.getCell(13).getDateCellValue());

        // Mã người sửa (Giả sử là chuỗi)
        chiTietGiay.setMaNVSua(row.getCell(14).getStringCellValue());

        // Số tiền trước khi giảm (Giả sử là số thập phân)
        chiTietGiay.setSoTienTruocKhiGiam(row.getCell(15).getNumericCellValue());

        // Lý do sửa (Giả sử là chuỗi)
        chiTietGiay.setLyDoSua(row.getCell(16).getStringCellValue());

        // Barcode (Giả sử là chuỗi)
        chiTietGiay.setBarcode(row.getCell(17).getStringCellValue());
        return chiTietGiay;
    }

    public List<Size> findDistinctSizeByGiayAndMauSac(Giay giay, MauSac mauSac) {
        return giayChiTietRepository.findDistinctSizeByGiayAndTrangThai(giay, mauSac);
    }

    @Override
    public List<ChiTietGiay> findByMauSacAndGiay(MauSac mauSac, Giay giay, int trangThai) {
        return giayChiTietRepository.findByMauSacAndGiayAndTrangThai(mauSac, giay, trangThai);
    }

    public List<MauSac> findDistinctMauSacByGiay(Giay giay) {
        return giayChiTietRepository.findDistinctMauSacByGiayAndTrangThai(giay);
    }

    @Override
    public List<ChiTietGiay> findByGiayAndMau(Giay giay, MauSac mauSac) {
        return giayChiTietRepository.findByGiayAndMauSac(giay, mauSac);
    }

    @Override
    public void updatePriceCTGGHCT(ChiTietGiay chiTietGiay) {
        GioHangChiTiet gioHangChiTiet = ghctService.findByCTSP(chiTietGiay);
        gioHangChiTiet.setDonGia(gioHangChiTiet.getSoLuong() * chiTietGiay.getSoTienTruocKhiGiam());
        gioHangChiTiet.setDonGiaKhiGiam(gioHangChiTiet.getSoLuong() * chiTietGiay.getGiaBan());
        ghctService.addNewGHCT(gioHangChiTiet);
    }

    @Override
    public boolean isDuplicateChiTietGiay(UUID giayId, UUID sizeId, UUID mauSacId, UUID hinhAnhId) {
        return giayChiTietRepository.findByGiayAndSizeAndMauSacAndHinhAnh(giayId, sizeId, mauSacId, hinhAnhId)
                .isPresent();
    }

    @Override
    public List<ChiTietGiay> findByGiay(Giay giay) {
        return giayChiTietRepository.findByGiay(giay);
    }

    @Override
    public List<ChiTietGiay> findByMauSac(MauSac mauSac) {
        return giayChiTietRepository.findByMauSac(mauSac);
    }

    @Override
    public List<ChiTietGiay> findBySize(Size size) {
        return giayChiTietRepository.findBySize(size);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<ChiTietGiay> findProductsOutOfWarranty() {
        Date currentDate = new Date(); // Lấy thời gian hiện tại

        String jpql = "SELECT p FROM ChiTietGiay p WHERE p.namBH > 0 AND p.namBH < :currentYear";
        List<ChiTietGiay> products = entityManager.createQuery(jpql, ChiTietGiay.class)
                .setParameter("currentYear", currentDate.getYear() + 1900)
                .getResultList();

        return products;
    }

    @Override
    public ChiTietGiay findByMa(String ma) {
        return giayChiTietRepository.findByMaCTG(ma);
    }
}
