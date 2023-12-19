package com.plexus.plextalent.service;

import java.util.List;
import java.util.Optional;

import com.plexus.plextalent.model.TechnicalRole;

/**
 * This interface provides methods for managing TechnicalRole entities.
 */
public interface TechnicalRoleService {

    /**
     * Retrieves a list of all available technical roles.
     *
     * @return A list of TechnicalRole objects.
     */
    List<TechnicalRole> getAllTechnicalRoles();

    /**
     * Retrieves a specific technical role by its unique ID.
     *
     * @param id The unique ID of the technical role to retrieve.
     * @return An Optional containing the TechnicalRole if found, or an empty Optional if not.
     */
    Optional<TechnicalRole> getTechnicalRoleById(Long id);

    /**
     * Saves a new TechnicalRole object to the data store.
     *
     * @param technicalRole The TechnicalRole object to be saved.
     */
    void saveTechnicalRole(TechnicalRole technicalRole);

    /**
     * Updates an existing TechnicalRole object with a new version.
     *
     * @param id            The unique ID of the technical role to update.
     * @param technicalRole The updated TechnicalRole object.
     */
    void updateTechnicalRole(Long id, TechnicalRole technicalRole);

    /**
     * Deletes a technical role with the specified ID from the data store.
     *
     * @param id The unique ID of the technical role to delete.
     */
    void deleteTechnicalRole(Long id);
}
