package com.example.CRUDEMPLOYEES.model.dto.response;

import com.example.CRUDEMPLOYEES.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {
  private Long id;
  private String fullName;
  private Integer age;
  private Gender gender;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate birthdate;
  private String position;
}
