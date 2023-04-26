package com.frombelow.web.app.repository;

import com.frombelow.web.app.entity.UserLiga;
import com.frombelow.web.app.entity.UserLigaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLigaRepository extends JpaRepository<UserLiga, UserLigaId> {
}
