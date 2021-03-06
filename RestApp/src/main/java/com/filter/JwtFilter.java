package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.TokenService;

@Component
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
			
			if(StringUtils.hasText(token)){
				try{
					Authentication authentication = tokenService.parseUserFromJwtToken(token);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}catch(AccessDeniedException ade){
					AccessDeniedHandler acc = new AccessDeniedHandlerImpl();
					acc.handle(request, response, ade);
					return;
				}				
			}else{
				SecurityContextHolder.getContext().setAuthentication(null);		
			}		
		}
		
		fc.doFilter(request, response);
	}

}
