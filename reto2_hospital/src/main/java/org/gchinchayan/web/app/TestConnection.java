package org.gchinchayan.web.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class TestConnection implements CommandLineRunner {

    private final DataSource dataSource;

    public TestConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("Conexión exitosa a Oracle!");
        } catch (Exception e) {
            System.err.println("Error conectando a Oracle: " + e.getMessage());
        }
    }
}

