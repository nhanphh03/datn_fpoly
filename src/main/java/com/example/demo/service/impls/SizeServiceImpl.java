package com.example.demo.service.impls;

import com.example.demo.model.Size;
import com.example.demo.repository.SizeRepository;
import com.example.demo.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> getAllSize() {
        return sizeRepository.findAll();
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
        return sizeRepository.findBySoSizeAndMaSize(selectedSize, maSize);
    }

}
