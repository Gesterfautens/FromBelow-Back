package com.frombelow.web.app.service;

import com.frombelow.web.app.entity.Liga;
import com.frombelow.web.app.payload.PartidaResponse;
import com.frombelow.web.app.repository.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigaService {

    @Autowired
    private LigaRepository ligaRepository;

    public List<Liga> getLigasActivas(){
        return ligaRepository.getAllActivas();
    }

    public Liga getLigaById(int id){
        return ligaRepository.findById(id).get();
    }
}
