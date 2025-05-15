package com.example.CRUDEMPLOYEES.service.impl;

import com.example.CRUDEMPLOYEES.model.dto.request.EmployeeRequestDTO;
import com.example.CRUDEMPLOYEES.model.dto.response.EmployeeResponseDTO;
import com.example.CRUDEMPLOYEES.model.entities.Employee;
import com.example.CRUDEMPLOYEES.model.enums.Gender;
import com.example.CRUDEMPLOYEES.repository.EmployeeRepository;
import com.example.CRUDEMPLOYEES.service.AuditLogService;
import com.example.CRUDEMPLOYEES.service.EmployeeMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.example.CRUDEMPLOYEES.exception.EmployeeNotFoundException;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class EmployeeServiceImplTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private AuditLogService auditLogService;

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  private Employee employee;
  private EmployeeRequestDTO requestDTO;

  @BeforeEach
  void setUp() {
    employee = new Employee(1L, "John", "A", "Doe", "Smith", 30, Gender.MALE,
        LocalDate.of(1993, 1, 1), "Engineer");
    requestDTO = new EmployeeRequestDTO("John", "A", "Doe", "Smith", 30, Gender.MALE,
        LocalDate.of(1993, 1, 1), "Engineer");
  }


  @Test
  void testGetAllEmployees() {
    when(employeeRepository.findAll()).thenReturn(List.of(employee));

    List<EmployeeResponseDTO> result = employeeService.getAllEmployees();

    assertEquals(1, result.size());
    assertTrue(result.get(0).getFullName().startsWith("John"));
    verify(employeeRepository).findAll();
  }

  @Test
  void testGetByIdFound() {
    when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

    EmployeeResponseDTO result = employeeService.getById(1L);

    Assertions.assertTrue(result.getFullName().startsWith("John"));
    verify(employeeRepository).findById(1L);
  }

  @Test
  void testGetByIdNotFound() {
    when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(EmployeeNotFoundException.class, () -> employeeService.getById(1L));
  }

  @Test
  void testCreate() {
    when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

    EmployeeResponseDTO result = employeeService.create(requestDTO);

    Assertions.assertTrue(result.getFullName().startsWith("John"));
    verify(employeeRepository).save(any(Employee.class));
    verify(auditLogService).log(eq("CREATE"), eq("EMPLOYEE"), eq(1L), eq("SYSTEM"), anyString());
  }

  @Test
  void testCreateMany() {
    List<EmployeeRequestDTO> dtos = List.of(requestDTO);
    when(employeeRepository.saveAll(anyList())).thenReturn(List.of(employee));

    List<EmployeeResponseDTO> result = employeeService.createMany(dtos);

    assertEquals(1, result.size());
    verify(employeeRepository).saveAll(anyList());
    verify(auditLogService).log(eq("CREATE"), eq("EMPLOYEE"), eq(1L), eq("SYSTEM"), anyString());
  }

  @Test
  void testUpdateSuccess() {
    Employee updated = new Employee(1L, "Updated", "A", "Doe", "Smith", 30, Gender.MALE,
        LocalDate.of(1993, 1, 1), "Engineer");

    when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
    when(employeeRepository.save(any(Employee.class))).thenReturn(updated);

    EmployeeRequestDTO updateDTO = new EmployeeRequestDTO("Updated", "A", "Doe", "Smith", 30, Gender.MALE,
        LocalDate.of(1993, 1, 1), "Engineer");

    EmployeeResponseDTO result = employeeService.update(1L, updateDTO);

    Assertions.assertTrue(result.getFullName().startsWith("Updated"));
    verify(employeeRepository).save(any(Employee.class));
    verify(auditLogService).log(eq("UPDATE"), eq("EMPLOYEE"), eq(1L), eq("SYSTEM"), anyString());
  }

  @Test
  void testUpdateNotFound() {
    when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(EmployeeNotFoundException.class, () -> employeeService.update(1L, requestDTO));
  }

  @Test
  void testDeleteSuccess() {
    when(employeeRepository.existsById(1L)).thenReturn(true);

    employeeService.delete(1L);

    verify(employeeRepository).deleteById(1L);
    verify(auditLogService).log(eq("DELETE"), eq("EMPLOYEE"), eq(1L), eq("SYSTEM"), anyString());
  }

  @Test
  void testDeleteNotFound() {
    when(employeeRepository.existsById(1L)).thenReturn(false);

    assertThrows(EmployeeNotFoundException.class, () -> employeeService.delete(1L));
  }
}