package com.plexus.plextalent.service;

import java.util.List;

import com.plexus.plextalent.model.InterviewerTechnology;

/**
 * This interface provides methods for managing InterviewerTechnology entities.
 */
public interface InterviewerTechnologyService {

    /**
     * Retrieves a list of all available interviewer technologies.
     *
     * @return A list of InterviewerTechnology objects.
     */
    List<InterviewerTechnology> getAllInterviewerTechnologies();
}
