package com.frombelow.web.app.repository;

import com.frombelow.web.app.entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida,Integer> {
    @Query(value = "SELECT u.username,ur.username from Partida p " +
            "INNER JOIN p.player1 u " +
            "INNER JOIN p.player2 ur " +
            "INNER JOIN p.liga l " +
            "WHERE p.player1=:player_id AND p.liga=:liga_id")
    public List<String> getPartidasByPlayerIdaAndLigaId(int player_id,int liga_id);

}
