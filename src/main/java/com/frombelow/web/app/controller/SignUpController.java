package com.frombelow.web.app.controller;

import com.frombelow.web.app.entity.User;
import com.frombelow.web.app.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signUp")
    public User signUp(@RequestBody User user){
        return signUpService.singUp(user);
    }
}
