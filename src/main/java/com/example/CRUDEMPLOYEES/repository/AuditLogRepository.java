package com.example.CRUDEMPLOYEES.repository;

import com.example.CRUDEMPLOYEES.model.entities.AuditLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog,Long> {

  List<AuditLog> findByEntityAndEntityId(String entity, Long entityId);
}
