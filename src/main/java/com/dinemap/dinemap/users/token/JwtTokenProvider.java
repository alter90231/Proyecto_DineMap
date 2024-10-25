package com.dinemap.dinemap.users.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private SecretKey key;

    public JwtTokenProvider(){
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
    private final long EXPIRATION_TIME = 86400000; //24 HORAS DE INICIO DE SESION
    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
