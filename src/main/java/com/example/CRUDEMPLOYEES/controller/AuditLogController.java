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
@RequestMapping("${endpoints.audit-logs.base-url}")
@RequiredArgsConstructor
public class AuditLogController {

  private final AuditLogService auditLogService;

  @GetMapping("${endpoints.audit-logs.paths.getAll}")
  public List<AuditLog> getAllLogs() {
    return auditLogService.getAll();
  }

  @GetMapping("${endpoints.audit-logs.paths.getByEntity}")
  public List<AuditLog> getLogsByEntity(@PathVariable String entityType,
      @PathVariable Long entityId) {
    return auditLogService.getByEntity(entityType, entityId);
  }
}
