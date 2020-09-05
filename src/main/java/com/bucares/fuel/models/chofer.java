package com.bucares.fuel.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chofer")
public class chofer {
    @Column(unique = true, nullable = false)
    private String cedula;

    @Column(nullable = false)
    private Object nombre;

    @Column(nullable = false)
    private String condicion;

    @Column(nullable = false)
    private String password;

    public Object getCedula() {
            return this.cedula;
    }

    public void setCedula(Object cedula) {
        this.cedula= (String) cedula;
    }
    public Object getNombre() {
            return this.nombre;
    }

    public void setNombre(Object nombre) {
            this.nombre=nombre;
    }


    public Object getCondicion() {
            return this.condicion;
    }

    public void setCondicion(Object condicion) {
            this.condicion= (String) condicion;
    }

    public Object getPassword() {
            return this.password;
    }

    public void setPassword(Object password) {
            this.password= (String) password;
    }
}
