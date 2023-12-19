package com.plexus.plextalent.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.plexus.plextalent.dto.TechnicalInterviewerDTO;
import com.plexus.plextalent.model.TechnicalInterviewer;
import com.plexus.plextalent.repository.TechnicalInterviewerRepository;

public class TechnicalInterviewerServiceImplTest {

	@Mock
	private TechnicalInterviewerRepository technicalInterviewerRepository;

	@InjectMocks
	private TechnicalInterviewerServiceImpl technicalInterviewerService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllTechnicalInterviewers() {
		List<TechnicalInterviewer> technicalInterviewerList = new ArrayList<>();

		when(technicalInterviewerRepository.findAll()).thenReturn(technicalInterviewerList);

		List<TechnicalInterviewer> result = technicalInterviewerService.getAllTechnicalInterviewers();

		assertEquals(technicalInterviewerList.size(), result.size());
		verify(technicalInterviewerRepository, times(1)).findAll();
	}

	@Test
	void getTechnicalInterviewerById() {
		TechnicalInterviewer technicalInterviewer = new TechnicalInterviewer(1L, "John", "Doe", "Smith",
				"john.doe@example.com", "123-456-7890", "987-654-3210", "johndoe", null, null);
		Optional<TechnicalInterviewer> optionalTechnicalInterviewer = Optional.of(technicalInterviewer);

		when(technicalInterviewerRepository.findById(1L)).thenReturn(optionalTechnicalInterviewer);

		Optional<TechnicalInterviewer> result = technicalInterviewerService.getTechnicalInterviewerById(1L);

		assertTrue(result.isPresent());
		assertEquals(optionalTechnicalInterviewer.get(), result.get());
		verify(technicalInterviewerRepository, times(1)).findById(1L);
	}

	@Test
    void getTechnicalInterviewerById_NotFound() {
        when(technicalInterviewerRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<TechnicalInterviewer> result = technicalInterviewerService.getTechnicalInterviewerById(1L);

        assertFalse(result.isPresent());
        verify(technicalInterviewerRepository, times(1)).findById(1L);
    }

	@Test
	void saveTechnicalInterviewer() {
		TechnicalInterviewer technicalInterviewer = new TechnicalInterviewer(1L, "John", "Doe", "Smith",
				"john.doe@example.com", "123-456-7890", "987-654-3210", "johndoe", null, null);

		technicalInterviewerService.saveTechnicalInterviewer(technicalInterviewer);

		verify(technicalInterviewerRepository, times(1)).save(technicalInterviewer);
	}

	@Test
	void updateTechnicalInterviewer() {
		TechnicalInterviewer updatedTechnicalInterviewer = new TechnicalInterviewer(1L, "Jane", "Doe", "Smith",
				"jane.doe@example.com", "123-456-7890", "987-654-3210", "janedoe", null, null);

		when(technicalInterviewerRepository.existsById(1L)).thenReturn(true);

		technicalInterviewerService.updateTechnicalInterviewer(1L, updatedTechnicalInterviewer);

		verify(technicalInterviewerRepository, times(1)).save(updatedTechnicalInterviewer);
	}

	@Test
	void updateTechnicalInterviewer_NotFound() {
		TechnicalInterviewer updatedTechnicalInterviewer = new TechnicalInterviewer(1L, "Jane", "Doe", "Smith",
				"jane.doe@example.com", "123-456-7890", "987-654-3210", "janedoe", null, null);

		when(technicalInterviewerRepository.existsById(1L)).thenReturn(false);

		technicalInterviewerService.updateTechnicalInterviewer(1L, updatedTechnicalInterviewer);

		verify(technicalInterviewerRepository, times(0)).save(updatedTechnicalInterviewer);
	}

	@Test
    void deleteTechnicalInterviewer() {
        when(technicalInterviewerRepository.existsById(1L)).thenReturn(true);

        technicalInterviewerService.deleteTechnicalInterviewer(1L);

        verify(technicalInterviewerRepository, times(1)).deleteById(1L);
    }

	@Test
    void deleteTechnicalInterviewer_NotFound() {
        when(technicalInterviewerRepository.existsById(1L)).thenReturn(false);

        technicalInterviewerService.deleteTechnicalInterviewer(1L);

        verify(technicalInterviewerRepository, times(0)).deleteById(1L);
    }

	@Test
	void testListTEchnicalInterviewers() {
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

		Page<TechnicalInterviewer> technicalInterviewerPage = new PageImpl<>(simulatedCollection);
		Mockito.when(technicalInterviewerRepository.findAll(Mockito.any(Pageable.class)))
				.thenReturn(technicalInterviewerPage);
		TechnicalInterviewerDTO technicalInterviewerDTO = technicalInterviewerService.listTechnicalInterviewer(0, 3);

		Mockito.verify(technicalInterviewerRepository, Mockito.times(1)).findAll(Mockito.eq(PageRequest.of(0, 3)));
		assertNotNull(technicalInterviewerDTO);
		assertEquals(simulatedCollection, technicalInterviewerDTO.getTechnicalInterviewerList());
		assertEquals(0, technicalInterviewerDTO.getPageable().getPageNumber());
		assertEquals(3, technicalInterviewerDTO.getPageable().getPageSize());
	}

}
