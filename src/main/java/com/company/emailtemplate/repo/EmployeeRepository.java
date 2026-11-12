package com.company.emailtemplate.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.emailtemplate.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByEmployeeCode(String employeeCode);

	boolean existsByEmployeeCode(String employeeCode);

	boolean existsByEmail(String email);

	List<Employee> findByStatus(String status);

}
