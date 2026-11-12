package com.company.emailtemplate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.emailtemplate.dto.EmailSendRequestDto;
import com.company.emailtemplate.dto.EmailSendResponseDto;
import com.company.emailtemplate.service.EmailSendingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/emails")
public class EmailSendingController {

	private final EmailSendingService emailSendingService;

	public EmailSendingController(EmailSendingService emailSendingService) {
		this.emailSendingService = emailSendingService;
	}

	@PostMapping("/send")
	public ResponseEntity<EmailSendResponseDto> sendEmail(@Valid @RequestBody EmailSendRequestDto request) {

		EmailSendResponseDto response = emailSendingService.sendEmail(request.getEmployeeId(), request.getTemplateId());

		return ResponseEntity.ok(response);
	}
}