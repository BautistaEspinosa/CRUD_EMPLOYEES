package com.example.CRUDEMPLOYEES.service.impl;

import com.example.CRUDEMPLOYEES.model.entities.AuditLog;
import com.example.CRUDEMPLOYEES.repository.AuditLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuditLogServiceImplTest {

  @Mock
  private AuditLogRepository auditLogRepository;

  @InjectMocks
  private AuditLogServiceImpl auditLogService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void log_ShouldSaveAuditLogWithCorrectFields() {
    // Arrange
    String action = "CREATE";
    String entity = "Employee";
    Long entityId = 1L;
    String performedBy = "admin";
    String details = "Created new employee";

    ArgumentCaptor<AuditLog> captor = ArgumentCaptor.forClass(AuditLog.class);

    // Act
    auditLogService.log(action, entity, entityId, performedBy, details);

    // Assert
    verify(auditLogRepository).save(captor.capture());
    AuditLog savedLog = captor.getValue();

    assertEquals(action, savedLog.getAction());
    assertEquals(entity, savedLog.getEntity());
    assertEquals(entityId, savedLog.getEntityId());
    assertEquals(performedBy, savedLog.getPerformedBy());
    assertEquals(details, savedLog.getDetails());
    assertNotNull(savedLog.getTimestamp());
    assertTrue(savedLog.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)));
  }

  @Test
  void log_ShouldDefaultPerformedByToSystemWhenNull() {
    // Arrange
    String action = "DELETE";
    String entity = "Employee";
    Long entityId = 2L;
    String details = "Deleted employee";

    ArgumentCaptor<AuditLog> captor = ArgumentCaptor.forClass(AuditLog.class);

    // Act
    auditLogService.log(action, entity, entityId, null, details);

    // Assert
    verify(auditLogRepository).save(captor.capture());
    AuditLog savedLog = captor.getValue();

    assertEquals("SYSTEM", savedLog.getPerformedBy());
  }

  @Test
  void getAll_ShouldReturnAllAuditLogs() {
    // Arrange
    List<AuditLog> mockLogs = List.of(
        AuditLog.builder().entity("Employee").build(),
        AuditLog.builder().entity("Department").build()
    );
    when(auditLogRepository.findAll()).thenReturn(mockLogs);

    // Act
    List<AuditLog> result = auditLogService.getAll();

    // Assert
    assertEquals(2, result.size());
    assertEquals(mockLogs, result);
  }

  @Test
  void getByEntity_ShouldReturnFilteredAuditLogs() {
    // Arrange
    String entityType = "Employee";
    Long entityId = 5L;
    List<AuditLog> mockLogs = List.of(
        AuditLog.builder().entity(entityType).entityId(entityId).build()
    );
    when(auditLogRepository.findByEntityAndEntityId(entityType, entityId)).thenReturn(mockLogs);

    // Act
    List<AuditLog> result = auditLogService.getByEntity(entityType, entityId);

    // Assert
    assertEquals(1, result.size());
    assertEquals(mockLogs, result);
  }
}
