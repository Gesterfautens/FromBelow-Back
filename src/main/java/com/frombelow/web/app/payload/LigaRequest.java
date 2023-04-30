package com.frombelow.web.app.payload;

public class LigaRequest {
    private int LigaId;
    private boolean activa;

    public int getLigaId() {
        return LigaId;
    }

    public void setLigaId(int ligaId) {
        LigaId = ligaId;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
