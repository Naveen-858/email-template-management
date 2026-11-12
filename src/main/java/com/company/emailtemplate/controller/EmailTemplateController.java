package com.company.emailtemplate.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.emailtemplate.dto.EmailTemplateRequestDto;
import com.company.emailtemplate.dto.EmailTemplateResponseDto;
import com.company.emailtemplate.service.EmailTemplateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/email-templates")
public class EmailTemplateController {
	private final EmailTemplateService emailTemplateService;

	public EmailTemplateController(EmailTemplateService emailTemplateService) {
		this.emailTemplateService = emailTemplateService;
	}

	// Create Email Template
	@PostMapping
	public ResponseEntity<EmailTemplateResponseDto> createTemplate(
			@Valid @RequestBody EmailTemplateRequestDto request) {

		EmailTemplateResponseDto response = emailTemplateService.createTemplate(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// Get All Templates
	@GetMapping
	public ResponseEntity<List<EmailTemplateResponseDto>> getAllTemplates() {

		return ResponseEntity.ok(emailTemplateService.getAllTemplates());
	}

	// Get Template By ID
	@GetMapping("/{id}")
	public ResponseEntity<EmailTemplateResponseDto> getTemplateById(@PathVariable Long id) {

		return ResponseEntity.ok(emailTemplateService.getTemplateById(id));
	}

	// Update Template
	@PutMapping("/{id}")
	public ResponseEntity<EmailTemplateResponseDto> updateTemplate(@PathVariable Long id,
			@Valid @RequestBody EmailTemplateRequestDto request) {

		return ResponseEntity.ok(emailTemplateService.updateTemplate(id, request));
	}

	// Activate / Deactivate Template
	@PatchMapping("/{id}/status")
	public ResponseEntity<EmailTemplateResponseDto> updateTemplateStatus(@PathVariable Long id,
			@RequestParam Boolean active) {

		return ResponseEntity.ok(emailTemplateService.updateTemplateStatus(id, active));
	}

	// Get Active Templates
	@GetMapping("/active")
	public ResponseEntity<List<EmailTemplateResponseDto>> getActiveTemplates() {

		return ResponseEntity.ok(emailTemplateService.getActiveTemplates());
	}
}
