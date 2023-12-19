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

import com.plexus.plextalent.dto.TechnicalInterviewerDTO;
import com.plexus.plextalent.mapper.TechnicalInterviewerMapper;
import com.plexus.plextalent.model.TechnicalInterviewer;
import com.plexus.plextalent.service.impl.TechnicalInterviewerServiceImpl;
import com.plexus.plextalent.service.impl.TechnicalRoleServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * This class is the technical interviewer controller, which provides the
 * endpoints for the technical interviewer API.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/technical_interviewers")
@Tag(name = "technical-interviewers", description = "Endpoints for Technical Interviewers")
public class TechnicalInterviewerController {

	private final TechnicalInterviewerServiceImpl technicalInterviewerService;
	private final TechnicalInterviewerMapper technicalInterviewerMapperDTOConververter;
	private final TechnicalRoleServiceImpl technicalRoleService;

	/**
	 * This endpoint uses HTTP method GET to retrieve a list of all technical
	 * interviewer objects from the database.
	 *
	 * @return This method returns a list of TechnicalInterviewerDTO objects found
	 *         in the database, or a 404 response if the list is empty.
	 */
	@GetMapping("/technical_interviewers")
	@Operation(summary = "Get all Technical Interviewers", description = "Get a list of all TechnicalInterviewerDTO objects from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technical Interviewers not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<List<TechnicalInterviewerDTO>> getAllTechnicalInterviewers() {
		List<TechnicalInterviewer> technicalInterviewers = technicalInterviewerService.getAllTechnicalInterviewers();

		if (technicalInterviewers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			// Convert the list of TechnicalInterviewer to a list of TechnicalInterviewerDTO
			List<TechnicalInterviewerDTO> technicalInterviewerDTOs = technicalInterviewers.stream()
					.map(technicalInterviewerMapperDTOConververter::technicalInterviewerToDTO)
					.collect(Collectors.toList());

			return ResponseEntity.status(HttpStatus.OK).body(technicalInterviewerDTOs);
		}
	}

