package com.estockmarketapp.apigateway.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.estockmarketapp.apigateway.exception.JwtTokenMalformedException;
import com.estockmarketapp.apigateway.exception.JwtTokenMissingException;

@Service
public class JwtUtil {

	private String secret = "apisecretcode";

	private static final Logger LOGGER=LoggerFactory.getLogger(JwtUtil.class);
	
	public void validateToken(String token) throws JwtTokenMalformedException, JwtTokenMissingException {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT Signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT Token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT Token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT Token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT Claims String is empty");
		}
	}

	public Claims getClaims(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			return body;
		} catch (Exception e) {
			LOGGER.error("Exception in getClaims(): "+e);
		}
		return null;
	}
}
