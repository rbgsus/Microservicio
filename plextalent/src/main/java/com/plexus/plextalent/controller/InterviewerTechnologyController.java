package com.plexus.plextalent.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.plexus.plextalent.model.InterviewerTechnology;
import com.plexus.plextalent.service.impl.InterviewerTechnologyServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * This class is the interviewer technology controller, which provides the
 * endpoints for the interviewer technology API.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/interviewer-technologies")
@Tag(name = "interviewer-technologies", description = "Endpoints for Interviewer Technologies")
public class InterviewerTechnologyController {

	private final InterviewerTechnologyServiceImpl interviewerTechnologyService;

	/**
	 * This endpoint uses HTTP method GET to retrieve a list of all interviewer
	 * technology objects from the database.
	 *
	 * @return This method returns a list of InterviewerTechnology objects found in
	 *         the database.
	 */
	@GetMapping("/interviewer-technologies")
	@Operation(summary = "Get all Interviewer Technologies", description = "Get a list of all InterviewerTechnology objects from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Interviewer Technologies not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getAllInterviewerTechnologies() {
		List<InterviewerTechnology> interviewerTechnologies = interviewerTechnologyService
				.getAllInterviewerTechnologies();

		if (interviewerTechnologies.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,
					"No hay tecnologías del entrevistador disponibles.");
		}

		return ResponseEntity.status(HttpStatus.OK).body(interviewerTechnologies);
	}
}
