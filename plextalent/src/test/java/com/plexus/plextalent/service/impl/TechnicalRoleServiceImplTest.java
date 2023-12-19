package com.plexus.plextalent.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.mockito.MockitoAnnotations;

import com.plexus.plextalent.model.TechnicalRole;
import com.plexus.plextalent.repository.TechnicalRoleRepository;

public class TechnicalRoleServiceImplTest {

	@Mock
	private TechnicalRoleRepository technicalRoleRepository;

	@InjectMocks
	private TechnicalRoleServiceImpl technicalRoleService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllTechnicalRoles() {
		List<TechnicalRole> technicalRoleList = new ArrayList<>();
		technicalRoleList.add(new TechnicalRole(1L, "Software Engineer", "Development role"));
		technicalRoleList.add(new TechnicalRole(2L, "Data Scientist", "Data analysis role"));

		when(technicalRoleRepository.findAll()).thenReturn(technicalRoleList);

		List<TechnicalRole> result = technicalRoleService.getAllTechnicalRoles();

		assertEquals(2, result.size());
		verify(technicalRoleRepository, times(1)).findAll();
	}

	@Test
	void getTechnicalRoleById() {
		TechnicalRole technicalRole = new TechnicalRole(1L, "Software Engineer", "Development role");
		Optional<TechnicalRole> optionalTechnicalRole = Optional.of(technicalRole);

		when(technicalRoleRepository.findById(1L)).thenReturn(optionalTechnicalRole);

		Optional<TechnicalRole> result = technicalRoleService.getTechnicalRoleById(1L);

		assertTrue(result.isPresent());
		assertEquals(optionalTechnicalRole.get(), result.get());
		verify(technicalRoleRepository, times(1)).findById(1L);
	}

	@Test
    void getTechnicalRoleById_NotFound() {
        when(technicalRoleRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<TechnicalRole> result = technicalRoleService.getTechnicalRoleById(1L);

        assertFalse(result.isPresent());
        verify(technicalRoleRepository, times(1)).findById(1L);
    }

	@Test
	void saveTechnicalRole() {
		TechnicalRole technicalRole = new TechnicalRole(1L, "Software Engineer", "Development role");

		technicalRoleService.saveTechnicalRole(technicalRole);

		verify(technicalRoleRepository, times(1)).save(technicalRole);
	}

	@Test
	void updateTechnicalRole() {
		TechnicalRole updatedTechnicalRole = new TechnicalRole(1L, "Data Scientist", "Data analysis role");

		when(technicalRoleRepository.existsById(1L)).thenReturn(true);

		technicalRoleService.updateTechnicalRole(1L, updatedTechnicalRole);

		verify(technicalRoleRepository, times(1)).save(updatedTechnicalRole);
	}

	@Test
	void updateTechnicalRole_NotFound() {
		TechnicalRole updatedTechnicalRole = new TechnicalRole(1L, "Data Scientist", "Data analysis role");

		when(technicalRoleRepository.existsById(1L)).thenReturn(false);

		technicalRoleService.updateTechnicalRole(1L, updatedTechnicalRole);

		verify(technicalRoleRepository, times(0)).save(updatedTechnicalRole);
	}

	@Test
    void deleteTechnicalRole() {
        when(technicalRoleRepository.existsById(1L)).thenReturn(true);

        technicalRoleService.deleteTechnicalRole(1L);

        verify(technicalRoleRepository, times(1)).deleteById(1L);
    }

	@Test
    void deleteTechnicalRole_NotFound() {
        when(technicalRoleRepository.existsById(1L)).thenReturn(false);

        technicalRoleService.deleteTechnicalRole(1L);

        verify(technicalRoleRepository, times(0)).deleteById(1L);
    }
}
