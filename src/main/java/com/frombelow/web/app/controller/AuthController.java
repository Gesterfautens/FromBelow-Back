package com.frombelow.web.app.controller;

import com.frombelow.web.app.entity.Liga;
import com.frombelow.web.app.entity.Partida;
import com.frombelow.web.app.jwt.JwtUtil;
import com.frombelow.web.app.payload.LoginRequest;
import com.frombelow.web.app.payload.LoginResponse;
import com.frombelow.web.app.payload.PartidaResponse;
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


    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());

            ResponseCookie cookie = jwtUtil.generateJwtCookie(userDetails);

            loginResponse.setStatus(true);
            loginResponse.setMessage(userDetails.getAuthorities().toString());

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(loginResponse);

        } catch (BadCredentialsException e) {
            loginResponse.setStatus(false);
            loginResponse.setMessage("Malas credenciales");
        } catch (Exception e) {
            loginResponse.setStatus(false);
            loginResponse.setMessage("error indefinido");
        }

        return ResponseEntity.status(400).body(loginResponse);

    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtil.getCleanJwtCookie();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setStatus(true);
        loginResponse.setMessage("deslogueado");
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(loginResponse);

    }

    @GetMapping("/getRole")
    public ResponseEntity<?> getRole(@CookieValue(value="jwtToken") String jwtToken) {
        String role = userService.getUserRole(jwtUtil.getUserNameFromToken(jwtToken));
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setStatus(true);
        loginResponse.setMessage(role);
        return ResponseEntity.ok().body(loginResponse);
    }

    @GetMapping("/getPartidas")
    public List<PartidaResponse> check(@CookieValue(value="jwtToken") String jwtToken, @RequestParam int liga){
        String username = jwtUtil.getUserNameFromToken(jwtToken);
        int id = userService.findByUsername(username).get().getId();
        return userService.getPartidasByPlayerAndLiga(id,liga);
    }

    @GetMapping("/getLigas")
    public List<Liga> getLigasActivas(){
        return userService.getLigasActivas();
    }

}
