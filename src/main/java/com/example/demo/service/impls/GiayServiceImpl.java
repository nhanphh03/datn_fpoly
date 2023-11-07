package com.example.demo.service.impls;

import com.example.demo.model.ChatLieu;
import com.example.demo.model.Giay;
import com.example.demo.model.Hang;
import com.example.demo.model.Size;
import com.example.demo.repository.ChatLieuRepository;
import com.example.demo.repository.GiayRepository;
import com.example.demo.repository.HangRepository;
import com.example.demo.service.GiayService;
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
public class GiayServiceImpl implements GiayService {
    @Autowired
    private GiayRepository giayRepository;
    @Autowired
    private HangRepository hangRepository;
    @Autowired
    private ChatLieuRepository chatLieuRepository;

    @Override
    public List<Giay> getAllGiay() {
        return giayRepository.findAllByOrderByTgThemDesc();
    }

    @Override
    public void save(Giay giay) {
        giayRepository.save(giay);
    }

    @Override
    public void deleteByIdGiay(UUID id) {
        giayRepository.deleteById(id);
    }

    @Override
    public Giay getByIdGiay(UUID id) {
        return giayRepository.findById(id).orElse(null);
    }

    @Override
    public Giay getByName(String name) {
        return giayRepository.findByTenGiay(name);
    }

    @Override
    public List<Giay> fillterGiay(String searchTerm) {
        if ("Mã Giày".equals(searchTerm) && "Tên Giày".equals(searchTerm) && "Hãng".equals(searchTerm) && "Chất Liệu".equals(searchTerm)) {
            return giayRepository.findAll();
        }
        return giayRepository.customSearch(searchTerm);
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
                Giay giay = new Giay();
                giay.setMaGiay(row.getCell(0).getStringCellValue()); // Cột 0 trong tệp Excel
                giay.setTenGiay(row.getCell(1).getStringCellValue()); // Cột 1 trong tệp Excel

                // Đối tượng Hang
                String hangName = row.getCell(2).getStringCellValue();
                Hang hang = hangRepository.findByTenHang(hangName); // Tìm đối tượng Hang theo tên
                giay.setHang(hang);

                // Đối tượng ChatLieu
                String chatLieuName = row.getCell(3).getStringCellValue();
                ChatLieu chatLieu = chatLieuRepository.findByTenChatLieu(chatLieuName); // Tìm đối tượng ChatLieu theo tên
                giay.setChatLieu(chatLieu);

                giay.setMoTa(row.getCell(4).getStringCellValue());
                giay.setTrangThai(1);
                giay.setTgThem(new Date());
                giayRepository.save(giay);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            // Xử lý lỗi nếu cần
        }
    }

    @Override
    public List<Giay> findByHang(Hang hang) {
        return giayRepository.findByHang(hang);
    }

    @Override
    public List<Giay> findByChatLieu(ChatLieu chatLieu) {
        return giayRepository.findByChatLieu(chatLieu);
    }

    @Override
    public List<Giay> findByTrangThai(int trangThai) {
        return giayRepository.findByTrangThai(trangThai);
    }
}
