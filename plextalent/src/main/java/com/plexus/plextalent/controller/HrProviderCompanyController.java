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

import com.plexus.plextalent.model.HrProviderCompany;
import com.plexus.plextalent.service.impl.HrProviderCompanyServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
@Tag(name = "companies", description = "Endpoints for HR Provider Companies")
public class HrProviderCompanyController {

	private final HrProviderCompanyServiceImpl companyService;

	@PostMapping("/companies")
	@Operation(summary = "Save an HR provider company", description = "Save an HR provider company to the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "HR provider company saved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> saveCompany(@RequestBody HrProviderCompany company) {

		String errorMessage = utils.ValidationCompany.validateCompany(company);

		if (!errorMessage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}

		companyService.saveHrProviderCompany(company);
		return ResponseEntity.status(HttpStatus.CREATED).body(company);
	}

	@GetMapping("/companies")
	@Operation(summary = "Get all HR provider companies", description = "Get a list of all HR provider company objects from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "HR provider companies not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<List<HrProviderCompany>> getAllCompanies() {
		List<HrProviderCompany> companies = companyService.getAllHrProviderCompanies();
		if (companies.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay empresas registradas");
		}
		return ResponseEntity.status(HttpStatus.OK).body(companies);
	}

	@GetMapping("/companies/{id}")
	@Operation(summary = "Get HR provider company by ID", description = "Get an HR provider company object from the database based on the given ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "HR provider company not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<HrProviderCompany> getCompanyById(
			@Parameter(in = ParameterIn.PATH, name = "id", required = true, description = "ID of the HR provider company") @PathVariable Long id) {
		HrProviderCompany company = companyService.getHrProviderCompanyById(id);
		if (company != null) {
			return new ResponseEntity<>(company, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay compañía registrada con el ID: " + id);
		}
	}

	@PutMapping("/companies/{id}")
	@Operation(summary = "Update HR provider company", description = "Update an HR provider company in the database based on the given ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "HR provider company updated successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "HR provider company not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> updateCompany(
			@Parameter(in = ParameterIn.PATH, name = "id", required = true, description = "ID of the HR provider company to be updated") @PathVariable Long id,
			@RequestBody @Parameter(description = "Updated HR provider company information") HrProviderCompany company) {

		HrProviderCompany existCompany = companyService.getHrProviderCompanyById(id);
		if (existCompany == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay compañía registrada con el ID: " + id);
		}

		String errorMessage = utils.ValidationCompany.validateCompany(company);

		if (!errorMessage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}

		companyService.updateHrProviderCompany(id, company);
		return new ResponseEntity<>(company, HttpStatus.OK);
	}

	@DeleteMapping("/companies/{id}")
	@Operation(summary = "Delete HR provider company", description = "Delete an HR provider company from the database based on the given ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "HR provider company deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "HR provider company not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> deleteCompany(
			@Parameter(in = ParameterIn.PATH, name = "id", required = true, description = "ID of the HR provider company to be deleted") @PathVariable Long id) {
		HrProviderCompany res = companyService.getHrProviderCompanyById(id);
		if (res != null) {
			companyService.deleteHrProviderCompany(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"No se ha podido borrar la empresa con el ID: " + id);
		}
	}
}
