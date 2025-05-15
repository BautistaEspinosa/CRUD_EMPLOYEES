package com.example.CRUDEMPLOYEES.controller;

import com.example.CRUDEMPLOYEES.model.entities.AuditLog;
import com.example.CRUDEMPLOYEES.service.AuditLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AuditLogControllerTest {

  @Mock
  private AuditLogService auditLogService;

  @InjectMocks
  private AuditLogController auditLogController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAllLogs_ReturnsListOfAuditLogs() {
    // Arrange
    AuditLog log1 = new AuditLog();
    AuditLog log2 = new AuditLog();
    List<AuditLog> mockLogs = List.of(log1, log2);

    when(auditLogService.getAll()).thenReturn(mockLogs);

    // Act
    List<AuditLog> result = auditLogController.getAllLogs();

    // Assert
    assertEquals(2, result.size());
    assertEquals(mockLogs, result);
  }

  @Test
  void getLogsByEntity_ReturnsFilteredAuditLogs() {
    // Arrange
    String entityType = "Employee";
    Long entityId = 123L;

    AuditLog log1 = new AuditLog();
    AuditLog log2 = new AuditLog();
    List<AuditLog> filteredLogs = List.of(log1, log2);

    when(auditLogService.getByEntity(entityType, entityId)).thenReturn(filteredLogs);

    // Act
    List<AuditLog> result = auditLogController.getLogsByEntity(entityType, entityId);

    // Assert
    assertEquals(2, result.size());
    assertEquals(filteredLogs, result);
  }
}
