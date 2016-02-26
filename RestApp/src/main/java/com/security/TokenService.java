package com.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class TokenService {

	private final static String secret = "aRt8kL98Edf";
	private final static int EXPIRATION_TIME = 1000 * 60 * 60;
	
	/**
	 * Retreive the User detaisl from the Jwt Token
	 * @param request
	 * @return
	 */
	public Authentication parseUserFromJwtToken(String token){
		JwtUser jwtUser = null;
		
		try{
			Jws<Claims> jws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			
			
			List<String> lRole = (ArrayList<String>) jws.getBody().get("roles");
			
			List<GrantedAuthority> lg = new ArrayList<GrantedAuthority>();
			
			for(String role : lRole){
				lg.add(new SimpleGrantedAuthority(role));
			}
			
			jwtUser = new JwtUser(new User(jws.getBody().getSubject(), "", lg), token);
			jwtUser.setAuthenticated(true);
		}catch (SignatureException sie){
			throw new AccessDeniedException("Jwt Non valide", sie);
		}
		return jwtUser;
	}
	
	
	/**
	 * Create a Jwt Token for the User passed in parameter
	 * @param user
	 * @return
	 */
	public String createTokenForUser(User user){
		Collection<GrantedAuthority> lGranted = user.getAuthorities();
		List<String> lFormated = new ArrayList<String>();
		
		for(GrantedAuthority sga : lGranted){
			lFormated.add(sga.getAuthority());
		}
		
		return Jwts.builder().setSubject(user.getUsername())
			.claim("roles", lFormated.toArray())
			.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + TokenService.EXPIRATION_TIME))
			.signWith(SignatureAlgorithm.HS256, secret).compact();
	}
}
