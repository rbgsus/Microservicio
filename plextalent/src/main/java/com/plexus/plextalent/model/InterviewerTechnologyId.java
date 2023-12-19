package com.plexus.plextalent.model;

import java.io.Serializable;

import lombok.Data;

/**
 * This class defines a composite key for the relationship between TechnicalInterviewer and Technology
 */
@Data
public class InterviewerTechnologyId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long technicalInterviewer;
    private Long technology;

}

