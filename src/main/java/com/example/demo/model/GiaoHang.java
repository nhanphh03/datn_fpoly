package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "Giao_Hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiaoHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Giao_Hang")
    private UUID idGH;

    @OneToMany(mappedBy = "giaoHang")
    private List<ViTriDonHang> viTriDonHangs;

    @OneToOne
    @JoinColumn(name = "id_HD")
    private HoaDon hoaDon;

    @Column(name = "ma_giao_hang")
    private String maGiaoHang;

    @Column(name="trang_Thai")
    private int trangThai;

    @Column(name = "thoi_Gian")
    private Date thoiGian;

    @Column(name ="noi_Dung")
    private String noiDung;

    @Column(name ="ten_dvvc")
    private String tenDVVC;

    @Column(name ="ma_van_don")
    private String maVanDon;

    @Column(name ="phi_giao_hang")
    private Double phiGiaoHang;
}
