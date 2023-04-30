package com.frombelow.web.app.controller;

import com.frombelow.web.app.entity.User;
import com.frombelow.web.app.jwt.JwtUtil;
import com.frombelow.web.app.payload.LoginRequest;
import com.frombelow.web.app.payload.LoginResponse;
import com.frombelow.web.app.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signUp")
    public LoginResponse signUp(@RequestBody User user){
        return signUpService.singUp(user);
    }

    @PostMapping("/cambiarPassUser")
    public LoginResponse cambiarPassUser(@CookieValue(value="jwtToken") String jwtToken,@RequestBody LoginRequest loginRequest){
        String username = jwtUtil.getUserNameFromToken(jwtToken);
        return signUpService.cambiarPassUser(username,loginRequest.getPassword());
    }

}
