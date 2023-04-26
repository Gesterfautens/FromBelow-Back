package com.frombelow.web.app.repository;

import com.frombelow.web.app.entity.Clasificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion,Integer> {

    public Clasificacion findClasificacionByJugadorIdAndLigaId(int jugadorId, int ligaId);

    public List<Clasificacion> findAllByLigaId(int ligaId);



}
