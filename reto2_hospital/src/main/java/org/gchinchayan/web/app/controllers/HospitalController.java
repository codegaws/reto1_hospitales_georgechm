package org.gchinchayan.web.app.controllers;


import org.gchinchayan.web.app.entities.Hospital;
import org.gchinchayan.web.app.services.HospitalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/hospitales")
public class HospitalController {
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(@RequestParam(required = false) String condicion) {
        try {
            List<Map<String, Object>> hospitales = hospitalService.listarHospitales(condicion);
            if (hospitales.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(hospitales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al recuperar hospitales: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.buscarPorId(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crear(@RequestBody Hospital hospital) {
        hospitalService.registrarHospital(hospital);
        return ResponseEntity.ok("Hospital registrado correctamente");
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody Hospital hospital) {
        // 🔹 Asegurar que el ID de la URL se asigne al objeto recibido
        hospital.setIdHospital(id);

        // 🔹 Imprimir los valores recibidos para verificar
        System.out.println("Solicitud recibida para actualizar hospital con ID: " + id);
        System.out.println("Datos del hospital: " + hospital);

        hospitalService.actualizarHospital(hospital);
        return ResponseEntity.ok("Hospital actualizado correctamente");
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        hospitalService.eliminarHospital(id);
        return ResponseEntity.ok("Hospital eliminado correctamente");
    }
}

