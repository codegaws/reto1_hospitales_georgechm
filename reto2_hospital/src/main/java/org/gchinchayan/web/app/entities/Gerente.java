package org.gchinchayan.web.app.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Gerente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGerente;

    private String descGerente;
    private Date fechaRegistro;
}
