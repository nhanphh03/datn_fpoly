package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name= "Don_Hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Don_Hang")
    private UUID idDH;

    @OneToOne
    @JoinColumn(name="idHD")
    private HoaDon hoaDon;

    @Column(name = "dc_GH")
    private String dia_Chi_GH;

    @Column(name = "tong_Tien")
    private Double tong_Tien;

    @Column(name = "ten_KH")
    private String tenKH;

    @Column(name = "sdt_KH")
    private String sdtKH;

    @Column(name = "ghi_Chu")
    private String ghi_Chu;

}
