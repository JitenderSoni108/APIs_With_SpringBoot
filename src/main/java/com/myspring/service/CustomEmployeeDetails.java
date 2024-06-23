package com.myspring.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.myspring.model.Employee;

public class CustomEmployeeDetails implements UserDetails {

	private String username; 
	private String password; 
	public CustomEmployeeDetails(Employee employee) {
		this.username = employee.getUsername();
		this.password = employee.getPassword();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
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
			return true;
	}

}
