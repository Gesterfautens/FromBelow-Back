package com.frombelow.web.app.jwt;

import com.frombelow.web.app.service.UserService;
import io.jsonwebtoken.*;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "frombelowtemple";
    private static final String COOKIE_NAME = "jwtToken";

    public ResponseCookie getCleanJwtCookie() {
       ResponseCookie cookie = ResponseCookie.from(COOKIE_NAME,null).build();
       return cookie;
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, COOKIE_NAME);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public ResponseCookie generateJwtCookie(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String jwt = createToken(claims, userDetails);
        ResponseCookie cookie = ResponseCookie.from(COOKIE_NAME, jwt).maxAge(24 * 60 * 60).build();
        return cookie;
    }

    public String createToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validatoken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid signature");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid jwt token");
        } catch (ExpiredJwtException e) {
            System.out.println("jwt esta caducado");
        } catch (UnsupportedJwtException e) {
            System.out.println("jwt no esta soportado");
        } catch (IllegalArgumentException e) {
            System.out.println("jwt claims sta vacio");
        }
        return false;
    }
}
