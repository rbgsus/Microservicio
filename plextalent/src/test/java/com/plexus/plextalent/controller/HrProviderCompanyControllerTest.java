package com.plexus.plextalent.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.plexus.plextalent.model.HrProviderCompany;
import com.plexus.plextalent.service.impl.HrProviderCompanyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HrProviderCompanyControllerTest {

	@Mock
	private HrProviderCompanyServiceImpl hrProviderCompanyServiceImpl;

	@InjectMocks
	private HrProviderCompanyController hrProviderCompanyController;

	@Test
	void saveCompany_Success() {
		HrProviderCompany company = new HrProviderCompany(1L, "Plexus", "example");
		hrProviderCompanyController.saveCompany(company);
		Mockito.verify(hrProviderCompanyServiceImpl).saveHrProviderCompany(company);

		ResponseEntity<?> response = hrProviderCompanyController.saveCompany(company);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void saveCompany_ValidationFailureEmptyName() {
		HrProviderCompany company = new HrProviderCompany(1L, "", "example");
		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> hrProviderCompanyController.saveCompany(company));
		Mockito.verify(hrProviderCompanyServiceImpl, Mockito.never()).saveHrProviderCompany(company);
		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
	}

	@Test
	void getAllCompanies_Success() {
		List<HrProviderCompany> companies = new ArrayList<>();
		companies.add(new HrProviderCompany(1L, "Company1", "Description1"));
		companies.add(new HrProviderCompany(2L, "Company2", "Description2"));
		Mockito.when(hrProviderCompanyServiceImpl.getAllHrProviderCompanies()).thenReturn(companies);
		ResponseEntity<List<HrProviderCompany>> response = hrProviderCompanyController.getAllCompanies();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(companies, response.getBody());
	}

	@Test
	void getAllCompanies_NoCompaniesFound() {
		List<HrProviderCompany> companies = new ArrayList<>();
		Mockito.when(hrProviderCompanyServiceImpl.getAllHrProviderCompanies()).thenReturn(companies);
		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> hrProviderCompanyController.getAllCompanies());
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
	}

	@Test
	void getCompanyById_Success() {
		Long companyId = 1L;
		HrProviderCompany company = new HrProviderCompany(companyId, "Company1", "Description1");
		Mockito.when(hrProviderCompanyServiceImpl.getHrProviderCompanyById(companyId)).thenReturn(company);
		ResponseEntity<HrProviderCompany> response = hrProviderCompanyController.getCompanyById(companyId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(company, response.getBody());
	}

	@Test
	void getCompanyById_CompanyNotFound() {
		Long companyId = 1L;
		Mockito.when(hrProviderCompanyServiceImpl.getHrProviderCompanyById(companyId)).thenReturn(null);
		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> hrProviderCompanyController.getCompanyById(companyId));
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
	}

	@Test
	void updateCompany_Success() {
		Long companyId = 1L;
		HrProviderCompany company = new HrProviderCompany(companyId, "Plexus", "Description1");
		Mockito.when(hrProviderCompanyServiceImpl.getHrProviderCompanyById(companyId)).thenReturn(company);
		hrProviderCompanyController.updateCompany(companyId, company);
		Mockito.verify(hrProviderCompanyServiceImpl).updateHrProviderCompany(companyId, company);
	}

	@Test
	void updateCompany__ValidationFailureEmptyName() {
		Long companyId = 1L;
		HrProviderCompany company = new HrProviderCompany(companyId, "Plexus", "Description1");
		HrProviderCompany company2 = new HrProviderCompany(companyId, "", "Description1");
		Mockito.when(hrProviderCompanyServiceImpl.getHrProviderCompanyById(companyId)).thenReturn(company);
		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> hrProviderCompanyController.updateCompany(companyId, company2));
		Mockito.verify(hrProviderCompanyServiceImpl, Mockito.never()).updateHrProviderCompany(companyId, company);
		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
	}

	@Test
	void updateCompany__ValidationFailureNotFoundCompany() {
		Long companyId = 1L;
		HrProviderCompany company = new HrProviderCompany(companyId, "Plexus", "Description1");
		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> hrProviderCompanyController.updateCompany(companyId, company));
		Mockito.verify(hrProviderCompanyServiceImpl, Mockito.never()).updateHrProviderCompany(companyId, company);
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
	}

	@Test
	void deleteCompany_Success() {
		Long companyId = 1L;
		HrProviderCompany company = new HrProviderCompany(companyId, "Company1", "Description1");
		Mockito.when(hrProviderCompanyServiceImpl.getHrProviderCompanyById(companyId)).thenReturn(company);
		hrProviderCompanyController.deleteCompany(companyId);
		Mockito.verify(hrProviderCompanyServiceImpl).deleteHrProviderCompany(companyId);
	}

	@Test
	void deleteCompany_CompanyNotFound() {
		Long companyId = 1L;
		Mockito.when(hrProviderCompanyServiceImpl.getHrProviderCompanyById(companyId)).thenReturn(null);
		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> hrProviderCompanyController.deleteCompany(companyId));
		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
	}
}
