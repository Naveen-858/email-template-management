package com.company.emailtemplate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.emailtemplate.dto.EmailPreviewResponseDto;
import com.company.emailtemplate.service.EmailPreviewService;

@RestController
@RequestMapping("/api/email-preview")
public class EmailPreviewController {

	private final EmailPreviewService emailPreviewService;

	public EmailPreviewController(EmailPreviewService emailPreviewService) {
		this.emailPreviewService = emailPreviewService;
	}

	// Generate Personalized Email Preview
	@GetMapping
	public ResponseEntity<EmailPreviewResponseDto> generatePreview(@RequestParam Long employeeId,
			@RequestParam Long templateId) {

		EmailPreviewResponseDto response = emailPreviewService.generatePreview(employeeId, templateId);

		return ResponseEntity.ok(response);
	}
}