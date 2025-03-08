package com.prime.InventroryMgtSystem.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
public class JwtUtils {
    private static final long EXPIRATION_TIME_MILLISEC=1000l*60L*60L*24L*6L;// expires in 6 months in milli sec
    private SecretKey key;

    //private String secretJwtString = "Abdulrahman123456789Abdulrahman123456789"; or

    @Value("${secretJwtString}")
    private String secretJwtString;

    private void init(){
        byte[] keybite = secretJwtString.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keybite,"HmacSHA256");
    }

    public String generatetoken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()))
                .signWith(key)
                .compact();
    }
    public String getUsernamefromToken(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsTFunction){
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=getUsernamefromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractClaims(token,Claims::getExpiration).before(new Date());
    }

}
