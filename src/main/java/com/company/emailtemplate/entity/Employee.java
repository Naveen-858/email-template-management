package com.company.emailtemplate.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "employees", uniqueConstraints = {
		@UniqueConstraint(name = "uk_employee_code", columnNames = "employee_code"),
		@UniqueConstraint(name = "uk_employee_email", columnNames = "email") })
public class Employee {
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank
	    @Column(name = "employee_code", nullable = false, unique = true)
	    private String employeeCode;

	    @NotBlank
	    @Column(nullable = false)
	    private String name;

	    @Email
	    @NotBlank
	    @Column(nullable = false, unique = true)
	    private String email;

	    private String designation;

	    @NotNull
	    @Column(name = "date_of_joining", nullable = false)
	    private LocalDate dateOfJoining;

	    @Column(name = "previous_experience_months")
	    private Integer previousExperienceMonths = 0;

	    @Column(nullable = false)
	    private String status = "ACTIVE";

	    @Column(name = "created_at")
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;

	    @PrePersist
	    public void onCreate() {
	        createdAt = LocalDateTime.now();
	        updatedAt = LocalDateTime.now();
	    }

	    @PreUpdate
	    public void onUpdate() {
	        updatedAt = LocalDateTime.now();
	    }

	    public Employee() {
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

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

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
	    
	    

}
