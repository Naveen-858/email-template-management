package com.company.emailtemplate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmailTemplateRequestDto {

	@NotBlank(message = "Template code is required")
	@Size(max = 100, message = "Template code cannot exceed 100 characters")
	private String templateCode;

	@NotBlank(message = "Template name is required")
	@Size(max = 150, message = "Template name cannot exceed 150 characters")
	private String templateName;

	@NotBlank(message = "Email subject is required")
	@Size(max = 255, message = "Email subject cannot exceed 255 characters")
	private String subject;

	@NotBlank(message = "Email body is required")
	private String body;

	private String description;

	// Getters and Setters

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}