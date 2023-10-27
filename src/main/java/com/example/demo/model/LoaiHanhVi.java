package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Loai_Hanh_Vi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoaiHanhVi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Loai_HV")
    private UUID idLHV;

    @Column(name = "ma_Loai_HV")
    private String maLoaiHV;

    @Column(name = "ten_Hanh_Vi")
    private String tenHV;

    @Column(name = "tg_Tao")
    private Date tgTao;

    @Column(name = "trangThai_Login")
    private Boolean ttLogin;

    @Column(name = "tg_Sua")
    private Date tgSua;

    @Column(name = "mo_Ta")
    private String moTa;

    @Column(name = "trang_Thai")
    private int trangThai;
}
