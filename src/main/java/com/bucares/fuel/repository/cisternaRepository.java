package com.bucares.fuel.repository;

import com.bucares.fuel.models.cisterna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cisternaRepository extends JpaRepository<cisterna, Long> {
    cisterna findByPlaca(String placa);
    cisterna findByCondicion(String condicion);
    cisterna findByKilometraje(int kilometraje);
    cisterna deleteByPlaca(String placa);
}
