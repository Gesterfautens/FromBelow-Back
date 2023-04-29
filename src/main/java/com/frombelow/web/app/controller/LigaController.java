package com.frombelow.web.app.controller;

import com.frombelow.web.app.entity.Liga;
import com.frombelow.web.app.jwt.JwtUtil;
import com.frombelow.web.app.payload.ClasificacionResponse;
import com.frombelow.web.app.payload.LoginResponse;
import com.frombelow.web.app.payload.PartidaRequest;
import com.frombelow.web.app.payload.PartidaResponse;
import com.frombelow.web.app.service.LigaService;
import com.frombelow.web.app.service.PartidaService;
import com.frombelow.web.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true")
public class LigaController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private LigaService ligaService;

    @Autowired
    private PartidaService partidaService;

    @GetMapping("/getPartidas")
    public List<PartidaResponse> getPartidas(@CookieValue(value="jwtToken") String jwtToken, @RequestParam int liga){
        String username = jwtUtil.getUserNameFromToken(jwtToken);
        int id = userService.findByUsername(username).get().getId();
        return partidaService.getPartidasByPlayerAndLiga(id,liga);
    }

    @GetMapping("/getLigas")
    public List<Liga> getLigasActivas(){
        return ligaService.getLigasActivas();
    }


    @PostMapping("/actualizaPartida")
    public LoginResponse actualizaPartida(@RequestBody PartidaRequest partidaRequest){
        partidaService.actualizaPartida(partidaRequest);
        return new LoginResponse();
    }

    @GetMapping("/getClasificacion")
    public List<ClasificacionResponse> getClasificacion(@RequestParam int liga){
        return partidaService.getClasificacionesLiga(liga);
    }

    @GetMapping("/getLiga")
    public Liga getLigasById(@RequestParam int id){
        return ligaService.getLigaById(id);
    }

    @GetMapping("/getLigasUser")
    public List<Liga> getLigasUser(@CookieValue(value="jwtToken") String jwtToken){
        String username = jwtUtil.getUserNameFromToken(jwtToken);
        return userService.getLigasofUser(username);
    }


}
