package com.example.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

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
    @Column(name ="id_lkh")
    private UUID idLKH;

    @Column(name = "ma_LoaiKH")
    private String maLKH;

    @Column(name="ten_LoaiKH")
    private String tenLKH;

    @Column( name = "trang_Thai")
    private int trangThai;


    @Min(value = 1)
    @Column( name = "soDiem")
    private int soDiem;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;


}
