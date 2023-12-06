package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Danh_Gia_Khach_Hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DanhGiaKhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_DGKH")
    private UUID idDGKH;

    @ManyToOne
    @JoinColumn(name = "id_KH")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "id_Giay")
    private Giay giay;

    @Column(name="noi_Dung_DG")
    private String noiDungDG;

    @Column(name="so_Sao")
    private int soSao;

    @Column(name="trang_Thai")
    private int trangThai;

    @Column(name="anh_Danh_Gia")
    private String anhDG;

    @Column(name="anh_Danh_Gia2")
    private String anhDG2;

    @Column(name="anh_Danh_Gia3")
    private String anhDG3;

    @Column(name = "tg_DG")
    private Date tgDG;

}
