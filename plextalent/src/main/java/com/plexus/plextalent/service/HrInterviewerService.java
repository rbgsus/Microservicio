package com.plexus.plextalent.service;

import java.util.List;

import com.plexus.plextalent.model.HrInterviewer;

/**
 * This interface provides methods for managing HrInterviewer entities.
 */
public interface HrInterviewerService {

    /**
     * Retrieves a list of all available HR interviewers.
     *
     * @return A list of HrInterviewer objects.
     */
    List<HrInterviewer> getAllHrInterviewers();
    
    //TODO: METODO HECHO E IMPLEMENTADO CON Y SIN JAVA 8 POR ABURRIMIENTO
    //List<HrInterviewer> getHrInterviewerByLastName1(String lastName1);

    /**
     * Retrieves a specific HR interviewer by their unique ID.
     *
     * @param id The unique ID of the HR interviewer to retrieve.
     * @return The HrInterviewer object if found, or null if not found.
     */
    HrInterviewer getHrInterviewerById(Long id);

    /**
     * Saves a new HrInterviewer object to the data store.
     *
     * @param interviewer The HrInterviewer object to be saved.
     */
    void saveHrInterviewer(HrInterviewer interviewer);

    /**
     * Updates an existing HrInterviewer object with a new version.
     *
     * @param id          The unique ID of the HR interviewer to update.
     * @param interviewer The updated HrInterviewer object.
     */
    void updateHrInterviewer(Long id, HrInterviewer interviewer);

    /**
     * Deletes an HR interviewer with the specified ID from the data store.
     *
     * @param id The unique ID of the HR interviewer to delete.
     */
    void deleteHrInterviewer(Long id);
}
