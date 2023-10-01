package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Loai_Khach_Hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoaiKhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name ="id_Hang")
    private UUID idLKH;

    @Column(name = "ma_LoaiKH")
    private String maLKH;

    @Column(name="ten_LoaiKH")
    private String tenLKH;

    @Column( name = "trang_Thai")
    private int trangThai;

    @Column( name = "soDiem")
    private int soDiem;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;


}
