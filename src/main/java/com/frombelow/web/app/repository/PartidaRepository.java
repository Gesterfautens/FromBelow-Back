package com.frombelow.web.app.repository;

import com.frombelow.web.app.entity.Partida;
import com.frombelow.web.app.payload.PartidaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida,Integer> {

    @Query(value = "SELECT new com.frombelow.web.app.payload.PartidaResponse(" +
            "p.id,u.id,ur.id,u.nombre,ur.nombre,p.result_player1,p.result_player2,p.jugada) from Partida p " +
            "INNER JOIN p.player1 u " +
            "INNER JOIN p.player2 ur " +
            "INNER JOIN p.liga l " +
            "WHERE p.player1.id=:player_id AND p.liga.id=:liga_id")
    public List<PartidaResponse> getPartidasByPlayerIdaAndLigaId(int player_id, int liga_id);

}
