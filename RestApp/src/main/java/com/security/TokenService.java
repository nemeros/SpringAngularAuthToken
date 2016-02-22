package com.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	private final static String secret = "aRt8kL98Edf";
	
	/**
	 * Retreive the User detaisl from the Jwt Token
	 * @param request
	 * @return
	 */
	public Authentication parseUserFromJwtToken(String token){
		Jws<Claims> jws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		
		
		List<String> lRole = (ArrayList<String>) jws.getBody().get("roles");
		
		List<GrantedAuthority> lg = new ArrayList<GrantedAuthority>();
		
		for(String role : lRole){
			lg.add(new SimpleGrantedAuthority(role));
		}
		
		JwtUser jwtUser = new JwtUser(new User(jws.getBody().getSubject(), "", lg), token);
		
		return jwtUser;
	}
	
	
	/**
	 * Create a Jwt Token for the User passed in parameter
	 * @param user
	 * @return
	 */
	public String createTokenForUser(User user){
		return Jwts.builder().setSubject(user.getUsername())
			.claim("roles", user.getAuthorities().toArray())
			.setIssuedAt(new Date())
			.signWith(SignatureAlgorithm.HS256, secret).compact();
	}
}