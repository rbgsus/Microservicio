package com.plexus.plextalent.service;

import java.util.List;
import java.util.Optional;

import com.plexus.plextalent.model.TechnicalInterviewer;

/**
 * This interface provides methods for managing TechnicalInterviewer entities.
 */
public interface TechnicalInterviewerService {

    /**
     * Retrieves a list of all available technical interviewers.
     *
     * @return A list of TechnicalInterviewer objects.
     */
    List<TechnicalInterviewer> getAllTechnicalInterviewers();

    /**
     * Retrieves a specific technical interviewer by their unique ID.
     *
     * @param id The unique ID of the technical interviewer to retrieve.
     * @return An Optional containing the TechnicalInterviewer if found, or an empty Optional if not.
     */
    Optional<TechnicalInterviewer> getTechnicalInterviewerById(Long id);

    /**
     * Saves a new TechnicalInterviewer object to the data store.
     *
     * @param technicalInterviewer The TechnicalInterviewer object to be saved.
     */
    void saveTechnicalInterviewer(TechnicalInterviewer technicalInterviewer);

    /**
     * Updates an existing TechnicalInterviewer object with a new version.
     *
     * @param id                  The unique ID of the technical interviewer to update.
     * @param technicalInterviewer The updated TechnicalInterviewer object.
     */
    void updateTechnicalInterviewer(Long id, TechnicalInterviewer technicalInterviewer);

    /**
     * Deletes a technical interviewer with the specified ID from the data store.
     *
     * @param id The unique ID of the technical interviewer to delete.
     */
    void deleteTechnicalInterviewer(Long id);
}
