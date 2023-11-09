package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Hinh_Thuc_Thanh_Toan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idHTTT")
    private UUID idHTTT;

    @Column(name="name_HTTT")
    private String nameHTTT;

    @Column(name = "tgTao")
    private Date tgTao;

    @Column(name = "trang_Thai")
    private int trangThai;
}
