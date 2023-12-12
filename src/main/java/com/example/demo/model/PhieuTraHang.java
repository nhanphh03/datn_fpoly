package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Phieu_Tra_Hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhieuTraHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_MPTH")
    private UUID idMPTH;

    @ManyToOne
    @JoinColumn(name = "id_KH")
    private KhachHang khachHang;

    @OneToOne
    @JoinColumn(name = "id_HD")
    private HoaDon hoaDon;

    @Column(name = "ma_MPTH")
    private String maMPTH;

    @Column(name = "ly_Do_Hoan")
    private String lyDoHoanHang;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Tao")
    private Date tgTao;

    @Column(name = "tg_Xac_Nhan")
    private Date tgXacNhan;

    @Column(name = "tg_Hoan_Thanh_Cong")
    private Date tgHoanThanhCong;

    @Column(name = "tg_Khach_Huy")
    private Date tgKhachHuy;

    @Column(name = "tg_Khach_Xac_Nhan")
    private Date tgKhachXacNhan;

    @Column(name = "hinh1")
    private String hinh1;

    @Column(name = "hinh2")
    private String hinh2;

    @Column(name = "hinh3")
    private String hinh3;
}
