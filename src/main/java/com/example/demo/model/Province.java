package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name= "province")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Province {

    @Id
    @Column(name = "province_id")
    private int IdPovince;

    @Column( name = "province_name")
    private String nameProvince;

    @Column( name = "transport_coefficient")
    private Integer transportCoefficient;

}
