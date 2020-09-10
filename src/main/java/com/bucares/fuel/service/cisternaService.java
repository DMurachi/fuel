package com.bucares.fuel.service;

import com.bucares.fuel.models.cisterna;
import com.bucares.fuel.repository.cisternaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cisternaService {
    @Autowired
    private cisternaRepository cisternaRepository;

    public cisterna storeCisterna(cisterna cisterna){ return cisternaRepository.save(cisterna); }
    public List<cisterna> getAllCisterna(){ return cisternaRepository.findAll();}
    public cisterna getCisternaByPlaca(String placa){ return cisternaRepository.findByPlaca(placa); }
    public List<cisterna> getCisternaByCondicion(String condicion){ return (List<cisterna>) cisternaRepository.findByCondicion(condicion); }
    public boolean checkKm(String placa){
        cisterna cisternaAux = cisternaRepository.findByPlaca(placa);
        if(cisternaAux.getKm()>= 2000){//KMlimite
            return false;
        }
        if(cisternaAux.getKm() + 800 >= 2000){
            return false;
        }
        if(cisternaAux.getKm() + 700 < 2000){
            return true;
        }
        return false;
    }

    public void switcher(String condicion, cisterna cisterna){
        if(condicion == "activo" ){
            cisterna.setCondicion("inactivo");
        }
        if(condicion == "inactivo"){
            cisterna.setCondicion("activo");
        }
    }
    public void deleteCisterna(String placa){ cisternaRepository.deleteByPlaca(placa); }
    public List<cisterna> getCisternaByKilometraje(int kilometraje){ return (List<cisterna>) cisternaRepository.findByKilometraje(kilometraje); }
}
