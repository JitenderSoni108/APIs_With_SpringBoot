package com.myspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
 
	private String id; 
	@Size(min = 3, max=35, message = "Invalid Name!!")
	private String name;
	@NotBlank(message = "username is required and should be unique!!")
	private String username;
	
	@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Invalid User Email!!")
	@NotBlank(message = "Email is required!!")
	private String email;
	
	@NotBlank
	private String password;
	
	private double salary;

}
