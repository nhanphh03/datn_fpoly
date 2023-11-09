package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="Chat_Lieu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ChatLieu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name ="id_ChatLieu")
    private UUID idChatLieu;

    @NotBlank
    @Column(name = "ma_ChatLieu")
    private String maChatLieu;

    @NotBlank
    @Column(name="ten_ChatLieu")
    private String tenChatLieu;

    @Column( name = "trang_Thai")
    private int trangThai;

    @Column(name = "tg_Them")
    private Date tgThem;

    @Column(name = "tg_Sua")
    private Date tgSua;

}
