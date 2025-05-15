package com.example.CRUDEMPLOYEES.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.CRUDEMPLOYEES.model.dto.request.EmployeeRequestDTO;
import com.example.CRUDEMPLOYEES.model.dto.response.EmployeeResponseDTO;
import com.example.CRUDEMPLOYEES.model.enums.Gender;
import com.example.CRUDEMPLOYEES.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EmployeeService employeeService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("GET /api/employees should return list of employees")
  void shouldReturnAllEmployees() throws Exception {
    var mockResponse = List.of(new EmployeeResponseDTO(1L, "John Doe IT", 20,
        Gender.MALE, LocalDate.of(1993, 1, 1), "tester"));
    Mockito.when(employeeService.getAllEmployees()).thenReturn(mockResponse);

    mockMvc.perform(get("/api/employees"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("John Doe"));
  }

  @Test
  @DisplayName("GET /api/employees/{id} should return an employee")
  void shouldReturnEmployeeById() throws Exception {
    var employee = new EmployeeResponseDTO(1L, "John Doe IT", 24,
        Gender.MALE, LocalDate.of(1992, 1, 2), "developer");
    Mockito.when(employeeService.getById(1L)).thenReturn(employee);

    mockMvc.perform(get("/api/employees/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("John Doe"));
  }

  @Test
  @DisplayName("POST /api/employees should create an employee")
  void shouldCreateEmployee() throws Exception {
    var request = new EmployeeRequestDTO("Jane Smith", "HR", "Bautista",
        "Espinosa", 22, Gender.MALE, LocalDate.of(1993, 4, 22), "Scrum");
    var response = new EmployeeResponseDTO(1L, "Jane Smith Bautista Espinosa", 22,
        Gender.MALE, LocalDate.of(1993, 4, 22), "Scrum");
    Mockito.when(employeeService.create(any(EmployeeRequestDTO.class))).thenReturn(response);

    mockMvc.perform(post("/api/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("Jane Smith"));
  }

  @Test
  @DisplayName("PUT /api/employees/{id} should update an employee")
  void shouldUpdateEmployee() throws Exception {
    var request = new EmployeeRequestDTO("Jane Smith", "HR", "Bautista",
        "Espinosa", 22, Gender.MALE, LocalDate.of(1993, 4, 22), "Scrum");
    var response = new EmployeeResponseDTO(1L, "Jane Smith Bautista Espinosa", 22,
        Gender.MALE, LocalDate.of(1993, 4, 22), "Scrum");
    Mockito.when(employeeService.update(eq(1L), any(EmployeeRequestDTO.class)))
        .thenReturn(response);

    mockMvc.perform(put("/api/employees/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Updated Name"));
  }

  @Test
  @DisplayName("DELETE /api/employees/{id} should delete an employee")
  void shouldDeleteEmployee() throws Exception {
    mockMvc.perform(delete("/api/employees/1"))
        .andExpect(status().isNoContent());
    Mockito.verify(employeeService).delete(1L);
  }

  @Test
  @DisplayName("POST /api/employees/batch should create multiple employees")
  void shouldCreateBatchEmployees() throws Exception {
    var requestList = List.of(new EmployeeRequestDTO("John", "A", "Doe", "Smith", 30, Gender.MALE,
        LocalDate.of(1993, 1, 1), "Engineer"),
        new EmployeeRequestDTO("John", "A", "Doe", "Smith", 30, Gender.MALE,
            LocalDate.of(1993, 1, 1), "Engineer"));
    var responseList = List.of(new EmployeeResponseDTO(1L, "Jane Smith Bautista Espinosa", 22,
            Gender.MALE, LocalDate.of(1993, 4, 22), "Scrum"),
        new EmployeeResponseDTO(1L, "Jane Smith Bautista Espinosa", 22,
            Gender.MALE, LocalDate.of(1993, 4, 22), "Scrum"));
    Mockito.when(employeeService.createMany(any())).thenReturn(responseList);

    mockMvc.perform(post("/api/employees/batch")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestList)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$[0].name").value("John"));
  }
}