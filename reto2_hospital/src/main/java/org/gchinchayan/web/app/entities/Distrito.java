package org.gchinchayan.web.app.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Distrito") // Nombre exacto de la tabla en la BD
public class Distrito {

    @Id
    @Column(name = "IDDISTRITO") // Debe coincidir con la BD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDistrito;

    @Column(name = "DESCDISTRITO") // Debe coincidir con la BD
    private String descDistrito;

    @Column(name = "FECHAREGISTRO") // Debe coincidir con la BD
    private Date fechaRegistro;

    // 🔹 Relación con Provincia
    @ManyToOne
    @JoinColumn(name = "IDPROVINCIA", nullable = true)  // IMPORTANTE: Debe coincidir con la BD
    private Provincia provincia;

    // 🔹 Constructor vacío
    public Distrito() {}

    // 🔹 Getters y Setters
    public Integer getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(Integer idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getDescDistrito() {
        return descDistrito;
    }

    public void setDescDistrito(String descDistrito) {
        this.descDistrito = descDistrito;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Distrito{" +
                "idDistrito=" + idDistrito +
                ", descDistrito='" + descDistrito + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", provincia=" + (provincia != null ? provincia.getIdProvincia() : "null") +
                '}';
    }
}