	/**
	 * This endpoint uses HTTP method GET to retrieve a technical interviewer object
	 * from the database based on the given ID.
	 *
	 * @param id This parameter is the ID of the technical interviewer object to be
	 *           retrieved from the database.
	 * @return This method returns a TechnicalInterviewerDTO object with the
	 *         matching ID if found in the database, or a 404 response if not found.
	 */
	@GetMapping("/technical_interviewers/{id}")
	@Operation(summary = "Get Technical Interviewer by ID", description = "Get a TechnicalInterviewerDTO object from the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technical Interviewer not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<TechnicalInterviewerDTO> getTechnicalInterviewerById(
			@Parameter(in = ParameterIn.PATH, description = "ID of the Technical Interviewer", required = true) @PathVariable Long id) {
		TechnicalInterviewer technicalInterviewer = technicalInterviewerService.getTechnicalInterviewerById(id)
				.orElse(null);

		if (technicalInterviewer != null) {
			TechnicalInterviewerDTO technicalInterviewerDTO = technicalInterviewerMapperDTOConververter
					.technicalInterviewerToDTO(technicalInterviewer);

			return ResponseEntity.status(HttpStatus.OK).body(technicalInterviewerDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * This endpoint uses HTTP method DELETE to delete a technical interviewer
	 * object from the database based on the given ID.
	 *
	 * @param id This parameter is the ID of the technical interviewer object to be
	 *           deleted from the database.
	 * @return This method returns a success response if the deletion is successful,
	 *         or an error message if the deletion fails.
	 */
	@DeleteMapping("/technical_interviewers/{id}")
	@Operation(summary = "Delete Technical Interviewer by ID", description = "Delete a TechnicalInterviewer object from the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Successful operation, no content"),
			@ApiResponse(responseCode = "400", description = "Bad request, Technical Interviewer not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> deleteTechnicalInterviewer(
			@Parameter(in = ParameterIn.PATH, description = "ID of the Technical Interviewer", required = true) @PathVariable Long id) {
		if (technicalInterviewerService.getTechnicalInterviewerById(id).isPresent()) {
			technicalInterviewerService.deleteTechnicalInterviewer(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("No existe ningún technical_interviewer con el ID: " + id);
		}
	}

	/**
	 * This endpoint uses HTTP method POST to save a technical interviewer object to
	 * the database.
	 *
	 * @param technicalInterviewer This parameter is a technical interviewer object
	 *                             to be saved to the database.
	 * @return This method returns a success response if the creation is successful,
	 *         or an error message if the creation fails.
	 */
	@PostMapping("/technical_interviewers")
	@Operation(summary = "Save Technical Interviewer", description = "Save a TechnicalInterviewer object to the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Successful operation, created"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid Technical Interviewer"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> saveTechnicalInterviewer(
			@Parameter(description = "Technical Interviewer object to be saved", required = true) @RequestBody TechnicalInterviewer technicalInterviewer) {
		String errorMessage = utils.ValidationTechnicalInterviewer.validateTechnicalInterviewer(technicalInterviewer,
				technicalRoleService);
		
		if(technicalInterviewerService.getAllTechnicalInterviewers().contains(technicalInterviewer)) {
			errorMessage = errorMessage + "El nombre de usuario corporativo ya existe. ";
		}

		if (!errorMessage.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}

		technicalInterviewerService.saveTechnicalInterviewer(technicalInterviewer);

		return ResponseEntity.status(HttpStatus.CREATED).body(technicalInterviewer);
	}

	/**
	 * This endpoint uses HTTP method PUT to update a technical interviewer object
	 * in the database based on the given ID.
	 *
	 * @param id                   This parameter is the ID of the technical
	 *                             interviewer object to be updated.
	 * @param technicalInterviewer This parameter is the updated technical
	 *                             interviewer object.
	 * @return This method returns the updated technical interviewer object if
	 *         successful, or an error message if the update fails.
	 */
	@PutMapping("/technical_interviewers/{id}")
	@Operation(summary = "Update Technical Interviewer by ID", description = "Update a TechnicalInterviewer object in the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid Technical Interviewer"),
			@ApiResponse(responseCode = "404", description = "Technical Interviewer not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> updateTechnicalInterviewer(
			@Parameter(in = ParameterIn.PATH, description = "ID of the Technical Interviewer", required = true) @PathVariable Long id,
			@Parameter(description = "Updated Technical Interviewer object", required = true) @RequestBody TechnicalInterviewer technicalInterviewer) {
		String errorMessage = utils.ValidationTechnicalInterviewer.validateTechnicalInterviewer(technicalInterviewer,
				technicalRoleService);

		if(technicalInterviewerService.getAllTechnicalInterviewers().contains(technicalInterviewer)) {
			errorMessage = errorMessage + "El nombre de usuario corporativo ya existe. ";
		}
		
		if (!errorMessage.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}

		TechnicalInterviewer existingTechnicalInterviewer = technicalInterviewerService.getTechnicalInterviewerById(id)
				.orElse(null);
		if (existingTechnicalInterviewer != null) {
			technicalInterviewerService.updateTechnicalInterviewer(id, technicalInterviewer);
			return ResponseEntity.status(HttpStatus.OK).body(technicalInterviewer);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
	}

	/**
	 * This endpoint uses HTTP method GET to retrieve a paginated list of technical
	 * interviewers.
	 *
	 * @param page This parameter is the page number to retrieve (required)
	 * @param size This parameter is the number of elements per page (required)
	 * @return This method returns a technical interviewer list in a page
	 */
	@GetMapping("/technical_interviewersPage")
	@Operation(summary = "Show paginated technical interviewer list", description = "Show paginated technical interviewer list")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technical Interviewers not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getPaginatedTechnicalInterviewers(
			@Parameter(in = ParameterIn.QUERY, name = "page", required = true) @RequestParam int page,
			@Parameter(in = ParameterIn.QUERY, name = "size", required = true) @RequestParam int size) {
		TechnicalInterviewerDTO technicalInterviewerDTO = technicalInterviewerService.listTechnicalInterviewer(page,
				size);

		if (technicalInterviewerDTO.getTechnicalInterviewerList().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(technicalInterviewerDTO.getTechnicalInterviewerList());
		}
	}

}
