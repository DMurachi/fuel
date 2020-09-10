package com.bucares.fuel.repository;

import com.bucares.fuel.models.chofer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface choferRepository extends JpaRepository<chofer, Long> {
    chofer findByCedula(String cedula);
    chofer findByCondicion(String condicion);
    chofer deleteByCedula(String cedula);
}
