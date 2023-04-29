package com.frombelow.web.app.controller;

import com.frombelow.web.app.entity.User;
import com.frombelow.web.app.payload.LigaRequest;
import com.frombelow.web.app.payload.LoginRequest;
import com.frombelow.web.app.payload.LoginResponse;
import com.frombelow.web.app.payload.UserLigaRequest;
import com.frombelow.web.app.service.PartidaService;
import com.frombelow.web.app.service.SignUpService;
import com.frombelow.web.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PartidaService partidaService;

    @Autowired
    private SignUpService signUpService;


    // Ejecutar solo una vez cuando esten todos los usuarios apuntados a la liga
    @PostMapping("/crearClasificacion")
    public LoginResponse crear(@RequestBody LigaRequest ligaRequest){
       return partidaService.crearClasificaciones(ligaRequest.getLigaId());
    }

    // Ejecutar solo una vez cuando esten todos los usuarios apuntados a la liga
    @PostMapping("/crearPartidas")
    public LoginResponse crearPartida(@RequestBody LigaRequest ligaRequest){
        return partidaService.crearPartidas(ligaRequest.getLigaId());
    }

    @PostMapping("/getUsers")
    public List<User> getUsers(@CookieValue(value="jwtToken") String jwtToken){
        return userService.getAllUsers();
    }

    @PostMapping("/cambiarPass")
    public LoginResponse cambiarPass(@RequestBody LoginRequest loginRequest){
        return signUpService.cambiarPass(loginRequest);
    }

    @PostMapping("/userToLiga")
    public LoginResponse addUserToLiga(@CookieValue(value="jwtToken") String jwtToken, @RequestBody UserLigaRequest userLigaRequest){

       if(userService.userToLiga(userLigaRequest.getUserId(),userLigaRequest.getLigaId())){
           return new LoginResponse(true,"Añadido");
       }else{
           return new LoginResponse(false,"error");
       }

    }


}
