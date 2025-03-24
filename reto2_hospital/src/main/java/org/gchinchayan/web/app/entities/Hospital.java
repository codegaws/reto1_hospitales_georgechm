package org.gchinchayan.web.app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "HOSPITAL")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDHOSPITAL")
    private Long idHospital;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "ANTIGUEDAD", nullable = false)
    private int antiguedad;

    @Column(name = "AREA", nullable = false)
    private double area;

    @Column(name = "IDDISTRITO", nullable = false)
    private int idDistrito;

    @Column(name = "IDSEDE", nullable = false)  // ✅ Agregado
    private int idSede;

    @Column(name = "IDGERENTE", nullable = false)  // ✅ Agregado
    private int idGerente;

    @Column(name = "IDCONDICION", nullable = false)  // ✅ Agregado
    private int idCondicion;

    // 🔹 Constructor vacío (requerido por JPA)
    public Hospital() {
    }

    // 🔹 Constructor con parámetros
    public Hospital(Long idHospital, String nombre, int antiguedad, double area,
                    int idDistrito, int idSede, int idGerente, int idCondicion) {
        this.idHospital = idHospital;
        this.nombre = nombre;
        this.antiguedad = antiguedad;
        this.area = area;
        this.idDistrito = idDistrito;
        this.idSede = idSede;
        this.idGerente = idGerente;
        this.idCondicion = idCondicion;
    }

    // 🔹 Getters y Setters
    public Long getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Long idHospital) {
        this.idHospital = idHospital;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public int getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(int idGerente) {
        this.idGerente = idGerente;
    }

    public int getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(int idCondicion) {
        this.idCondicion = idCondicion;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "idHospital=" + idHospital +
                ", nombre='" + nombre + '\'' +
                ", antiguedad=" + antiguedad +
                ", area=" + area +
                ", idDistrito=" + idDistrito +
                ", idSede=" + idSede +
                ", idGerente=" + idGerente +
                ", idCondicion=" + idCondicion +
                '}';
    }
}
