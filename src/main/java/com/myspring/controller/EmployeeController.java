package com.myspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.dto.ApiResponseMessage;
import com.myspring.dto.EmployeeDto;
import com.myspring.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Controller
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping
	public ResponseEntity<EmployeeDto>  create(@Valid @RequestBody EmployeeDto employeeDto){
		EmployeeDto empdto = service.create(employeeDto);
		return new ResponseEntity<EmployeeDto>(empdto,HttpStatus.OK);
	}
   
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto>  getByid(@PathVariable String id){
		 EmployeeDto byId = service.getById(id);
		return new ResponseEntity<EmployeeDto>(byId,HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public  ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto employeeDTO ,@PathVariable String id){
		EmployeeDto empDto = service.update(employeeDTO,id);
		return new  ResponseEntity<EmployeeDto>(empDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<ApiResponseMessage> delete(@PathVariable String id){
		  service.delete(id);
		  ApiResponseMessage employeedelete = ApiResponseMessage.builder()
				  .message("Employee Deleted")
				  .status(HttpStatus.OK)
				  .success(true)
				  .build();
		return new  ResponseEntity<ApiResponseMessage>(employeedelete,HttpStatus.OK);
	}
	
 
	
	@GetMapping
	public  ResponseEntity<List<EmployeeDto>> findAll(){
		 List<EmployeeDto> allemployee = service.getAll();
		return new  ResponseEntity<List<EmployeeDto>>(allemployee,HttpStatus.OK);
	}
}
