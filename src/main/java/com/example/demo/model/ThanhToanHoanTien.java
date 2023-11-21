package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Thanh_Toan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThanhToanHoanTien {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idHTTT")
    private UUID idHTTT;

    @Column(name="maKH")
    private String maKH;

    @Column(name = "tgThanhToan")
    private Date tgThanhToan;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name="noiDungThanhToan")
    private String noiDungThanhToan;

}
