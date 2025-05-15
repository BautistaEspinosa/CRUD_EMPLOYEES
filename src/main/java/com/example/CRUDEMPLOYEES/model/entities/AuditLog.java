package com.example.CRUDEMPLOYEES.model.entities;

import com.example.CRUDEMPLOYEES.constants.LoggerConstants;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = LoggerConstants.TABLE_NAME_AUDIT)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String action;

  private String entity;

  private Long entityId;

  private String performedBy;

  private LocalDateTime timestamp;

  private String details;
}
