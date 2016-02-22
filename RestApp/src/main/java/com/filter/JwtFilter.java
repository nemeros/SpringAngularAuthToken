package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.TokenService;

public class JwtFilter extends OncePerRequestFilter {
	
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	
	@Autowired
	TokenService tokenService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain fc)
			throws ServletException, IOException {
		
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		if(!"/auth".equals(httpRequest.getRequestURI())){
			//Extract X-AUTH-TOKEN
			String token = httpRequest.getHeader(AUTH_HEADER_NAME);
			
			if(token != null){
				Authentication authentication = tokenService.parseUserFromJwtToken(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}else{
				SecurityContextHolder.getContext().setAuthentication(null);		
			}		
		}
	}

}
