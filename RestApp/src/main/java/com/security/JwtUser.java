package com.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class JwtUser implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3298888496204932897L;
	
	private String jwt;	
	private boolean isAuth;
	private final User user;
	
	public JwtUser(User user, String jwt){
		this.user = user;
		this.jwt = jwt;
	}
	
	public String getJwt(){
		return jwt;
	}

	@Override
	public String getName() {
		return user.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return user;
	}

	@Override
	public Object getPrincipal() {
		return user.getUsername();
	}

	@Override
	public boolean isAuthenticated() {
		return isAuth;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuth = isAuthenticated;		
	}
}
