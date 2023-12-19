package com.plexus.plextalent.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.plexus.plextalent.dto.HrInterviewerDTO;
import com.plexus.plextalent.dto.HrInterviewerDTOPage;
import com.plexus.plextalent.mapper.HrInterviewerMapper;
import com.plexus.plextalent.model.HrInterviewer;
import com.plexus.plextalent.repository.HrInterviewerRepository;
import com.plexus.plextalent.service.HrInterviewerService;

import lombok.RequiredArgsConstructor;

/**
 * This class implements HrInterviewerServiceInterface
 */
@Service
@RequiredArgsConstructor
public class HrInterviewerServiceImpl implements HrInterviewerService {
	private final HrInterviewerRepository interviewerRepository;
	private final HrInterviewerMapper hrInterviewerMapper;

	/**
	 * This method creates a new HR interviewer record in the database
	 *
	 * @param interviewer This param is an object of HR interviewer
	 */
	public void saveHrInterviewer(HrInterviewer interviewer) {
		interviewerRepository.save(interviewer);
	}

	/**
	 * This method returns a list of all HR interviewer records in the database
	 *
	 * @return method interviewerRepository.findAll()
	 */
	public List<HrInterviewer> getAllHrInterviewers() {
		return interviewerRepository.findAll();
	}

	/**
	 * This method returns an HR interviewer record by looking for it by id
	 *
	 * @param id This param is the id of the HR interviewer record in the database
	 * @return method interviewerRepository.findById(id).orElse(null)
	 */
	public HrInterviewer getHrInterviewerById(Long id) {
		return interviewerRepository.findById(id).orElse(null);
	}

	/**
	 * This method updates an HR interviewer record by its id
	 *
	 * @param id          This param is the id of the HR interviewer record in the
	 *                    database
	 * @param interviewer This param is the updated object of HR interviewer
	 */
	public void updateHrInterviewer(Long id, HrInterviewer interviewer) {
		if (interviewerRepository.existsById(id)) {
			interviewer.setId(id);
			interviewerRepository.save(interviewer);
		}
	}

	/**
	 * This method deletes an HR interviewer record by its id
	 *
	 * @param id This param is the id of the HR interviewer record in the database
	 */
	public void deleteHrInterviewer(Long id) {
		if (interviewerRepository.existsById(id)) {
			interviewerRepository.deleteById(id);
		}
	}

	/**
	 * This method returns a page of HR interviewers based on the provided pageable
	 * parameters
	 * 
	 * @param page This parameter is the page number to retrieve
	 * @param size This parameter is the number of elements per page
	 * @return This method returns an HR interviewer list in a page
	 */

	public HrInterviewerDTOPage listInterviewers(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<HrInterviewer> interviewerPage = interviewerRepository.findAll(pageable);
		List<HrInterviewerDTO> interviewerList = interviewerPage.stream().map(hrInterviewerMapper::hrInterviewerToDTO)
				.collect(Collectors.toList());
		HrInterviewerDTOPage hrInterviewerDTOPage = new HrInterviewerDTOPage(pageable, interviewerList);
		return hrInterviewerDTOPage;
	}

    //TODO: METODO HECHO E IMPLEMENTADO CON Y SIN JAVA 8 POR ABURRIMIENTO
	/* Con java 8
	public List<HrInterviewer> getHrInterviewerByLastName1(String lastName1) {
		return getAllHrInterviewers().stream().filter(h -> h.getLastname1().equals(lastName1)).collect(Collectors.toList());
	}
	*/
	
	/* Sin java 8
	public List<HrInterviewer> getAllHrInterviewerByLastName1(String lastName1){
		List<HrInterviewer> res = new ArrayList<>();
		
		for (HrInterviewer hr : getAllHrInterviewers()) {
			if (hr.getLastname1().equals(lastName1)) {
				res.add(hr);
			}
		}
		return res;		
	}
	*/
	
	/* Sin java 8
	public List<HrInterviewer> getAllHrInterviewerByLastName1(String lastName1) {
		List<HrInterviewer> all = new ArrayList<>();
		all.addAll(getAllHrInterviewers());

		List<HrInterviewer> res = new ArrayList<>();

		for (int i = 0; i < all.size(); i++) {
			if (all.get(i).getLastname1().equals(lastName1)) {
				res.add(all.get(i));
			}
		}
		return res;
	}
	*/
}