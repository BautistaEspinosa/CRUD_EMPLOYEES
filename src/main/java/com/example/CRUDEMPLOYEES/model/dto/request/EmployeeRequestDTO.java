package com.example.CRUDEMPLOYEES.model.dto.request;

import com.example.CRUDEMPLOYEES.constants.Constants;
import com.example.CRUDEMPLOYEES.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeRequestDTO {


  @Pattern(regexp = Constants.PATTERNREGIX, message = Constants.MESSAGEFIRSTNAME)
  private String firstName;

  @Pattern(regexp = Constants.PATTERN_REGIX , message = Constants.MESSAGEMIDDLENAME)
  private String middleName;

  @Pattern(regexp = Constants.PATTERNREGIX, message = Constants.MESSAGELASTNAME )
  private String lastName;

  @Pattern(regexp = Constants.PATTERN_REGIX, message = Constants.MESSAGESECONDLAST)
  private String secondLastName;

  @Min(value = Constants.VALUE_MIN, message = Constants.MESSAGEAGEMIN)
  @Max(value = Constants.VALUE_MAX, message = Constants.MESSAGE_AGEMAX)
  private Integer age;


  private Gender gender;

  @JsonFormat(pattern = Constants.PATTER_DATE)
  @Past(message = Constants.MESSAGE_DATE)
  private LocalDate birthday;

  private String position;
}