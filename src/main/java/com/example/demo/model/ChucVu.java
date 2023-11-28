package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Chuc_Vu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChucVu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name ="id_Chuc_Vu")
    private UUID idCV;

    @Column(name = "ma_Chuc_Vu")
    private String maCV;

    @Column(name="ten_Chuc_Vu")
    private String tenCV;

    @Column( name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;
}
