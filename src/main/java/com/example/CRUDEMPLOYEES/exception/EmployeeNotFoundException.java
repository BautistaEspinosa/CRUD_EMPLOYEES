package com.example.CRUDEMPLOYEES.exception;

import com.example.CRUDEMPLOYEES.constants.LoggerConstants;

public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException(Long id) {
    super(LoggerConstants.MESSAGEEXCEPTION +id+LoggerConstants.COMPLETE_MESSAGE);
  }
}
