package com.company.emailtemplate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.emailtemplate.dto.EmployeeRequestDto;
import com.company.emailtemplate.dto.EmployeeResponseDto;
import com.company.emailtemplate.entity.Employee;
import com.company.emailtemplate.repo.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	// Create Employee
	public EmployeeResponseDto createEmployee(EmployeeRequestDto request) {

		if (employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
			throw new RuntimeException("Employee code already exists");
		}

		if (employeeRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		Employee employee = new Employee();

		employee.setEmployeeCode(request.getEmployeeCode());
		employee.setName(request.getName());
		employee.setEmail(request.getEmail());
		employee.setDesignation(request.getDesignation());
		employee.setDateOfJoining(request.getDateOfJoining());
		employee.setPreviousExperienceMonths(
				request.getPreviousExperienceMonths() == null ? 0 : request.getPreviousExperienceMonths());

		Employee savedEmployee = employeeRepository.save(employee);

		return convertToResponse(savedEmployee);
	}

	// Get All Employees
	public List<EmployeeResponseDto> getAllEmployees() {

		return employeeRepository.findAll().stream().map(this::convertToResponse).toList();
	}

	// Get Employee By ID
	public EmployeeResponseDto getEmployeeById(Long id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));

		return convertToResponse(employee);
	}

	// Update Employee
	public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto request) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));

		if (!employee.getEmployeeCode().equals(request.getEmployeeCode())
				&& employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
			throw new RuntimeException("Employee code already exists");
		}

		if (!employee.getEmail().equals(request.getEmail()) && employeeRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		employee.setEmployeeCode(request.getEmployeeCode());
		employee.setName(request.getName());
		employee.setEmail(request.getEmail());
		employee.setDesignation(request.getDesignation());
		employee.setDateOfJoining(request.getDateOfJoining());
		employee.setPreviousExperienceMonths(
				request.getPreviousExperienceMonths() == null ? 0 : request.getPreviousExperienceMonths());

		Employee updatedEmployee = employeeRepository.save(employee);

		return convertToResponse(updatedEmployee);
	}

	// Convert Entity to Response DTO
	private EmployeeResponseDto convertToResponse(Employee employee) {

		EmployeeResponseDto response = new EmployeeResponseDto();

		response.setId(employee.getId());
		response.setEmployeeCode(employee.getEmployeeCode());
		response.setName(employee.getName());
		response.setEmail(employee.getEmail());
		response.setDesignation(employee.getDesignation());
		response.setDateOfJoining(employee.getDateOfJoining());
		response.setPreviousExperienceMonths(employee.getPreviousExperienceMonths());
		response.setStatus(employee.getStatus());
		response.setCreatedAt(employee.getCreatedAt());

		return response;
	}
}