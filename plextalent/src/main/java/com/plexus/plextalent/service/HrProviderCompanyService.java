package com.plexus.plextalent.service;

import java.util.List;

import com.plexus.plextalent.model.HrProviderCompany;

/**
 * This interface provides methods for managing HrProviderCompany entities.
 */
public interface HrProviderCompanyService {

    /**
     * Retrieves a list of all available HR provider companies.
     *
     * @return A list of HrProviderCompany objects.
     */
    List<HrProviderCompany> getAllHrProviderCompanies();

    /**
     * Retrieves a specific HR provider company by its unique ID.
     *
     * @param id The unique ID of the HR provider company to retrieve.
     * @return The HrProviderCompany object if found, or null if not found.
     */
    HrProviderCompany getHrProviderCompanyById(Long id);

    /**
     * Saves a new HrProviderCompany object to the data store.
     *
     * @param company The HrProviderCompany object to be saved.
     */
    void saveHrProviderCompany(HrProviderCompany company);

    /**
     * Updates an existing HrProviderCompany object with a new version.
     *
     * @param id      The unique ID of the HR provider company to update.
     * @param company The updated HrProviderCompany object.
     */
    void updateHrProviderCompany(Long id, HrProviderCompany company);

    /**
     * Deletes an HR provider company with the specified ID from the data store.
     *
     * @param id The unique ID of the HR provider company to delete.
     */
    void deleteHrProviderCompany(Long id);
}
