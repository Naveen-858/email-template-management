package com.company.emailtemplate.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.company.emailtemplate.dto.EmailPreviewResponseDto;
import com.company.emailtemplate.entity.EmailTemplate;
import com.company.emailtemplate.entity.Employee;
import com.company.emailtemplate.repo.EmailTemplateRepository;
import com.company.emailtemplate.repo.EmployeeRepository;

@Service
public class EmailPreviewService {

	private final EmployeeRepository employeeRepository;
	private final EmailTemplateRepository emailTemplateRepository;
	private final EmailTemplateProcessor emailTemplateProcessor;

	public EmailPreviewService(EmployeeRepository employeeRepository, EmailTemplateRepository emailTemplateRepository,
			EmailTemplateProcessor emailTemplateProcessor) {

		this.employeeRepository = employeeRepository;
		this.emailTemplateRepository = emailTemplateRepository;
		this.emailTemplateProcessor = emailTemplateProcessor;
	}

	// Generate Employee Email Preview
	public EmailPreviewResponseDto generatePreview(Long employeeId, Long templateId) {

		// Fetch Employee
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));

		// Fetch Email Template
		EmailTemplate template = emailTemplateRepository.findById(templateId)
				.orElseThrow(() -> new RuntimeException("Email template not found with ID: " + templateId));

		// Check whether template is active
		if (!Boolean.TRUE.equals(template.getActive())) {
			throw new RuntimeException("This email template is inactive");
		}

		// Build Employee Placeholders
		Map<String, String> placeholders = emailTemplateProcessor.buildPlaceholders(employee);

		// Replace Subject Placeholders
		String processedSubject = emailTemplateProcessor.processSubject(template.getSubject(), placeholders);

		// Replace Body Placeholders
		String processedBody = emailTemplateProcessor.processBody(template.getBody(), placeholders);

		// Prepare Preview Response
		EmailPreviewResponseDto response = new EmailPreviewResponseDto();

		response.setEmployeeName(employee.getName());
		response.setRecipientEmail(employee.getEmail());
		response.setTemplateCode(template.getTemplateCode());
		response.setSubject(processedSubject);
		response.setBody(processedBody);
		response.setGeneratedAt(LocalDateTime.now());

		return response;
	}
}