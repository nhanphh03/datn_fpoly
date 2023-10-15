package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "LuotXem_FA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LuotXemFA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idDGFA")
    private UUID idDGFA;

    @ManyToOne
    @JoinColumn(name="id_Giay")
    private Giay giay;

    @ManyToOne
    @JoinColumn(name="id_KhachHang")
    private KhachHang khachHang;

    @Column(name="tg_Them")
    private Date tgThem;

    @Column(name="tg_Sua")
    private Date tgSua;

    @Column(name = "min_price")
    private Double minPrice;

    @Column(name = "slTon")
    private int slTon;

    @Column(name="url1")
    private String hinhAnh;

    @Column(name = "so_Luong_Da_Ban")
    private int soLuongDaBan;

    @Column(name="trang_Thai")
    private int trangThai;

    @Column(name="loai")
    private int loai;


}
