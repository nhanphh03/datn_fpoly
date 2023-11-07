package com.example.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPhamBanChayDTO {
    private UUID idCTG;
    private String tenGiay;
    private int soSize;
    private String tenHang;
    private String tenChatLieu;
    private String tenMau;
    private int soLuongBan;
}
