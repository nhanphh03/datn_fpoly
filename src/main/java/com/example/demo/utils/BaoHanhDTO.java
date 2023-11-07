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
public class BaoHanhDTO {
    private UUID idCTG;
    private String maGiay;
    private String tenGiay;
    private String tenHang;
    private int namBaoHanh;
}
