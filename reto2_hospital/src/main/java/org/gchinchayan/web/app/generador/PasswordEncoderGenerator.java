package org.gchinchayan.web.app.generador;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123"; // La contraseña en texto plano
        String encodedPasswordFromDB = "$2a$10$EuyZ5fkpflH994q55JFYnutGtmYY9clB5F63YSQB1BkcTUJiXwXli"; // Nuevo Hash de Oracle

        boolean matches = encoder.matches(rawPassword, encodedPasswordFromDB);
        System.out.println("¿Las contraseñas coinciden?: " + matches);

    }
}
