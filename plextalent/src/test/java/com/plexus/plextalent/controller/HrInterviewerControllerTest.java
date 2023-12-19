package com.plexus.plextalent.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.plexus.plextalent.dto.HrInterviewerDTO;
import com.plexus.plextalent.dto.HrInterviewerDTOPage;
import com.plexus.plextalent.mapper.HrInterviewerMapper;
import com.plexus.plextalent.model.HrInterviewer;
import com.plexus.plextalent.model.HrProviderCompany;
import com.plexus.plextalent.repository.HrProviderCompanyRepository;
import com.plexus.plextalent.service.impl.HrInterviewerServiceImpl;
import com.plexus.plextalent.service.impl.HrProviderCompanyServiceImpl;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class HrInterviewerControllerTest {
	@Mock
	private HrProviderCompanyServiceImpl hrProviderCompanyServiceImpl;

	@InjectMocks
	private HrProviderCompanyController hrProviderCompanyController;

	@Mock
	private HrInterviewerServiceImpl interviewerServiceImpl;

	@Mock
	private HrInterviewerMapper hrInterviewerMapper;

	@Mock
	private HrProviderCompanyRepository hrProviderCompanyRepository;

	@Mock
	private HrInterviewerController interviewerController;

	@BeforeEach
	public void setUp() {
		interviewerController = new HrInterviewerController(interviewerServiceImpl, hrInterviewerMapper);
	}

	/**
	 * Test case for successfully saving valid interviewer.
	 */

	@Test
	public void saveInterviewer_Success() {
		// Crear un objeto HrProviderCompany
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");

		// Crear un objeto HrInterviewer válido
		HrInterviewer validInterviewer = new HrInterviewer(1L, "John", "Doe", "Smith", "john@plexus.com", "1234567890",
				"958882244", "johndoe@plexus.com", company);

		// Llamar al método saveInterviewer y verificar la respuesta
		ResponseEntity<?> response = interviewerController.saveInterviewer(validInterviewer);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(validInterviewer, response.getBody());
		log.info("1.SaveValidInterviewer: " + response.getStatusCode() + "\n");

	}

	/**
	 * Test cases for saving an interviewer with null or empty values in required
	 * fields.
	 */
	@Test
	public void saveInterviewer_ValidationFailure_EmptyName() {
		// Crear un objeto HrProviderCompany
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");

		HrInterviewer invalidInterviewer = new HrInterviewer(2L, "", "Doe1", "Smith1", "john1@plexus.com", "1234567891",
				"958882242", "johndoe1@plexus.com", company);

		// Llamar al método saveInterviewer y verificar que lanza una excepción
		try {
			interviewerController.saveInterviewer(invalidInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("Nombre es un campo obligatorio. ", ex.getReason());
			log.info("2.SaveInvalidInterviewerEmptyName: " + ex.getReason() + "\n");

		}
	}

	@Test
	public void saveInterviewer_ValidationFailure_NullName() {
		// Crear un objeto HrProviderCompany
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");
		HrInterviewer invalidInterviewer = new HrInterviewer(2L, null, "Doe1", "Smith1", "john1@plexus.com",
				"1234567891", "958882242", "johndoe1@plexus.com", company);

		// Llamar al método saveInterviewer y verificar que lanza una excepción
		try {
			interviewerController.saveInterviewer(invalidInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("Nombre es un campo obligatorio. ", ex.getReason());
			log.info("3.SaveInvalidInterviewerNullName: " + ex.getReason() + "\n");

		}
	}

	@Test
	public void saveInterviewer_ValidationFailure_EmptyLastName1() {
		// Crear un objeto HrProviderCompany
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");

		HrInterviewer invalidInterviewer = new HrInterviewer(2L, "Jonh", "", "Smith1", "john1@plexus.com", "1234567891",
				"958882242", "johndoe1@plexus.com", company);

		// Llamar al método saveInterviewer y verificar que lanza una excepción
		try {
			interviewerController.saveInterviewer(invalidInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("Primer apellido es un campo obligatorio. ", ex.getReason());
			log.info("4.SaveInvalidInterviewerEmptyLastName1: " + ex.getReason() + "\n");

		}
	}

	@Test
	public void saveInterviewer_ValidationFailure_NullLastName1() {
		// Crear un objeto HrProviderCompany
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");

		HrInterviewer invalidInterviewer = new HrInterviewer(2L, "Jonh", null, "Smith1", "john1@plexus.com",
				"1234567891", "958882242", "johndoe1@plexus.com", company);

		// Llamar al método saveInterviewer y verificar que lanza una excepción
		try {
			interviewerController.saveInterviewer(invalidInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("Primer apellido es un campo obligatorio. ", ex.getReason());
			log.info("5.SaveInvalidInterviewerNullLastName1: " + ex.getReason() + "\n");

		}
	}

	@Test
	public void saveInterviewer_ValidationFailure_EmptyEmail() {
		// Crear un objeto HrProviderCompany
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");

		HrInterviewer invalidInterviewer = new HrInterviewer(2L, "Jonh", "Doe", "Smith1", "", "1234567891", "958882242",
				"johndoe1@plexus.com", company);

		// Llamar al método saveInterviewer y verificar que lanza una excepción
		try {
			interviewerController.saveInterviewer(invalidInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("El campo email no es válido. ", ex.getReason());
			log.info("6.SaveInvalidInterviewerEmptyEmail: " + ex.getReason() + "\n");
		}
	}

	@Test
	public void saveInterviewer_ValidationFailure_NullEmail() {
		// Crear un objeto HrProviderCompany
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");

		HrInterviewer invalidInterviewer = new HrInterviewer(2L, "Jonh", "Doe", "Smith1", null, "1234567891",
				"958882242", "johndoe1@plexus.com", company);

		// Llamar al método saveInterviewer y verificar que lanza una excepción
		try {
			interviewerController.saveInterviewer(invalidInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("El campo email no es válido. ", ex.getReason());
			log.info("7.SaveInvalidInterviewerNullEmail: " + ex.getReason() + "\n");
		}
	}

	@Test
	public void saveInterviewer_ValidationFailure_EmptyCorporativeUsername() {
		// Crear un objeto HrProviderCompany
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");

		HrInterviewer invalidInterviewer = new HrInterviewer(2L, "Jonh", "Doe", "Smith1", "john1@plexus.com",
				"1234567891", "958882242", "", company);

		// Llamar al método saveInterviewer y verificar que lanza una excepción
		try {
			interviewerController.saveInterviewer(invalidInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("El nombre de usuario corporativo no es válido. ", ex.getReason());
			log.info("8.SaveInvalidInterviewerEmptyCorporativeUserName: " + ex.getReason() + "\n");
		}
	}

	@Test
	public void saveInterviewer_ValidationFailure_NullCorporativeUsername() {
		// Crear un objeto HrProviderCompany
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");

		HrInterviewer invalidInterviewer = new HrInterviewer(2L, "Jonh", "Doe", "Smith1", "john1@plexus.com",
				"1234567891", "958882242", null, company);

		// Llamar al método saveInterviewer y verificar que lanza una excepción
		try {
			interviewerController.saveInterviewer(invalidInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("El nombre de usuario corporativo no es válido. ", ex.getReason());
			log.info("9.SaveInvalidInterviewerNullCorporativeUserName: " + ex.getReason() + "\n");
		}
	}

	@Test
	public void saveInterviewer_ValidationFailure_EmptyCompany() {

		HrProviderCompany company = new HrProviderCompany();
		HrInterviewer invalidInterviewer = new HrInterviewer(2L, "Jonh", "Doe", "Smith1", "john1@plexus.com",
				"1234567891", "958882242", "johndoe1@plexus.com", company);

		// Llamar al método saveInterviewer y verificar que lanza una excepción
		try {
			interviewerController.saveInterviewer(invalidInterviewer);
		} catch (ResponseStatusException ex) {

			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("El nombre de la empresa es obligatorio. ", ex.getReason());
			log.info("10.SaveInvalidInterviewerEmptyCompany: " + ex.getReason() + "\n");
		}
	}

	/**
	 * Test for retrieving all interviewers.
	 */
	@Test
	void getInterviewerAll_success() {
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");
		HrInterviewer hrInterviewer1 = new HrInterviewer(1L, "John", "Doe", "Smith", "john@plexus.com", "1234567890",
				"958882244", "johndoe@plexus.com", company);
		HrInterviewer hrInterviewer2 = new HrInterviewer(2L, "John1", "Doe1", "Smith1", "john1@plexus.com",
				"1234567891", "958882242", "johndoe1@plexus.com", company);

		when(interviewerServiceImpl.getAllHrInterviewers()).thenReturn(Arrays.asList(hrInterviewer1, hrInterviewer2));

		ResponseEntity<?> responseEntity = interviewerController.getAllInterviewers();

		verify(interviewerServiceImpl).getAllHrInterviewers();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		log.info("11.InterviewerAll: " + responseEntity.getStatusCode() + "\n");

	}

	@Test
    void getAllInterviewers_InterviewersNotFound() {

        when(interviewerServiceImpl.getAllHrInterviewers()).thenReturn(Collections.emptyList());
        try {
            interviewerController.getAllInterviewers();
        } catch (ResponseStatusException e) {
            // Assert
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            assertEquals("No hay entrevistadores registrados", e.getReason());
    		log.info("12.InterviewerAllNotFound: " + e.getStatusCode() + "\n");
        }
        verify(interviewerServiceImpl).getAllHrInterviewers();
    }

	/**
	 * Test for interviewer retrieval by ID.
	 */
	@Test
	void getInterviewerById_Success() {
		Long interviewerId = 1L;
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");
		HrInterviewer hrInterviewer = new HrInterviewer(1L, "John", "Doe", "Smith", "john@plexus.com", "1234567890",
				"958882244", "johndoe@plexus.com", company);

		when(interviewerServiceImpl.getHrInterviewerById(interviewerId)).thenReturn(hrInterviewer);
		when(hrInterviewerMapper.hrInterviewerToDTO(hrInterviewer)).thenReturn(new HrInterviewerDTO());

		ResponseEntity<?> responseEntity = interviewerController.getInterviewerById(interviewerId);

		verify(interviewerServiceImpl).getHrInterviewerById(interviewerId);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		log.info("13.InterviewerById: " + responseEntity.getStatusCode() + "\n");

	}

	@Test
	void getInterviewerById_InterviewerNotFound() {
		Long interviewerId = 1L;
		when(interviewerServiceImpl.getHrInterviewerById(interviewerId)).thenReturn(null);

		try {
			interviewerController.getInterviewerById(interviewerId);
		} catch (ResponseStatusException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
			log.info("14.InterviewerByIdNotFound: " + e.getStatusCode() + "\n");
		}

		verify(interviewerServiceImpl).getHrInterviewerById(interviewerId);

	}

	/**
	 * Test the deletion of an interviewer.
	 */
	@Test
	void deleteInterviewer_Success() {
		Long interviewerId = 1L;
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");
		HrInterviewer hrInterviewer = new HrInterviewer(1L, "John", "Doe", "Smith", "john@plexus.com", "1234567890",
				"958882244", "johndoe@plexus.com", company);

		when(interviewerServiceImpl.getHrInterviewerById(interviewerId)).thenReturn(hrInterviewer);

		ResponseEntity<?> responseEntity = interviewerController.deleteInterviewer(interviewerId);

		verify(interviewerServiceImpl).deleteHrInterviewer(interviewerId);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		log.info("15.DeleteIntervier: " + responseEntity.getStatusCode() + "\n");

	}

	@Test
	void deleteInterviewer_InterviewerNotFound() {
		Long interviewerId = 1L;
		when(interviewerServiceImpl.getHrInterviewerById(interviewerId)).thenReturn(null);

		try {

			ResponseEntity<?> responseEntity = interviewerController.deleteInterviewer(interviewerId);

			assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		} catch (ResponseStatusException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
			log.info("16.DeleteIntervierNotFound: " + e.getStatusCode() + "\n");
		}

		verify(interviewerServiceImpl, times(0)).deleteHrInterviewer(interviewerId);

	}

	/**
	 * Test cases for updating an interviewer with null or empty values in required
	 * fields.
	 */
	@Test
	void testUpdateInterviewer_Success() {
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");
		Long interviewerId = 1L;

		HrInterviewer existingInterviewer = new HrInterviewer(interviewerId, "OldName", "OldLastname", "OldUsername",
				"oldemail@plexus.com", "9876543210", "123456789", "oldemail@plexus.com", company);

		HrInterviewer updatedInterviewer = new HrInterviewer(interviewerId, "John", "Doe", "Smith1",
				"johndoe@plexus.com", "1234567890", "958882242", "johndoe@plexus.com", company);

		Mockito.when(interviewerServiceImpl.getHrInterviewerById(interviewerId)).thenReturn(existingInterviewer);

		ResponseEntity<?> response = interviewerController.updateInterviewer(interviewerId, updatedInterviewer);

		Mockito.verify(interviewerServiceImpl).updateHrInterviewer(interviewerId, updatedInterviewer);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		log.info("17. UpdateInterviewer: " + response.getStatusCode() + "\n");
	}

	@Test
	void updateInterviewer_ValidationFailure_EmptyLastName1() {
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");
		Long interviewerId = 1L;

		HrInterviewer existingInterviewer = new HrInterviewer(interviewerId, "OldName", "OldLastname", "OldUsername",
				"oldemail@plexus.com", "9876543210", "123456789", "oldemail@plexus.com", company);

		HrInterviewer updatedInterviewer = new HrInterviewer(interviewerId, "John", "", "Smith1", "johndoe@plexus.com",
				"1234567890", "958882242", "johndoe@plexus.com", company);

		interviewerController.saveInterviewer(existingInterviewer);

		try {
			interviewerController.updateInterviewer(interviewerId, updatedInterviewer);
			Mockito.verify(interviewerServiceImpl).updateHrInterviewer(interviewerId, updatedInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("Primer apellido es un campo obligatorio. ", ex.getReason());
			log.info("18. UpdateInterviewer_InvalidEmptyLastName1: " + ex.getReason() + "\n");
		}
	}

	@Test
	void updateInterviewer_ValidationFailure_EmptyName() {
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");
		Long interviewerId = 1L;

		HrInterviewer existingInterviewer = new HrInterviewer(interviewerId, "OldName", "OldLastname", "OldUsername",
				"oldemail@plexus.com", "9876543210", "123456789", "oldemail@plexus.com", company);

		HrInterviewer updatedInterviewer = new HrInterviewer(interviewerId, "", "Doe", "Smith1", "johndoe@plexus.com",
				"1234567890", "958882242", "johndoe@plexus.com", company);

		interviewerController.saveInterviewer(existingInterviewer);

		try {
			interviewerController.updateInterviewer(interviewerId, updatedInterviewer);
			Mockito.verify(interviewerServiceImpl).updateHrInterviewer(interviewerId, updatedInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("Nombre es un campo obligatorio. ", ex.getReason());
			log.info("19. UpdateInterviewer_InvalidEmptyName: " + ex.getReason() + "\n");
		}
	}

	@Test
	void updateInterviewer_ValidationFailure_EmptyEmail() {
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");
		Long interviewerId = 1L;

		HrInterviewer existingInterviewer = new HrInterviewer(interviewerId, "OldName", "OldLastname", "OldUsername",
				"oldemail@plexus.com", "9876543210", "123456789", "oldemail@plexus.com", company);

		HrInterviewer updatedInterviewer = new HrInterviewer(interviewerId, "", "Doe", "Smith1", "johndoe@plexus.com",
				"1234567890", "958882242", "johndoe@plexus.com", company);

		interviewerController.saveInterviewer(existingInterviewer);

		try {
			interviewerController.updateInterviewer(interviewerId, updatedInterviewer);
			Mockito.verify(interviewerServiceImpl).updateHrInterviewer(interviewerId, updatedInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("Nombre es un campo obligatorio. ", ex.getReason());
			log.info("20. UpdateInterviewer_InvalidEmptyEmail: " + ex.getReason() + "\n");
		}
	}

	@Test
	void updateInterviewer_ValidationFailure_EmptyCorporativeUsername() {
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");
		Long interviewerId = 1L;

		HrInterviewer existingInterviewer = new HrInterviewer(interviewerId, "OldName", "OldLastname", "OldUsername",
				"oldemail@plexus.com", "9876543210", "123456789", "oldemail@plexus.com", company);

		HrInterviewer updatedInterviewer = new HrInterviewer(interviewerId, "Jonh", "Doe", "Smith1",
				"johndoe@plexus.com", "1234567890", "958882242", "", company);

		interviewerController.saveInterviewer(existingInterviewer);

		try {
			interviewerController.updateInterviewer(interviewerId, updatedInterviewer);
			Mockito.verify(interviewerServiceImpl).updateHrInterviewer(interviewerId, updatedInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("El nombre de usuario corporativo no es válido. ", ex.getReason());
			log.info("21. UpdateInterviewer_InvalidCorporativeUsername: " + ex.getReason() + "\n");
		}
	}

	@Test
	void updateInterviewer_ValidationFailure_EmptyCompany() {

		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");
		HrProviderCompany company2 = new HrProviderCompany();
		Long interviewerId = 1L;

		HrInterviewer existingInterviewer = new HrInterviewer(interviewerId, "OldName", "OldLastname", "OldUsername",
				"oldemail@plexus.com", "9876543210", "123456789", "oldemail@plexus.com", company);

		HrInterviewer updatedInterviewer = new HrInterviewer(interviewerId, "Jonh", "Doe", "Smith1",
				"johndoe@plexus.com", "1234567890", "958882242", "johndoe@plexus.com", company2);

		interviewerController.saveInterviewer(existingInterviewer);

		try {
			interviewerController.updateInterviewer(interviewerId, updatedInterviewer);
			Mockito.verify(interviewerServiceImpl).updateHrInterviewer(interviewerId, updatedInterviewer);
		} catch (ResponseStatusException ex) {
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
			assertEquals("El nombre de la empresa es obligatorio. ", ex.getReason());
			log.info("22. UpdateInterviewer_InvalidCompanyName: " + ex.getReason() + "\n");
		}
	}

	@Test
	void testListHrInterviewers() {
	    int page = 1;
	    int size = 1;
	    HrProviderCompany company = new HrProviderCompany(1L, "Company1", "Description1");
	    HrInterviewer interviewer1 = new HrInterviewer(1L, "Interviewer1", "LastName1", "LastName2",
	            "interviewer1@example.com", "123456789", "987654321", "Username1", company);
	    HrInterviewer interviewer2 = new HrInterviewer(2L, "Interviewer2", "LastName1", "LastName2",
	            "interviewer2@example.com", "123456780", "987654320", "Username2", company);
	    HrInterviewer interviewer3 = new HrInterviewer(3L, "Interviewer3", "LastName1", "LastName2",
	            "interviewer3@example.com", "123456783", "987654323", "Username3", company);

	    List<HrInterviewer> simulatedCollection = new ArrayList<>();
	    simulatedCollection.add(interviewer1);
	    simulatedCollection.add(interviewer2);
	    simulatedCollection.add(interviewer3);

	    HrInterviewerDTOPage expectedHrInterviewerDTOPage = new HrInterviewerDTOPage(
	            PageRequest.of(page, size),
	            simulatedCollection.stream().map(hrInterviewerMapper::hrInterviewerToDTO).collect(Collectors.toList())
	    );

	    Mockito.when(interviewerServiceImpl.listInterviewers(page, size)).thenReturn(expectedHrInterviewerDTOPage);

	    ResponseEntity<?> response = interviewerController.getPaginatedInterviewers(page, size);
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(expectedHrInterviewerDTOPage.getInterviewerList(), response.getBody());
	}
}
