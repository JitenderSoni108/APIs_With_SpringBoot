package com.myspring.service;

import java.util.List;

import com.myspring.dto.EmployeeDto;
import com.myspring.model.Employee;


public interface EmployeeService {
	EmployeeDto create(EmployeeDto st);
	EmployeeDto update(EmployeeDto st,String id);
	void delete(String id);
	EmployeeDto getById(String id);
	List<EmployeeDto> getAll();
	

}
