package com.threadsync.project.utils;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

@Component
public class JwtUtil {
    private static final String SECRET = "xzcsdgfgfhjsarewrefsdfsdffdsfdsfdsfsdfsdfsdgfdfgdfgdgfdfgdfgdfgdfgfertertydfgdsdgfewrywerytewrdgffdsgsertgwr4rtesdfdsfgsdhwetyweryewy54w6gfhdsfgtwe5tyrgdfhtrdjhdgdsrgdfyhssgerte5yrtgdfgdfghetrytetrg";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
        // return Jwts.builder()
        //     .setSubject(email)
        //     .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        //     .signWith(SignatureAlgorithm.HS512, SECRET)
        //     .compact();
    }
    // public static String extractemail(String token) {
    //     return Jwts.parser()
    //         .setSigningKey(SECRET)
    //         .parseClaimsJws(token)
    //         .getBody()
    //         .getSubject();
    // }

     public static Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public static boolean isTokenExpired(String token) {
        final Date expiration = extractClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public static boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
