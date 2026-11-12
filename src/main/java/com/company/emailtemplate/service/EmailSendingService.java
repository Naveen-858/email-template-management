package com.company.emailtemplate.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.company.emailtemplate.dto.EmailSendResponseDto;
import com.company.emailtemplate.entity.EmailLog;
import com.company.emailtemplate.entity.EmailTemplate;
import com.company.emailtemplate.entity.Employee;
import com.company.emailtemplate.exception.EmailSendingException;
import com.company.emailtemplate.repo.EmailLogRepository;
import com.company.emailtemplate.repo.EmailTemplateRepository;
import com.company.emailtemplate.repo.EmployeeRepository;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSendingService {

	private final EmployeeRepository employeeRepository;
	private final EmailTemplateRepository emailTemplateRepository;
	private final EmailTemplateProcessor emailTemplateProcessor;
	private final EmailLogRepository emailLogRepository;
	private final JavaMailSender javaMailSender;

	public EmailSendingService(EmployeeRepository employeeRepository, EmailTemplateRepository emailTemplateRepository,
			EmailTemplateProcessor emailTemplateProcessor, EmailLogRepository emailLogRepository,
			JavaMailSender javaMailSender) {

		this.employeeRepository = employeeRepository;
		this.emailTemplateRepository = emailTemplateRepository;
		this.emailTemplateProcessor = emailTemplateProcessor;
		this.emailLogRepository = emailLogRepository;
		this.javaMailSender = javaMailSender;
	}

	public EmailSendResponseDto sendEmail(Long employeeId, Long templateId) {

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found: " + employeeId));

		if (!"ACTIVE".equalsIgnoreCase(employee.getStatus())) {
			throw new RuntimeException("Cannot send email to inactive employee");
		}

		EmailTemplate template = emailTemplateRepository.findById(templateId)
				.orElseThrow(() -> new RuntimeException("Email template not found: " + templateId));

		if (!Boolean.TRUE.equals(template.getActive())) {
			throw new RuntimeException("Email template is inactive");
		}

		Map<String, String> placeholders = emailTemplateProcessor.buildPlaceholders(employee);

		String processedSubject = emailTemplateProcessor.processSubject(template.getSubject(), placeholders);

		String processedBody = emailTemplateProcessor.processBody(template.getBody(), placeholders);

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			helper.setTo(employee.getEmail());
			helper.setSubject(processedSubject);
			helper.setText(processedBody, true);

			javaMailSender.send(mimeMessage);

			// Save successful email history
			EmailLog emailLog = new EmailLog();
			emailLog.setEmployee(employee);
			emailLog.setTemplate(template);
			emailLog.setRecipientEmail(employee.getEmail());
			emailLog.setSubject(processedSubject);
			emailLog.setStatus("SENT");
			emailLog.setSentAt(LocalDateTime.now());

			emailLogRepository.save(emailLog);

			EmailSendResponseDto response = new EmailSendResponseDto();

			response.setMessage("Email sent successfully");
			response.setRecipientEmail(employee.getEmail());
			response.setSubject(processedSubject);
			response.setStatus("SENT");
			response.setSentAt(emailLog.getSentAt());

			return response;

		} catch (Exception exception) {

			// Save failed email history
			EmailLog emailLog = new EmailLog();
			emailLog.setEmployee(employee);
			emailLog.setTemplate(template);
			emailLog.setRecipientEmail(employee.getEmail());
			emailLog.setSubject(processedSubject);
			emailLog.setStatus("FAILED");
			emailLog.setErrorMessage(exception.getMessage());

			emailLogRepository.save(emailLog);

			throw new EmailSendingException("Failed to send email: " + exception.getMessage(), exception);
		}
	}
}