package com.example.CRUDEMPLOYEES.controller;

import com.example.CRUDEMPLOYEES.apidoc.EmployeeApiDoc;
import com.example.CRUDEMPLOYEES.model.dto.request.EmployeeRequestDTO;
import com.example.CRUDEMPLOYEES.model.dto.response.EmployeeResponseDTO;
import com.example.CRUDEMPLOYEES.service.EmployeeService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController implements EmployeeApiDoc {

  private final EmployeeService employeeService;

  @GetMapping
  public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(
      @RequestHeader HttpHeaders headers) {
    logHeaders(headers);
    List<EmployeeResponseDTO> employees = employeeService.getAllEmployees();
    return ResponseEntity.status(HttpStatus.OK).body(employees);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeResponseDTO> getById(@PathVariable Long id,
      @RequestHeader HttpHeaders headers) {
    logHeaders(headers);
    return ResponseEntity.status(HttpStatus.OK).body(employeeService.getById(id));
  }

  @PostMapping
  public ResponseEntity<EmployeeResponseDTO> create(@Valid @RequestBody EmployeeRequestDTO dto,
      @RequestHeader HttpHeaders headers) {
    logHeaders(headers);
    return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(dto));
  }

  @PostMapping("/batch")
  public ResponseEntity<List<EmployeeResponseDTO>> createBatch(
      @Valid @RequestBody List<EmployeeRequestDTO> dtos,
      @RequestHeader HttpHeaders headers) {
    logHeaders(headers);
    return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createMany(dtos));
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeResponseDTO> update(@PathVariable Long id,
      @Valid @RequestBody EmployeeRequestDTO dto,
      @RequestHeader HttpHeaders headers) {
    logHeaders(headers);
    return ResponseEntity.status(HttpStatus.OK).body(employeeService.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id,
      @RequestHeader HttpHeaders headers){
    logHeaders(headers);
    employeeService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  private void logHeaders(HttpHeaders headers) {
    headers.forEach((key, value) -> log.info("Header '{}': {}", key,
        String.join(",", value)));
  }
}
