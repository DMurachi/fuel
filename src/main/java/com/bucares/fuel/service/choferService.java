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
}
