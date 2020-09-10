package com.bucares.fuel.service;

import com.bucares.fuel.models.chofer;
import com.bucares.fuel.repository.choferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class choferService {
    @Autowired
    private choferRepository choferRepository;

    public chofer storeChofer(chofer chofer) {
        return choferRepository.save(chofer);
    }

    public List<chofer> getAllChofer() {
        return choferRepository.findAll();
    }

    public chofer getChoferByCedula(String cedula) {
        return choferRepository.findByCedula(cedula);
    }
    public List<chofer> getChoferByCondicion(String condicion) { return (List<chofer>) choferRepository.findByCondicion(condicion); }
    public void deleteChoferByCedula(String cedula){ choferRepository.deleteByCedula(cedula); }
    public void switcher(String condicion, chofer chofer){
        if(condicion == "activo" ){
            chofer.setCondicion("inactivo");
        }
        if(condicion == "inactivo"){
            chofer.setCondicion("activo");
        }
    }
}
