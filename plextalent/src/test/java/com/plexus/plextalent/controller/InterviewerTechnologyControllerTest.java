package com.plexus.plextalent.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.plexus.plextalent.model.InterviewerTechnology;
import com.plexus.plextalent.service.impl.InterviewerTechnologyServiceImpl;

@ExtendWith(MockitoExtension.class)
class InterviewerTechnologyControllerTest {

	@Mock
	private InterviewerTechnologyServiceImpl interviewerTechnologyService;

	@InjectMocks
	private InterviewerTechnologyController interviewerTechnologyController;

	@Test
	void getAllInterviewerTechnologies_Success() {
		List<InterviewerTechnology> interviewerTechnologies = new ArrayList<>();
		interviewerTechnologies.add(new InterviewerTechnology());
		interviewerTechnologies.add(new InterviewerTechnology());

		when(interviewerTechnologyService.getAllInterviewerTechnologies()).thenReturn(interviewerTechnologies);

		ResponseEntity<?> response = interviewerTechnologyController.getAllInterviewerTechnologies();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(interviewerTechnologies, response.getBody());
	}

	@Test
	void getAllInterviewerTechnologies_NoInterviewerTechnologiesFound() {
		List<InterviewerTechnology> interviewerTechnologies = new ArrayList<>();

		when(interviewerTechnologyService.getAllInterviewerTechnologies()).thenReturn(interviewerTechnologies);

		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> interviewerTechnologyController.getAllInterviewerTechnologies());

		assertEquals(HttpStatus.NO_CONTENT, exception.getStatusCode());
	}
}
