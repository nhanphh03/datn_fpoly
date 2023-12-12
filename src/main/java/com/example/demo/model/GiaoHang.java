package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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

    @ManyToOne
    @JoinColumn(name="idHD")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name="id_DVVC")
    private DonViVanChuyen donViVanChuyen;

    @Column(name = "ma_van_don")
    private String maVanDon;

    @Column(name="trang_Thai")
    private int trangThai;

    @Column(name = "thoi_Gian")
    private Date thoiGian;

    @Column(name ="noi_Dung")
    private String noiDung;

    @Column(name ="vi_Tri_DH")
    private String viTri;
}
