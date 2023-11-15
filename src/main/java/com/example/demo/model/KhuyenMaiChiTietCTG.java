package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Khuyen_Mai_Chi_Tiet_CTG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhuyenMaiChiTietCTG {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_KMCT_CTG")
    private UUID idKMCTCTG;

    @ManyToOne
    @JoinColumn(name="id_Khuyen_Mai")
    private KhuyenMai khuyenMai;

    @Column(name = "ten_KM")
    private String tenKM;

    @ManyToOne
    @JoinColumn(name="id_Chi_Tiet_Giay")
    private ChiTietGiay chiTietGiay;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;

    // Getter và setter cho tenKM
    public String getTenKM() {
        return khuyenMai != null ? khuyenMai.getTenKM() : null;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }
}
