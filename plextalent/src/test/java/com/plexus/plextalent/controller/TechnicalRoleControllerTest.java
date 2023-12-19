package com.plexus.plextalent.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.plexus.plextalent.model.TechnicalRole;
import com.plexus.plextalent.service.impl.TechnicalRoleServiceImpl;

@ExtendWith(MockitoExtension.class)
class TechnicalRoleControllerTest {

	@Mock
	private TechnicalRoleServiceImpl technicalRoleService;

	@InjectMocks
	private TechnicalRoleController technicalRoleController;

	@Test
	void saveTechnicalRole_Success() {
		TechnicalRole technicalRole = new TechnicalRole(1L, "Software Engineer", "Development role");
		technicalRoleService.saveTechnicalRole(technicalRole);

		ResponseEntity<?> response = technicalRoleController.saveTechnicalRole(technicalRole);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(technicalRole, response.getBody());
	}

	@Test
	void saveTechnicalRole_ValidationFailure_EmptyName() {
	    TechnicalRole technicalRole = new TechnicalRole(1L, "", "Development role");
	    
	    ResponseStatusException exception = assertThrows(ResponseStatusException.class,
	            () -> technicalRoleController.saveTechnicalRole(technicalRole));
	    
	    assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());	    
	    Mockito.verify(technicalRoleService, Mockito.never()).saveTechnicalRole(technicalRole);
	}


	@Test
	void getAllTechnicalRoles_Success() {
		List<TechnicalRole> technicalRoles = new ArrayList<>();
		technicalRoles.add(new TechnicalRole(1L, "Software Engineer", "Development role"));
		technicalRoles.add(new TechnicalRole(2L, "DevOps Engineer", "Operations role"));
		Mockito.when(technicalRoleService.getAllTechnicalRoles()).thenReturn(technicalRoles);

		ResponseEntity<?> response = technicalRoleController.getAllTechnicalRoles();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(technicalRoles, response.getBody());
	}

	@Test
	void getAllTechnicalRoles_NoTechnicalRolesFound() {
		List<TechnicalRole> technicalRoles = new ArrayList<>();
		Mockito.when(technicalRoleService.getAllTechnicalRoles()).thenReturn(technicalRoles);
		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> technicalRoleController.getAllTechnicalRoles());
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
	}

	@Test
	void getTechnicalRoleById_Success() {
		Long technicalRoleId = 1L;
		TechnicalRole technicalRole = new TechnicalRole(technicalRoleId, "Software Engineer", "Development role");
		Mockito.when(technicalRoleService.getTechnicalRoleById(technicalRoleId)).thenReturn(Optional.of(technicalRole));

		ResponseEntity<?> response = technicalRoleController.getTechnicalRoleById(technicalRoleId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(technicalRole, response.getBody());
	}

	@Test
	void getTechnicalRoleById_TechnicalRoleNotFound() {
		Long technicalRoleId = 1L;
		Mockito.when(technicalRoleService.getTechnicalRoleById(technicalRoleId)).thenReturn(Optional.empty());
		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> technicalRoleController.getTechnicalRoleById(technicalRoleId));
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
	}

	@Test
	void updateTechnicalRole_Success() {
		Long technicalRoleId = 1L;
		TechnicalRole technicalRole = new TechnicalRole(technicalRoleId, "Software Engineer", "Development role");
		Mockito.when(technicalRoleService.getTechnicalRoleById(technicalRoleId)).thenReturn(Optional.of(technicalRole));

		ResponseEntity<?> response = technicalRoleController.updateTechnicalRole(technicalRoleId, technicalRole);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(technicalRole, response.getBody());
	}

	@Test
	void updateTechnicalRole_ValidationFailure_EmptyName() {
		Long technicalRoleId = 1L;
		TechnicalRole technicalRole = new TechnicalRole(technicalRoleId, "", "Development role");
		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> technicalRoleController.updateTechnicalRole(technicalRoleId, technicalRole));
		Mockito.verify(technicalRoleService, Mockito.never()).updateTechnicalRole(technicalRoleId, technicalRole);
		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
		
	}

	@Test
	void updateTechnicalRole_TechnicalRoleNotFound() {
		Long technicalRoleId = 1L;
		TechnicalRole technicalRole = new TechnicalRole(technicalRoleId, "Software Engineer", "Development role");
		Mockito.when(technicalRoleService.getTechnicalRoleById(technicalRoleId)).thenReturn(Optional.empty());
		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> technicalRoleController.updateTechnicalRole(technicalRoleId, technicalRole));
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
	}

	@Test
	void deleteTechnicalRole_Success() {
		Long technicalRoleId = 1L;
		TechnicalRole technicalRole = new TechnicalRole(technicalRoleId, "Software Engineer", "Development role");
		Mockito.when(technicalRoleService.getTechnicalRoleById(technicalRoleId)).thenReturn(Optional.of(technicalRole));

		ResponseEntity<?> response = technicalRoleController.deleteTechnicalRole(technicalRoleId);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void deleteTechnicalRole_TechnicalRoleNotFound() {
		Long technicalRoleId = 1L;
		Mockito.when(technicalRoleService.getTechnicalRoleById(technicalRoleId)).thenReturn(Optional.empty());
		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> technicalRoleController.deleteTechnicalRole(technicalRoleId));
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
	}
}
