package com.frombelow.web.app.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserLigaId implements Serializable {
    @Column(name = "user_id")
    private int userId;
    @Column(name = "liga_id")
    private int ligaId;

    public UserLigaId(int userId, int ligaId) {
        this.userId = userId;
        this.ligaId = ligaId;
    }

    public UserLigaId() {
    }

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
