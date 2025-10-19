package com.legal_system.mediation.repository;

import com.legal_system.mediation.model.ChatbotLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatbotLogsRepository extends JpaRepository<ChatbotLogs, Integer> {
}