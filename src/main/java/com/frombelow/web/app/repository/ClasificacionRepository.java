package com.frombelow.web.app.repository;

import com.frombelow.web.app.entity.Clasificacion;
import com.frombelow.web.app.payload.ClasificacionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion,Integer> {


    public Clasificacion findClasificacionByJugadorIdAndLigaId(int jugadorId, int ligaId);

    @Query(value = "SELECT new com.frombelow.web.app.payload.ClasificacionResponse( " +
            "j.nombre,c.rondas_ganadas,c.rondas_perdidas,c.partigas_ganadas," +
            "c.partidas_empatadas,c.puntos,c.partidas_jugadas,c.winrate)" +
            "FROM Clasificacion c " +
            "INNER JOIN c.jugador j " +
            "INNER JOIN c.liga l " +
            "WHERE l.id=:ligaId ORDER BY c.puntos DESC ,c.partidas_jugadas DESC ,c.rondas_ganadas DESC ")
    public List<ClasificacionResponse> findAllByLigaId(int ligaId);



}
