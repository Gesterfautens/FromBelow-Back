package com.frombelow.web.app.entity;

import javax.persistence.*;

@Entity
@Table(name="USER_LIGA")
public class UserLiga {

    @EmbeddedId
    private UserLigaId id;

    public UserLigaId getId() {
        return id;
    }

    public void setId(UserLigaId id) {
        this.id = id;
    }
}
