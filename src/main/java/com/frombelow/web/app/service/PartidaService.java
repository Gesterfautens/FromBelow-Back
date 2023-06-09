package com.frombelow.web.app.service;

import com.frombelow.web.app.entity.Clasificacion;
import com.frombelow.web.app.entity.Liga;
import com.frombelow.web.app.entity.Partida;
import com.frombelow.web.app.entity.User;
import com.frombelow.web.app.payload.ClasificacionResponse;
import com.frombelow.web.app.payload.LoginResponse;
import com.frombelow.web.app.payload.PartidaRequest;
import com.frombelow.web.app.payload.PartidaResponse;
import com.frombelow.web.app.repository.ClasificacionRepository;
import com.frombelow.web.app.repository.PartidaRepository;
import com.frombelow.web.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private ClasificacionRepository clasificacionRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public LoginResponse actualizaPartida(PartidaRequest partida) {
        int partidaId = partida.getPartida_id();
        int player1Id = partida.getPlayer1_id();
        int player2Id = partida.getPlayer2_id();
        int ligaId = partida.getLiga_id();
        int resultPayer1 = partida.getResult_player1();
        int resultPayer2 = partida.getResult_player2();

        Partida partidaNormal = partidaRepository.findById(partidaId).get();
        partidaNormal.setResult_player1(resultPayer1);
        partidaNormal.setResult_player2(resultPayer2);
        partidaNormal.setJugada(true);


        Partida partidaContraria = partidaRepository.getPartidaContraria(ligaId, player1Id, player2Id);

        //ponemos el resultado al reves por que es la misma partida desde el lado del rival
        partidaContraria.setResult_player1(resultPayer2);
        partidaContraria.setResult_player2(resultPayer1);
        partidaContraria.setJugada(true);

        try{
            //guardamos el resultado de las partidas
            partidaRepository.save(partidaNormal);
            partidaRepository.save(partidaContraria);
        }catch(Exception e){
            return new LoginResponse(false,"error guardando las partidas");
        }

        //actualizamos la informacion de las clasificaciones de los participantes de la partida
        Clasificacion clasiPl1 = clasificacionRepository.findClasificacionByJugadorIdAndLigaId(player1Id, ligaId);

        clasiPl1.setPartidas_jugadas(clasiPl1.getPartidas_jugadas() + 1);
        if (resultPayer1 > resultPayer2) {
            clasiPl1.setPartigas_ganadas(clasiPl1.getPartigas_ganadas() + 1);
            clasiPl1.setPuntos(clasiPl1.getPuntos() + 3);
        } else if (resultPayer1 == resultPayer2) {
            clasiPl1.setPartidas_empatadas(clasiPl1.getPartidas_empatadas() + 1);
            clasiPl1.setPuntos(clasiPl1.getPuntos() + 1);
        }

        clasiPl1.setRondas_ganadas(clasiPl1.getRondas_ganadas() + resultPayer1);
        clasiPl1.setRondas_perdidas(clasiPl1.getRondas_perdidas() + resultPayer2);
        double winrate;
        if (clasiPl1.getPartidas_empatadas() > 0) {
            winrate = (2 * (double) clasiPl1.getPartigas_ganadas() + (double) clasiPl1.getPartidas_empatadas())
                    / (2 * (double) clasiPl1.getPartidas_jugadas()) * 100;
        } else {
            winrate = ((double) clasiPl1.getPartigas_ganadas() / (double) clasiPl1.getPartidas_jugadas()) * 100;
        }

        clasiPl1.setWinrate(winrate);


        //Aqui actualizamos la informacion al reves porque es el contrincante
        Clasificacion clasiPl2 = clasificacionRepository.findClasificacionByJugadorIdAndLigaId(player2Id, ligaId);

        clasiPl2.setPartidas_jugadas(clasiPl2.getPartidas_jugadas() + 1);
        if (resultPayer2 > resultPayer1) {
            clasiPl2.setPartigas_ganadas(clasiPl2.getPartigas_ganadas() + 1);
            clasiPl2.setPuntos(clasiPl2.getPuntos() + 3);
        } else if (resultPayer2 == resultPayer1) {
            clasiPl2.setPartidas_empatadas(clasiPl2.getPartidas_empatadas() + 1);
            clasiPl2.setPuntos(clasiPl2.getPuntos() + 1);
        }
        clasiPl2.setRondas_ganadas(clasiPl2.getRondas_ganadas() + resultPayer2);
        clasiPl2.setRondas_perdidas(clasiPl2.getRondas_perdidas() + resultPayer1);

        double winrateDos;
        if (clasiPl2.getPartidas_empatadas() > 0) {
            winrateDos = (2 * (double) clasiPl2.getPartigas_ganadas() + (double) clasiPl2.getPartidas_empatadas())
                    / (2 * (double) clasiPl2.getPartidas_jugadas()) * 100;
        } else {
            winrateDos = ((double) clasiPl2.getPartigas_ganadas() / (double) clasiPl2.getPartidas_jugadas()) * 100;
        }

        clasiPl2.setWinrate(winrateDos);

        try{
            clasificacionRepository.save(clasiPl1);
            clasificacionRepository.save(clasiPl2);
        }catch(Exception e){
            return new LoginResponse(false,"error guardadno la clasificacion");
        }

        return new LoginResponse(true, "bieng");

    }

    public List<ClasificacionResponse> getClasificacionesLiga(int ligaId) {
        return clasificacionRepository.findAllByLigaId(ligaId);
    }

    public List<PartidaResponse> getPartidasByPlayerAndLiga(int player_id, int liga_id){
        return partidaRepository.getPartidasByPlayerIdaAndLigaId(player_id,liga_id);
    }

    public LoginResponse crearClasificaciones(int ligaId) {

        List<User> users = userRepository.findUsersByLigaId(ligaId);
        Liga liga = new Liga();
        liga.setId(ligaId);

        List<Clasificacion> clasificaciones = new ArrayList<>();
        for (User user : users) {

            if(user.getRole().getTipo().equals("admin"))
                continue;

            Clasificacion clasificacion = new Clasificacion(
                    1,
                    liga,
                    user,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0.0
            );
            clasificaciones.add(clasificacion);
        }
        try {
            clasificacionRepository.saveAll(clasificaciones);
            return new LoginResponse(true, " clasificacion creada con exito");
        } catch (Exception e) {
            return new LoginResponse();
        }

    }

    public LoginResponse crearPartidas(int ligaId) {

        List<User> users = userRepository.findUsersByLigaId(ligaId);
        Liga liga = new Liga();
        liga.setId(ligaId);

        for (User jugador : users) {
            List<Partida> partidas = new ArrayList<>();

            for (User rival : users) {
                if (jugador.getRole().getTipo().equals( "admin") || jugador.getId() == rival.getId() || rival.getRole().getTipo() == "admin") {
                    continue;
                }

                Partida partida = new Partida();
                partida.setId(1);
                partida.setResult_player1(0);
                partida.setResult_player2(0);
                partida.setJugada(false);
                partida.setLiga(liga);
                partida.setPlayer1(jugador);
                partida.setPlayer2(rival);

                partidas.add(partida);
            }
            try {
                partidaRepository.saveAll(partidas);
            } catch (Exception e) {
                return new LoginResponse(false,"");
            }
        }
        return new LoginResponse(true, "partidas creadas con exito  ");
    }


}
