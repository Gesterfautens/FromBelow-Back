package com.frombelow.web.app.entity;

import javax.persistence.*;

@Entity
@Table(name="partidas")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int liga_id;
    private int result_player1;
    private int result_player2;
    private boolean jugada;

    @ManyToOne
    @JoinColumn(name="player1_id")
    private User player1;

    @ManyToOne
    @JoinColumn(name="player2_id")
    private User player2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLiga_id() {
        return liga_id;
    }

    public void setLiga_id(int liga_id) {
        this.liga_id = liga_id;
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

    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public boolean isJugada() {
        return jugada;
    }

    public void setJugada(boolean jugada) {
        this.jugada = jugada;
    }
}
