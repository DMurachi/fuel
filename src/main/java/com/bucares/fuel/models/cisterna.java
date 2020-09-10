package com.bucares.fuel.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class cisterna {
    @Column(unique = true, nullable = false)
    private String placa;

    @Column(nullable = false)
    private String condicion;

    @Column(nullable = false)
    private int capacidad;

    @Column(nullable = false)
    private int km;

    @Column(nullable=false)
    private int kmRecorrido;
    @Column(nullable=false)
    private int kmAcumulado;
    @Column(nullable = false)
    private int kmLimite;

    public cisterna(String placa, String condicion, int capacidad, int km) {
        this.placa=placa;
        this.condicion=condicion;
        this.capacidad=capacidad;
        this.km=km;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa=placa;
    }

    public String getCondicion() {
        return this.condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion=condicion;
    }

    public int getKm() {
        return this.km;
    }

    public void setKm(int kilometraje) {
        this.km= kilometraje;
    }

    public int getCapacidad(){
        return this.capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad=capacidad;
    }
}
