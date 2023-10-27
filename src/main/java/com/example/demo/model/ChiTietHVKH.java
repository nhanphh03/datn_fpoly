package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Chi_Tiet_HVKH")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ChiTietHVKH {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_CT_HVKH")
    private UUID idCTHVKH;

    @ManyToOne
    @JoinColumn(name = "id_Loai_HV")
    private LoaiHanhVi loaiHanhVi;

    @ManyToOne
    @JoinColumn(name = "id_HVKH")
    private HanhViKhachHang hanhViKhachHang;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "id_HVCTSP")
    private UUID id_CTSP;

    @Column(name = "id_HVGiay")
    private UUID id_Giay;

    @Column(name = "id_HVHang")
    private UUID id_Hang;

    @Column(name = "id_HVSize")
    private UUID id_Size;

    @Column(name = "id_HVMau")
    private UUID id_Mau;

    @Column(name = "tbGiaSP")
    private Double tbGia;

    @Column(name = "keyword_Search")
    private  String keywordSearch;

}
