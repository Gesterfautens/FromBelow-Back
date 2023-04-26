package com.frombelow.web.app.payload;

public class UserLigaRequest {
    private int userId;
    private int ligaId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLigaId() {
        return ligaId;
    }

    public void setLigaId(int ligaId) {
        this.ligaId = ligaId;
    }
}
