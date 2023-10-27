package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "_Hanh_Vi_Khach_Hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HanhViKhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_HVKH")
    private UUID idHVKH;

    @OneToOne
    @JoinColumn(name = "id_KH")
    private KhachHang khachHang;

    @Column(name = "ma_HVKH")
    private String ma_HVKH;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "trang_Thai")
    private int trangThai;


}
