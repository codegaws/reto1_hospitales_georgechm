package org.gchinchayan.web.app.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.gchinchayan.web.app.entities.Hospital;
import org.gchinchayan.web.app.repositories.HospitalRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.ParameterMode;

import java.util.*;

import java.util.*;

@Service
@Transactional
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

        @PersistenceContext
        private EntityManager entityManager;

    public List<Map<String, Object>> listarHospitales(String condicion) {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_HOSPITAL_LISTAR");

            // Registrar parámetros
            query.registerStoredProcedureParameter("p_condicion", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_result", Class.class, ParameterMode.REF_CURSOR); // 🔥 Corregido aquí

            // Asignar valor a p_condicion (permitir NULL si no se envía)
            if (condicion != null && !condicion.isEmpty()) {
                query.setParameter("p_condicion", condicion);
            } else {
                query.setParameter("p_condicion", null);
            }

            // Ejecutar procedimiento
            query.execute();

            // Obtener el cursor de salida
            List<Object[]> resultado = query.getResultList();

            // Convertir el resultado a una lista de mapas
            List<Map<String, Object>> hospitales = new ArrayList<>();
            for (Object[] row : resultado) {
                Map<String, Object> hospital = new HashMap<>();
                hospital.put("idHospital", row[0]);
                hospital.put("nombre", row[1]);
                hospital.put("antiguedad", row[2]);
                hospital.put("area", row[3]);
                hospital.put("descDistrito", row[4]);
                hospital.put("descGerente", row[5]);
                hospital.put("descSede", row[6]);
                hospital.put("descCondicion", row[7]);
                hospitales.add(hospital);
            }
            return hospitales;
        } catch (Exception e) {
            throw new RuntimeException("Error al listar hospitales: " + e.getMessage(), e);
        }
    }

    public Hospital buscarPorId(Long id) {
        return hospitalRepository.findById(id).orElse(null);
    }

    public void registrarHospital(Hospital hospital) {
        try {
            hospitalRepository.registrarHospital(
                    hospital.getIdDistrito(),  // Ahora se pasa el idDistrito correcto
                    hospital.getNombre(),
                    hospital.getAntiguedad(),
                    hospital.getArea(),
                    1,  // ID Sede de prueba (esto debería venir del objeto hospital si es posible)
                    1,  // ID Gerente de prueba
                    1   // ID Condición de prueba
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar el hospital: " + e.getMessage(), e);
        }
    }


    public void actualizarHospital(Hospital hospital) {
        System.out.println("Actualizando hospital con ID: " + hospital.getIdHospital());
        System.out.println("Actualizando hospital con ID: " + hospital.getIdHospital());
        System.out.println("Valores a actualizar:");
        System.out.println("idDistrito: " + hospital.getIdDistrito());
        System.out.println("idSede: " + hospital.getIdSede());
        System.out.println("idGerente: " + hospital.getIdGerente());
        System.out.println("idCondicion: " + hospital.getIdCondicion());

        hospitalRepository.actualizarHospital(
                hospital.getIdHospital().intValue(),
                hospital.getIdDistrito(),   // ✅ Se usa el valor correcto
                hospital.getIdSede(),       // ✅ Se usa el valor correcto
                hospital.getIdGerente(),    // ✅ Se usa el valor correcto
                hospital.getIdCondicion()   // ✅ Se usa el valor correcto
        );
    }

    public void eliminarHospital(Long id) {
        hospitalRepository.eliminarHospital(id.intValue());
    }
}

