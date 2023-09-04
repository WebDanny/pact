package com.danny.propiedades.controller;


import com.danny.propiedades.model.Propiedades;
import com.danny.propiedades.repository.PropiedadsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class PropiedadesController {
    private final PropiedadsRepository propiedadesRepository;

    @Autowired
    public PropiedadesController(PropiedadsRepository ordersRepository) {
        this.propiedadesRepository = ordersRepository;
    }

    @GetMapping("/propiedades")
    List<Propiedades> getAllPropiedades() throws IOException {
        return propiedadesRepository.getPropiedades();
    }
}
