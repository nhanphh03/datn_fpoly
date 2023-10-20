package com.example.demo.service;

import com.example.demo.model.ChatLieu;
import com.example.demo.model.Size;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public interface SizeService {

    public List<Size> getAllSizeActiveOrderByName();

    public List<Size> getAllSize();

    public void save(Size size);

    public void deleteByIdSize(UUID id);

    public Size getByIdSize(UUID id);

    public List<Size> getByActive();

    public List<Size> filterSizes(Integer selectedSize, String maSize);

    public void importDataFromExcel(InputStream excelFile);

}
