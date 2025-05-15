package com.example.CRUDEMPLOYEES.service;

import com.example.CRUDEMPLOYEES.model.dto.request.EmployeeRequestDTO;
import com.example.CRUDEMPLOYEES.model.dto.response.EmployeeResponseDTO;
import com.example.CRUDEMPLOYEES.model.entities.Employee;
import java.util.List;

public class EmployeeMapper {

  public static Employee toEntity(EmployeeRequestDTO dto){
    return Employee.builder()
        .firstName(dto.getFirstName())
        .middleName(dto.getMiddleName())
        .lastName(dto.getLastName())
        .secondLastName(dto.getSecondLastName())
        .age(dto.getAge())
        .gender(dto.getGender())
        .birthday(dto.getBirthday())
        .position(dto.getPosition())
        .build();
  }

  public static EmployeeResponseDTO toDto(Employee employee){
    return EmployeeResponseDTO.builder()
        .id(employee.getId())
        .fullName(buildFullName(employee))
        .age(employee.getAge())
        .gender(employee.getGender())
        .birthdate(employee.getBirthday())
        .position(employee.getPosition())
        .build();
  }
  private static String buildFullName(Employee employee) {
    return String.join(" ", List.of(
            employee.getFirstName(),
            employee.getMiddleName(),
            employee.getLastName(),
            employee.getSecondLastName()
        ).stream()
        .filter(s -> s != null && !s.trim().isEmpty())
        .toList());
  }
}
