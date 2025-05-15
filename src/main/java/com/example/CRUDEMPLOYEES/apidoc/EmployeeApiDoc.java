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
import static com.example.CRUDEMPLOYEES.constants.ApiDocConstants.*;

@Tag(name = TAG_EMPLOYEES, description = TAG_EMPLOYEES_DESC)
public interface EmployeeApiDoc {

  @Operation(
      summary = GET_ALL_SUMMARY,
      description = GET_ALL_DESC,
      responses = @ApiResponse(
          responseCode = RESP_200,
          description = RESP_OK,
          content = @Content(
              mediaType = METATYPE,
              array = @ArraySchema(schema = @Schema(implementation = EmployeeResponseDTO.class))
          )
      )
  )
  ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(@RequestHeader HttpHeaders headers);

  @Operation(
      summary = GET_BY_ID_SUMMARY,
      description = GET_BY_ID_DESC,
      responses = @ApiResponse(
          responseCode = RESP_200,
          description = RESP_OK,
          content = @Content(
              mediaType = METATYPE,
              schema = @Schema(implementation = EmployeeResponseDTO.class)
          )
      )
  )
  ResponseEntity<EmployeeResponseDTO> getById(@PathVariable Long id,
      @RequestHeader HttpHeaders headers);

  @Operation(
      summary = CREATE_SUMMARY,
      description = CREATE_DESC,
      responses = @ApiResponse(
          responseCode = RESP_201,
          description = RESP_OK,
          content = @Content(
              mediaType = METATYPE,
              schema = @Schema(implementation = EmployeeResponseDTO.class)
          )
      )
  )
  ResponseEntity<EmployeeResponseDTO> create(@Valid @RequestBody EmployeeRequestDTO dto,
      @RequestHeader HttpHeaders headers);

  @Operation(
      summary = CREATE_BATCH_SUMMARY,
      description = CREATE_BATCH_DESC,
      responses = @ApiResponse(
          responseCode = RESP_201,
          description = RESP_OK,
          content = @Content(
              mediaType = METATYPE,
              array = @ArraySchema(schema = @Schema(implementation = EmployeeResponseDTO.class))
          )
      )
  )
  ResponseEntity<List<EmployeeResponseDTO>> createBatch(
      @Valid @RequestBody List<EmployeeRequestDTO> dtos,
      @RequestHeader HttpHeaders headers);

  @Operation(
      summary = UPDATE_SUMMARY,
      description = UPDATE_DESC,
      responses = @ApiResponse(
          responseCode = RESP_200,
          description = RESP_OK,
          content = @Content(
              mediaType = METATYPE,
              schema = @Schema(implementation = EmployeeResponseDTO.class)
          )
      )
  )
  ResponseEntity<EmployeeResponseDTO> update(@PathVariable Long id,
      @Valid @RequestBody EmployeeRequestDTO dto,
      @RequestHeader HttpHeaders headers);

  @Operation(
      summary = DELETE_SUMMARY,
      description = DELETE_DESC,
      responses = @ApiResponse(
          responseCode = RESP_204,
          description = RESP_OK,
          content = @Content(
              mediaType = METATYPE,
              schema = @Schema(hidden = true)
          )
      )
  )
  ResponseEntity<Void> delete(@PathVariable Long id,
      @RequestHeader HttpHeaders headers);
}
