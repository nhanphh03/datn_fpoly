package com.example.demo.service.impls;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.repository.GiayChiTietRepository;
import com.example.demo.repository.GiayRepository;
import com.example.demo.service.GiayChiTietService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

                // Đối tượng Giay
                String giayName = row.getCell(0).getStringCellValue(); // Cột 2 trong tệp Excel
                Giay giay = giayRepository.findByTenGiay(giayName); // Tìm đối tượng Giay theo tên
                chiTietGiay.setGiay(giay);

                // Đối tượng HinhAnh
                String hinhAnhName = row.getCell(1).getStringCellValue(); // Cột 3 trong tệp Excel
                HinhAnh hinhAnh = hinhAnhRepository.findByMaAnh(hinhAnhName); // Tìm đối tượng HinhAnh theo tên
                chiTietGiay.setHinhAnh(hinhAnh);

                // Đối tượng Size
                int soSize = (int) row.getCell(2).getNumericCellValue(); // Cột 1 trong tệp Excel
                Size size = sizeRepository.findBySoSize(soSize); // Tìm đối tượng Size theo soSize
                chiTietGiay.setSize(size);

                // Đối tượng MauSac
                String mauSacName = row.getCell(3).getStringCellValue(); // Cột 4 trong tệp Excel
                MauSac mauSac = mauSacRepository.findByTenMau(mauSacName); // Tìm đối tượng MauSac theo tên
                chiTietGiay.setMauSac(mauSac);
                //
                if (row.getCell(4).getCellType() == CellType.NUMERIC) {
                    chiTietGiay.setNamSX((int) row.getCell(4).getNumericCellValue()); // Chuyển đổi thành kiểu int
                }
                if (row.getCell(5).getCellType() == CellType.NUMERIC) {
                    chiTietGiay.setNamBH((int) row.getCell(5).getNumericCellValue()); // Chuyển đổi thành kiểu int
                }
                if (row.getCell(6).getCellType() == CellType.NUMERIC) {
                    chiTietGiay.setTrongLuong((int) row.getCell(6).getNumericCellValue()); // Chuyển đổi thành kiểu int
                }
                chiTietGiay.setGiaBan(row.getCell(7).getNumericCellValue());
                if (row.getCell(8).getCellType() == CellType.NUMERIC) {
                    chiTietGiay.setSoLuong((int) row.getCell(8).getNumericCellValue()); // Chuyển đổi thành kiểu int
                }

                chiTietGiay.setTgThem(new Date());
                chiTietGiay.setTrangThai(1);
                giayChiTietRepository.save(chiTietGiay);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
}
