package com.plexus.plextalent.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plexus.plextalent.model.Rol;
import com.plexus.plextalent.service.impl.RolServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * This class is the rol controller, that provides the end points of the rol api
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.version}")
@Tag(name = "rol", description = "This endpoint manages information about Plex Talent roles.")
public class RolController {
	private final RolServiceImpl rolServiceImpl;

	/**
	 * This endpoint uses HTTP method POST and saves a rol object to the database
	 * 
	 * @param rol This parameter is a rol object to be saved to the database
	 */

	@PostMapping("/rol")
	@Operation(summary = "Add a new rol to the db", description = "Add a new rol to the db by defining information in body")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public void save(@RequestBody Rol rol) {
		rolServiceImpl.save(rol);
	}

	/**
	 * This endpoint uses HTTP method GET and retrieves a list of all rol objects
	 * from the database
	 *
	 * @return This method returns a list of all rol objects found in the database
	 */

	@GetMapping("/rol")
	@Operation(summary = "Show all roles from db", description = "Show all roles from db")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal error") })

	public List<Rol> findAll() {
		return rolServiceImpl.findAll();
	}

	/**
	 * This endpoint uses HTTP method GET and retrieves a rol object from the
	 * database based on the given ID
	 *
	 * @param id This parameter is the ID of the rol object to be retrieved from the
	 *           database
	 * @return This method returns a rol object with the matching ID if found in the
	 *         database, otherwise returns null
	 */

	@GetMapping("/rol/{id}")
	@Operation(summary = "Show a specific rol", description = "Show a specific rol introducing the id in path")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public Rol findById(@Parameter(in = ParameterIn.PATH, name = "id", required = true) @PathVariable Long id) {
		return rolServiceImpl.findById(id).orElse(null);
	}

	/**
	 * This endpoint uses HTTP method DELETE and deletes a rol object from the
	 * database based on the given ID
	 *
	 * @param id This parameter is the ID of the rol object to be deleted from the
	 *           database
	 */

	@DeleteMapping("/rol/{id}")
	@Operation(summary = "Delete a rol from db", description = "Delete a rol from db introducing the id in path")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public void delete(@Parameter(in = ParameterIn.PATH, name = "id", required = true) @PathVariable Long id) {
		rolServiceImpl.deleteById(id);
	}

}
