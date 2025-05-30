package org.gchinchayan.web.app.repositories;


import org.gchinchayan.web.app.entities.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Integer> {
}
