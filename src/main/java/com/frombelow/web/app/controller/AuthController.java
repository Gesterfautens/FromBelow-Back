package com.frombelow.web.app.controller;

import com.frombelow.web.app.entity.Clasificacion;
import com.frombelow.web.app.entity.Liga;
import com.frombelow.web.app.entity.Role;
import com.frombelow.web.app.entity.User;
import com.frombelow.web.app.jwt.JwtUtil;
import com.frombelow.web.app.payload.*;
import com.frombelow.web.app.service.PartidaService;
import com.frombelow.web.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PartidaService partidaService;



    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());

            ResponseCookie cookie = jwtUtil.generateJwtCookie(userDetails);

            loginResponse.setSuccess(true);
            loginResponse.setMessage(userDetails.getAuthorities().toString());

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(loginResponse);

        } catch (BadCredentialsException e) {
            loginResponse.setSuccess(false);
            loginResponse.setMessage("Malas credenciales");
        } catch (Exception e) {
            loginResponse.setSuccess(false);
            loginResponse.setMessage("error indefinido");
        }

        return ResponseEntity.ok().body(loginResponse);

    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtil.getCleanJwtCookie();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(true);
        loginResponse.setMessage("deslogueado");
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(loginResponse);

    }

    @GetMapping("/getRole")
    public ResponseEntity<?> getRole(@CookieValue(value="jwtToken") String jwtToken) {
        String role = userService.getUserRole(jwtUtil.getUserNameFromToken(jwtToken));
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(true);
        loginResponse.setMessage(role);
        return ResponseEntity.ok().body(loginResponse);
    }


}
