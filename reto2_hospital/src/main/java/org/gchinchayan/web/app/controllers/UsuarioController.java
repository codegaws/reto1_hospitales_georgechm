package org.gchinchayan.web.app.controllers;


import org.gchinchayan.web.app.dto.LoginRequest;
import org.gchinchayan.web.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean autenticado = usuarioService.autenticarUsuario(loginRequest.getUsername(), loginRequest.getPassword());

        if (autenticado) {
            return ResponseEntity.ok("{\"message\": \"Inicio de sesión exitoso\"}");
        }
        return ResponseEntity.status(401).body("{\"error\": \"Credenciales incorrectas\"}");
    }
}
