package com.plexus.plextalent.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plexus.plextalent.model.InterviewerTechnology;
import com.plexus.plextalent.repository.InterviewerTechnologyRepository;
import com.plexus.plextalent.service.InterviewerTechnologyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterviewerTechnologyServiceImpl implements InterviewerTechnologyService {

	private final InterviewerTechnologyRepository interviewerTechnologyRepository;


	@Override
	public List<InterviewerTechnology> getAllInterviewerTechnologies() {
		return interviewerTechnologyRepository.findAll();
	}

}
