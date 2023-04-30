package com.frombelow.web.app.service;

import com.frombelow.web.app.entity.Liga;
import com.frombelow.web.app.payload.LigaRequest;
import com.frombelow.web.app.payload.LoginResponse;
import com.frombelow.web.app.payload.PartidaResponse;
import com.frombelow.web.app.repository.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigaService {

    @Autowired
    private LigaRepository ligaRepository;

    public List<Liga> getLigasActivas() {
        return ligaRepository.getAllActivas();
    }

    public List<Liga> getAllLigas() {
        return ligaRepository.findAll();
    }

    public Liga getLigaById(int id) {
        return ligaRepository.findById(id).get();
    }

    public LoginResponse actuLiga(LigaRequest ligaRequest) {
        Liga liga = ligaRepository.findById(ligaRequest.getLigaId()).get();
        liga.setActiva(ligaRequest.isActiva());
        try{
         ligaRepository.save(liga);
        }catch(Exception e){
            return new LoginResponse();
        }
        return new LoginResponse(true," liga modificada con exito");
    }


    public LoginResponse crearLiga(Liga liga) {
        try {
            ligaRepository.save(liga);
            return new LoginResponse(true, "liga creada con exito");
        } catch (Exception e) {
            return new LoginResponse(false, "error al crear liga");
        }
    }
}
