package com.plexus.plextalent.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plexus.plextalent.model.HrProviderCompany;
import com.plexus.plextalent.repository.HrProviderCompanyRepository;
import com.plexus.plextalent.service.HrProviderCompanyService;

import lombok.RequiredArgsConstructor;

/**
 * This class implements HrProviderCompanyServiceInterface
 */
@Service
@RequiredArgsConstructor
public class HrProviderCompanyServiceImpl implements HrProviderCompanyService {
	private final HrProviderCompanyRepository companyRepository;

	/**
	 * This method creates a new HR provider company record in the database
	 *
	 * @param company This param is an object of HR provider company
	 */
	public void saveHrProviderCompany(HrProviderCompany company) {
		companyRepository.save(company);
	}

	/**
	 * This method returns a list of all HR provider company records in the database
	 *
	 * @return method companyRepository.findAll()
	 */
	public List<HrProviderCompany> getAllHrProviderCompanies() {
		return companyRepository.findAll();
	}

	/**
	 * This method returns an HR provider company record by looking for it by id
	 *
	 * @param id This param is the id of the HR provider company record in the
	 *           database
	 * @return method companyRepository.findById(id).orElse(null)
	 */
	public HrProviderCompany getHrProviderCompanyById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}

	/**
	 * This method updates an HR provider company record by its id
	 *
	 * @param id      This param is the id of the HR provider company record in the
	 *                database
	 * @param company This param is the updated object of HR provider company
	 */
	public void updateHrProviderCompany(Long id, HrProviderCompany company) {
		if (companyRepository.existsById(id)) {
			company.setId(id);
			companyRepository.save(company);
		}
	}

	/**
	 * This method deletes an HR provider company record by its id
	 *
	 * @param id This param is the id of the HR provider company record in the
	 *           database
	 */
	public void deleteHrProviderCompany(Long id) {
		if (companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
		}
	}
}
