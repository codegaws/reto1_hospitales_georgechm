package org.gchinchayan.web.app.services;

import org.gchinchayan.web.app.dto.DatosInicialesDTO;
import org.gchinchayan.web.app.entities.*;
import org.gchinchayan.web.app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatosInicialesService {
    @Autowired
    private final DistritoRepository distritoRepository;
    @Autowired
    private final GerenteRepository gerenteRepository;
    @Autowired
    private final SedeRepository sedeRepository;
    @Autowired
    private final CondicionRepository condicionRepository;
    @Autowired
    private final ProvinciaRepository provinciaRepository;

    public DatosInicialesService(DistritoRepository distritoRepository,
                                 GerenteRepository gerenteRepository,
                                 SedeRepository sedeRepository,
                                 CondicionRepository condicionRepository,
                                 ProvinciaRepository provinciaRepository) {
        this.distritoRepository = distritoRepository;
        this.gerenteRepository = gerenteRepository;
        this.sedeRepository = sedeRepository;
        this.condicionRepository = condicionRepository;
        this.provinciaRepository = provinciaRepository;
    }

    public DatosInicialesDTO obtenerDatosIniciales() {
        List<Distrito> distritos = distritoRepository.findAll();
        List<Gerente> gerentes = gerenteRepository.findAll();
        List<Sede> sedes = sedeRepository.findAll();
        List<Condicion> condiciones = condicionRepository.findAll();
        List<Provincia> provincias = provinciaRepository.findAll();

        return new DatosInicialesDTO(distritos, gerentes, sedes, condiciones, provincias);
    }
}
