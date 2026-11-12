package com.company.emailtemplate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.emailtemplate.dto.EmailTemplateRequestDto;
import com.company.emailtemplate.dto.EmailTemplateResponseDto;
import com.company.emailtemplate.entity.EmailTemplate;
import com.company.emailtemplate.repo.EmailTemplateRepository;

@Service
public class EmailTemplateService {

	private final EmailTemplateRepository emailTemplateRepository;

	public EmailTemplateService(EmailTemplateRepository emailTemplateRepository) {
		this.emailTemplateRepository = emailTemplateRepository;
	}

	// Create Email Template
	public EmailTemplateResponseDto createTemplate(EmailTemplateRequestDto request) {

		if (emailTemplateRepository.existsByTemplateCode(request.getTemplateCode())) {
			throw new RuntimeException("Template code already exists");
		}

		EmailTemplate template = new EmailTemplate();

		template.setTemplateCode(request.getTemplateCode());
		template.setTemplateName(request.getTemplateName());
		template.setSubject(request.getSubject());
		template.setBody(request.getBody());
		template.setDescription(request.getDescription());
		template.setActive(true);

		EmailTemplate savedTemplate = emailTemplateRepository.save(template);

		return convertToResponse(savedTemplate);
	}

	// Get All Templates
	public List<EmailTemplateResponseDto> getAllTemplates() {

		return emailTemplateRepository.findAll().stream().map(this::convertToResponse).toList();
	}

	// Get Template By ID
	public EmailTemplateResponseDto getTemplateById(Long id) {

		EmailTemplate template = emailTemplateRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Email template not found with ID: " + id));

		return convertToResponse(template);
	}

	// Update Template
	public EmailTemplateResponseDto updateTemplate(Long id, EmailTemplateRequestDto request) {

		EmailTemplate template = emailTemplateRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Email template not found with ID: " + id));

		if (!template.getTemplateCode().equals(request.getTemplateCode())
				&& emailTemplateRepository.existsByTemplateCode(request.getTemplateCode())) {
			throw new RuntimeException("Template code already exists");
		}

		template.setTemplateCode(request.getTemplateCode());
		template.setTemplateName(request.getTemplateName());
		template.setSubject(request.getSubject());
		template.setBody(request.getBody());
		template.setDescription(request.getDescription());

		EmailTemplate updatedTemplate = emailTemplateRepository.save(template);

		return convertToResponse(updatedTemplate);
	}

	// Activate / Deactivate Template
	public EmailTemplateResponseDto updateTemplateStatus(Long id, Boolean active) {

		EmailTemplate template = emailTemplateRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Email template not found with ID: " + id));

		template.setActive(active);

		EmailTemplate updatedTemplate = emailTemplateRepository.save(template);

		return convertToResponse(updatedTemplate);
	}

	// Get Active Templates
	public List<EmailTemplateResponseDto> getActiveTemplates() {

		return emailTemplateRepository.findByActive(true).stream().map(this::convertToResponse).toList();
	}

	// Convert Entity to Response DTO
	private EmailTemplateResponseDto convertToResponse(EmailTemplate template) {

		EmailTemplateResponseDto response = new EmailTemplateResponseDto();

		response.setId(template.getId());
		response.setTemplateCode(template.getTemplateCode());
		response.setTemplateName(template.getTemplateName());
		response.setSubject(template.getSubject());
		response.setBody(template.getBody());
		response.setDescription(template.getDescription());
		response.setActive(template.getActive());
		response.setCreatedAt(template.getCreatedAt());
		response.setUpdatedAt(template.getUpdatedAt());

		return response;
	}
}