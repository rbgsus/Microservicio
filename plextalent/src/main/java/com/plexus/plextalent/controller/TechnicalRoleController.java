package com.plexus.plextalent.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.plexus.plextalent.model.TechnicalRole;
import com.plexus.plextalent.service.impl.TechnicalRoleServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * This class is the technical role controller, which provides the endpoints for
 * the technical role API.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/technical_roles")
@Tag(name = "technical-roles", description = "Endpoints for Technical Roles")
public class TechnicalRoleController {

	private final TechnicalRoleServiceImpl technicalRoleService;

	/**
	 * This endpoint uses HTTP method POST to save a technical role object to the
	 * database.
	 *
	 * @param technicalRole This parameter is a technical role object to be saved to
	 *                      the database.
	 * @return This method returns a success response if the creation is successful,
	 *         or an error message if the creation fails.
	 */
	@PostMapping("/technical_roles")
	@Operation(summary = "Save Technical Role", description = "Save a TechnicalRole object to the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Successful operation, created"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid Technical Role"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> saveTechnicalRole(
			@Parameter(description = "Technical Role object to be saved", required = true) @RequestBody TechnicalRole technicalRole) {
		String errorMessage = utils.ValidationTechnicalRole.validateRole(technicalRole.getName());

		if (!errorMessage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}

		technicalRoleService.saveTechnicalRole(technicalRole);
		return ResponseEntity.status(HttpStatus.CREATED).body(technicalRole);
	}

	/**
	 * This endpoint uses HTTP method GET to retrieve a list of all technical role
	 * objects from the database.
	 *
	 * @return This method returns a list of all technical role objects found in the
	 *         database.
	 */
	@GetMapping("/technical_roles")
	@Operation(summary = "Get all Technical Roles", description = "Get a list of all TechnicalRole objects from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technical Roles not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<List<TechnicalRole>> getAllTechnicalRoles() {
		List<TechnicalRole> technicalRoles = technicalRoleService.getAllTechnicalRoles();

		if (technicalRoles.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay roles técnicos registrados");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(technicalRoles);
		}
	}

	/**
	 * This endpoint uses HTTP method GET to retrieve a technical role object from
	 * the database based on the given ID.
	 *
	 * @param id This parameter is the ID of the technical role object to be
	 *           retrieved from the database.
	 * @return This method returns a technical role object with the matching ID if
	 *         found in the database, otherwise returns an error message.
	 */
	@GetMapping("/technical_roles/{id}")
	@Operation(summary = "Get Technical Role by ID", description = "Get a TechnicalRole object from the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technical Role not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getTechnicalRoleById(
			@Parameter(in = ParameterIn.PATH, description = "ID of the Technical Role", required = true) @PathVariable Long id) {
		TechnicalRole technicalRole = technicalRoleService.getTechnicalRoleById(id).orElse(null);

		if (technicalRole != null) {
			return ResponseEntity.status(HttpStatus.OK).body(technicalRole);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No hay roles técnicos registrados con el ID: " + id);
		}
	}

	/**
	 * This endpoint uses HTTP method PUT to update a technical role object in the
	 * database based on the given ID.
	 *
	 * @param id            This parameter is the ID of the technical role object to
	 *                      be updated.
	 * @param technicalRole This parameter is the updated technical role object.
	 * @return This method returns the updated technical role object if successful,
	 *         or an error message if the update fails.
	 */
	@PutMapping("/technical_roles/{id}")
	@Operation(summary = "Update Technical Role by ID", description = "Update a TechnicalRole object in the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid Technical Role"),
			@ApiResponse(responseCode = "404", description = "Technical Role not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> updateTechnicalRole(
			@Parameter(in = ParameterIn.PATH, description = "ID of the Technical Role", required = true) @PathVariable Long id,
			@Parameter(description = "Updated Technical Role object", required = true) @RequestBody TechnicalRole technicalRole) {
		String errorMessage = utils.ValidationTechnicalRole.validateRole(technicalRole.getName());

		if (!errorMessage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}

		TechnicalRole existingTechnicalRole = technicalRoleService.getTechnicalRoleById(id).orElse(null);
		if (existingTechnicalRole != null) {
			technicalRoleService.updateTechnicalRole(id, technicalRole);
			return ResponseEntity.status(HttpStatus.OK).body(technicalRole);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No se ha podido actualizar el rol técnico con el ID: " + id);
		}
	}

	/**
	 * This endpoint uses HTTP method DELETE to delete a technical role object from
	 * the database based on the given ID.
	 *
	 * @param id This parameter is the ID of the technical role object to be deleted
	 *           from the database.
	 * @return This method returns a success response if the deletion is successful,
	 *         or an error message if the deletion fails.
	 */
	@DeleteMapping("/technical_roles/{id}")
	@Operation(summary = "Delete Technical Role by ID", description = "Delete a TechnicalRole object from the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Successful operation, no content"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid Technical Role"),
			@ApiResponse(responseCode = "404", description = "Technical Role not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> deleteTechnicalRole(
			@Parameter(in = ParameterIn.PATH, description = "ID of the Technical Role", required = true) @PathVariable Long id) {
		if (technicalRoleService.getTechnicalRoleById(id).isPresent()) {
			technicalRoleService.deleteTechnicalRole(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No hay roles técnicos registrados con el ID: " + id);
		}
	}
}
