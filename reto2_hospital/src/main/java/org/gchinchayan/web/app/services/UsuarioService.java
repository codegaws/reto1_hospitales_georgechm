package org.gchinchayan.web.app.services;

import jakarta.persistence.PersistenceContext;
import org.gchinchayan.web.app.entities.Usuario;
import org.gchinchayan.web.app.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;  // 🔹 Importa DataSource
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;  // 🔹 Inyecta DataSource

    // ✅ Método para verificar la conexión a la base de datos
    public void verificarConexion() {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("✅ Conectado a la base de datos: " + connection.getMetaData().getURL());
            System.out.println("🔹 Usuario conectado: " + connection.getMetaData().getUserName());
            System.out.println("🔹 Base de datos: " + connection.getCatalog());
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public boolean autenticarUsuario(String username, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // 🔹 Imprimir datos del usuario encontrado
            System.out.println("✅ Usuario encontrado: " + usuario.getUsername());
            System.out.println("🔹 Contraseña en BD: " + usuario.getPassword());

            return password.equals(usuario.getPassword()); // Comparación sin encriptar
        } else {
            System.out.println("❌ Usuario no encontrado en la base de datos");
        }
        return false;
    }

}
