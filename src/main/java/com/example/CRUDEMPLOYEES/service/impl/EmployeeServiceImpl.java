package com.example.CRUDEMPLOYEES.service.impl;

import static java.util.stream.Collectors.toList;

import com.example.CRUDEMPLOYEES.constants.LoggerConstants;
import com.example.CRUDEMPLOYEES.exception.EmployeeNotFoundException;
import com.example.CRUDEMPLOYEES.model.dto.request.EmployeeRequestDTO;
import com.example.CRUDEMPLOYEES.model.dto.response.EmployeeResponseDTO;
import com.example.CRUDEMPLOYEES.model.entities.Employee;
import com.example.CRUDEMPLOYEES.repository.EmployeeRepository;
import com.example.CRUDEMPLOYEES.service.AuditLogService;
import com.example.CRUDEMPLOYEES.service.EmployeeMapper;
import com.example.CRUDEMPLOYEES.service.EmployeeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final AuditLogService auditLogService;

  @Override
  public List<EmployeeResponseDTO> getAllEmployees() {
    log.info(LoggerConstants.LOG_FETCHING);
    return employeeRepository.findAll().stream()
        .map(EmployeeMapper::toDto)
        .collect(toList());
  }

  @Override
  public EmployeeResponseDTO getById(Long id) {
    log.info(LoggerConstants.LOG_GETTING, id);
    return employeeRepository.findById(id)
        .map(EmployeeMapper::toDto)
        .orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  @Override
  public EmployeeResponseDTO create(EmployeeRequestDTO dto) {
    log.info(LoggerConstants.LOG_CREATE);
    Employee employee = EmployeeMapper.toEntity(dto);
    Employee saved = employeeRepository.save(employee);
    auditLogService.log(LoggerConstants.LOG_ACTION, LoggerConstants.LOG_ENTITY, saved.getId(), LoggerConstants.PERFOMEDSYSTEM,
        LoggerConstants.LOG_DETAILS);
    return EmployeeMapper.toDto(saved);
  }

  @Override
  public List<EmployeeResponseDTO> createMany(List<EmployeeRequestDTO> dtos) {
    log.info(LoggerConstants.LOG_CREATING, dtos.size());
    List<Employee> saved = employeeRepository.saveAll(
        dtos.stream().map(EmployeeMapper::toEntity).toList()
    );

    saved.forEach(emp ->
        auditLogService.log(LoggerConstants.LOG_ACTION, LoggerConstants.LOG_ENTITY, emp.getId(), LoggerConstants.PERFOMEDSYSTEM,
            LoggerConstants.LOG_DETAILS_BATCH));
    return saved.stream().map(EmployeeMapper::toDto).toList();
  }

  @Override
  public EmployeeResponseDTO update(Long id, EmployeeRequestDTO dto) {
    log.info(LoggerConstants.LOG_UPDATE, id);

    return employeeRepository.findById(id)
        .map(existing -> {
          if (dto.getFirstName() != null) existing.setFirstName(dto.getFirstName());
          if (dto.getMiddleName() != null) existing.setMiddleName(dto.getMiddleName());
          if (dto.getLastName() != null) existing.setLastName(dto.getLastName());
          if (dto.getSecondLastName() != null) existing.setSecondLastName(dto.getSecondLastName());
          if (dto.getAge() != null) existing.setAge(dto.getAge());
          if (dto.getGender() != null) existing.setGender(dto.getGender());
          if (dto.getBirthday() != null) existing.setBirthday(dto.getBirthday());
          if (dto.getPosition() != null) existing.setPosition(dto.getPosition());

          Employee saved = employeeRepository.save(existing);
          auditLogService.log(LoggerConstants.LOG_ACTION_UPDATE, LoggerConstants.LOG_ENTITY, saved.getId(), LoggerConstants.PERFOMEDSYSTEM, LoggerConstants.LOG_DETAILS_UPDATE);
          return EmployeeMapper.toDto(saved);
        })
        .orElseThrow(() -> new EmployeeNotFoundException(id));
  }


  @Override
  public void delete(Long id) {
    log.info(LoggerConstants.LOG_DELETE, id);
    if (!employeeRepository.existsById(id)) {
      throw new EmployeeNotFoundException(id);
    }
    employeeRepository.deleteById(id);
    auditLogService.log(LoggerConstants.LOG_ACTION_DELETE,LoggerConstants.LOG_ENTITY,id,LoggerConstants.PERFOMEDSYSTEM,
        LoggerConstants.LOG_DETAILS_DELETE);
  }
}
