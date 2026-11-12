package com.company.emailtemplate.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.emailtemplate.entity.EmailTemplate;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

	Optional<EmailTemplate> findByTemplateCode(String templateCode);

	boolean existsByTemplateCode(String templateCode);

	List<EmailTemplate> findByActive(Boolean active);
}