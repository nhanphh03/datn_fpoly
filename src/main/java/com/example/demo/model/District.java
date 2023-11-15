package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name= "district")
@Getter
@Setter
@AllArgsConstructor
public class District {

    @Id
    @Column(name = "district_id")
    private int IdDistrict;

    @ManyToOne
    @JoinColumn( name = "province_id")
    private Province province;

    @Column( name = "district_name")
    private String nameDistrict;

    @Column( name = "transport_coefficient")
    private Integer transportCoefficient;

}
