package com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.TokenService;

import io.jsonwebtoken.SignatureException;

@RestController
@CrossOrigin
public class AuthService {
	
	@Autowired
	TokenService tokenService;

	@RequestMapping(value="/auth", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<AuthResponse> login(@RequestBody(required=true) AuthRequest req, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String token = null;
		User usr = null;
		List<GrantedAuthority> lg = new ArrayList<GrantedAuthority>();
		
		if(req.nom == null){
			new AccessDeniedHandlerImpl().handle(request, response, new AccessDeniedException("Bad credential"));	
		}else{
			if(req.nom.equals("admin")){
				lg.add(new SimpleGrantedAuthority("USER"));
				lg.add(new SimpleGrantedAuthority("ADMIN"));
				usr = new User(req.nom, "", lg);
			}else if(req.nom.equals("user")){
				lg.add(new SimpleGrantedAuthority("USER"));
				usr = new User(req.nom, "", lg);
			}else if(req.nom.equals("guest")){
				lg.add(new SimpleGrantedAuthority("GUEST"));
				usr = new User(req.nom, "", lg);
			}
			
			
			try{
				
				token = tokenService.createTokenForUser(usr);
				response.addHeader("X-AUTH-TOKEN", token);
			}catch(SignatureException e){
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<AuthResponse>(new AuthResponse(token), HttpStatus.OK);
	}
	
	@SuppressWarnings("unused")
	private static class AuthRequest{
		public String nom;
		public String password;
	}
	
	@SuppressWarnings("unused")
	private static class AuthResponse{
		public String token;
		
		public AuthResponse(String token){
			this.token = token;
		}
	}
}
