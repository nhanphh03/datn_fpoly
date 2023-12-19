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

    @Override
    public void importDataFromExcel(InputStream excelFile) {
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên (index 0)
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    // Bỏ qua hàng đầu tiên nếu nó là tiêu đề
                    continue;
                }
                ChiTietGiay chiTietGiay = new ChiTietGiay();
                chiTietGiay.setMaCTG(row.getCell(0).getStringCellValue());

                String maGiay = row.getCell(1).getStringCellValue();
                Giay giay = giayRepository.findByMaGiay(maGiay);
                chiTietGiay.setGiay(giay);

                String maSize = row.getCell(2).getStringCellValue();
                Size size = sizeRepository.findByMaSize(maSize);
                chiTietGiay.setSize(size);

                String maMau = row.getCell(3).getStringCellValue();
                MauSac mauSac = mauSacRepository.findByMa(maMau);
                chiTietGiay.setMauSac(mauSac);

                if (row.getCell(4).getCellType() == CellType.NUMERIC) {
                    chiTietGiay.setNamSX((int) row.getCell(4).getNumericCellValue()); // Chuyển đổi thành kiểu int
                }

                if (row.getCell(5).getCellType() == CellType.NUMERIC) {
                    chiTietGiay.setNamBH((int) row.getCell(5).getNumericCellValue()); // Chuyển đổi thành kiểu int
                }

                if (row.getCell(6).getCellType() == CellType.NUMERIC) {
                    chiTietGiay.setTrongLuong((int) row.getCell(6).getNumericCellValue()); // Chuyển đổi thành kiểu int
                }

                Cell giaBanCell = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                if (giaBanCell.getCellType() == CellType.NUMERIC) {
                    chiTietGiay.setGiaBan(giaBanCell.getNumericCellValue());
                }

                if (row.getCell(8).getCellType() == CellType.NUMERIC) {
                    chiTietGiay.setSoLuong((int) row.getCell(8).getNumericCellValue()); // Chuyển đổi thành kiểu int
                }

                String maHA = row.getCell(9).getStringCellValue();
                HinhAnh hinhAnh = hinhAnhRepository.findByMaAnh(maHA);
                chiTietGiay.setHinhAnh(hinhAnh);

                chiTietGiay.setGiaBan(3000000);
                chiTietGiay.setSoTienTruocKhiGiam(3000000.00);
                chiTietGiay.setTrangThai(1);
                chiTietGiay.setTgThem(new Date());

                giayChiTietRepository.save(chiTietGiay);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi ở đâu đó ================================================================================================="+ e.getMessage());
            // Xử lý lỗi nếu cần
        }
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
        gioHangChiTiet.setDonGiaTruocKhiGiam(gioHangChiTiet.getSoLuong() * chiTietGiay.getGiaBan());
        ghctService.addNewGHCT(gioHangChiTiet);
    }

    @Override
    public List<ChiTietGiay> isDuplicateChiTietGiay(UUID giayId, UUID sizeId, UUID mauSacId, UUID hinhAnhId) {
        return giayChiTietRepository.findByGiayAndSizeAndMauSacAndHinhAnh(giayId, sizeId, mauSacId, hinhAnhId);
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
