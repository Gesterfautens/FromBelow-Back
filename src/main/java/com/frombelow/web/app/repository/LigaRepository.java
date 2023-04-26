package com.frombelow.web.app.repository;

import com.frombelow.web.app.entity.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigaRepository  extends JpaRepository<Liga,Integer> {

    @Query("select l from Liga l where l.activa = true")
    public List<Liga> getAllActivas();


}
