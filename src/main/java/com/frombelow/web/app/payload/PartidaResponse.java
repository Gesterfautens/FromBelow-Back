package com.frombelow.web.app.payload;

public class PartidaResponse {

    private int partida_id;
    private int player1_id;
    private int player2_id;
    private String player1_nombre;
    private String player2_nombre;
    private int result_player1;
    private int result_player2;
    private boolean jugada;

    public PartidaResponse(int partida_id, int player1_id, int player2_id, String player1_nombre, String player2_nombre, int result_player1, int result_player2, boolean jugada) {
        this.partida_id = partida_id;
        this.player1_id = player1_id;
        this.player2_id = player2_id;
        this.player1_nombre = player1_nombre;
        this.player2_nombre = player2_nombre;
        this.result_player1 = result_player1;
        this.result_player2 = result_player2;
        this.jugada = jugada;
    }

    public int getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(int player1_id) {
        this.player1_id = player1_id;
    }

    public int getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(int player2_id) {
        this.player2_id = player2_id;
    }

    public String getPlayer1_nombre() {
        return player1_nombre;
    }

    public void setPlayer1_nombre(String player1_nombre) {
        this.player1_nombre = player1_nombre;
    }

    public String getPlayer2_nombre() {
        return player2_nombre;
    }

    public void setPlayer2_nombre(String player2_nombre) {
        this.player2_nombre = player2_nombre;
    }

    public int getResult_player1() {
        return result_player1;
    }

    public void setResult_player1(int result_player1) {
        this.result_player1 = result_player1;
    }

    public int getResult_player2() {
        return result_player2;
    }

    public void setResult_player2(int result_player2) {
        this.result_player2 = result_player2;
    }

    public boolean isJugada() {
        return jugada;
    }

    public void setJugada(boolean jugada) {
        this.jugada = jugada;
    }

    public int getPartida_id() {
        return partida_id;
    }

    public void setPartida_id(int partida_id) {
        this.partida_id = partida_id;
    }
}
