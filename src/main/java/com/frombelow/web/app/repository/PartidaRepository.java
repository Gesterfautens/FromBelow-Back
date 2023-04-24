package com.frombelow.web.app.repository;

import com.frombelow.web.app.entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida,Integer> {
    @Query(value = "SELECT u.username, ur.username from Partida p" +
            " inner join p.player1 u " +
            "inner join p.player2 ur " +
            "where p.player1.id=:id")
    public List<String> getPartidasByPlayerId(int id);
}
