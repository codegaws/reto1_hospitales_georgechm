package org.gchinchayan.web.app.repositories;



import org.gchinchayan.web.app.entities.Condicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondicionRepository extends JpaRepository<Condicion, Integer> {
}
