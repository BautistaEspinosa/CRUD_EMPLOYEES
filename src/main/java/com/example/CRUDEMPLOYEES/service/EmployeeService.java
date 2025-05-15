package com.example.CRUDEMPLOYEES.service;

import com.example.CRUDEMPLOYEES.model.dto.request.EmployeeRequestDTO;
import com.example.CRUDEMPLOYEES.model.dto.response.EmployeeResponseDTO;
import java.util.List;

public interface EmployeeService {

  List<EmployeeResponseDTO> getAllEmployees();
  EmployeeResponseDTO getById(Long id);
  EmployeeResponseDTO create(EmployeeRequestDTO dto);
  List<EmployeeResponseDTO> createMany(List<EmployeeRequestDTO> dtos);
  EmployeeResponseDTO update(Long id, EmployeeRequestDTO dto);
  void delete(Long id);
}
