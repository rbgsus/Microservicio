package com.plexus.plextalent.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.plexus.plextalent.dto.HrInterviewerDTOPage;
import com.plexus.plextalent.model.HrInterviewer;
import com.plexus.plextalent.model.HrProviderCompany;
import com.plexus.plextalent.repository.HrInterviewerRepository;

@ExtendWith(MockitoExtension.class)
class HrInterviewerServiceTest {

	@Mock
	private HrInterviewerRepository interviewerRepository;

	@InjectMocks
	private HrInterviewerServiceImpl interviewerServiceImpl;

	@Test
	void saveHrInterviewer() {
		HrProviderCompany company = new HrProviderCompany(1L, "Company1", "Description1");
		HrInterviewer interviewer = new HrInterviewer(1L, "Interviewer1", "LastName1", "LastName2",
				"interviewer@example.com", "123456789", "987654321", "Username1", company);
		interviewerServiceImpl.saveHrInterviewer(interviewer);
		verify(interviewerRepository).save(interviewer);
	}

	@Test
	void getAllHrInterviewersWithData() {
		HrProviderCompany company = new HrProviderCompany(1L, "Company1", "Description1");

		List<HrInterviewer> simulatedCollection = new ArrayList<>();
		simulatedCollection.add(new HrInterviewer(1L, "Interviewer1", "LastName1", "LastName2",
				"interviewer1@example.com", "123456789", "987654321", "Username1", company));
		simulatedCollection.add(new HrInterviewer(2L, "Interviewer2", "LastName1", "LastName2",
				"interviewer2@example.com", "123456789", "987654321", "Username2", company));

		List<HrInterviewer> expected = new ArrayList<>(simulatedCollection);

		Mockito.when(interviewerRepository.findAll()).thenReturn(simulatedCollection);

		final List<HrInterviewer> result = interviewerServiceImpl.getAllHrInterviewers();
		assertEquals(expected, result);
	}

	@Test
	void getAllHrInterviewersWithoutData() {
		List<HrInterviewer> simulatedCollection = new ArrayList<>();
		List<HrInterviewer> expectedCollection = new ArrayList<>();

		Mockito.when(interviewerRepository.findAll()).thenReturn(simulatedCollection);

		final List<HrInterviewer> result = interviewerServiceImpl.getAllHrInterviewers();
		assertEquals(expectedCollection, result);
	}

	@Test
	void getHrInterviewerByIdExistenceId() {
		HrProviderCompany company = new HrProviderCompany(1L, "Company1", "Description1");
		HrInterviewer simulatedObject = new HrInterviewer(1L, "Interviewer1", "LastName1", "LastName2",
				"interviewer1@example.com", "123456789", "987654321", "Username1", company);
		HrInterviewer expected = new HrInterviewer(1L, "Interviewer1", "LastName1", "LastName2",
				"interviewer1@example.com", "123456789", "987654321", "Username1", company);

		Mockito.when(interviewerRepository.findById(1L)).thenReturn(Optional.of(simulatedObject));

		final HrInterviewer result = interviewerServiceImpl.getHrInterviewerById(1L);
		assertEquals(expected, result);
	}

	@Test
	void getHrInterviewerByIdNonExistenceId() {
		Mockito.when(interviewerRepository.findById(-1L)).thenReturn(Optional.empty());
		final HrInterviewer result = interviewerServiceImpl.getHrInterviewerById(-1L);
		assertNull(result);
	}

	@Test
	void updateHrInterviewerExistenceId() {
		HrProviderCompany company = new HrProviderCompany(1L, "Company1", "Description1");
//        HrInterviewer existingInterviewer = new HrInterviewer(1L, "Interviewer1", "LastName1", "LastName2",
//                "interviewer1@example.com", "123456789", "987654321", "Username1", company);
		HrInterviewer updatedInterviewer = new HrInterviewer(1L, "UpdatedInterviewer", "UpdatedLastName1",
				"UpdatedLastName2", "updated@example.com", "987654321", "123456789", "UpdatedUsername", company);

		Mockito.when(interviewerRepository.existsById(1L)).thenReturn(true);

		interviewerServiceImpl.updateHrInterviewer(1L, updatedInterviewer);
		verify(interviewerRepository).save(updatedInterviewer);
	}

	@Test
	void updateHrInterviewerNonExistenceId() {
		HrInterviewer updatedInterviewer = new HrInterviewer(1L, "UpdatedInterviewer", "UpdatedLastName1",
				"UpdatedLastName2", "updated@example.com", "987654321", "123456789", "UpdatedUsername", null);

		Mockito.when(interviewerRepository.existsById(1L)).thenReturn(false);

		interviewerServiceImpl.updateHrInterviewer(1L, updatedInterviewer);
		verify(interviewerRepository, Mockito.never()).save(any());
	}

	@Test
	void deleteHrInterviewerExistenceId() {
		Mockito.when(interviewerRepository.existsById(1L)).thenReturn(true);
		interviewerServiceImpl.deleteHrInterviewer(1L);
		verify(interviewerRepository).deleteById(1L);
	}

	@Test
	void deleteHrInterviewerNonExistenceId() {
		Mockito.when(interviewerRepository.existsById(1L)).thenReturn(false);
		interviewerServiceImpl.deleteHrInterviewer(1L);
		verify(interviewerRepository, Mockito.never()).deleteById(any());
	}

}
