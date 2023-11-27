package com.example.demo.viewModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class CTHDViewModel {
    @Id
    @Column(name = "id_hdct")
    private UUID idHDCT;

    @Column(name = "id_mau")
    private UUID idMau;
    @Column(name = "soLuong")
    private Double soLuong;
    @Column(name = "don_gia")
    private Double donGia;

}
