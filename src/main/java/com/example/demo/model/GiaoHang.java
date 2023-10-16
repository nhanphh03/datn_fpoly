package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Giao_Hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiaoHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Giao_Hang")
    private UUID idGH;

    @ManyToOne
    @JoinColumn(name="idDH")
    private DonHang donHang;

    @ManyToOne
    @JoinColumn(name="idNV")
    private NhanVien nhanVien;

    @Column(name="trang_Thai")
    private int trangThai;

    @Column(name = "thoi_Gian")
    private Date thoiGian;

    @Column(name ="noi_Dung")
    private String noiDung;
}
