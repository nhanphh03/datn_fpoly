package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name ="id_Hang")
    private UUID idHang;

    @Column(name = "ma_Hang")
    private String maHang;

    @Column(name="ten_Hang")
    private String tenHang;

    @Column( name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;
}
