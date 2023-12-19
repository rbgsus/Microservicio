package com.plexus.plextalent.service;

import java.util.List;
import java.util.Optional;

import com.plexus.plextalent.model.Technology;

/**
 * This interface provides methods for managing Technology entities.
 */
public interface TechnologyService {
    
    /**
     * Retrieves a list of all available technologies.
     *
     * @return A list of Technology objects.
     */
    List<Technology> getAllTechnologies();

    /**
     * Retrieves a specific technology by its unique ID.
     *
     * @param id The unique ID of the technology to retrieve.
     * @return An Optional containing the Technology if found, or an empty Optional if not.
     */
    Optional<Technology> getTechnologyById(Long id);

    /**
     * Saves a new Technology object to the data store.
     *
     * @param technology The Technology object to be saved.
     */
    void saveTechnology(Technology technology);

    /**
     * Updates an existing Technology object with a new version.
     *
     * @param id         The unique ID of the technology to update.
     * @param technology The updated Technology object.
     */
    void updateTechnology(Long id, Technology technology);

    /**
     * Deletes a technology with the specified ID from the data store.
     *
     * @param id The unique ID of the technology to delete.
     */
    void deleteTechnology(Long id);
}
