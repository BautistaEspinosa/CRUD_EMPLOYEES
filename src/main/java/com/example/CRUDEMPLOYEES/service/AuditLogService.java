package com.example.CRUDEMPLOYEES.service;

import com.example.CRUDEMPLOYEES.model.entities.AuditLog;
import java.util.List;

public interface AuditLogService {

  void log(String action, String entity, Long entityId, String performedBy, String details);
  List<AuditLog> getAll();

  List<AuditLog> getByEntity(String entityType, Long entityId);
}
