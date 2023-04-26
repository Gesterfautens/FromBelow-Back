package com.frombelow.web.app.entity;


import javax.persistence.*;

@Entity
public class Clasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="liga_id")
    private Liga liga;

    @ManyToOne
    @JoinColumn(name="jugador_id")
    private User jugador;

    public Clasificacion() {
    }

    public Clasificacion(Integer id, Liga liga, User jugador, int puntos, int rondas_ganadas, int rondas_perdidas, int partigas_ganadas, int partidas_empatadas, int partidas_jugadas, Double winrate) {

        this.liga = liga;
        this.jugador = jugador;
        this.puntos = puntos;
        this.rondas_ganadas = rondas_ganadas;
        this.rondas_perdidas = rondas_perdidas;
        this.partigas_ganadas = partigas_ganadas;
        this.partidas_empatadas = partidas_empatadas;
        this.partidas_jugadas = partidas_jugadas;
        this.winrate = winrate;
    }

    private int puntos;
    private int rondas_ganadas;
    private int rondas_perdidas;
    private int partigas_ganadas;
    private int partidas_empatadas;
    private int partidas_jugadas;
    private Double winrate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public User getJugador() {
        return jugador;
    }

    public void setJugador(User jugador) {
        this.jugador = jugador;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getRondas_ganadas() {
        return rondas_ganadas;
    }

    public void setRondas_ganadas(int rondas_ganadas) {
        this.rondas_ganadas = rondas_ganadas;
    }

    public int getRondas_perdidas() {
        return rondas_perdidas;
    }

    public void setRondas_perdidas(int rondas_perdidas) {
        this.rondas_perdidas = rondas_perdidas;
    }

    public int getPartigas_ganadas() {
        return partigas_ganadas;
    }

    public void setPartigas_ganadas(int partigas_ganadas) {
        this.partigas_ganadas = partigas_ganadas;
    }

    public int getPartidas_jugadas() {
        return partidas_jugadas;
    }

    public void setPartidas_jugadas(int partidas_jugadas) {
        this.partidas_jugadas = partidas_jugadas;
    }

    public Double getWinrate() {
        return winrate;
    }

    public void setWinrate(Double winrate) {
        this.winrate = winrate;
    }

    public int getPartidas_empatadas() {
        return partidas_empatadas;
    }

    public void setPartidas_empatadas(int partidas_empatadas) {
        this.partidas_empatadas = partidas_empatadas;
    }
}
