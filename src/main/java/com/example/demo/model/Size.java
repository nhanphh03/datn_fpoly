package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "Size")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Size {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO )
    @Column(name ="id_Size")
    private UUID idSize;

    @NotEmpty(message = "Không được để trống Mã")
    @Column(name = "ma_Size")
    private String maSize;

    @Min(value = 1, message = "Size phải lớn hơn 0")
    @Max(value = 50, message = "Size phải nhỏ hơn 50")
    @NotNull(message = "Không được để trống Size")
    @Column(name="so_Size")
    private int soSize;

    @Column(name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;
}
