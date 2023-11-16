package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Khuyen_Mai_Chi_Tiet_Hoa_Don")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhuyenMaiChiTietHoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id+KMCT_HD")
    private UUID idKMCTHD;

    @ManyToOne
    @JoinColumn(name="id_Khuyen_Mai")
    private KhuyenMai khuyenMai;

    @ManyToOne
    @JoinColumn(name="id_HoaDon")
    private HoaDon hoaDon;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "sotienGiam")
    private Double soTienGiam;
}
