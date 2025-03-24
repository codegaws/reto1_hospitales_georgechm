package org.gchinchayan.web.app.repositories;

import org.gchinchayan.web.app.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    // Registrar hospital
    @Procedure(value = "SP_HOSPITAL_REGISTRAR")
    void registrarHospital(
            @Param("p_idDistrito") int idDistrito,
            @Param("p_Nombre") String nombre,
            @Param("p_Antiguedad") int antiguedad,
            @Param("p_Area") double area,
            @Param("p_idSede") int idSede,
            @Param("p_idGerente") int idGerente,
            @Param("p_idCondicion") int idCondicion
    );

    // Actualizar hospital
    @Procedure(value = "SP_HOSPITAL_ACTUALIZAR")
    void actualizarHospital(
            @Param("p_idHospital") int idHospital,
            @Param("p_idDistrito") int idDistrito,
            @Param("p_idSede") int idSede,
            @Param("p_idGerente") int idGerente,
            @Param("p_idCondicion") int idCondicion
    );

    // Eliminar hospital
    @Procedure(value = "SP_HOSPITAL_ELIMINAR")
    void eliminarHospital(@Param("p_idHospital") int idHospital);





}
