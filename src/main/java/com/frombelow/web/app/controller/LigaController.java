package com.frombelow.web.app.controller;

import com.frombelow.web.app.entity.Liga;
import com.frombelow.web.app.jwt.JwtUtil;
import com.frombelow.web.app.payload.*;
import com.frombelow.web.app.service.LigaService;
import com.frombelow.web.app.service.PartidaService;
import com.frombelow.web.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
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
    public List<PartidaResponse> getPartidas(@CookieValue(value = "jwtToken") String jwtToken, @RequestParam int liga) {
        String username = jwtUtil.getUserNameFromToken(jwtToken);
        int id = userService.findByUsername(username).get().getId();
        return partidaService.getPartidasByPlayerAndLiga(id, liga);
    }

    @GetMapping("/getLigas")
    public List<Liga> getLigasActivas() {
        return ligaService.getLigasActivas();
    }


    @GetMapping("/getAllLigas")
    public List<Liga> getAllLigas() {
        return ligaService.getAllLigas();
    }

    @PostMapping("/actuLiga")
    public LoginResponse actuLiga(@RequestBody LigaRequest ligaRequest) {

        return ligaService.actuLiga(ligaRequest);
    }

    @PostMapping("/actualizaPartida")
    public LoginResponse actualizaPartida(@RequestBody PartidaRequest partidaRequest) {
        partidaService.actualizaPartida(partidaRequest);
        return new LoginResponse();
    }

    @GetMapping("/getClasificacion")
    public List<ClasificacionResponse> getClasificacion(@RequestParam int liga) {
        return partidaService.getClasificacionesLiga(liga);
    }

    @GetMapping("/getLiga")
    public Liga getLigasById(@RequestParam int id) {
        return ligaService.getLigaById(id);
    }

    @GetMapping("/getLigasUser")
    public List<Liga> getLigasUser(@CookieValue(value = "jwtToken") String jwtToken) {
        String username = jwtUtil.getUserNameFromToken(jwtToken);
        return userService.getLigasofUser(username);
    }

    @PostMapping("/crearLiga")
    public LoginResponse creaLiga(@RequestBody Liga liga) {
        return ligaService.crearLiga(liga);
    }


}
