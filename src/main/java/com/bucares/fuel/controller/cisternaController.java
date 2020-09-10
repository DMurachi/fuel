package com.bucares.fuel.controller;

import com.bucares.fuel.models.cisterna;
import com.bucares.fuel.service.Response;
import com.bucares.fuel.service.cisternaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class cisternaController {
    @Autowired
    private cisternaService cisternaService;
    private static final Logger logger = LoggerFactory.getLogger(cisternaController.class);
    @GetMapping(value = "/cisterna")
    public ResponseEntity<Response<List<cisterna>>> getAllCisterna() {
        logger.info("Called resource: getAllCisterna");

        List<cisterna> cisternaList = cisternaService.getAllCisterna();
        Response<List<cisterna>> response = new Response<>("0000",cisternaList,null);

        logger.info("Called resource: getAllCisterna");

        logger.info("Consulted: every cisterna in database");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping(value = "/cisterna/{placa}")
    public ResponseEntity<Response<cisterna>> getCisternaByPlaca(@PathVariable("placa") String placa) {
        logger.info("Called resource: getCisternaByPlaca");

        cisterna cisterna = cisternaService.getCisternaByPlaca(placa);

        Response<cisterna> response = new Response<>("0000",cisterna,null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping(value = "/cisterna/{condicion}")
    public ResponseEntity<Response<List<cisterna>>> getCisternaByCondicion(@PathVariable("condicion") String condicion) {
        logger.info("Called resource: getCisternaByCondicion");

        List<cisterna> cisternaList = cisternaService.getCisternaByCondicion(condicion);
        Response<List<cisterna>> response = new Response<>("0000", cisternaList, null);

        logger.info("Called resource: getCisternaByCondicion");

        logger.info("Consulted: every cisterna condition on database");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "/cisterna")
    public ResponseEntity<Response<cisterna>> createCisterna(@Valid @RequestBody cisterna cisterna) {
        logger.info("Called resource: createCisterna");
        logger.info("{}", cisterna.getPlaca());

        cisterna oldCisterna = cisternaService.getCisternaByPlaca((String) cisterna.getPlaca());
        if (oldCisterna != null) {
            oldCisterna.setPlaca(cisterna.getPlaca());
            oldCisterna.setCondicion(cisterna.getCondicion());
            oldCisterna.setKm(cisterna.getKm());
            oldCisterna.setCapacidad(cisterna.getCapacidad());
            cisternaService.storeCisterna(oldCisterna);
        } else {
            cisternaService.storeCisterna(cisterna);
        }

        Response<cisterna> response = new Response<>("0000", cisterna, null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping(value = "/cisterna/{placa}")
    public void deleteCisterna(@PathVariable("placa") String placa) {
        logger.info("Called resource: deleteCisterna");
        cisternaService.deleteCisterna(placa);
        logger.info("succesfull");
    }

    public cisterna getCisternaDisponible(String condicion) {
        logger.info("Called resource: getCisternaDisponible");
        List<cisterna> cisternaList = cisternaService.getCisternaByCondicion(condicion);
        cisterna cisternaAux;
        for (int i = 0; i <= cisternaList.size() - 1; i++) {
            if(cisternaService.checkKm(cisternaList.get(i).getPlaca())){
                cisternaService.switcher("activo", cisternaList.get(i));
                cisternaAux = cisternaList.get(i);
                cisternaService.storeCisterna(cisternaList.get(i));
                logger.info("called resource: switcher and storeCisterna");
                return new cisterna(cisternaAux.getPlaca(), cisternaAux.getCondicion(),cisternaAux.getCapacidad(),cisternaAux.getKm());
                /*Response<cisterna> response = new Response<>("0000", cisternaList.get(i),null);
                return new ResponseEntity<>(response,HttpStatus.OK);*/
            }
            if(cisternaService.checkKm(cisternaList.get(i).getPlaca()) == false){
                cisternaService.switcher("activo", cisternaList.get(i));
                cisternaService.storeCisterna(cisternaList.get(i));
                logger.info("inactiva por cacuhos.");
            }
            if(cisternaList.isEmpty()) return null;
        }
        return null;
    }
}
