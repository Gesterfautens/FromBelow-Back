package com.frombelow.web.app.controller;

import com.frombelow.web.app.entity.User;
import com.frombelow.web.app.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signUp")
    public User signUp(@RequestBody User user){
        return signUpService.singUp(user);
    }

}
