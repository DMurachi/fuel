package com.bucares.fuel.controller;

import com.bucares.fuel.models.chofer;
import com.bucares.fuel.service.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
@Controller
public class choferController {
    @Autowired
    private com.bucares.fuel.service.choferService choferService;

    private static final Logger logger = LoggerFactory.getLogger(choferController.class);

    @GetMapping(value = "/chofer")
    public ResponseEntity<Response<List<chofer>>> getAllchofer() {
        logger.info("Called resource: getAllchofer");

        List<chofer> choferList = choferService.getAllChofer();
        Response<List<chofer>> response = new Response<>("0000", choferList, null);

        logger.info("Called resource: getAllProducts");

        logger.info("Consulted: every product in database");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/chofer/{cedula}")
    public ResponseEntity<Response<chofer>> getChofer(@PathVariable("cedula") String cedula) {
        logger.info("Called resource: getChofer");

        chofer chofer = choferService.getChoferByCedula(cedula);

        Response<chofer> response = new Response<>("0000",chofer,null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/chofer")
    public ResponseEntity<Response<chofer>> createChofer(@Valid @RequestBody chofer chofer) {
        logger.info("Called resource: createChofer");
      //  logger.info("{}", chofer.getNombre());

        chofer oldChofer = choferService.getChoferByCedula((String) chofer.getCedula());
        if (oldChofer != null) {
            oldChofer.setCedula(chofer.getCedula());
            oldChofer.setNombre(chofer.getNombre());
            oldChofer.setCondicion(chofer.getCondicion());
            oldChofer.setPassword(chofer.getPassword());
            choferService.storeChofer(oldChofer);
        } else {
            choferService.storeChofer(chofer);
        }

        Response<chofer> response = new Response<>("0000", chofer, null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
