package com.myspring.controller;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.dto.EmployeeDto;
import com.myspring.dto.JwtRequest;
import com.myspring.dto.JwtResponse;
import com.myspring.exception.BadApiRequestException;
import com.myspring.model.Employee;
import com.myspring.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private ModelMapper  modelMapper;
	
  @PostMapping("/login")	
  public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
    
	  
	  UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
	  try {
		  Authentication authenticate = authenticationManager.authenticate(authentication);
		  
		
	} catch (BadCredentialsException  e) {
		throw new BadApiRequestException("Invalid username & password Error ");
	}
	  Employee userdetails = (Employee)userDetailsService.loadUserByUsername(request.getUsername()); 
	  String generatedtoken = this.jwtUtil.generateToken(userdetails);
	  JwtResponse response = JwtResponse.builder()
			  .jwtToken(generatedtoken)
			  .employee(modelMapper.map(userdetails, EmployeeDto.class))
			  .build();
	  return new ResponseEntity<JwtResponse>(response,HttpStatus.OK);
  }
  
  @GetMapping("/current")
  public ResponseEntity<EmployeeDto> getCurrentUser(Principal principal){
	  String name = principal.getName();
	  Employee userDetails  =(Employee) userDetailsService.loadUserByUsername(name);
	  EmployeeDto userDto = modelMapper.map(userDetails, EmployeeDto.class);
	  return new ResponseEntity<EmployeeDto>(userDto,HttpStatus.OK);
  }
  
	

	
}
