package com.bucares.fuel.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chuto")
public class chuto {
    @Column(unique = true, nullable = false)
    private String placa;

    @Column(nullable = false)
    private String condicion;

    @Column(nullable = false)
    private int capacidad;

    @Column(nullable = false)
    private int km;
}
