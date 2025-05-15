package com.example.CRUDEMPLOYEES.model.dto.request;

import com.example.CRUDEMPLOYEES.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeRequestDTO {


  @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo debe contener letras")
  private String firstName;

  @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El segundo nombre solo debe contener letras")
  private String middleName;

  @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El apellido solo debe contener letras")
  private String lastName;

  @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El segundo apellido solo debe contener letras")
  private String secondLastName;

  @Min(value = 18, message = "La edad mínima es 18")
  @Max(value = 100, message = "La edad máxima es 100")
  private Integer age;


  private Gender gender;

  @JsonFormat(pattern = "dd-MM-yyyy")
  @Past(message = "La fecha de nacimiento debe ser en el pasado")
  private LocalDate birthday;

  private String position;
}