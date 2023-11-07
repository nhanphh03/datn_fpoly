package com.example.demo.service.impls;

import com.example.demo.model.Size;
import com.example.demo.repository.SizeRepository;
import com.example.demo.service.SizeService;
import jakarta.transaction.Transactional;
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
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> getAllSizeActiveOrderByName() {
        return sizeRepository.findByTrangThaiOrderByMaSize(1);
    }

    @Override
    public List<Size> getAllSize() {
        return sizeRepository.findAllByOrderByTgThemDesc();
    }

    @Override
    public void save(Size size) {
        sizeRepository.save(size);
    }

    @Override
    public void deleteByIdSize(UUID id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public Size getByIdSize(UUID id) {
        return sizeRepository.findById(id).orElse(null);
    }

    @Override
    public java.util.List<Size> getByActive() {
        return sizeRepository.findByTrangThai(1);
    }

    @Override
    public List<Size> filterSizes(Integer selectedSize, String maSize) {
        if ("Size".equals(selectedSize) && "Mã Size".equals(maSize)) {
            // Trường hợp không chọn kích thước, bạn có thể trả về toàn bộ danh sách
            return sizeRepository.findAll();
        }
        // Thực hiện truy vấn hoặc logic lọc dữ liệu dựa trên selectedSize
        return sizeRepository.findBySoSizeOrMaSize(selectedSize, maSize);
    }

    @Transactional
    @Override
    public void importDataFromExcel(InputStream excelFile) {
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên (index 0)

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    // Bỏ qua hàng đầu tiên nếu nó là tiêu đề
                    continue;
                }
                Size size = new Size();
                size.setMaSize(row.getCell(0).getStringCellValue()); // Cột 0 trong tệp Excel
                // Đảm bảo cột 1 trong tệp Excel chứa số nguyên
                if (row.getCell(1).getCellType() == CellType.NUMERIC) {
                    size.setSoSize((int) row.getCell(1).getNumericCellValue()); // Chuyển đổi thành kiểu int
                }
                size.setTgThem(new Date());
                size.setTrangThai(1);
                sizeRepository.save(size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
    }

}
