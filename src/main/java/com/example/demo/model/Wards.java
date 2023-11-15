package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name= "ward")
@Getter
@Setter
@AllArgsConstructor
public class Wards {

    @Id
    @Column(name = "wards_id")
    private int IdWards;

    @ManyToOne
    @JoinColumn( name = "district_id")
    private District district;

    @Column( name = "wards_name")
    private String nameWards;

    @Column( name = "transport_coefficient")
    private Integer transportCoefficient;

}
