package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Thong_Bao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThongBaoKhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idThong_Bao")
    private UUID idTB;

    @ManyToOne
    @JoinColumn(name="idHD")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name="idKH")
    private KhachHang khachHang;

    @Column(name = "ma_TB")
    private String maTB;

    @Column(name = "noi_Dung")
    private String noiDungTB;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name= "tg_TB")
    private Date tgTB;

}
