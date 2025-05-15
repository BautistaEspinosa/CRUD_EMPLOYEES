package com.example.CRUDEMPLOYEES.controller;

import com.example.CRUDEMPLOYEES.model.entities.AuditLog;
import com.example.CRUDEMPLOYEES.service.AuditLogService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {
  private final AuditLogService auditLogService;

  @GetMapping
  public List<AuditLog> getAllLogs() {
    return auditLogService.getAll();
  }

  @GetMapping("/{entityType}/{entityId}")
  public List<AuditLog> getLogsByEntity(@PathVariable String entityType,
      @PathVariable Long entityId) {
    return auditLogService.getByEntity(entityType, entityId);
  }
}
