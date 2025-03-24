package org.gchinchayan.web.app.repositories;

import org.gchinchayan.web.app.entities.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
}
