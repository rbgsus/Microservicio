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

import com.plexus.plextalent.model.Technology;
import com.plexus.plextalent.service.impl.TechnologyServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * This class is the technology controller, which provides the endpoints for the
 * technology API.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/technologies")
@Tag(name = "technologies", description = "Endpoints for Technologies")
public class TechnologyController {

	private final TechnologyServiceImpl technologyService;

	/**
	 * This endpoint uses HTTP method POST to save a technology object to the
	 * database.
	 *
	 * @param technology This parameter is a technology object to be saved to the
	 *                   database.
	 * @return This method returns a success response if the creation is successful,
	 *         or an error message if the creation fails.
	 */
	@PostMapping("/technologies")
	@Operation(summary = "Save Technology", description = "Save a Technology object to the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Successful operation, created"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid Technology"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> saveTechnology(
			@Parameter(description = "Technology object to be saved", required = true) @RequestBody Technology technology) {
		String errorMessage = utils.ValidationTechnology.validateTechnology(technology.getName());

		if (!errorMessage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}

		technologyService.saveTechnology(technology);
		return ResponseEntity.status(HttpStatus.CREATED).body(technology);
	}

	/**
	 * This endpoint uses HTTP method GET to retrieve a list of all technology
	 * objects from the database.
	 *
	 * @return This method returns a list of all technology objects found in the
	 *         database.
	 */
	@GetMapping("/technologies")
	@Operation(summary = "Get all Technologies", description = "Get a list of all Technology objects from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technologies not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getAllTechnologies() {
		List<Technology> technologies = technologyService.getAllTechnologies();

		if (technologies.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay tecnologías registradas");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(technologies);
		}
	}

	/**
	 * This endpoint uses HTTP method GET to retrieve a technology object from the
	 * database based on the given ID.
	 *
	 * @param id This parameter is the ID of the technology object to be retrieved
	 *           from the database.
	 * @return This method returns a technology object with the matching ID if found
	 *         in the database, otherwise returns null.
	 */
	@GetMapping("/technologies/{id}")
	@Operation(summary = "Get Technology by ID", description = "Get a Technology object from the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technology not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getTechnologyById(
			@Parameter(in = ParameterIn.PATH, description = "ID of the Technology", required = true) @PathVariable Long id) {
		Technology technology = technologyService.getTechnologyById(id).orElse(null);

		if (technology != null) {
			return ResponseEntity.status(HttpStatus.OK).body(technology);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay tecnología registrada con el ID: " + id);
		}
	}

	/**
	 * This endpoint uses HTTP method PUT to update a technology object in the
	 * database based on the given ID.
	 *
	 * @param id         This parameter is the ID of the technology object to be
	 *                   updated.
	 * @param technology This parameter is the updated technology object.
	 * @return This method returns the updated technology object if successful, or
	 *         an error message if the update fails.
	 */
	@PutMapping("/technologies/{id}")
	@Operation(summary = "Update Technology by ID", description = "Update a Technology object in the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid Technology"),
			@ApiResponse(responseCode = "404", description = "Technology not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> updateTechnology(
			@Parameter(in = ParameterIn.PATH, description = "ID of the Technology", required = true) @PathVariable Long id,
			@Parameter(description = "Updated Technology object", required = true) @RequestBody Technology technology) {
		String errorMessage = utils.ValidationTechnology.validateTechnology(technology.getName());

		if (!errorMessage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}

		Technology existingTechnology = technologyService.getTechnologyById(id).orElse(null);
		if (existingTechnology != null) {
			technologyService.updateTechnology(id, technology);
			return ResponseEntity.status(HttpStatus.OK).body(technology);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"No se ha podido actualizar la tecnología con el ID: " + id);
		}
	}

	/**
	 * This endpoint uses HTTP method DELETE to delete a technology object from the
	 * database based on the given ID.
	 *
	 * @param id This parameter is the ID of the technology object to be deleted
	 *           from the database.
	 * @return This method returns a success response if the deletion is successful,
	 *         or an error message if the deletion fails.
	 */
	@DeleteMapping("/technologies/{id}")
	@Operation(summary = "Delete Technology by ID", description = "Delete a Technology object from the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Successful operation, no content"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid Technology"),
			@ApiResponse(responseCode = "404", description = "Technology not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> deleteTechnology(
			@Parameter(in = ParameterIn.PATH, description = "ID of the Technology", required = true) @PathVariable Long id) {
		Technology technology = technologyService.getTechnologyById(id).orElse(null);
		if (technology != null) {
			technologyService.deleteTechnology(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"No se ha podido borrar la tecnología con el ID: " + id);
		}
	}
}
