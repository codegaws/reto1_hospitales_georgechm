package org.gchinchayan.web.app.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Provincia") // Nombre exacto de la tabla en la BD
public class Provincia {

    @Id
    @Column(name = "IDPROVINCIA") // Debe coincidir con la BD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProvincia;

    @Column(name = "DESCPROVINCIA") // Debe coincidir con la BD
    private String descProvincia;

    @Column(name = "FECHAREGISTRO") // Debe coincidir con la BD
    private Date fechaRegistro;

    // 🔹 Relación con Distrito
    @OneToMany(mappedBy = "provincia")
    private List<Distrito> distritos;

    // 🔹 Constructor vacío
    public Provincia() {}

    // 🔹 Getters y Setters
    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getDescProvincia() {
        return descProvincia;
    }

    public void setDescProvincia(String descProvincia) {
        this.descProvincia = descProvincia;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Distrito> getDistritos() {
        return distritos;
    }

    public void setDistritos(List<Distrito> distritos) {
        this.distritos = distritos;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "idProvincia=" + idProvincia +
                ", descProvincia='" + descProvincia + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
