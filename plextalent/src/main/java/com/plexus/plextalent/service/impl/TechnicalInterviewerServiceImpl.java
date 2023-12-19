package com.plexus.plextalent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.plexus.plextalent.dto.TechnicalInterviewerDTO;
import com.plexus.plextalent.model.TechnicalInterviewer;
import com.plexus.plextalent.repository.TechnicalInterviewerRepository;
import com.plexus.plextalent.service.TechnicalInterviewerService;

/**
 * This class implements TechnicalInterviewerServiceInterface
 */
@Service
public class TechnicalInterviewerServiceImpl implements TechnicalInterviewerService {

	private final TechnicalInterviewerRepository technicalInterviewerRepository;

	/**
	 * Constructor of TechnicalInterviewerServiceImpl
	 *
	 * @param technicalInterviewerRepository This param is an object of
	 *                                       TechnicalInterviewerRepository
	 */
	public TechnicalInterviewerServiceImpl(TechnicalInterviewerRepository technicalInterviewerRepository) {
		this.technicalInterviewerRepository = technicalInterviewerRepository;
	}

	/**
	 * This method creates a new technical interviewer record in the database
	 *
	 * @param technicalInterviewer This param is an object of technical interviewer
	 */
	@Override
	public void saveTechnicalInterviewer(TechnicalInterviewer technicalInterviewer) {
		technicalInterviewerRepository.save(technicalInterviewer);
	}

	/**
	 * This method returns a list of all technical interviewer records in the
	 * database
	 *
	 * @return method technicalInterviewerRepository.findAll()
	 */
	@Override
	public List<TechnicalInterviewer> getAllTechnicalInterviewers() {
		return technicalInterviewerRepository.findAll();
	}

	/**
	 * This method returns a technical interviewer record by looking for it by id
	 *
	 * @param id This param is the id of the technical interviewer record in the
	 *           database
	 * @return method technicalInterviewerRepository.findById()
	 */
	@Override
	public Optional<TechnicalInterviewer> getTechnicalInterviewerById(Long id) {
		return technicalInterviewerRepository.findById(id);
	}

	/**
	 * This method updates a technical interviewer record by its id
	 *
	 * @param id                   This param is the id of the technical interviewer
	 *                             record in the database
	 * @param technicalInterviewer This param is the updated object of technical
	 *                             interviewer
	 */
	@Override
	public void updateTechnicalInterviewer(Long id, TechnicalInterviewer technicalInterviewer) {
		if (technicalInterviewerRepository.existsById(id)) {
			technicalInterviewer.setId(id);
			technicalInterviewerRepository.save(technicalInterviewer);
		}
	}

	/**
	 * This method deletes a technical interviewer record by its id
	 *
	 * @param id This param is the id of the technical interviewer record in the
	 *           database
	 */
	@Override
	public void deleteTechnicalInterviewer(Long id) {
		if (technicalInterviewerRepository.existsById(id)) {
			technicalInterviewerRepository.deleteById(id);
		}
	}

	/**
	 * 
	 * This method returns a page of technical interviewers based on the provided
	 * pageable parameters
	 * 
	 * @param page This parameter is the page number to retrieve
	 * 
	 * @param size This parameter is the number of elements per page
	 * 
	 * @return This method returns a technical interviewer list in a page *
	 * 
	 */

	public TechnicalInterviewerDTO listTechnicalInterviewer(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<TechnicalInterviewer> technicalInterviewerPage = technicalInterviewerRepository.findAll(pageable);
		List<TechnicalInterviewer> technicalInterviewerList = technicalInterviewerPage.getContent();
		TechnicalInterviewerDTO technicalInterviewerDTO = new TechnicalInterviewerDTO(pageable,
				technicalInterviewerList);
		return technicalInterviewerDTO;
	}

}
