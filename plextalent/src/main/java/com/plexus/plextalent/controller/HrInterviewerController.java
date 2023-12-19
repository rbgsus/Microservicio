package com.plexus.plextalent.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.plexus.plextalent.dto.HrInterviewerDTO;
import com.plexus.plextalent.dto.HrInterviewerDTOPage;
import com.plexus.plextalent.mapper.HrInterviewerMapper;
import com.plexus.plextalent.model.HrInterviewer;
import com.plexus.plextalent.service.impl.HrInterviewerServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import utils.ValidationInterviewer;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/interviewers")
@Tag(name = "interviewers", description = "Endpoints for HR Interviewers")
public class HrInterviewerController {

	private final HrInterviewerServiceImpl interviewerServiceImpl;
	private final HrInterviewerMapper hrInterviewerMapper;

	@PostMapping("/interviewers")
	@Operation(summary = "Save an HR interviewer", description = "Save an HR interviewer to the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "HR interviewer saved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> saveInterviewer(
			@RequestBody @Parameter(description = "HR interviewer information") HrInterviewer interviewer) {
		String errorMessage = utils.ValidationInterviewer.checkIfExistInterviewer(interviewer, interviewerServiceImpl);

		if (!errorMessage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}

		errorMessage = ValidationInterviewer.validateInterviewer(interviewer);

		if (!errorMessage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}

		interviewerServiceImpl.saveHrInterviewer(interviewer);
		return ResponseEntity.status(HttpStatus.CREATED).body(interviewer);
	}

	@GetMapping("/interviewers")
	@Operation(summary = "Get all HR interviewers", description = "Get a list of all HR interviewer objects from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "HR interviewers not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getAllInterviewers() {
		List<HrInterviewer> res = interviewerServiceImpl.getAllHrInterviewers();

		if (res == null || res.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay entrevistadores registrados");
		} else {
			List<HrInterviewerDTO> listHrInterviewerDTO = res.stream().map(hrInterviewerMapper::hrInterviewerToDTO)
					.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(listHrInterviewerDTO);
		}
	}

	@GetMapping("/interviewers/{id}")
	@Operation(summary = "Get HR interviewer by ID", description = "Get an HR interviewer object from the database based on the given ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "HR interviewer not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getInterviewerById(
			@Parameter(in = ParameterIn.PATH, name = "id", required = true, description = "ID of the HR interviewer") @PathVariable Long id) {
		HrInterviewer interviewer = interviewerServiceImpl.getHrInterviewerById(id);
		if (interviewer != null) {
			return ResponseEntity.status(HttpStatus.OK).body(hrInterviewerMapper.hrInterviewerToDTO(interviewer));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay entrevistador registrado con el ID: " + id);
		}
	}

	@PutMapping("/interviewers/{id}")
	@Operation(summary = "Update HR interviewer", description = "Update an HR interviewer in the database based on the given ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "HR interviewer updated successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "HR interviewer not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> updateInterviewer(
			@Parameter(in = ParameterIn.PATH, name = "id", required = true, description = "ID of the HR interviewer to be updated") @PathVariable Long id,
			@RequestBody @Parameter(description = "Updated HR interviewer information") HrInterviewer interviewer) {

		String errorMessage = ValidationInterviewer.validateInterviewer(interviewer);

		if (interviewerServiceImpl.getAllHrInterviewers().contains(interviewer)) {
			errorMessage = errorMessage + ("El nombre de usuario corporativo ya existe. ");
		}

		if (!errorMessage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.toString());
		}

		HrInterviewer res = interviewerServiceImpl.getHrInterviewerById(id);
		if (res != null) {
			interviewerServiceImpl.updateHrInterviewer(id, interviewer);
			return ResponseEntity.status(HttpStatus.OK).body(interviewer);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"No se ha podido actualizar el entrevistador con el ID: " + id);
		}
	}

	@DeleteMapping("/interviewers/{id}")
	@Operation(summary = "Delete HR interviewer", description = "Delete an HR interviewer from the database based on the given ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "HR interviewer deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "HR interviewer not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> deleteInterviewer(
			@Parameter(in = ParameterIn.PATH, name = "id", required = true, description = "ID of the HR interviewer to be deleted") @PathVariable Long id) {
		HrInterviewer res = interviewerServiceImpl.getHrInterviewerById(id);
		if (res != null) {
			interviewerServiceImpl.deleteHrInterviewer(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No se ha podido borrar, el entrevistador con el ID: " + id + " no se ha encontrado");
		}

	}

	/**
	 * 
	 * This endpoint uses HTTP method GET and retrieves a paginated list of HR
	 * interviewers
	 * 
	 * @param page This parameter is the page number to retrieve (required)
	 * @param size This parameter is the number of elements per page (required)
	 * @return This method returns a HR interviewer list in a page
	 */

	@GetMapping("/interviewerPage")
	@Operation(summary = "Get paginated HR interviewer", description = "Get a paginated list of HR interviewers")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "HR interviewers not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getPaginatedInterviewers(
			@Parameter(in = ParameterIn.QUERY, name = "page", required = true) @RequestParam int page,
			@Parameter(in = ParameterIn.QUERY, name = "size", required = true) @RequestParam int size) {

		HrInterviewerDTOPage hrInterviewerDTOPage = interviewerServiceImpl.listInterviewers(page, size);

		if (hrInterviewerDTOPage.getInterviewerList().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay entrevistadores registrados");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(hrInterviewerDTOPage.getInterviewerList());
		}
	}
}
