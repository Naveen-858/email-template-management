package com.company.emailtemplate.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.emailtemplate.entity.EmailLog;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {

	List<EmailLog> findByEmployeeId(Long employeeId);

	List<EmailLog> findByStatus(String status);

	List<EmailLog> findByEmployeeIdAndTemplateId(Long employeeId, Long templateId);

	boolean existsByEmployeeIdAndTemplateIdAndStatus(Long employeeId, Long templateId, String status);
}