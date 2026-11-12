package com.company.emailtemplate.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.company.emailtemplate.entity.Employee;

@Service
public class EmailTemplateProcessor {

	// Build employee-specific placeholder values
	public Map<String, String> buildPlaceholders(Employee employee) {

		LocalDate today = LocalDate.now();
		LocalDate joiningDate = employee.getDateOfJoining();

		int anniversaryYears = Period.between(joiningDate, today).getYears();

		long companyExperienceMonths = ChronoUnit.MONTHS.between(joiningDate, today);

		int previousExperienceMonths = employee.getPreviousExperienceMonths() == null ? 0
				: employee.getPreviousExperienceMonths();

		long totalExperienceMonths = companyExperienceMonths + previousExperienceMonths;

		Map<String, String> placeholders = new HashMap<>();

		placeholders.put("name", safeValue(employee.getName()));
		placeholders.put("employeeCode", safeValue(employee.getEmployeeCode()));
		placeholders.put("email", safeValue(employee.getEmail()));
		placeholders.put("designation", safeValue(employee.getDesignation()));

		placeholders.put("dateOfJoining", joiningDate.toString());

		placeholders.put("anniversaryYears", String.valueOf(anniversaryYears));

		placeholders.put("previousExperienceMonths", String.valueOf(previousExperienceMonths));

		placeholders.put("totalExperienceMonths", String.valueOf(totalExperienceMonths));

		placeholders.put("totalExperienceYears", String.valueOf(totalExperienceMonths / 12.0));

		return placeholders;
	}

	// Process email subject
	public String processSubject(String subject, Map<String, String> placeholders) {

		return replacePlaceholders(subject, placeholders, false);
	}

	// Process HTML email body
	public String processBody(String body, Map<String, String> placeholders) {

		return replacePlaceholders(body, placeholders, true);
	}

	// Replace placeholders dynamically
	private String replacePlaceholders(String content, Map<String, String> placeholders, boolean escapeHtml) {

		String result = content;

		for (Map.Entry<String, String> entry : placeholders.entrySet()) {

			String placeholder = "{{" + entry.getKey() + "}}";

			String value = entry.getValue();

			if (escapeHtml) {
				value = escapeHtml(value);
			}

			result = result.replace(placeholder, value);
		}

		return result;
	}

	// Escape employee values before inserting them into HTML
	private String escapeHtml(String value) {

		return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")
				.replace("'", "&#39;");
	}

	private String safeValue(String value) {
		return value == null ? "" : value;
	}
}