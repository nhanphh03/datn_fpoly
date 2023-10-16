package com.example.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Không được để trống Mã")
    @Column(name = "ma_Hang")
    private String maHang;

    @NotEmpty(message = "Không được để trống Tên")
    @Column(name="ten_Hang")
    private String tenHang;

    @NotEmpty(message = "Không được để trống Logo")
    @Column(name="logo_Hang")
    private String logoHang;

    @Column( name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;
}
