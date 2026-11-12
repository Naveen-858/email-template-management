package com.company.emailtemplate.dto;

import jakarta.validation.constraints.NotNull;

public class EmailSendRequestDto {

	@NotNull(message = "Employee ID is required")
	private Long employeeId;

	@NotNull(message = "Template ID is required")
	private Long templateId;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
}