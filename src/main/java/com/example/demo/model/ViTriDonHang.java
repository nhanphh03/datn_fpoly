package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Vi_Tri_Don_Hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViTriDonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_VTDH")
    private UUID idVTDH;

    @ManyToOne
    @JoinColumn(name="id_Giao_Hang")
    private GiaoHang giaoHang;

    @Column(name="trang_Thai")
    private int trangThai;

    @Column(name = "thoi_Gian")
    private Date thoiGian;

    @Column(name ="noi_Dung")
    private String noiDung;

    @Column(name ="vi_Tri_DH")
    private String viTri;

}
