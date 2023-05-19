package com.smedia.security;


import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.smedia.exception.sMediaException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	@Value("${app.jwt.secret}")
	private String jwtsecret;
	
	@Value("${app.jwt.expiration}")
	private long jwtExpirationDate;

	public String generateToken(Authentication auth) {
		
		String userName=auth.getName();
		Date currentDate=new Date();
		Date expirationDate=new Date(currentDate.getTime()+jwtExpirationDate);
		String token = Jwts.builder().setSubject(userName).setIssuedAt(new Date()).setExpiration(expirationDate).signWith(key()).compact();
		
		return token;
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtsecret));
	}
	
	public String getUserName(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
		String userName = claims.getSubject();
		return userName;
	}
	
	public boolean validateToke(String token) {
		
		try {
			Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
			return true;
			
		} catch (MalformedJwtException e) {
			throw new sMediaException(HttpStatus.BAD_REQUEST,"Invalid JWT token");
		}
		catch (ExpiredJwtException e) {
			throw new sMediaException(HttpStatus.BAD_REQUEST,"Expired JWT token");
		}
		catch (UnsupportedJwtException e) {
			throw new sMediaException(HttpStatus.BAD_REQUEST,"Unsupported JWT token");
		}
		catch (IllegalArgumentException e) {
			throw new sMediaException(HttpStatus.BAD_REQUEST,"JWT claims String is empty");
		}
		
	}
}
