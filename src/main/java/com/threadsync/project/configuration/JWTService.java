package com.threadsync.project.configuration;

import java.security.Key;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;
import java.util.*;

@Service
public class JWTService {
    
	@Value("${jwt.sign.key}")
    private String signingKey;
	
    public String extractUserEmail(String token){
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
		Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	public String generateToken(UserDetails userDetails){
		//Setting token expiration into 30 mins
		Date expiration = new Date(System.currentTimeMillis() + (1000 * 60 * 30));
		return generateToken(new HashMap<>(), userDetails, expiration);
	}

	public String generateRefreshToken(UserDetails userDetails){
		//Setting refresh token expiration into 7 days
		Integer sevenDaysMilliseconds = 7 * 24 * 60 * 60 * 1000;
		Date expiration = new Date(System.currentTimeMillis() + sevenDaysMilliseconds);
		return generateToken(new HashMap<>(), userDetails, expiration);
	}

	public String generateToken(
		Map<String, Object> extraClaims,
		UserDetails userDetails,
		Date expiration
	){
		return Jwts.builder()
					.setClaims(extraClaims)
					.setSubject(userDetails.getUsername())
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(expiration)
					.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	public boolean isTokenValid(String token, UserDetails userDetails){
		String username = extractUserEmail(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	public Claims extractAllClaims(String token){
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token).getBody();
	}

	public boolean isTokenExpired(String token){
		return extractTokenExpiration(token).before(new Date());
	}

	public Date extractTokenExpiration(String token){
		return extractClaim(token, Claims::getExpiration);
	}

	public Key getSigningKey(){
		byte[] keyByte = Base64.getDecoder().decode(signingKey);
		return Keys.hmacShaKeyFor(keyByte);
	}
}
