package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Loai_Khuyen_Mai")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoaiKhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_LKM")
    private UUID idLKM;

    @Column(name = "ma_LKM")
    private String maLKM;

    @Column(name = "ten_LKM")
    private String tenLKM;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;
}
