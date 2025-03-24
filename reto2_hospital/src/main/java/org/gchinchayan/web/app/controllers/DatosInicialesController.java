package org.gchinchayan.web.app.controllers;

import org.gchinchayan.web.app.dto.DatosInicialesDTO;
import org.gchinchayan.web.app.services.DatosInicialesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/datos-iniciales")
@CrossOrigin(origins = "http://localhost:4200")
public class DatosInicialesController {
    private final DatosInicialesService datosInicialesService;

    public DatosInicialesController(DatosInicialesService datosInicialesService) {
        this.datosInicialesService = datosInicialesService;
    }

    @GetMapping
    public ResponseEntity<DatosInicialesDTO> obtenerDatosIniciales() {
        DatosInicialesDTO datos = datosInicialesService.obtenerDatosIniciales();
        return ResponseEntity.ok(datos);
    }
}
