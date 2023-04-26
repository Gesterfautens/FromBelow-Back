package com.frombelow.web.app.payload;

public class ClasificacionResponse {

    private String nombre;
    private int rondas_ganadas;
    private int rondas_perdidas;
    private int partidas_ganadas;
    private int partidas_empatadas;
    private int puntos;
    private int partidas_jugadas;
    private double winrate;

    public ClasificacionResponse(String nombre, int rondas_ganadas, int rondas_perdidas, int partidas_ganadas, int partidas_empatadas, int puntos, int partidas_jugadas, double winrate) {
        this.nombre = nombre;
        this.rondas_ganadas = rondas_ganadas;
        this.rondas_perdidas = rondas_perdidas;
        this.partidas_ganadas = partidas_ganadas;
        this.partidas_empatadas = partidas_empatadas;
        this.puntos = puntos;
        this.partidas_jugadas = partidas_jugadas;
        this.winrate = winrate;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getPartidas_ganadas() {
        return partidas_ganadas;
    }

    public void setPartidas_ganadas(int partidas_ganadas) {
        this.partidas_ganadas = partidas_ganadas;
    }

    public int getPartidas_empatadas() {
        return partidas_empatadas;
    }

    public void setPartidas_empatadas(int partidas_empatadas) {
        this.partidas_empatadas = partidas_empatadas;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPartidas_jugadas() {
        return partidas_jugadas;
    }

    public void setPartidas_jugadas(int partidas_jugadas) {
        this.partidas_jugadas = partidas_jugadas;
    }

    public double getWinrate() {
        return winrate;
    }

    public void setWinrate(double winrate) {
        this.winrate = winrate;
    }
}
