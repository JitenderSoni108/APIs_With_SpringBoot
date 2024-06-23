package com.myspring.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myspring.dto.EmployeeDto;
import com.myspring.exception.ResourceNotFoundException;
import com.myspring.model.Employee;
import com.myspring.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private  PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper; 

	@Override
	public EmployeeDto create(EmployeeDto employeeDto) {
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		String[] empid = UUID.randomUUID().toString().split("-");
		employee.setId(empid[0]);
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		Employee saveemp = repository.save(employee);
		EmployeeDto saveempDto = modelMapper.map(saveemp, EmployeeDto.class);
		return saveempDto;
	}

	 

	@Override
	public EmployeeDto getById(String id) {
		Employee employee = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Empoloyee with give id not found "));
    	return modelMapper.map(employee, EmployeeDto.class);
	}

	@Override
	public List<EmployeeDto> getAll() {
          List<Employee> allemployee = repository.findAll();
          List<EmployeeDto> allempoyeedto  =allemployee.stream().map(employee->modelMapper.map(employee, EmployeeDto.class)).toList();
     	return  allempoyeedto;
	}
	
	@Override
	public EmployeeDto update(EmployeeDto employeeDTO, String id) {
		Employee  employee = modelMapper.map(employeeDTO,Employee.class);
		Employee updateemployee = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee with Given id not found"));
		updateemployee.setName(employee.getName());
		updateemployee.setSalary(employee.getSalary());
		Employee updatedEmployee = repository.save(updateemployee);
		return modelMapper.map(updatedEmployee, EmployeeDto.class);
	}

	@Override
	public void delete(String id) { 
		 Employee employee =repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee with Given id not found"));
		  repository.delete(employee);
	}
	

}
