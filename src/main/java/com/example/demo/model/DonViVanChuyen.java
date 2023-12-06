package com.example.demo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name= "don_vi_van_chuyen")
@Getter
@Setter
@AllArgsConstructor
public class DonViVanChuyen {

    @Id
    @Column(name = "id_cvvc")
    private int IdDistrict;

    @Column(name = "ma_DVVC")
    private String maDVVC;

    @Column(name = "ten_DVVC")
    private String tenDVVC;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Tao")
    private Date tgTao;

}
