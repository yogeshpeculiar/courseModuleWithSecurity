package com.revature.coursems.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class MyUserDetails implements UserDetails {
	
	
	private String username;
	private String password;
	private Boolean isactive;
	private List<GrantedAuthority> authorities;
	
	
	

	public MyUserDetails() {
		
	}

	public MyUserDetails(User user) {
	
		this.username = user.getUserName();
		this.password=user.getPassword();
		this.isactive=user.getIsActive();
        this.authorities=Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority ::new).collect(Collectors.toList());
        System.out.println(this.authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
			return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return isactive;
	}

	
}
