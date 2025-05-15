package com.example.CRUDEMPLOYEES.service.impl;

import com.example.CRUDEMPLOYEES.constants.LoggerConstants;
import com.example.CRUDEMPLOYEES.model.entities.AuditLog;
import com.example.CRUDEMPLOYEES.repository.AuditLogRepository;
import com.example.CRUDEMPLOYEES.service.AuditLogService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

  private final AuditLogRepository auditLogRepository;

  @Override
  public void log(String action, String entity, Long entityId, String performedBy, String details) {
    AuditLog log = AuditLog.builder()
        .action(action)
        .entity(entity)
        .entityId(entityId)
        .performedBy(performedBy != null ? performedBy : LoggerConstants.PERFOMEDSYSTEM)
        .timestamp(LocalDateTime.now())
        .details(details)
        .build();
    auditLogRepository.save(log);
  }
  @Override
  public List<AuditLog> getAll() {
    return auditLogRepository.findAll();
  }

  @Override
  public List<AuditLog> getByEntity(String entityType, Long entityId) {
    return auditLogRepository.findByEntityAndEntityId(entityType, entityId);
  }
}
