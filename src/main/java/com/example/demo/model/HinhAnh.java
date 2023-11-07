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
@Table(name = "HinhAnh")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnh {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_HinhAnh")
    private UUID idHinhAnh;

    @NotBlank
    @Column(name = "ma_Anh")
    private String maAnh;

    @Column(name = "url1")
    private String url1;

    @Column(name = "url2")
    private String url2;

    @Column(name = "url3")
    private String url3;

    @Column(name = "url4")
    private String url4;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;
}
