package com.myspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myspring.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	Optional<Employee> findByUsername(String username);

}
