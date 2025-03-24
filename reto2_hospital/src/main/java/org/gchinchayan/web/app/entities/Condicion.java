package org.gchinchayan.web.app.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Condicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCondicion;

    private String descCondicion;
    private Date fechaRegistro;
}
