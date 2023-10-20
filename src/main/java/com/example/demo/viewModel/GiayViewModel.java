package com.example.demo.viewModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GiayViewModel {
    @Id
    @Column(name = "id_giay")
    private UUID idGiay;

    @Column(name = "ten_giay")
    private String tenGiay;

    @Column(name = "so_luong")
    private Long soLuong;

    @Column(name = "gia_ban")
    private double giaBan;

    @Column(name="url1")
    private String hinhAnh;

    @Column(name="ten_mau ")
    private String mauSac;
}
