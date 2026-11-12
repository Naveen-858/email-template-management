package com.company.emailtemplate.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class EmployeeRequestDto {

	@NotBlank(message = "Employee code is required")
	private String employeeCode;

	@NotBlank(message = "Employee name is required")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Please provide a valid email address")
	private String email;

	private String designation;

	@NotNull(message = "Date of joining is required")
	@PastOrPresent(message = "Date of joining cannot be in the future")
	private LocalDate dateOfJoining;

	@Min(value = 0, message = "Previous experience cannot be negative")
	private Integer previousExperienceMonths = 0;

	// Getters and Setters

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Integer getPreviousExperienceMonths() {
		return previousExperienceMonths;
	}

	public void setPreviousExperienceMonths(Integer previousExperienceMonths) {
		this.previousExperienceMonths = previousExperienceMonths;
	}
}