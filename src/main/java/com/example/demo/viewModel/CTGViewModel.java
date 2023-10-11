package com.example.demo.viewModel;

import jakarta.persistence.*;
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
public class CTGViewModel {
    @Id
    @Column(name = "id_giay")
    private UUID idGiay;

    @Column(name = "min_price")
    private Double minPrice;

    @Column(name = "ten_giay")
    private String tenGiay;

    @Column(name = "slTon")
    private int slTon;

    @Column(name="url1")
    private String hinhAnh;

    @Column(name = "so_Luong_Da_Ban")
    private int soLuongDaBan;

}
