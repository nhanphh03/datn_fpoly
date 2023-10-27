package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "MauSac")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MauSac {

    //Nhan Pham Ngu
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name ="id_Mau")
    private UUID idMau;

    @NotBlank
    @Column(name = "ma")
    private String ma;

    @NotBlank
    @Column(name="ma_Mau")
    private String maMau;

    @NotBlank
    @Column(name="ten_Mau")
    private String tenMau;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;
}
