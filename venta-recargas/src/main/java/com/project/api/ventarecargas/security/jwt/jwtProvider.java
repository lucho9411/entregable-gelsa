package com.project.api.ventarecargas.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.project.api.ventarecargas.security.entities.Usuarios;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class jwtProvider {
	
	private final static Logger logger = LoggerFactory.getLogger(jwtProvider.class);
	
	@Value("${jwt.secret}")
	private String secret;
	@Value("${token.signing.key}")
    private String jwtSigningKey;
	@Value("${jwt.expiration}")
	private int expiration;
	
	public String generateToken(Usuarios usuario) {
		
		 return Jwts.builder().setSubject(usuario.getNombre())
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
	                .signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		}
		catch(MalformedJwtException mfe) {
			logger.error("Token malformado");
			return false;
		}
		catch(UnsupportedJwtException use) {
			logger.error("Token no soportado");
			return false;
		}
		catch(ExpiredJwtException exe) {
			logger.error("Token expirado");
			return false;
		}
		catch(IllegalArgumentException iae) {
			logger.error("Token vacío");
			return false;
		}
		catch(SignatureException sge) {
			logger.error("Fallo con la firma del token");
			return false;
		}
	}
	
	public String validateTokenMessage(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return "Ok";
		}
		catch(MalformedJwtException mfe) {
			logger.error("Token malformado");
			return "Token malformado";
		}
		catch(UnsupportedJwtException use) {
			logger.error("Token no soportado");
			return "Token no soportado";
		}
		catch(ExpiredJwtException exe) {
			logger.error("Token expirado");
			return "Token expirado";
		}
		catch(IllegalArgumentException iae) {
			logger.error("Token vacío");
			return "Token vacío";
		}
		catch(SignatureException sge) {
			logger.error("Fallo con la firma del token");
			return "Fallo con la firma del token";
		}
	}

}
