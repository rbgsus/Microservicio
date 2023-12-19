package com.plexus.plextalent.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.plexus.plextalent.model.InterviewerTechnology;
import com.plexus.plextalent.repository.InterviewerTechnologyRepository;

public class InterviewerTechnologyServiceImplTest {

	@Mock
	private InterviewerTechnologyRepository interviewerTechnologyRepository;

	@InjectMocks
	private InterviewerTechnologyServiceImpl interviewerTechnologyService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllInterviewerTechnologies() {
		List<InterviewerTechnology> interviewerTechnologyList = new ArrayList<>();

		when(interviewerTechnologyRepository.findAll()).thenReturn(interviewerTechnologyList);

		List<InterviewerTechnology> result = interviewerTechnologyService.getAllInterviewerTechnologies();

		assertEquals(interviewerTechnologyList.size(), result.size());
		verify(interviewerTechnologyRepository, times(1)).findAll();
	}

}
