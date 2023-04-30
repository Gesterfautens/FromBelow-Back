package com.frombelow.web.app.service;

import com.frombelow.web.app.entity.User;
import com.frombelow.web.app.payload.LoginRequest;
import com.frombelow.web.app.payload.LoginResponse;
import com.frombelow.web.app.repository.UserRepository;
import org.aspectj.weaver.patterns.ExactTypePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    public LoginResponse singUp(User user) {
        if (existsByUsername(user.getUsername())) {
            return new LoginResponse(false,"El usuario ya existe");
        }

        user.setPass(passwordEncoder.encode(user.getPass()));
        try {
            userRepository.save(user);
        }catch (Exception e){
            return new LoginResponse(false,"error creando el usuario");
        }
        return new LoginResponse(true,"creado con exito");
    }

    public LoginResponse cambiarPass(LoginRequest loginRequest){
        try{
            User user = userRepository.findByUsername(loginRequest.getUsername()).get();
            user.setPass(passwordEncoder.encode(loginRequest.getPassword()));
            userRepository.save(user);
            return new LoginResponse(true,"Contraseña cambiada");
        }catch (Exception e){
            return new LoginResponse(false,"error");
        }
    }

    public User loadByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public LoginResponse cambiarPassUser(String username,String pass){
        try{
            User user = userRepository.findByUsername(username).get();
            user.setPass(passwordEncoder.encode(pass));
            userRepository.save(user);
            return new LoginResponse(true,"Contraseña cambiada");
        }catch (Exception e){
            return new LoginResponse(false,"error");
        }
    }
}
