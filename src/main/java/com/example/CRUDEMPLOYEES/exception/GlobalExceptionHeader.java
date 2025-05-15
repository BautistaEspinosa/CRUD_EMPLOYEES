package com.example.CRUDEMPLOYEES.exception;

import com.example.CRUDEMPLOYEES.constants.LoggerConstants;
import com.example.CRUDEMPLOYEES.model.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHeader {

  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(EmployeeNotFoundException e,
      HttpServletRequest request) {
    return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,
      HttpServletRequest rq) {
    String message = ex.getBindingResult().getFieldErrors().stream()
        .map(e -> e.getField() + ": " + e.getDefaultMessage())
        .findFirst()
        .orElse(LoggerConstants.EXCEPTIONVALIDATE);
    return buildErrorResponse(HttpStatus.BAD_REQUEST, message, rq.getRequestURI());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleContraint(ConstraintViolationException ex, HttpServletRequest request){
    return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(EmployeeServiceException.class)
  public ResponseEntity<ErrorResponse> handleDomain(EmployeeServiceException e,HttpServletRequest request){
    return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGeneric(Exception e,HttpServletRequest request){
    log.error(LoggerConstants.UNHANDLED,e);
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,LoggerConstants.MESSAGE_SERVER_ERROR,request.getRequestURI());
  }
  private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message,
      String path) {
    ErrorResponse error = new ErrorResponse(LocalDateTime.now(), status.value(),
        status.getReasonPhrase(), message, path);
    return new ResponseEntity<>(error, status);
  }
}
