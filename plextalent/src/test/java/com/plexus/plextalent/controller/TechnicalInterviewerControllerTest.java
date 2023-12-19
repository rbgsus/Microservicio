package com.plexus.plextalent.controller;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.plexus.plextalent.dto.TechnicalInterviewerDTO;
import com.plexus.plextalent.mapper.TechnicalInterviewerMapper;
import com.plexus.plextalent.model.TechnicalInterviewer;
import com.plexus.plextalent.model.TechnicalRole;
import com.plexus.plextalent.model.Technology;
import com.plexus.plextalent.service.impl.TechnicalInterviewerServiceImpl;
import com.plexus.plextalent.service.impl.TechnicalRoleServiceImpl;

class TechnicalInterviewerControllerTest {

	@InjectMocks
	private TechnicalInterviewerController technicalInterviewerController;

	@Mock
	private TechnicalInterviewerServiceImpl technicalInterviewerService;

	@Mock
	private TechnicalInterviewerMapper technicalInterviewerMapperDTOConverter;

	@Mock
	private TechnicalRoleServiceImpl technicalRoleService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	private TechnicalInterviewer createMockTechnicalInterviewer() {
		return new TechnicalInterviewer(1L, "John", "Doe", "Smith", "john.doe@example.com", "123-456-7890",
				"987-654-3210", "johndoe", createMockTechnology(), createMockTechnicalRole());
	}

	private Technology createMockTechnology() {
		return new Technology(1L, "Java", "Programming language");
	}

	private TechnicalRole createMockTechnicalRole() {
		return new TechnicalRole(1L, "Software Engineer", "Development role");
	}

	@Test
	void getAllTechnicalInterviewers_success() {

		TechnicalInterviewer mockInterviewer = createMockTechnicalInterviewer();
		List<TechnicalInterviewer> interviewers = Arrays.asList(mockInterviewer);
		when(technicalInterviewerService.getAllTechnicalInterviewers()).thenReturn(interviewers);

		TechnicalInterviewerDTO expectedDTO = new TechnicalInterviewerDTO();
		when(technicalInterviewerMapperDTOConverter.technicalInterviewerToDTO(any(TechnicalInterviewer.class)))
				.thenReturn(expectedDTO);

		ResponseEntity<List<TechnicalInterviewerDTO>> response = technicalInterviewerController
				.getAllTechnicalInterviewers();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(Arrays.asList(expectedDTO), response.getBody());

	}

	@Test
    void getAllTechnicalInterviewersShouldReturnNoContentForEmptyList() {
        // Arrange
        when(technicalInterviewerService.getAllTechnicalInterviewers()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<List<TechnicalInterviewerDTO>> response = technicalInterviewerController
                .getAllTechnicalInterviewers();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertFalse(response.hasBody());
    }

	@Test
	void getTechnicalInterviewerByIdShouldReturnInterviewerDTOIfExists() {
		// Arrange
		Long interviewerId = 1L;
		TechnicalInterviewer mockInterviewer = createMockTechnicalInterviewer();
		when(technicalInterviewerService.getTechnicalInterviewerById(interviewerId))
				.thenReturn(Optional.of(mockInterviewer));

		TechnicalInterviewerDTO expectedDTO = new TechnicalInterviewerDTO();
		when(technicalInterviewerMapperDTOConverter.technicalInterviewerToDTO(mockInterviewer)).thenReturn(expectedDTO);

		// Act
		ResponseEntity<TechnicalInterviewerDTO> response = technicalInterviewerController
				.getTechnicalInterviewerById(interviewerId);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedDTO, response.getBody());
	}

	@Test
	void deleteTechnicalInterviewerShouldReturnNoContentForExistingInterviewer() {
		// Arrange
		Long interviewerId = 1L;
		when(technicalInterviewerService.getTechnicalInterviewerById(interviewerId))
				.thenReturn(Optional.of(createMockTechnicalInterviewer()));

		// Act
		ResponseEntity<?> response = technicalInterviewerController.deleteTechnicalInterviewer(interviewerId);

		// Assert
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertNull(response.getBody());
		verify(technicalInterviewerService).deleteTechnicalInterviewer(interviewerId);
	}

	@Test
	void deleteTechnicalInterviewerShouldReturnBadRequestForNonExistingInterviewer() {
		// Arrange
		Long interviewerId = 1L;
		when(technicalInterviewerService.getTechnicalInterviewerById(interviewerId)).thenReturn(Optional.empty());

		// Act
		ResponseEntity<?> response = technicalInterviewerController.deleteTechnicalInterviewer(interviewerId);

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("No existe ningún technical_interviewer con el ID: " + interviewerId, response.getBody());
	}

	@Test
	void testListHrInterviewers() {
		int page = 1;
		int size = 1;
		TechnicalInterviewer technicalInterviewer1 = new TechnicalInterviewer(1L, "John1", "Doe", "Smith",
				"john.doe1@example.com", "123-456-7890", "987-654-3210", "johndoe1", null, null);
		TechnicalInterviewer technicalInterviewer2 = new TechnicalInterviewer(2L, "John2", "Doe", "Smith",
				"john.doe2@example.com", "123-456-7891", "987-654-3211", "johndoe2", null, null);
		TechnicalInterviewer technicalInterviewer3 = new TechnicalInterviewer(3L, "John3", "Doe", "Smith",
				"john.doe3@example.com", "123-456-7892", "987-654-3212", "johndoe3", null, null);
		List<TechnicalInterviewer> simulatedCollection = new ArrayList<>();
		simulatedCollection.add(technicalInterviewer1);
		simulatedCollection.add(technicalInterviewer2);
		simulatedCollection.add(technicalInterviewer3);

		Pageable pageable = PageRequest.of(page, size);
		TechnicalInterviewerDTO expectedTechnicalInterviewerDTO = new TechnicalInterviewerDTO(pageable,
				simulatedCollection);

		Mockito.when(technicalInterviewerService.listTechnicalInterviewer(page, size))
				.thenReturn(expectedTechnicalInterviewerDTO);

		ResponseEntity<?> response = technicalInterviewerController.getPaginatedTechnicalInterviewers(page, size);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedTechnicalInterviewerDTO.getTechnicalInterviewerList(), response.getBody());
	}

}
