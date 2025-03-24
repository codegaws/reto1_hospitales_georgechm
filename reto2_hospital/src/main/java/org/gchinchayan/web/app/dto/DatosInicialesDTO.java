package org.gchinchayan.web.app.dto;

import org.gchinchayan.web.app.entities.*;

import java.util.List;

public class DatosInicialesDTO {
    private List<Distrito> distritos;
    private List<Gerente> gerentes;
    private List<Sede> sedes;
    private List<Condicion> condiciones;
    private List<Provincia> provincias;

    //  Constructor vacío
    public DatosInicialesDTO() {}

    //  Constructor con todos los atributos
    public DatosInicialesDTO(List<Distrito> distritos, List<Gerente> gerentes, List<Sede> sedes,
                             List<Condicion> condiciones, List<Provincia> provincias) {
        this.distritos = distritos;
        this.gerentes = gerentes;
        this.sedes = sedes;
        this.condiciones = condiciones;
        this.provincias = provincias;
    }

    // Getters y Setters
    public List<Distrito> getDistritos() { return distritos; }
    public void setDistritos(List<Distrito> distritos) { this.distritos = distritos; }

    public List<Gerente> getGerentes() { return gerentes; }
    public void setGerentes(List<Gerente> gerentes) { this.gerentes = gerentes; }

    public List<Sede> getSedes() { return sedes; }
    public void setSedes(List<Sede> sedes) { this.sedes = sedes; }

    public List<Condicion> getCondiciones() { return condiciones; }
    public void setCondiciones(List<Condicion> condiciones) { this.condiciones = condiciones; }

    public List<Provincia> getProvincias() { return provincias; }
    public void setProvincias(List<Provincia> provincias) { this.provincias = provincias; }
}
