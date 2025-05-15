package com.example.CRUDEMPLOYEES.apidoc;

import com.example.CRUDEMPLOYEES.model.dto.request.EmployeeRequestDTO;
import com.example.CRUDEMPLOYEES.model.dto.response.EmployeeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "Employees", description = "Operations related to employees")
public interface EmployeeApiDoc {

  @Operation(
      summary = "Get all employees",
      description = "Returns a list of all registered employees",
      responses = @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(
              mediaType = "application/json",
              array = @ArraySchema(schema = @Schema(implementation = EmployeeResponseDTO.class))
          )
      )
  )
  ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(
      @RequestHeader HttpHeaders headers);

  @Operation(
      summary = "Get employee",
      description = "Returns one registered employee by his id",
      responses = @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = EmployeeResponseDTO.class)
          )
      )
  )
  ResponseEntity<EmployeeResponseDTO> getById(@PathVariable Long id,
      @RequestHeader HttpHeaders headers);

  @Operation(
      summary = "Create employee",
      description = "Create new employee",
      responses = @ApiResponse(
          responseCode = "201",
          description = "Successful operation",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = EmployeeResponseDTO.class)
          )
      )
  )
  ResponseEntity<EmployeeResponseDTO> create(@Valid @RequestBody EmployeeRequestDTO dto,
      @RequestHeader HttpHeaders headers);

  @Operation(
      summary = "Create multiple employees",
      description = "Allows you to create multiple employees",
      responses = @ApiResponse(
          responseCode = "201",
          description = "Successful operation",
          content = @Content(
              mediaType = "application/json",
              array = @ArraySchema(schema = @Schema(implementation = EmployeeResponseDTO.class))
          )
      )
  )
  ResponseEntity<List<EmployeeResponseDTO>> createBatch(
      @Valid @RequestBody List<EmployeeRequestDTO> dtos,
      @RequestHeader HttpHeaders headers);

  @Operation(
      summary = "Update employee",
      description = "Updates an existing employee's data in the system",
      responses = @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = EmployeeResponseDTO.class)
          )
      )
  )
  ResponseEntity<EmployeeResponseDTO> update(@PathVariable Long id,
      @Valid @RequestBody EmployeeRequestDTO dto,
      @RequestHeader HttpHeaders headers);

  @Operation(
      summary = "Delete Employee",
      description = "Delete an employee by id",
      responses = @ApiResponse(
          responseCode = "204",
          description = "Successful operation",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(hidden = true)  // ocultamos el schema para delete void
          )
      )
  )
  ResponseEntity<Void> delete(@PathVariable Long id,
      @RequestHeader HttpHeaders headers);

}
