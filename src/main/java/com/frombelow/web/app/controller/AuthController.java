package com.frombelow.web.app.controller;

import com.frombelow.web.app.jwt.JwtUtil;
import com.frombelow.web.app.payload.LoginRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());

            ResponseCookie cookie = jwtUtil.generateJwtCookie(userDetails);

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(userDetails.getAuthorities().toString());
        } catch (BadCredentialsException e) {
            System.out.println("malas credenciales");
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return ResponseEntity.status(400).body("Some error has occurred");

    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtil.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("You've been signed out!");

    }

    @PostMapping("/as")
    public String sas(@RequestBody LoginRequest loginRequest) {
        return "a";
    }

}
