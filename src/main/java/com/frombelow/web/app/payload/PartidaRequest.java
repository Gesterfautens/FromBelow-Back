package com.frombelow.web.app.payload;

public class PartidaRequest {
    private int partida_id;
    private int liga_id;
    private int player1_id;
    private int player2_id;
    private int result_player1;
    private int result_player2;

    public int getPartida_id() {
        return partida_id;
    }

    public void setPartida_id(int partida_id) {
        this.partida_id = partida_id;
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

    public int getLiga_id() {
        return liga_id;
    }

    public void setLiga_id(int liga_id) {
        this.liga_id = liga_id;
    }
}
